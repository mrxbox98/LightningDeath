package me.mrxbox98.LightningDeath;

import org.bukkit.entity.EntityType;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LightningDeathPlugin extends JavaPlugin {
    /**
     * The death event listener
     */
    public static DeathListener listener;

    /**
     * Types of entities the lightning are allowed to be summoned on
     */
    public static List<EntityType> allowedEntities = new ArrayList<>();

    /**
     * Called when the plugin is enabled
     * Checks the servers version and registers
     * the event listener
     */
    @Override
    public void onEnable() {
        getConfig().addDefault("Types","PLAYER");
        getConfig().options().copyDefaults(true);
        saveConfig();

        // add config entity types to allowed entities list
        for(EntityType entityType : EntityType.values())
            if(getConfig().getString("Types").contains(entityType.name()))
                allowedEntities.add(entityType);

        // listener is registered inside of the class
        listener = new DeathListener(this);
    }


    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Checks whether the entity provided
     * is in the array of allowed entities
     * @param type The type to check
     * @return true if it is the right type false otherwise
     */
    public static boolean isEntityAllowed(EntityType type) {
        // checks the entire "allowedEntities" list to see if EntityType "type" is anywhere in the list
        return allowedEntities.stream().anyMatch(entityType -> entityType.equals(type));
    }
}
