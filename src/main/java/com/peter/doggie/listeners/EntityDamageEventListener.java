package com.peter.doggie.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import java.util.ArrayList;
import java.util.List;



public class EntityDamageEventListener implements Listener
{
    public static List<EntityDamageEvent.DamageCause> causelist = new ArrayList<>();


    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        EntityDamageEvent.DamageCause cause = event.getCause();
        Entity damagee = event.getEntity();

        // If it's not a wolf that's being damaged, we don't care.
        if( !(damagee instanceof Wolf wolf) )
            return;

        // If the wolf isn't tamed, we don't care about it.
        if( ! wolf.isTamed() )
            return;

        // It would be nice to set the name with the wolf's colar color, but it's not obvious how to do this.
        String name = ChatColor.stripColor( damagee.getCustomName() );

        if( causelist.contains(cause) )
        {
            event.setCancelled(true);
            System.out.println("Papa Plugin: " + name + " has been saved from damage cause: " + cause);
        } else {
            event.setCancelled(true);
            System.out.println("Not in cause list.  Damage was caused by " + cause);
        }
    }

}
