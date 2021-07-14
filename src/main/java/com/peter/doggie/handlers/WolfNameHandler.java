package com.peter.doggie.handlers;

import com.peter.doggie.DoggiePlugin;
import com.peter.doggie.utils.ColorTranslate;
import org.bukkit.ChatColor;
import org.bukkit.entity.Wolf;

public class WolfNameHandler {

    private DoggiePlugin plugin;

    public WolfNameHandler(DoggiePlugin plugin) {
        this.plugin = plugin;
    }

    public void setWolfNameToCollarColor(Wolf wolf) {
        String foundColor = plugin.getConfig().getString("color." + wolf.getCollarColor().name());
        if (foundColor == null) return;
        wolf.setCustomName(ColorTranslate.colorTranslate(foundColor) + ChatColor.stripColor(wolf.getCustomName()));
    }
}
