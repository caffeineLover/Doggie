package com.peter.doggie;

import com.peter.doggie.handlers.WolfNameHandler;
import com.peter.doggie.listeners.EntityDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class DoggiePlugin extends JavaPlugin
{
    private WolfNameHandler wolfNameHandler;
    private String configFileName = "config.yml";


    @Override
    public void onEnable()
    {
        File configFile = new File(getDataFolder(), this.configFileName);
        if( ! configFile.exists() )
        {
            Bukkit.getLogger().info("Config file does not exist; copying the defaults from the jar file.");
            this.saveDefaultConfig();
        }

        new EntityDamageListener(this);
        new EntityDamageListener(this);

        wolfNameHandler = new WolfNameHandler(this);
    }



    public WolfNameHandler getWolfNameHandler()
    {
        return wolfNameHandler;
    }



}
