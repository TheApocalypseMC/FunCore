/*
 * Copyright (C) 2015 The Apocalypse MC.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.theapocalypsemc.funcore;

import net.md_5.bungee.api.ChatColor;

/**
 * Main class for FunCore. Loads some parts of the API, but more importantly,
 * sates the appetite of Bukkit's plugin loader which requires each plugin
 * have a JavaPlugin class in order to actually run.
 *
 * @author SirFaizdat
 */
public class FunCore extends FunPlugin {

    // == Variables

    // == Methods

    @Override
    public void enable() {
    }

    @Override
    public void disable() {
    }

    // == Private methods

    // == Static methods

    /**
     * Translates &-prefixed color codes to MC color codes.
     *
     * @param txt The String to transalte the color codes of.
     * @return The fully colored string.
     */
    public static String color(String txt) {
        return ChatColor.translateAlternateColorCodes('&', txt);
    }

    // == Getters and setters

}
