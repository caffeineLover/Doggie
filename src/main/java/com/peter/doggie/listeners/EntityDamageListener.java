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


public class EntityDamageListener implements Listener {

    private DoggiePlugin plugin;

    private List<EntityDamageEvent.DamageCause> causeList;

    public EntityDamageListener(DoggiePlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);

        causeList = new ArrayList<>();

        causeList = plugin.getConfig().getStringList("damage.wolf").stream().map(EntityDamageEvent.DamageCause::valueOf).collect(Collectors.toList());
    }

    @EventHandler
    public void on(EntityDamageEvent event) {
        EntityDamageEvent.DamageCause cause = event.getCause();
        Entity damagee = event.getEntity();

        // If it's not a wolf that's being damaged, we don't care.
        if (!(damagee instanceof Wolf)) return;
        Wolf wolf = (Wolf) damagee;
        // If the wolf isn't tamed, we don't care about it.
        if (!wolf.isTamed()) return;

        String name = ChatColor.stripColor(damagee.getCustomName());

        // Update name color
        plugin.getWolfNameHandler().setWolfNameToCollarColor(wolf);

        event.setCancelled(true);
        Bukkit.getLogger().info(causeList.contains(cause) ?
                "Papa Plugin: " + name + " has been saved from damage cause: " + cause :
                "Not in cause list.  Damage was caused by " + cause);
    }

}
