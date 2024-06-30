package com.velocitypowered.arcane.collections;

import com.velocitypowered.arcane.armor.mageApprenticeArmor;
import com.velocitypowered.arcane.armor.mageMasterArmor;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public enum Armor {
    MAGE_APPRENTICE,
    MAGE_MASTER;

    ItemStack helmet;
    ItemStack chestplate;
    ItemStack leggings;
    ItemStack boots;
    public void initializeActions(JavaPlugin plugin) {
        switch (this) {
            case MAGE_APPRENTICE:
                mageApprenticeArmor mageApprenticeArmor = new mageApprenticeArmor();

                helmet = mageApprenticeArmor.mageApprenticeHelmet();
                chestplate = mageApprenticeArmor.mageApprenticeChestplate();
                leggings = mageApprenticeArmor.mageApprenticeLeggings();
                boots = mageApprenticeArmor.mageApprenticeBoots();

                mageApprenticeArmor.helmetCraftRecipe(plugin);
                mageApprenticeArmor.chestplateCraftRecipe(plugin);
                mageApprenticeArmor.leggingsCraftRecipe(plugin);
                mageApprenticeArmor.bootsCraftRecipe(plugin);
                break;
            case MAGE_MASTER:
                mageMasterArmor mageMasterArmor = new mageMasterArmor();

                helmet = mageMasterArmor.mageMasterHelmet();
                chestplate = mageMasterArmor.mageMasterChestplate();
                leggings = mageMasterArmor.mageMasterLeggings();
                boots = mageMasterArmor.mageMasterBoots();

                mageMasterArmor.helmetCraftRecipe(plugin);
                mageMasterArmor.chestplateCraftRecipe(plugin);
                mageMasterArmor.leggingsCraftRecipe(plugin);
                mageMasterArmor.bootsCraftRecipe(plugin);
                break;
        }
    }

    public void giveItem(Player player) {
        if (player.isOp()) {
            player.getInventory().addItem(helmet);
            player.getInventory().addItem(chestplate);
            player.getInventory().addItem(leggings);
            player.getInventory().addItem(boots);
        }
    }
}
