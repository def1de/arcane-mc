package com.velocitypowered.arcane;

import com.velocitypowered.arcane.collections.Armor;
import com.velocitypowered.arcane.collections.Weapons;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class commandListener implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("arcane")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp()) {
                    player.sendMessage("You do not have permission to use this command!");
                    return false;
                }
                try {
                    switch (args[0]) {
                        case "give":
                            switch (args[1]) {
                                case "weapon":
                                    Weapons.valueOf(args[2]).giveItem(player);
                                    break;
                                case "armor":
                                    Armor.valueOf(args[2]).giveItem(player);
                                    break;
                            }
                            break;
                        case "refresh":
                            break;
                        default:
                            player.sendMessage("Invalid command!");
                            return false;
                    }
                }
                catch (Exception e) {
                    player.sendMessage("Wrong usage! Perhaps you misspell the item ID?");
                    return false;

                }
                return true;
            }
        }
        sender.sendMessage("Wrong label!");
        return false;
    }
}
