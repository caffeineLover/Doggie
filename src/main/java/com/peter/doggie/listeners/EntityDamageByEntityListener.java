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
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class EntityDamageByEntityListener implements Listener
{
    final DoggiePlugin plugin;
    private List<EntityDamageEvent.DamageCause> noDamageOwner;
    private List<EntityDamageEvent.DamageCause> noDamagePlayer;
    private List<EntityDamageEvent.DamageCause> noDamageMob;


    public EntityDamageByEntityListener(DoggiePlugin plugin)
    {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        noDamageOwner  = new ArrayList<>();
        noDamageOwner  = plugin.getConfig().getStringList("nodamage.owner").stream().map(EntityDamageEvent.DamageCause::valueOf).collect(Collectors.toList());
        noDamagePlayer = new ArrayList<>();
        noDamagePlayer = plugin.getConfig().getStringList("nodamage.player").stream().map(EntityDamageEvent.DamageCause::valueOf).collect(Collectors.toList());
        noDamageMob    = new ArrayList<>();
        noDamageMob    = plugin.getConfig().getStringList("nodamage.mob").stream().map(EntityDamageEvent.DamageCause::valueOf).collect(Collectors.toList());
    }



    @EventHandler
    public void on(EntityDamageByEntityEvent event)
    {
        Entity damagee = event.getEntity();
        Entity damager = event.getDamager();

        // If it's not a wolf that's being damaged, we don't care.
        // FIXME: When the Pet class gets fully implemented, we need to check for Pet instead of Wolf.
        if( ! (damagee instanceof Wolf wolf) )
            return;

        // If the wolf isn't tamed, we don't care.
        if( ! wolf.isTamed() )
            return;

        // If the wolf wasn't damaged by a Player, we don't care.
        if( ! (damager instanceof Player player) )
            return;

        UUID ownerUUID, playerUUID;
        try                             { ownerUUID = wolf.getOwner().getUniqueId(); }
        catch( NullPointerException e ) { e.printStackTrace(); return;               }
        try                             { playerUUID = player.getUniqueId();         }
        catch( NullPointerException e ) { e.printStackTrace(); return;               }

        List<EntityDamageEvent.DamageCause> theListToUse;
        String deleteMeDebug;

        if     ( playerUUID == ownerUUID  )  { theListToUse = noDamageOwner;   deleteMeDebug = "owner"; }
        else if( damager instanceof Player ) { theListToUse = noDamagePlayer;  deleteMeDebug = "player"; }
        else                                 { theListToUse = noDamageMob;     deleteMeDebug = "mob"; }

        String name = ChatColor.stripColor(damagee.getCustomName());
        plugin.getWolfNameHandler().setWolfNameToCollarColor(wolf);

        if( theListToUse.contains(event.getCause()) ) {
            event.setCancelled(true);
            Bukkit.getLogger().info("no damage; cause: " + event.getCause() + "damager: " + deleteMeDebug + " name: " + name);
        } else
            Bukkit.getLogger().info("DAMAGE; cause: " + event.getCause() + "damager: " + deleteMeDebug + " name: " + name);

    }


}
