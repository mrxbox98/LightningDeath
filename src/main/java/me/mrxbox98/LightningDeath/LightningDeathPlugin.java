package me.mrxbox98.LightningDeath;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class LightningDeathPlugin extends JavaPlugin {

    /**
     * The plugin instance to use in other classes
     */
    public static LightningDeathPlugin instance;

    /**
     * The death event listener
     */
    public static DeathListener listener;

    /**
     * The server version which might be used later
     */
    public static int[] version = new int[3];

    /**
     * Called when the plugin is enabled
     * Checks the servers version and registers
     * the event listener
     */
    @Override
    public void onEnable()
    {
        instance=this;

        version[0]=Integer.parseInt(getServer().getVersion().split("_")[0]);
        version[1]=Integer.parseInt(getServer().getVersion().split("_")[1]);
        version[2]=Integer.parseInt(getServer().getVersion().split("_")[2]);

        listener=new DeathListener();
        getServer().getPluginManager().registerEvents(listener,this);
    }

    /**
     * Unregisters the linstener
     */
    @Override
    public void onDisable()
    {
        HandlerList.unregisterAll(listener);
    }

}
