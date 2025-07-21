package com.velocitypowered.arcane;

import com.velocitypowered.arcane.collections.Weapons;
import com.velocitypowered.arcane.collections.Armor;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("ALL")
public final class Arcane extends JavaPlugin {
    Logger logger = getLogger();
    @Override
    public void onEnable() {

        logger.info("Initializing Weapons...");
        for (Weapons weapon : Weapons.values()) {
            weapon.initializeActions(this);
        }

        logger.info("Initializing Armor...");
        for (Armor armor : Armor.values()) {
            armor.initializeActions(this);
        }

        getCommand("arcane").setExecutor(new commandListener());

        getServer().getPluginManager().registerEvents(new playerController(), this);

        new tickListener(this).start();

        logger.info("Arcane has been enabled!");
    }

    @Override
    public void onDisable() {
        logger.info("Arcane has shut down");
    }
}
