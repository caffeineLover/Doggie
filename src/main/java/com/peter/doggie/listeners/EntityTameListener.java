package com.peter.doggie.listeners;

import com.peter.doggie.DoggiePlugin;
import com.peter.doggie.handlers.PetHandler;
import com.peter.doggie.tameables.Pet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        // Not sure if this can happen, but if the tamer is not Player, we don't really care.
        if( ! (event.getOwner() instanceof Player) ) {
            Bukkit.getLogger().info("The tamer is type " + event.getOwner() + ", which is not a Player, so we don't care about it.");
            return;
        }

        PetHandler petHandler = new PetHandler();
        Pet pet = petHandler.createPet( event.getEntity() );
    }


}
