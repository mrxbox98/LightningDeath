package me.mrxbox98.LightningDeath;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class LightningDeathPlugin extends JavaPlugin {

    public static LightningDeathPlugin instance;

    public static DeathListener listener;

    @Override
    public void onEnable()
    {
        instance=this;
        listener=new DeathListener();
        getServer().getPluginManager().registerEvents(listener,this);
    }

    @Override
    public void onDisable()
    {
        HandlerList.unregisterAll(listener);
    }

}
