package com.velocitypowered.arcane.weapons;

import com.velocitypowered.arcane.Utilities;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings("CallToPrintStackTrace")
public class aotvItem extends ItemStack {
    Utilities utils = new Utilities();

    public aotvItem() {
        super(Material.DIAMOND_SHOVEL, 1);

        ItemMeta meta = getItemMeta();
        meta.displayName(utils.newText("Aspect of the Void", Style.style(NamedTextColor.LIGHT_PURPLE)));

        NamespacedKey idKey = new NamespacedKey("extrautils", "id");
        meta.getPersistentDataContainer().set(idKey, org.bukkit.persistence.PersistentDataType.STRING, "AOTV");

        meta.lore(Collections.singletonList(utils.newText("")));
        meta.lore(Arrays.asList(
                utils.newText("Teleport ", Style.style(NamedTextColor.GOLD)).append(utils.newText("RIGHT CLICK ABILITY:", Style.style(NamedTextColor.GOLD, TextDecoration.BOLD))),
                utils.newText("Teleports you 12 blocks in the direction you are looking", Style.style(NamedTextColor.GRAY)),
                utils.newText("Watch out for fall damage ;)", Style.style(NamedTextColor.GRAY)),
                utils.newText(" "),
                utils.newText("UTILITY", Style.style(NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD))
        ));
        meta.setUnbreakable(true);
        this.setItemMeta(meta);
    }

    public void teleportPlayer(Player player) {
        Location currentLocation = player.getLocation();
        Vector direction = currentLocation.getDirection().normalize();
        Location teleportLocation = currentLocation.add(direction.multiply(12));

        Block previousBlock = player.getLocation().getBlock();
        try {
            for(Block block : player.getLineOfSight(null, 12)) {
                if (block.getType() != Material.AIR) {
                    teleportLocation = previousBlock.getLocation();
                    teleportLocation.setPitch(currentLocation.getPitch());
                    teleportLocation.setYaw(currentLocation.getYaw());
                    player.sendMessage("§cThere are some blocks in the way!");
                    break;
                }
                previousBlock = block;
            }
        } catch (IllegalStateException e) {
            teleportLocation = player.getLocation().add(0, 1, 0);
            e.printStackTrace();
        }

        player.teleport(teleportLocation);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
    }

    public void craftRecipe(JavaPlugin plugin) {
        ItemStack aotv = new aotvItem();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_AOTV");
        ShapedRecipe recipe = new ShapedRecipe(key, aotv);

        recipe.shape(" B ", " A ", " A ");
        recipe.setIngredient('A', Material.DIAMOND);
        recipe.setIngredient('B', Material.ENDER_PEARL);

        Bukkit.addRecipe(recipe);
    }
}
