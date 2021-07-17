package me.mrxbox98.LightningDeath;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class LightningDeathPlugin extends JavaPlugin {

    public static LightningDeathPlugin instance;

    public static DeathListener listener;

    public static int[] version = new int[3];

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

    @Override
    public void onDisable()
    {
        HandlerList.unregisterAll(listener);
    }

}
