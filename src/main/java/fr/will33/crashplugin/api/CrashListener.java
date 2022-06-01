package fr.will33.crashplugin.api;

import fr.will33.crashplugin.CrashPlugin;
import org.bukkit.event.Listener;

public class CrashListener implements Listener {

    /**
     * Get plugin instance
     * @return
     */
    public CrashPlugin getPluginInstance() { return CrashPlugin.getInstance(); }

}
