package me.mrxbox98.LightningDeath;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {

    /**
     * Calls a lightning strike at the location and the world where
     * the player died
     * @param event The death event
     */
    @EventHandler (priority = EventPriority.LOWEST)
    public void EntityDeathEvent(EntityDeathEvent event)
    {
        Bukkit.getScheduler().runTaskAsynchronously(LightningDeathPlugin.instance, new Runnable() {
            @Override
            public void run() {
                if(LightningDeathPlugin.hasEntity(event.getEntity().getType()) && LightningDeathPlugin.hasEntity(event.getEntity().getKiller().getType()))
                {
                    LightningDeathPlugin.instance.getServer().getWorld(event.getEntity().getWorld().getUID()).strikeLightningEffect(event.getEntity().getLocation());
                }
            }
        });


    }

}
