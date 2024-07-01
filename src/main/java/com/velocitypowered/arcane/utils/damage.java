package com.velocitypowered.arcane.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class damage {
    public static void magicDamage(Player player, Entity entity, ItemStack weapon) {
        if (entity instanceof LivingEntity && entity != player) {
            LivingEntity livingEntity = (LivingEntity) entity;

            double armorPoints = Objects.requireNonNull(livingEntity.getAttribute(Attribute.GENERIC_ARMOR)).getValue();

            NamespacedKey primaryDmgKey = new NamespacedKey("arcane", "primary_dmg");
            double baseDamage = weapon.getItemMeta().getPersistentDataContainer().getOrDefault(primaryDmgKey, PersistentDataType.FLOAT, 0.0f);
            double finalDamage = baseDamage * (1 - (armorPoints / 100));

            livingEntity.damage(finalDamage, player);
        }
    }
}
