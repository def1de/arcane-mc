package com.velocitypowered.arcane.collections;

import com.velocitypowered.arcane.weapons.aotvItem;
import com.velocitypowered.arcane.weapons.meteoStaffItem;
import com.velocitypowered.arcane.weapons.bookOfHealsItem;

import java.util.function.Consumer;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public enum Weapons {
    AOTV,
    METEO_STAFF,
    BOOK_OF_HEALS;
    private Consumer<Player> leftClickAction;
    private Consumer<Player> rightClickAction;

    ItemStack item;

    public void initializeActions(JavaPlugin plugin) {
        switch (this) {
            case AOTV:
                this.item = new aotvItem();
                aotvItem aotvItem = new aotvItem();
                this.leftClickAction = (player) -> {};
                this.rightClickAction = aotvItem::teleportPlayer;
                aotvItem.craftRecipe(plugin);
                break;
            case METEO_STAFF:
                this.item = new meteoStaffItem();
                meteoStaffItem meteoStaffItem = new meteoStaffItem();
                this.leftClickAction = meteoStaffItem::fireZap;
                this.rightClickAction = meteoStaffItem::meteoStrike;
                meteoStaffItem.craftRecipe(plugin);
                break;
            case BOOK_OF_HEALS:
                this.item = new bookOfHealsItem();
                bookOfHealsItem bookOfHealsItem = new bookOfHealsItem();
                this.leftClickAction = bookOfHealsItem::lifeDrain;
                this.rightClickAction = bookOfHealsItem::hugeHeal;
                bookOfHealsItem.craftRecipe(plugin);
                break;
        }
    }

    public void executeLeftClickAction(Player player) {
        leftClickAction.accept(player);
    }

    public void executeRightClickAction(Player player) {
        rightClickAction.accept(player);
    }

    public void giveItem(Player player) {
        if (player.isOp()) {
            player.getInventory().addItem(item);
        }
    }
}