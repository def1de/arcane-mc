package com.velocitypowered.extrautils;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

@SuppressWarnings({"unused", "DataFlowIssue"})
public class playerStats {
    Player player;
    private double maxHealth;
    private double currentMana;
    private double maxMana;

    private double abilityDamage;
    private double defense;
    private double strength;

    public playerStats(Player player, double maxHealth, double currentMana, double maxMana, double abilityDamage, double defense, double strength) {
        this.player = player;
        this.maxHealth = maxHealth;
        this.currentMana = currentMana;
        this.maxMana = maxMana;
        this.abilityDamage = abilityDamage;
        this.defense = defense;
        this.strength = strength;
        this.updateHealth();
    }

    public void updateHealth() {
        this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.maxHealth);
    }

    public double getMaxHealth() {
        return maxHealth;

    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(double currentMana) {
        this.currentMana = currentMana;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    public double getAbilityDamage() {
        return abilityDamage;
    }

    public void setAbilityDamage(double abilityDamage) {
        this.abilityDamage = abilityDamage;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

}
