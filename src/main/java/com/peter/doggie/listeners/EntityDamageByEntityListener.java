package com.peter.doggie.listeners;

import com.peter.doggie.DoggiePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;



public class EntityDamageByEntityListener implements Listener
{
    private DoggiePlugin plugin;



    public EntityDamageByEntityListener(DoggiePlugin plugin)
    {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }



    @EventHandler
    public void on(EntityDamageByEntityEvent event)
    {
        Entity damagee = event.getEntity();
        Entity damager = event.getDamager();

        // If it's not a wolf that's being damaged, we don't care.
        if( ! (damagee instanceof Wolf wolf) )
            return;

        // If the wolf isn't tamed, we don't care about it.
        if( ! wolf.isTamed() )
            return;

        // Doggie was hurt by a player.
        if (damager instanceof Player player)
        {
            event.setCancelled(true);

            String name = ChatColor.stripColor(damagee.getCustomName());
            plugin.getWolfNameHandler().setWolfNameToCollarColor(wolf);
            player.sendMessage(ChatColor.RED + "Doggie Plugin: You want to hurt " + name + "?  I won't allow it!");
        }
    }
}
