package com.peter.doggie.utils;

import org.bukkit.ChatColor;

public class ColorTranslate {

    public static String colorTranslate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
