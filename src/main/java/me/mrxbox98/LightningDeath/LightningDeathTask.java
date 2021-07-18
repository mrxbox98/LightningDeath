package me.mrxbox98.LightningDeath;

import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LightningDeathTask extends BukkitRunnable {

    /**
     * The Death event to use
     */
    final EntityDeathEvent event;

    /**
     * Creates a new lightning task
     * @param event the event to use
     */
    public LightningDeathTask(EntityDeathEvent event) {
        this.event = event;
    }

    /**
     * Strikes the lightning effect at the
     * dead entities location
     */
    private void strikeLightningAtEntity() {

        // get the entity, killer, and world
        LivingEntity entity = event.getEntity();
        LivingEntity killer = entity.getKiller();
        World world = entity.getWorld();

        if (killer == null) return; // entity death was self-inflicted (ex. fall damage)

        if (LightningDeathPlugin.isEntityAllowed(entity.getType())) // killed entity type is in the "allowed types" list
            if (LightningDeathPlugin.isEntityAllowed(killer.getType())) // killer entity type is in the "allowed types" list
                if (killer.hasPermission("CauseLightning"))
                    world.strikeLightningEffect(entity.getLocation());
    }

    /**
     * Ran as a runnable
     */
    @Override
    public void run() {
       strikeLightningAtEntity();
    }
}