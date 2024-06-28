package com.velocitypowered.arcane;

import com.velocitypowered.arcane.collections.Weapons;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class playerController implements Listener {

    private final static HashMap<Player, playerStats> playersStats = new HashMap<>();

    public playerStats getPlayerStats(Player player) {
        return playersStats.get(player);
    }
    public void updatePlayerStats(Player player) {
        double maxHealth = 20.0;
        double maxMana = 100;
        double abilityDamage = 0;
        double defense = 0;
        double strength = 0;


        ItemStack[] armor = player.getInventory().getArmorContents();

        for(ItemStack item : armor) {
            if (item != null && item.getItemMeta() != null) {
                maxHealth += item.getItemMeta().getPersistentDataContainer().getOrDefault(new NamespacedKey("extrautils", "health"), PersistentDataType.DOUBLE, 0.0);
                maxMana += item.getItemMeta().getPersistentDataContainer().getOrDefault(new NamespacedKey("extrautils", "intelligence"), PersistentDataType.DOUBLE, 0.0);
                abilityDamage += item.getItemMeta().getPersistentDataContainer().getOrDefault(new NamespacedKey("extrautils", "ability_damage"), PersistentDataType.DOUBLE, 0.0);
                defense += item.getItemMeta().getPersistentDataContainer().getOrDefault(new NamespacedKey("extrautils", "defense"), PersistentDataType.DOUBLE, 0.0);
            }
        }

        playerStats stats = playersStats.get(player);
        if (stats == null) {
            stats = new playerStats(player, maxHealth, maxMana, maxMana, abilityDamage, defense, strength);
        } else {
            stats.setMaxHealth(maxHealth);
            stats.setAbilityDamage(abilityDamage);
            stats.setMaxMana(maxMana);
            stats.setDefense(defense);
            stats.setStrength(strength);
            stats.updateHealth();
        }

        playersStats.put(player, stats);
    }
    public void regenMana(Player player) {
        playerStats stats = getPlayerStats(player);
        if (stats == null) return;

        double currentMana = stats.getCurrentMana();
        double maxMana = stats.getMaxMana();
        double newMana = Math.min(currentMana + 0.02*maxMana, maxMana);
        stats.setCurrentMana(Math.floor(newMana));
    }

    public boolean takeMana(Player player, double amount) {
        playerStats stats = getPlayerStats(player);
        if (stats == null) return false;

        double currentMana = stats.getCurrentMana();
        double newMana = currentMana - amount;
        if (newMana < 0) {
            player.sendMessage("§cYou don't have enough mana to use this ability!");
            player.playSound(player.getLocation(), "minecraft:block.note_block.bass", 1, 1);
            return false;
        }
        stats.setCurrentMana(newMana);
        return true;
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();

        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand.getItemMeta() == null) return;
        PersistentDataContainer itemContainer = itemInHand.getItemMeta().getPersistentDataContainer();

        if(itemContainer.isEmpty()) return;
        NamespacedKey idKey = new NamespacedKey("extrautils", "id");
        if(!itemInHand.hasItemMeta()) return;

        String itemId = itemInHand.getItemMeta().getPersistentDataContainer().get(idKey, PersistentDataType.STRING);
        try {
            Weapons weapon = Weapons.valueOf(itemId);
            if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK){
                weapon.executeLeftClickAction(player);
            }
            if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
                weapon.executeRightClickAction(player);
            }
        }
        catch (IllegalArgumentException e) {
            // Do nothing
        }
    }
}
