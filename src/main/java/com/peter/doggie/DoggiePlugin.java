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
            System.out.println("Config file does not exist.  Copying the config from the jar file.");
            this.saveDefaultConfig();
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        getServer().getPluginManager().registerEvents(new EntityDamageByEntityEventListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageEventListener(), this);

        if( ! config.getBoolean("wolf.damage.blockexplosion") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION);

        if( ! config.getBoolean("wolf.damage.contact") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.CONTACT);

        if( ! config.getBoolean("wolf.damage.drowing") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.DROWNING);

        if( ! config.getBoolean("wolf.damage.dragon_breath") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.DRAGON_BREATH);

        if( ! config.getBoolean("wolf.damage.entity") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.ENTITY_ATTACK);

        if( ! config.getBoolean("wolf.damage.entity_explosion") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION);

        if( ! config.getBoolean("wolf.damage.entity_sweep_attack") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK);

        if( ! config.getBoolean("wolf.damage.fire") )
        {
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.FIRE);
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.FIRE_TICK);
        }

        if( ! config.getBoolean("wolf.damage.fall") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.FALL);

        if( ! config.getBoolean("wolf.damage.falling_block"))
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.FALLING_BLOCK);

        if( ! config.getBoolean("wolf.damage.hot_floor"))
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.HOT_FLOOR);

        if( ! config.getBoolean("wolf.damage.lava"))
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.LAVA);

        if( ! config.getBoolean("wolf.damage.projectile") )
            EntityDamageEventListener.causelist.add(EntityDamageEvent.DamageCause.PROJECTILE);
    }
}
