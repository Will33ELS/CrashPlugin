package fr.will33.crashplugin.gui;

import fr.will33.crashplugin.CrashPlugin;
import fr.will33.crashplugin.api.AbstractGUI;
import fr.will33.crashplugin.task.CrashTask;
import fr.will33.crashplugin.tchat.SelectBetTchatRequest;
import fr.will33.crashplugin.util.CrashAlgorithm;
import fr.will33.crashplugin.util.ItemUtil;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CrashGUI extends AbstractGUI {

    private int bet = 0, seconds = 10, multiplierIndex = 0;
    private double multiplier = 1.0D;
    private double showMultiplier = 0.0D;
    private CrashTask crashTask = null;

    @Override
    public void onOpen(Player player) {
        this.inventory = Bukkit.createInventory(null, 2 * 9, ChatColor.translateAlternateColorCodes('&', this.getPluginInstance().getConfig().getString("crashGUI.title")));
        this.onUpdate(player);
        player.openInventory(this.inventory);
    }

    public void onUpdate(Player player){
        FileConfiguration fileConfiguration = this.getPluginInstance().getConfig();
        this.setSlotData(
                new ItemUtil(
                        Material.valueOf(fileConfiguration.getString("crashGUI.confirmBet.material")),
                        1,
                        ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("crashGUI.confirmBet.displayName").replace("%amount%", String.valueOf(this.bet))),
                        this.transformColor(fileConfiguration.getStringList("crashGUI.confirmBet.lore"))).toItemStack(), 0, "confirm");

        this.setSlotData(
                new ItemUtil(
                        Material.valueOf(fileConfiguration.getString("crashGUI.gameStatus.material")),
                        1,
                        ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("crashGUI.gameStatus.displayName").replace("%seconds%", String.valueOf(this.seconds))),
                        this.transformColor(fileConfiguration.getStringList("crashGUI.gameStatus.lore"))
                ).toItemStack(), 4, "select"
        );

        this.setSlotData(
                new ItemUtil(
                        Material.valueOf(fileConfiguration.getString("crashGUI.firstAddBet.material")),
                        1,
                        ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("crashGUI.firstAddBet.displayName")),
                        this.transformColor(fileConfiguration.getStringList("crashGUI.firstAddBet.lore"))
                ).toItemStack(), 10, "firstAdd"
        );

        this.setSlotData(
                new ItemUtil(
                        Material.valueOf(fileConfiguration.getString("crashGUI.secondsAddBet.material")),
                        1,
                        ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("crashGUI.secondsAddBet.displayName")),
                        this.transformColor(fileConfiguration.getStringList("crashGUI.secondsAddBet.lore"))
                ).toItemStack(), 11, "secondsAdd"
        );

        this.setSlotData(
                new ItemUtil(
                        Material.valueOf(fileConfiguration.getString("crashGUI.selectBet.material")),
                        1,
                        ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("crashGUI.selectBet.displayName")),
                        this.transformColor(fileConfiguration.getStringList("crashGUI.selectBet.lore"))
                ).toItemStack(), 13, "selectBet"
        );

        this.setSlotData(
                new ItemUtil(
                        Material.valueOf(fileConfiguration.getString("crashGUI.firstRemoveBet.material")),
                        1,
                        ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("crashGUI.firstRemoveBet.displayName")),
                        this.transformColor(fileConfiguration.getStringList("crashGUI.firstRemoveBet.lore"))
                ).toItemStack(), 15, "firstRemove"
        );

        this.setSlotData(
                new ItemUtil(
                        Material.valueOf(fileConfiguration.getString("crashGUI.secondsRemoveBet.material")),
                        1,
                        ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("crashGUI.secondsRemoveBet.displayName")),
                        this.transformColor(fileConfiguration.getStringList("crashGUI.secondsRemoveBet.lore"))
                ).toItemStack(), 16, "secondsRemove"
        );
    }

    @Override
    public void onClick(Player player, ItemStack currentItem, ClickType clickType, String action) {
        FileConfiguration fileConfiguration = this.getPluginInstance().getConfig();
        switch (action){
            case "confirm" -> {
                if(this.crashTask != null){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("alreadyConfirmed")));
                } else {
                    if(this.bet == 0){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("noBetSelected")));
                    }else if(!this.confirm(player)){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("notMoney")));
                    }else{
                        this.seconds = 10;
                        this.selectMultiplier();
                        this.crashTask = new CrashTask(player, this);
                        this.crashTask.runTaskTimer(CrashPlugin.getInstance(), 10, 10);
                    }
                }
            }
            case "select" -> {
                if(this.crashTask != null){
                    this.stop(player);
                }
            }
            case "firstAdd" -> {
                int amount = fileConfiguration.getInt("crashGUI.firstAddBet.amount", 0);
                if(this.bet + amount > fileConfiguration.getInt("maxBetSelected")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            fileConfiguration.getString("betMore").replace("%maxBet%", String.valueOf(fileConfiguration.getInt("maxBetSelected")))
                    ));
                } else {
                    this.bet += amount;
                }
            }
            case "secondsAdd" -> {
                int amount = fileConfiguration.getInt("crashGUI.secondsAddBet.amount", 0);
                if(this.bet + amount > fileConfiguration.getInt("maxBetSelected")){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            fileConfiguration.getString("betMore").replace("%maxBet%", String.valueOf(fileConfiguration.getInt("maxBetSelected")))
                    ));
                } else {
                    this.bet += amount;
                }
            }
            case "firstRemove" -> {
                int amount = fileConfiguration.getInt("crashGUI.firstRemoveBet.amount", 0);
                if(this.bet - amount < 0){
                    this.bet = 0;
                } else {
                    this.bet -= amount;
                }
            }
            case "secondsRemove" -> {
                int amount = fileConfiguration.getInt("crashGUI.secondsRemoveBet.amount", 0);
                if(this.bet - amount < 0){
                    this.bet = 0;
                } else {
                    this.bet -= amount;
                }
            }
            case "selectBet" -> {
                player.closeInventory();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString("selectBet").replace("%maxBet%", String.valueOf(fileConfiguration.getInt("maxBetSelected")))));
                CrashPlugin.getInstance().getTchatRequests().put(player.getUniqueId(), new SelectBetTchatRequest(this));
            }
        }
        this.onUpdate(player);
    }

    @Override
    public void onClose(Player player) {
        if(this.crashTask != null){
            this.crashTask.cancel();
            this.crashTask = null;
        }
    }

    /**
     * Take money
     * @param player Instance of the player
     * @return if transaction is success
     */
    private boolean confirm(Player player){
        EconomyResponse economyResponse = this.getPluginInstance().getEconomy().withdrawPlayer(player, this.bet);
        return economyResponse.transactionSuccess();
    }

    /**
     * Transform color code
     * @param lore Instance of the lore
     * @return
     */
    private List<String> transformColor(List<String> lore){
        return lore.stream()
                .map(l -> ChatColor.translateAlternateColorCodes('&', l
                        .replace("%amount%", String.valueOf(this.bet))
                        .replace("%seconds%", String.valueOf(this.seconds))
                        .replace("%multiplier%", String.valueOf(this.showMultiplier))
                        .replace("%winnings%", String.valueOf(this.bet * this.multiplier))
                        )
                ).toList();
    }

    /**
     * Define bet
     * @param bet Amount of the bet
     * @return
     */
    public CrashGUI setBet(int bet) {
        this.bet = bet;
        return this;
    }

    /**
     * Get the number of seconds remaining
     * @return
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Decrement seconds remaining
     */
    public void decrementSeconds() {
        this.seconds --;
    }

    /**
     * Finish crash
     */
    public void onFinish(OfflinePlayer offlinePlayer){
        this.crashTask.cancel();
        int finalAmount = (int) (this.bet * this.multiplier);
        CrashPlugin.getInstance().getEconomy().depositPlayer(offlinePlayer, finalAmount);
        if(offlinePlayer.getPlayer() != null)
            offlinePlayer.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', CrashPlugin.getInstance().getConfig().getString("add").replace("%amount%", String.valueOf(finalAmount))));
        this.bet = 0;
        this.crashTask = null;
    }

    /**
     * Select max multiplier
     */
    public void selectMultiplier(){
        this.multiplier = CrashAlgorithm.random();
    }

    /**
     * Refresh multiplier
     * @param player Instance of the player
     */
    public void refreshMultiplier(Player player){
        this.multiplierIndex ++;
        double v = CrashAlgorithm.value.get(this.multiplierIndex);
        if(v == this.multiplier){
            this.onFinish(player);
        }else{
            this.showMultiplier = v;
        }
    }

    /**
     * Stop multiplier
     * @param player Instance of the player
     */
    public void stop(Player player){
        this.multiplier = this.showMultiplier;
        this.onFinish(player);
    }
}
