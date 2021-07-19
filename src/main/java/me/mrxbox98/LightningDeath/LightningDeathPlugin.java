package me.mrxbox98.LightningDeath;

import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
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
     * Whether debug mode is enabled
     */
    public static boolean debug;

    /**
     * If this is true lightning will strike on players when they die
     * without a killer
     */
    public static boolean selfInflicted;

    /**
     * The plugin's instance
     */
    public static JavaPlugin instance;

    /**
     * Called when the plugin is enabled
     * Checks the servers version and registers
     * the event listener
     */
    @Override
    public void onEnable() {
        instance=this;

        getConfig().addDefault("Types","PLAYER");
        getConfig().addDefault("Debug",false);
        getConfig().addDefault("SelfInflicted",false);
        getConfig().options().copyDefaults(true);
        saveConfig();

        // add config entity types to allowed entities list
        for(EntityType entityType : EntityType.values())
            if(getConfig().getString("Types").contains(entityType.name()))
                allowedEntities.add(entityType);

        //Debug mode for plugin
        debug=getConfig().getBoolean("Debug");

        //SelfInflicted lightning
        selfInflicted=getConfig().getBoolean("SelfInflicted");

        // listener is registered inside of the class
        listener = new DeathListener(this);

        //Only active in debug mode
        if(debug)
        {
            getLogger().info("LightningDeath debug mode is enabled");
            getLogger().info("Server Version: " + getServer().getVersion());
        }

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
