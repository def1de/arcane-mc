package com.velocitypowered.extrautils;

import com.velocitypowered.extrautils.collections.Weapons;
import com.velocitypowered.extrautils.collections.Armor;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("ALL")
public final class ExtraUtils extends JavaPlugin {
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

        getCommand("extrautils").setExecutor(new commandListener());

        getServer().getPluginManager().registerEvents(new playerController(), this);

        new tickListener(this).start();

        logger.info("ExtraUtils has been enabled!");
    }

    @Override
    public void onDisable() {
        logger.info("ExtraUtils has shut down");
    }
}
