package com.velocitypowered.extrautils.armor;

import com.velocitypowered.extrautils.Utilities;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class mageApprenticeArmor {
    Utilities utils = new Utilities();
    public ItemStack mageApprenticeHelmet() {
        double health = 5;
        double intelligence = 5;
        double abilityDamage = 5;
        double defense = 5;
        return createArmorPiece(Material.LEATHER_HELMET, "MAGE_APPRENTICE_HELMET", "Mage Apprentice Helmet", health, intelligence, abilityDamage, defense);
    }
    public ItemStack mageApprenticeChestplate() {
        double health = 5;
        double intelligence = 5;
        double abilityDamage = 5;
        double defense = 5;
        return createArmorPiece(Material.LEATHER_CHESTPLATE,"MAGE_APPRENTICE_CHESTPLATE", "Mage Apprentice Chestplate", health, intelligence, abilityDamage, defense);
    }
    public ItemStack mageApprenticeLeggings() {
        double health = 5;
        double intelligence = 5;
        double abilityDamage = 5;
        double defense = 5;
        return createArmorPiece(Material.LEATHER_LEGGINGS,"MAGE_APPRENTICE_LEGGINGS", "Mage Apprentice Leggings", health, intelligence, abilityDamage, defense);
    }
    public ItemStack mageApprenticeBoots() {
        double health = 5;
        double intelligence = 5;
        double abilityDamage = 5;
        double defense = 5;
        return createArmorPiece(Material.LEATHER_BOOTS,"MAGE_APPRENTICE_BOOTS", "Mage Apprentice Boots", health, intelligence, abilityDamage, defense);
    }

    private ItemStack createArmorPiece(Material material, String id, String name, double intelligence, double health, double abilityDamage, double defense) {
        ItemStack armor = new ItemStack(material);
        ItemMeta meta = armor.getItemMeta();

        NamespacedKey idKey = new NamespacedKey("extrautils", "id");
        NamespacedKey healthKey = new NamespacedKey("extrautils", "health");
        NamespacedKey intelligenceKey = new NamespacedKey("extrautils", "intelligence");
        NamespacedKey abilityDamageKey = new NamespacedKey("extrautils", "ability_damage");
        NamespacedKey defenseKey = new NamespacedKey("extrautils", "defense");

        meta.getPersistentDataContainer().set(idKey, org.bukkit.persistence.PersistentDataType.STRING, id);
        meta.getPersistentDataContainer().set(healthKey, org.bukkit.persistence.PersistentDataType.DOUBLE, health);
        meta.getPersistentDataContainer().set(intelligenceKey, org.bukkit.persistence.PersistentDataType.DOUBLE, intelligence);
        meta.getPersistentDataContainer().set(abilityDamageKey, org.bukkit.persistence.PersistentDataType.DOUBLE, abilityDamage);
        meta.getPersistentDataContainer().set(defenseKey, org.bukkit.persistence.PersistentDataType.DOUBLE, defense);

        meta.displayName(Component.text(name, Style.style(NamedTextColor.BLUE)).decoration(TextDecoration.ITALIC, false));
        meta.lore(Arrays.asList(
                utils.newText("Health: ", Style.style(NamedTextColor.GRAY)).append(utils.newText(Double.toString(health), Style.style(NamedTextColor.RED))),
                utils.newText("Intelligence: ", Style.style(NamedTextColor.GRAY)).append(utils.newText(Double.toString(intelligence), Style.style(NamedTextColor.RED))),
                utils.newText("Ability Damage: ", Style.style(NamedTextColor.GRAY)).append(utils.newText(Double.toString(abilityDamage), Style.style(NamedTextColor.RED))),
                utils.newText("Defense: ", Style.style(NamedTextColor.GRAY)).append(utils.newText(Double.toString(defense), Style.style(NamedTextColor.RED))),
                utils.newText(""),
                utils.newText("An armour obtained by a rookie mage.", Style.style(NamedTextColor.GRAY)),
                utils.newText("A lot yet to come.", Style.style(NamedTextColor.GRAY)),
                utils.newText(""),
                utils.newText("RARE", Style.style(NamedTextColor.BLUE, TextDecoration.BOLD))
        ));
        meta.setUnbreakable(true);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
        leatherArmorMeta.setColor(org.bukkit.Color.fromRGB(0, 102, 255));
        armor.setItemMeta(leatherArmorMeta);

        return armor;
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
    public void giveArmor(Player player) {
        player.getInventory().setHelmet(mageApprenticeHelmet());
        player.getInventory().setChestplate(mageApprenticeChestplate());
        player.getInventory().setLeggings(mageApprenticeLeggings());
        player.getInventory().setBoots(mageApprenticeBoots());
    }
}
