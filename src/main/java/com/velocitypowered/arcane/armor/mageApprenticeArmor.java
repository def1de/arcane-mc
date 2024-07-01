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

public class mageApprenticeArmor {
    private final List<Component> description = Arrays.asList(
            text.newText("An armor worn by the Mage Apprentice", Style.style(NamedTextColor.GRAY)),
            text.newText("A lot yet to come", Style.style(NamedTextColor.GRAY))
    );
    public ItemStack mageApprenticeHelmet() {
        double health = 5;
        double intelligence = 5;
        double abilityDamage = 5;
        double defense = 5;

        return armorManager.createArmorPiece(Material.LEATHER_HELMET, "MAGE_APPRENTICE_HELMET", "Mage Apprentice Helmet", health, intelligence, abilityDamage, defense, description, Rarity.RARE);
    }
    public ItemStack mageApprenticeChestplate() {
        double health = 5;
        double intelligence = 5;
        double abilityDamage = 5;
        double defense = 5;
        return armorManager.createArmorPiece(Material.LEATHER_CHESTPLATE,"MAGE_APPRENTICE_CHESTPLATE", "Mage Apprentice Chestplate", health, intelligence, abilityDamage, defense, description, Rarity.RARE);
    }
    public ItemStack mageApprenticeLeggings() {
        double health = 5;
        double intelligence = 5;
        double abilityDamage = 5;
        double defense = 5;
        return armorManager.createArmorPiece(Material.LEATHER_LEGGINGS,"MAGE_APPRENTICE_LEGGINGS", "Mage Apprentice Leggings", health, intelligence, abilityDamage, defense, description, Rarity.RARE);
    }
    public ItemStack mageApprenticeBoots() {
        double health = 5;
        double intelligence = 5;
        double abilityDamage = 5;
        double defense = 5;
        return armorManager.createArmorPiece(Material.LEATHER_BOOTS,"MAGE_APPRENTICE_BOOTS", "Mage Apprentice Boots", health, intelligence, abilityDamage, defense, description, Rarity.RARE);
    }

    public void helmetCraftRecipe(JavaPlugin plugin) {
        ItemStack helmet = mageApprenticeHelmet();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_MAGE_APPRENTICE_HELMET");
        ShapedRecipe recipe = new ShapedRecipe(key, helmet);

        recipe.shape("CBC", "CAC", "   ");
        recipe.setIngredient('A', Material.LEATHER_HELMET);
        recipe.setIngredient('B', Material.QUARTZ);
        recipe.setIngredient('C', Material.LAPIS_LAZULI);

        Bukkit.addRecipe(recipe);
    }

    public void chestplateCraftRecipe(JavaPlugin plugin) {
        ItemStack chestplate = mageApprenticeChestplate();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_MAGE_APPRENTICE_CHESTPLATE");
        ShapedRecipe recipe = new ShapedRecipe(key, chestplate);

        recipe.shape("CAC", "CBC", "CBC");
        recipe.setIngredient('A', Material.LEATHER_CHESTPLATE);
        recipe.setIngredient('B', Material.QUARTZ);
        recipe.setIngredient('C', Material.LAPIS_LAZULI);

        Bukkit.addRecipe(recipe);
    }

    public void leggingsCraftRecipe(JavaPlugin plugin) {
        ItemStack leggings = mageApprenticeLeggings();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_MAGE_APPRENTICE_LEGGINGS");
        ShapedRecipe recipe = new ShapedRecipe(key, leggings);

        recipe.shape("CBC", "CAC", "C C");
        recipe.setIngredient('A', Material.LEATHER_LEGGINGS);
        recipe.setIngredient('B', Material.QUARTZ);
        recipe.setIngredient('C', Material.LAPIS_LAZULI);

        Bukkit.addRecipe(recipe);
    }

    public void bootsCraftRecipe(JavaPlugin plugin) {
        ItemStack boots = mageApprenticeBoots();
        NamespacedKey key = new NamespacedKey(plugin, "RECIPE_MAGE_APPRENTICE_BOOTS");
        ShapedRecipe recipe = new ShapedRecipe(key, boots);

        recipe.shape("   ", "CAC", "B B");
        recipe.setIngredient('A', Material.LEATHER_BOOTS);
        recipe.setIngredient('B', Material.QUARTZ);
        recipe.setIngredient('C', Material.LAPIS_LAZULI);

        Bukkit.addRecipe(recipe);
    }
}
