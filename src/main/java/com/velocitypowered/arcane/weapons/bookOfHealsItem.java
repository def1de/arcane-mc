package com.velocitypowered.arcane.weapons;

import com.velocitypowered.arcane.Arcane;
import com.velocitypowered.arcane.utils.text;
import com.velocitypowered.arcane.playerController;
import com.velocitypowered.arcane.playerStats;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.*;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class bookOfHealsItem extends ItemStack{
    text utils = new text();
    playerController playerController = new playerController();

    JavaPlugin plugin = Arcane.getPlugin(Arcane.class);
    @SuppressWarnings("FieldCanBeLocal")
    private final int hugeHealRadius = 10;
    private int radius = 1;
    private int taskId;

    private final int lifeDrainManaCost = 10;
    private final int lifeDrainRange = 8;
    private final int lifeDrainCooldown = 5;

    private final int hugeHealManaCost = 75;
    private final int hugeHealCooldown = 20;

    private static final HashMap<UUID, Long> cooldownsLifeDrain = new HashMap<>();
    private static final HashMap<UUID, Long> cooldownsHugeHeal = new HashMap<>();

    public bookOfHealsItem() {
        super(Material.BOOK, 1);

        ItemMeta meta = getItemMeta();
        meta.displayName(utils.newText("Book of Health", Style.style(NamedTextColor.DARK_PURPLE)));

        NamespacedKey idKey = new NamespacedKey("arcane", "id");
        meta.getPersistentDataContainer().set(idKey, org.bukkit.persistence.PersistentDataType.STRING, "BOOK_OF_HEALS");

        meta.setCustomModelData(1);
        meta.lore(Arrays.asList(
                utils.newText("Life Drain ", Style.style(NamedTextColor.GOLD)).append(utils.newText("LEFT CLICK ABILITY:", Style.style(NamedTextColor.GOLD, TextDecoration.BOLD) )),
                utils.newText("Creates a beam that heals you by the percentage of missing health of the first enemy it hits", Style.style(NamedTextColor.GRAY)),
                utils.newText("Mana cost: ", Style.style(NamedTextColor.GRAY)).append((utils.newText("10", Style.style(NamedTextColor.WHITE))).append(utils.newText(" mana", Style.style(NamedTextColor.GRAY)))),
                utils.newText("Cooldown: ", Style.style(NamedTextColor.GRAY)).append((utils.newText(""+lifeDrainCooldown, Style.style(NamedTextColor.RED)))).append(utils.newText(" seconds", Style.style(NamedTextColor.GRAY))),
                utils.newText(" "),
                utils.newText("Huge Heal ", Style.style(NamedTextColor.GOLD)).append(utils.newText("RIGHT CLICK ABILITY:", Style.style(NamedTextColor.GOLD, TextDecoration.BOLD))),
                utils.newText("Creates a circle that instantly heals every player within its range by 50% of their health.", Style.style(NamedTextColor.GRAY)),
                utils.newText("Mana cost: ", Style.style(NamedTextColor.GRAY)).append((utils.newText("75", Style.style(NamedTextColor.WHITE))).append(utils.newText(" mana", Style.style(NamedTextColor.GRAY)))),
                utils.newText("Cooldown: ", Style.style(NamedTextColor.GRAY)).append((utils.newText(""+hugeHealCooldown, Style.style(NamedTextColor.RED)))).append(utils.newText(" seconds", Style.style(NamedTextColor.GRAY))),
                utils.newText(" "),
                utils.newText("EPIC", Style.style(NamedTextColor.DARK_PURPLE, TextDecoration.BOLD))
        ));
        this.setItemMeta(meta);
    }
    public void lifeDrain(Player player) {
        playerStats stats = playerController.getPlayerStats(player);
        if (stats == null) return;
        UUID playerID = player.getUniqueId();

        if (cooldownsLifeDrain.containsKey(playerID)) {
            long lastUsedTime = cooldownsLifeDrain.get(playerID);
            if (System.currentTimeMillis() - lastUsedTime < lifeDrainCooldown * 1000) {
                long secondsLeft = (lastUsedTime + lifeDrainCooldown * 1000 - System.currentTimeMillis()) / 1000;
                player.sendMessage("§cYou have to wait " + secondsLeft + " seconds before you can use the Life Drain again!");
                return;
            }
        }

        if(!playerController.takeMana(player, lifeDrainManaCost)){return;}

        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1, 1);
        Vector direction = player.getLocation().getDirection();
        particlePath:
        for (int i = 0; i < lifeDrainRange; i++) {
            Location particleLocation = player.getLocation().add(0, 1.5, 0).add(direction.clone().multiply(i));
            player.getWorld().spawnParticle(Particle.SOUL, particleLocation, 5, 0, 0, 0, 0);

            Collection<Entity> entities = player.getWorld().getNearbyEntities(particleLocation, 0.5, 0.5, 0.5);
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity && entity != player) {
                    LivingEntity livingEntity = (LivingEntity) entity;

                    double entityMaxHealth = Objects.requireNonNull(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                    double entityHealth = livingEntity.getHealth();
                    double missingHealth = (entityMaxHealth - entityHealth)/entityMaxHealth;

                    double currentHealth = player.getHealth();
                    double healAmount = (missingHealth)+1;
                    double newHealth = currentHealth * healAmount;
                    double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();

                    if (newHealth > maxHealth) {
                        newHealth = maxHealth;
                    }
                    player.sendMessage("§aYou have been healed by " + (int) (newHealth-currentHealth) + " health!");
                    player.setHealth(newHealth);

                    cooldownsLifeDrain.put(playerID, System.currentTimeMillis());
                    break particlePath;
                }
            }
        }
    }

    public void hugeHeal(Player player) {
        radius = 1;
        World world = player.getWorld();
        Location center = player.getLocation();

        UUID playerID = player.getUniqueId();

        if (cooldownsHugeHeal.containsKey(playerID)) {
            long lastUsedTime = cooldownsHugeHeal.get(playerID);
            if (System.currentTimeMillis() - lastUsedTime < hugeHealCooldown * 1000) {
                long secondsLeft = (lastUsedTime + hugeHealCooldown * 1000 - System.currentTimeMillis()) / 1000;
                player.sendMessage("§cYou have to wait " + secondsLeft + " seconds before you can use the Huge Heal again!");
                return;
            }
        }

        if(!playerController.takeMana(player, hugeHealManaCost)){return;}

        taskId = 0;

        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> makeSphere(player, center, world), 0L, 1L);

        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
        cooldownsHugeHeal.put(playerID, System.currentTimeMillis());
    }

    public void craftRecipe(JavaPlugin plugin) {
        ItemStack meteoStaff = new bookOfHealsItem();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_BOOK_OF_HEALS");
        ShapedRecipe recipe = new ShapedRecipe(key, meteoStaff);

        recipe.shape("BCB", "DAD", "BDB");
        recipe.setIngredient('A', Material.BOOK);
        recipe.setIngredient('B', Material.SOUL_SAND);
        recipe.setIngredient('C', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('D', Material.REDSTONE);

        Bukkit.addRecipe(recipe);
    }

    public void makeSphere(Player player, Location center, World world) {
        for (double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 8) {
            for (double phi = 0; phi <= Math.PI; phi += Math.PI / 8) {
                double x = radius * Math.sin(phi) * Math.cos(theta);
                double y = radius * Math.cos(phi);
                double z = radius * Math.sin(phi) * Math.sin(theta);
                Location particleLocation = center.clone().add(x, y, z);
                world.spawnParticle(Particle.HEART, particleLocation, 2);
            }
        }
        if (radius < hugeHealRadius){
            radius++; // Increment the radius
        } else {
            Bukkit.getScheduler().cancelTask(taskId);
            // healing
            Collection<Entity> entities = player.getWorld().getNearbyEntities(center, hugeHealRadius, hugeHealRadius, hugeHealRadius);
            for (Entity entity : entities) {
                if (entity instanceof Player) {
                    Player target = (Player) entity;
                    double maxHealth = Objects.requireNonNull(target.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                    double currentHealth = target.getHealth();
                    double newHealth = currentHealth + (maxHealth/2);
                    if (newHealth > maxHealth) {
                        newHealth = maxHealth;
                    }
                    target.setHealth(newHealth);
                    entity.sendMessage("§aYou have been healed by " + (int) (newHealth-currentHealth) + " health!");
                    if (target != player) {
                        player.sendMessage("§aYou have healed " + target.getName() + " by " + (int) (newHealth-currentHealth) + " health!");
                    }
                }
            }
        }
    }
}