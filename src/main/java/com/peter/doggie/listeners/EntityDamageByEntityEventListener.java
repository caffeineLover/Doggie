package com.peter.doggie.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class EntityDamageByEntityEventListener implements Listener
{

    @EventHandler
    public void onDamage(org.bukkit.event.entity.EntityDamageByEntityEvent event)
    {
        Entity damagee = event.getEntity();
        Entity damager = event.getDamager();

        // If it's not a wolf that's being damaged, we don't care.
        if( !(damagee instanceof Wolf wolf) )
            return;

        // If the wolf isn't tamed, we don't care about it.
        if( ! wolf.isTamed() )
            return;

        // It would be nice to set the name with the wolf's colar color, but it's not obvious how to do this.
        String name = ChatColor.stripColor( damagee.getCustomName() );
        AnimalTamer tamer = wolf.getOwner();

        // Doggie was hurt by a player.
        if( damager instanceof Player p ) {
            if (tamer == p) {
                event.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Doggie Plugin: You want to hurt " + name + "?  I won't allow it!");
                System.out.println("Doggie Plugin: You want to hurt " + name + "?  I won't allow it!");
            } else {
                event.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Doggie Plugin: You want to hurt " + name + "?  I won't allow it!");
                System.out.println("Doggie Plugin: You want to hurt " + name + "?  I won't allow it!");
            }
        }
    }


}
