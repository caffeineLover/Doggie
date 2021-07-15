package com.peter.doggie.listeners;

import com.peter.doggie.DoggiePlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;


public class EntityTameListener implements Listener
{
    private DoggiePlugin plugin;



    public EntityTameListener(DoggiePlugin plugin)
    {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents( this, plugin);
    }



    @EventHandler
    public void on(EntityTameEvent event)
    {
        // TODO: We probably want a Factory pattern here.  For now, we're just implementing Wolves.  In the future, other tameables.
        if( event.getEntity().getType() != EntityType.WOLF ) {
            Bukkit.getLogger().info("Entity type " + event.getEntity().getType() + " was tamed, but we don't support that yet.");
            return;
        }

        // If the tamer is not Player, we don't really care.
        if( ! (event.getOwner() instanceof Player player) ) {
            Bukkit.getLogger().info("The tamer is type " + event.getOwner() + ", which is not a Player, so we don't care about it.");
            return;
        }

        Wolf wolf = (Wolf) event.getEntity();
    }


}
