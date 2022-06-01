package fr.will33.crashplugin.listener.inventory;

import fr.will33.crashplugin.api.AbstractGUI;
import fr.will33.crashplugin.api.CrashListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick extends CrashListener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        AbstractGUI abstractGUI = this.getPluginInstance().getOpenGUI().get(player.getUniqueId());
        if(abstractGUI != null) {
            InventoryAction inventoryAction = event.getAction();
            if(inventoryAction == InventoryAction.COLLECT_TO_CURSOR || inventoryAction == InventoryAction.MOVE_TO_OTHER_INVENTORY){
                event.setCancelled(true);
            }
            if (inventory != null && abstractGUI.getInventory().equals(inventory) && event.getCurrentItem() != null) {
                event.setCancelled(true);
                String action = abstractGUI.getAction(event.getSlot());
                if (action != null) {
                    abstractGUI.onClick(player, event.getCurrentItem(), event.getClick(), action);
                }
            }
        }
    }

}
