package com.peter.doggie;

import com.peter.doggie.listeners.EntityDamageByEntityEventListener;
import com.peter.doggie.listeners.EntityDamageEventListener;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class DoggiePlugin extends JavaPlugin
{
    protected String configFileName = "config.yml";



    @Override
    public void onEnable()
    {
        System.out.println("Plugin Doggie enabled.");

        File configFile = new File(getDataFolder(), this.configFileName);
        if( ! configFile.exists() )
        {
            System.out.println("Config file does not exist.");
            getConfig().options().copyDefaults(true);
            saveConfig();
            saveDefaultConfig();
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        getServer().getPluginManager().registerEvents(new EntityDamageByEntityEventListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageEventListener(), this);


        if( ! config.getBoolean("damage.drowing") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.DROWNING);

        if( ! config.getBoolean("damage.entity") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.ENTITY_ATTACK);

        if( ! config.getBoolean("damage.fire") )
        {
            System.out.println("no fire damage");
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.FIRE);
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.FIRE_TICK);
        }

        if( ! config.getBoolean("damage.fall") )
        {
            System.out.println("no falling damage");
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.FALL);
        }

        if( ! config.getBoolean("damage.falling_block"))
        {
            System.out.println("no falling block damage");
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.FALLING_BLOCK);
        }

        if( ! config.getBoolean("damage.projectile") )
        {
            System.out.println("no projectile damage");
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.PROJECTILE);
        } else
        System.out.println("PROJECTILE DAMAGE IS TRUE");
    }
}
