package fr.will33.crashplugin.tchat;

import fr.will33.crashplugin.api.AbstractTchatRequest;
import fr.will33.crashplugin.gui.CrashGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SelectBetTchatRequest implements AbstractTchatRequest {

    private CrashGUI crashGUI;
    public SelectBetTchatRequest(CrashGUI crashGUI){
        this.crashGUI = crashGUI;
    }

    @Override
    public void onResponse(Player player, String var2) {
        try{
            int amount = Integer.parseInt(var2);
            if(amount < 1 || amount > this.getPluginInstance().getConfig().getInt("maxBetSelected")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getPluginInstance().getConfig().getString("selectBet").replace("%maxBet%", String.valueOf(this.getPluginInstance().getConfig().getInt("maxBetSelected")))));
                this.getPluginInstance().getTchatRequests().put(player.getUniqueId(), new SelectBetTchatRequest(this.crashGUI));
            }else{
                this.getPluginInstance().openGUI(player, crashGUI.setBet(amount));
            }
        }catch (Exception err){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getPluginInstance().getConfig().getString("betIsNotANumber")));
        }
    }
}
