package fr.will33.crashplugin.listener.inventory;

import fr.will33.crashplugin.api.AbstractGUI;
import fr.will33.crashplugin.api.CrashListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClose extends CrashListener {

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        AbstractGUI abstractGUI = this.getPluginInstance().getOpenGUI().get(player.getUniqueId());
        if(abstractGUI != null)
            if(inventory != null && abstractGUI.getInventory().equals(inventory)){
                this.getPluginInstance().getOpenGUI().remove(player.getUniqueId());
            }
    }

}
