package com.velocitypowered.arcane.utils;

import com.velocitypowered.arcane.collections.Rarity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class armorManager {
    public static ItemStack createArmorPiece(Material material, String id, String name, double health, double intelligence, double abilityDamage, double defense, List<Component> description, Rarity rarity) {
        ItemStack armor = new ItemStack(material);
        ItemMeta meta = armor.getItemMeta();

        NamespacedKey idKey = new NamespacedKey("arcane", "id");
        NamespacedKey healthKey = new NamespacedKey("arcane", "health");
        NamespacedKey intelligenceKey = new NamespacedKey("arcane", "intelligence");
        NamespacedKey abilityDamageKey = new NamespacedKey("arcane", "ability_damage");
        NamespacedKey defenseKey = new NamespacedKey("arcane", "defense");

        meta.getPersistentDataContainer().set(idKey, org.bukkit.persistence.PersistentDataType.STRING, id);
        meta.getPersistentDataContainer().set(healthKey, org.bukkit.persistence.PersistentDataType.DOUBLE, health);
        meta.getPersistentDataContainer().set(intelligenceKey, org.bukkit.persistence.PersistentDataType.DOUBLE, intelligence);
        meta.getPersistentDataContainer().set(abilityDamageKey, org.bukkit.persistence.PersistentDataType.DOUBLE, abilityDamage);
        meta.getPersistentDataContainer().set(defenseKey, org.bukkit.persistence.PersistentDataType.DOUBLE, defense);

        meta.displayName(Component.text(name, Style.style(NamedTextColor.BLUE)).decoration(TextDecoration.ITALIC, false));

        List<Component> lore = new ArrayList<>();
        lore.add(text.newText("Health: ", Style.style(NamedTextColor.GRAY)).append(text.newText(Double.toString(health), Style.style(NamedTextColor.RED))));
        lore.add(text.newText("Intelligence: ", Style.style(NamedTextColor.GRAY)).append(text.newText(Double.toString(intelligence), Style.style(NamedTextColor.RED))));
        lore.add(text.newText("Ability Damage: ", Style.style(NamedTextColor.GRAY)).append(text.newText(Double.toString(abilityDamage), Style.style(NamedTextColor.RED))));
        lore.add(text.newText("Defense: ", Style.style(NamedTextColor.GRAY)).append(text.newText(Double.toString(defense), Style.style(NamedTextColor.RED))));
        lore.add(text.newText(""));
        lore.addAll(description);
        lore.add(text.newText(""));
        lore.add(rarity.getRarity());

        meta.lore(lore);
        meta.setUnbreakable(true);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;
        leatherArmorMeta.setColor(org.bukkit.Color.fromRGB(0, 0, 255));
        armor.setItemMeta(leatherArmorMeta);

        return armor;
    }
}
