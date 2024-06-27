package com.velocitypowered.extrautils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("deprecation")
public class tickListener extends BukkitRunnable {
    private final JavaPlugin plugin;
    playerController playerController = new playerController();

    public tickListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerController.updatePlayerStats(player);
            playerController.regenMana(player);

            String text = "Mana: " + new playerController().getPlayerStats(player).getCurrentMana() + " / " + new playerController().getPlayerStats(player).getMaxMana();
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(text));
        }
    }

    public void start() {
        this.runTaskTimer(plugin, 0L, 20L); // Run every second (20 ticks = 1 second)
    }
}
