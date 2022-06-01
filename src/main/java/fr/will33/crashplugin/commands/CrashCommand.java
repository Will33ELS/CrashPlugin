package fr.will33.crashplugin.commands;

import fr.will33.crashplugin.CrashPlugin;
import fr.will33.crashplugin.gui.CrashGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CrashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            if(!player.hasPermission("crash.use")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CrashPlugin.getInstance().getConfig().getString("noPermission")));
            }else {
                CrashPlugin.getInstance().openGUI(player, new CrashGUI());
            }
        }
        return false;
    }
}
