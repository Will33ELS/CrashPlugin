package fr.will33.crashplugin.api;

import fr.will33.crashplugin.CrashPlugin;
import org.bukkit.entity.Player;

public interface AbstractTchatRequest {
    void onResponse(Player var1, String var2);

    default CrashPlugin getPluginInstance() { return CrashPlugin.getInstance(); }
}
