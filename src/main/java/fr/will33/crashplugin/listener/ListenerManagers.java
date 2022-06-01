package fr.will33.crashplugin.listener;

import fr.will33.crashplugin.listener.inventory.InventoryClick;
import fr.will33.crashplugin.listener.inventory.InventoryClose;
import fr.will33.crashplugin.listener.player.PlayerChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManagers {

    public void registerListeners(JavaPlugin javaPlugin){
        PluginManager pluginManager = Bukkit.getPluginManager();

        //INVENTORY
        pluginManager.registerEvents(new InventoryClick(), javaPlugin);
        pluginManager.registerEvents(new InventoryClose(), javaPlugin);

        //PLAYER
        pluginManager.registerEvents(new PlayerChat(), javaPlugin);
    }

}
