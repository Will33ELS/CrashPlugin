package fr.will33.crashplugin.listener.player;

import fr.will33.crashplugin.api.AbstractTchatRequest;
import fr.will33.crashplugin.api.CrashListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat extends CrashListener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        if(this.getPluginInstance().getTchatRequests().containsKey(player.getUniqueId())){
            AbstractTchatRequest abstractTchatRequest = this.getPluginInstance().getTchatRequests().get(player.getUniqueId());
            abstractTchatRequest.onResponse(player, message);
            this.getPluginInstance().getTchatRequests().remove(player.getUniqueId());
            event.setCancelled(true);
        }
    }

}
