package com.velocitypowered.arcane.armor;

import com.velocitypowered.arcane.collections.Rarity;
import com.velocitypowered.arcane.utils.armorManager;
import com.velocitypowered.arcane.utils.text;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class mageMasterArmor {
    private final List<Component> description = Arrays.asList(
            text.newText("An armor worn by the true mages", Style.style(NamedTextColor.GRAY)),
            text.newText("Feel the accomplishment", Style.style(NamedTextColor.GRAY))
    );
    public ItemStack mageMasterHelmet() {
        double health = 5;
        double intelligence = 7;
        double abilityDamage = 5;
        double defense = 5;
        return armorManager.createArmorPiece(Material.LEATHER_HELMET, "MAGE_MASTER_HELMET", "Mage Master Helmet", health, intelligence, abilityDamage, defense, description, Rarity.EPIC);
    }
    public ItemStack mageMasterChestplate() {
        double health = 5;
        double intelligence = 7;
        double abilityDamage = 5;
        double defense = 5;
        return armorManager.createArmorPiece(Material.LEATHER_CHESTPLATE,"MAGE_MASTER_CHESTPLATE", "Mage Master Chestplate", health, intelligence, abilityDamage, defense, description, Rarity.EPIC);
    }
    public ItemStack mageMasterLeggings() {
        double health = 5;
        double intelligence = 7;
        double abilityDamage = 5;
        double defense = 5;
        return armorManager.createArmorPiece(Material.LEATHER_LEGGINGS,"MAGE_MASTER_LEGGINGS", "Mage Master Leggings", health, intelligence, abilityDamage, defense, description, Rarity.EPIC);
    }
    public ItemStack mageMasterBoots() {
        double health = 5;
        double intelligence = 7;
        double abilityDamage = 5;
        double defense = 5;
        return armorManager.createArmorPiece(Material.LEATHER_BOOTS,"MAGE_APPRENTICE_BOOTS", "Mage Master Boots", health, intelligence, abilityDamage, defense, description, Rarity.EPIC);
    }

    public void helmetCraftRecipe(JavaPlugin plugin) {
        ItemStack helmet = mageMasterHelmet();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_MAGE_MASTER_HELMET");
        ShapedRecipe recipe = new ShapedRecipe(key, helmet);

        recipe.shape("CBC", "CAC", "   ");
        recipe.setIngredient('A', Material.LEATHER_HELMET);
        recipe.setIngredient('B', Material.QUARTZ);
        recipe.setIngredient('C', Material.LAPIS_LAZULI);

        Bukkit.addRecipe(recipe);
    }

    public void chestplateCraftRecipe(JavaPlugin plugin) {
        ItemStack chestplate = mageMasterChestplate();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_MAGE_MASTER_CHESTPLATE");
        ShapedRecipe recipe = new ShapedRecipe(key, chestplate);

        recipe.shape("CAC", "CBC", "CBC");
        recipe.setIngredient('A', Material.LEATHER_CHESTPLATE);
        recipe.setIngredient('B', Material.QUARTZ);
        recipe.setIngredient('C', Material.LAPIS_LAZULI);

        Bukkit.addRecipe(recipe);
    }

    public void leggingsCraftRecipe(JavaPlugin plugin) {
        ItemStack leggings = mageMasterLeggings();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_MAGE_MASTER_LEGGINGS");
        ShapedRecipe recipe = new ShapedRecipe(key, leggings);

        recipe.shape("CBC", "CAC", "C C");
        recipe.setIngredient('A', Material.LEATHER_LEGGINGS);
        recipe.setIngredient('B', Material.QUARTZ);
        recipe.setIngredient('C', Material.LAPIS_LAZULI);

        Bukkit.addRecipe(recipe);
    }

    public void bootsCraftRecipe(JavaPlugin plugin) {
        ItemStack boots = mageMasterBoots();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_MAGE_MASTER_BOOTS");
        ShapedRecipe recipe = new ShapedRecipe(key, boots);

        recipe.shape("   ", "CAC", "B B");
        recipe.setIngredient('A', Material.LEATHER_BOOTS);
        recipe.setIngredient('B', Material.QUARTZ);
        recipe.setIngredient('C', Material.LAPIS_BLOCK);

        Bukkit.addRecipe(recipe);
    }
}
