package fr.will33.crashplugin.task;

import fr.will33.crashplugin.gui.CrashGUI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CrashTask extends BukkitRunnable {

    private final Player player;
    private final CrashGUI crashGUI;
    private boolean decrement = false;
    public CrashTask(Player player, CrashGUI crashGUI){
        this.crashGUI = crashGUI;
        this.player = player;
    }

    @Override
    public void run() {
        if(this.decrement) {
            this.decrement = false;
            if (this.crashGUI.getSeconds() > 0) {
                this.crashGUI.decrementSeconds();
            }
        } else
            this.decrement = true;
        if(this.crashGUI.getSeconds() == 0)
            this.crashGUI.refreshMultiplier(player);
        this.crashGUI.onUpdate(this.player);
    }
}
