package me.mrxbox98.LightningDeath;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class DeathListener implements Listener {
    public Plugin plugin;

    /**
     * Creates a new DeathListener
     * @param plugin the LightningDeath plugin
     */
    public DeathListener(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);    // register listener
    }

    /**
     * Calls a lightning strike at the location and the world where
     * the player died
     * @param event The death event
     */
    @EventHandler (priority = EventPriority.LOWEST)
    public void onEntityDeath(EntityDeathEvent event) {
        // creates a new "task" to be ran later
        new LightningDeathTask(event).runTask(plugin);
        // runs the task
        // (striking lightning without async has no performance impact...)
    }
}
