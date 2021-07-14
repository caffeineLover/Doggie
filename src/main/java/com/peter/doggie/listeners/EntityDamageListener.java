package com.peter.doggie.listeners;

import com.peter.doggie.DoggiePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class EntityDamageListener implements Listener
{
    final DoggiePlugin plugin;
    private List<EntityDamageEvent.DamageCause> noDamageList;



    public EntityDamageListener(DoggiePlugin plugin)
    {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        noDamageList = new ArrayList<>();
        noDamageList = plugin.getConfig().getStringList("nodamage.nonentity").stream().map(EntityDamageEvent.DamageCause::valueOf).collect(Collectors.toList());
    }


    @EventHandler
    public void on(EntityDamageEvent event)
    {
        // If it's not a wolf that's being damaged, we don't care.
        // FIXME: When the Pet class gets fully implemented, we need to check for Pet instead of Wolf.
        Entity damagee = event.getEntity();
        if( !(damagee instanceof Wolf wolf) )
            return;

        // If the wolf isn't tamed, we don't care about it.
        // FIXME: When the Pet class gets fully implemented, this needs to change since a Pet is tamed by definition.
        if( ! wolf.isTamed() )
            return;

        // Update name color
        String name = ChatColor.stripColor(damagee.getCustomName());
        plugin.getWolfNameHandler().setWolfNameToCollarColor(wolf);

        // Disallow damage types that are in noDamageList.
        EntityDamageEvent.DamageCause cause = event.getCause();
        if( noDamageList.contains(cause) ) {
            event.setCancelled(true);
            Bukkit.getLogger().info("EntityDamage: " + name + " no damage from: " + cause);
        } else {
            Bukkit.getLogger().info("EntityDamage: " + name + " damage from: " + cause);
        }
    }



}