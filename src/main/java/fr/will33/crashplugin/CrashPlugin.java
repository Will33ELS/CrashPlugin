package fr.will33.crashplugin;

import fr.will33.crashplugin.api.AbstractGUI;
import fr.will33.crashplugin.api.AbstractTchatRequest;
import fr.will33.crashplugin.commands.CrashCommand;
import fr.will33.crashplugin.listener.ListenerManagers;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CrashPlugin extends JavaPlugin {

    private Economy economy;
    private final Map<UUID, AbstractGUI> openGUI = new HashMap<>();
    private final Map<UUID, AbstractTchatRequest> tchatRequests = new HashMap<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.setupEconomy();

        this.getCommand("crash").setExecutor(new CrashCommand());

        new ListenerManagers().registerListeners(this);
    }

    /**
     * Setup economy
     */
    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            this.economy = economyProvider.getProvider();
        }
    }



    /**
     * Get economy instance
     * @return
     */
    public Economy getEconomy() {
        return economy;
    }

    /**
     * Get all tchat requests
     * @return
     */
    public Map<UUID, AbstractTchatRequest> getTchatRequests() {
        return tchatRequests;
    }

    /**
     * Get all open GUI
     * @return
     */
    public Map<UUID, AbstractGUI> getOpenGUI() {
        return openGUI;
    }

    /**
     * Open GUI
     * @param player Instance of the player
     * @param abstractGUI Instance of the GUI
     */
    public void openGUI(Player player, AbstractGUI abstractGUI){
        this.openGUI.put(player.getUniqueId(), abstractGUI);
        abstractGUI.onOpen(player);
    }

    /**
     * Get plugin instance
     * @return
     */
    public static CrashPlugin getInstance() { return CrashPlugin.getPlugin(CrashPlugin.class); }
}
