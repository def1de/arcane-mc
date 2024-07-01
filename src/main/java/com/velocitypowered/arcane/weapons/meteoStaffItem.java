package com.velocitypowered.arcane.weapons;

import com.velocitypowered.arcane.utils.text;
import com.velocitypowered.arcane.playerController;
import com.velocitypowered.arcane.playerStats;
import com.velocitypowered.arcane.utils.damage;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.*;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class meteoStaffItem extends ItemStack{
    text utils = new text();
    playerController playerController = new playerController();

    @SuppressWarnings("FieldCanBeLocal")
    private final float meteoStrikeDamage = 4; // level 4 explosion
    private final int meteoStrikeRange = 10;
    private final int meteoStrikeCooldown = 20;

    private final float fireZapDamage = 10;
    private final int fireZapRange = 15;

    private static final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public meteoStaffItem() {
        super(Material.BLAZE_ROD, 1);

        ItemMeta meta = getItemMeta();
        meta.displayName(utils.newText("Meteo Staff", Style.style(NamedTextColor.GOLD)));

        NamespacedKey idKey = new NamespacedKey("arcane", "id");
        meta.getPersistentDataContainer().set(idKey, PersistentDataType.STRING, "METEO_STAFF");

        NamespacedKey primaryDmgKey = new NamespacedKey("arcane", "primary_dmg");
        meta.getPersistentDataContainer().set(primaryDmgKey, PersistentDataType.FLOAT, fireZapDamage);

        NamespacedKey secondaryDmgKey = new NamespacedKey("arcane", "secondary_dmg");
        meta.getPersistentDataContainer().set(secondaryDmgKey, PersistentDataType.FLOAT, meteoStrikeDamage);

        meta.setCustomModelData(1);
        meta.lore(Arrays.asList(
                utils.newText("Fire Zap ", Style.style(NamedTextColor.GOLD)).append(utils.newText("LEFT CLICK ABILITY:", Style.style(NamedTextColor.GOLD, TextDecoration.BOLD) )),
                utils.newText("Creates a fire beam that damages entities in its path!", Style.style(NamedTextColor.GRAY)),
                utils.newText(" "),
                utils.newText("Meteo Strike ", Style.style(NamedTextColor.GOLD)).append(utils.newText("RIGHT CLICK ABILITY:", Style.style(NamedTextColor.GOLD, TextDecoration.BOLD))),
                utils.newText("Summons a meteor at the targeted location!", Style.style(NamedTextColor.GRAY)),
                utils.newText("Cooldown: ", Style.style(NamedTextColor.GRAY)).append((utils.newText(""+meteoStrikeCooldown, Style.style(NamedTextColor.RED))).append(utils.newText(" seconds", Style.style(NamedTextColor.GRAY)))),
                utils.newText(" "),
                utils.newText("EPIC", Style.style(NamedTextColor.DARK_PURPLE, TextDecoration.BOLD))
        ));
        this.setItemMeta(meta);
    }
    public void fireZap(Player player) {
        playerStats stats = playerController.getPlayerStats(player);
        if (stats == null) return;

        double abilityDamage = stats.getAbilityDamage();


        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
        Vector direction = player.getLocation().getDirection();
        for (int i = 0; i < fireZapRange; i++) {
            Location particleLocation = player.getLocation().add(0, 1.5, 0).add(direction.clone().multiply(i));
            player.getWorld().spawnParticle(Particle.FLAME, particleLocation, 5, 0, 0, 0, 0);

            Collection<Entity> entities = player.getWorld().getNearbyEntities(particleLocation, 0.5, 0.5, 0.5);
            for (Entity entity : entities) {
                damage.magicDamage(player, entity, this);
            }
        }
    }
    public void meteoStrike(Player player) {
        UUID playerID = player.getUniqueId();

        if (cooldowns.containsKey(playerID)) {
            long lastUsedTime = cooldowns.get(playerID);
            if (System.currentTimeMillis() - lastUsedTime < meteoStrikeCooldown * 1000) {
                long secondsLeft = (lastUsedTime + meteoStrikeCooldown * 1000 - System.currentTimeMillis()) / 1000;
                player.sendMessage("§cYou have to wait " + secondsLeft + " seconds before you can use the Meteo Staff again!");
                return;
            }
        }

        Location targetLocation = player.getTargetBlock(null, meteoStrikeRange).getLocation();
        player.getWorld().strikeLightningEffect(targetLocation);
        player.getWorld().createExplosion(targetLocation,4,false ,false, player);
        cooldowns.put(playerID, System.currentTimeMillis());
    }

    public void craftRecipe(JavaPlugin plugin) {
        ItemStack meteoStaff = new meteoStaffItem();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_METEO_STAFF");
        ShapedRecipe recipe = new ShapedRecipe(key, meteoStaff);

        recipe.shape("CBC", "BAB", "CDC");
        recipe.setIngredient('A', Material.BLAZE_ROD);
        recipe.setIngredient('B', Material.MAGMA_CREAM);
        recipe.setIngredient('C', Material.BLAZE_POWDER);
        recipe.setIngredient('D', Material.MAGMA_BLOCK);

        Bukkit.addRecipe(recipe);
    }
}
