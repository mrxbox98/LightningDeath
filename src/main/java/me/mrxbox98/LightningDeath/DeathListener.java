package me.mrxbox98.LightningDeath;

import javafx.scene.layout.Priority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class DeathListener implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void EntityDeathEvent(EntityDeathEvent event)
    {
        if(event.getEntity() instanceof Plugin)
        {
            LightningDeathPlugin.instance.getServer().getWorld(event.getEntity().getWorld().getUID()).strikeLightningEffect(event.getEntity().getLocation());
        }
    }

}
