package com.peter.doggie;

import com.peter.doggie.handlers.WolfNameHandler;
import com.peter.doggie.listeners.EntityDamageByEntityListener;
import com.peter.doggie.listeners.EntityDamageListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class DoggiePlugin extends JavaPlugin {

    private WolfNameHandler wolfNameHandler;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Doggie has been enabled"); // You really need to stop doing this... lol

        new EntityDamageListener(this);
        new EntityDamageListener(this);

        wolfNameHandler = new WolfNameHandler(this);
    }

    public WolfNameHandler getWolfNameHandler() {
        return wolfNameHandler;
    }
}
