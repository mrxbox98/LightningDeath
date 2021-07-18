package me.mrxbox98.LightningDeath;

import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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
        Player killer = entity.getKiller();
        World world = entity.getWorld();

        if (killer == null)
        {
            LightningDeathPlugin.instance.getLogger().info(entity.getName() + " died but no lightning was struck as the kill was self inflicted.");
            return;
        }// entity death was self-inflicted (ex. fall damage)

        if (LightningDeathPlugin.isEntityAllowed(entity.getType()))
        {
            if (killer.hasPermission("CauseLightning"))
            {
                world.strikeLightningEffect(entity.getLocation());
            }
            else
            {
                LightningDeathPlugin.instance.getLogger().info(entity.getName() + " was killed but no lightning was struck because " + killer.getName() + " does not have the CauseLightning permission.");
            }

        }// killed entity type is in the "allowed types" list
        else
        {
            LightningDeathPlugin.instance.getLogger().info("A " + entity.getType().name() + " but no lightning was struck as this mob is not in the allowed mobs list in the config.yml");
        }

    }

    /**
     * Ran as a runnable
     */
    @Override
    public void run() {
       strikeLightningAtEntity();
    }
}