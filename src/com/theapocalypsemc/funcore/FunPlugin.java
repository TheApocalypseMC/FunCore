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

import com.theapocalypsemc.funcore.utilities.FunLogger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author SirFaizdat
 */
public class FunPlugin extends JavaPlugin {

    // == Variables

    private FunLogger funLogger;

    // == Methods

    /**
     * Code that is called before the world loads. Contains some important setup code.
     */
    @Override
    public void onLoad() {
        // Create the plugin's data folder if it doesn't exist already.
        if (!getDataFolder().exists()) getDataFolder().mkdir();
    }

    /**
     * Code that is called when the plugin is enabling. Everything happens here.
     */
    @Override
    public void onEnable() {
        // Init logger and log enabling message.
        funLogger = new FunLogger(this);
        funLogger.info("&7Enabling &b" + getDescription().getFullName() + "&7...");
        long startTime = System.currentTimeMillis(); // Time the enabling of the plugin

        // Init API

        enable(); // Enable the actual plugin.

        // Tell user how long it took to enable.
        funLogger.info("&7Enabled in &b" + (System.currentTimeMillis() - startTime) + "ms&7.");
    }

    /**
     * Code that is called when the plugin is disabling.
     */
    @Override
    public void onDisable() {
        disable();
    }

    /**
     * Disable the plugin.
     */
    public void suicide() {
        getServer().getPluginManager().disablePlugin(this);
    }

    // == Abstract methods

    /**
     * Called after the API initializes. Put your initialization code here.
     */
    public void enable() {
    }

    /**
     * Called before the API initializes. Put your deinitialization code here.
     */
    public void disable() {
    }

    // == Private methods

    // == Static methods

    // == Getters and setters

    public FunLogger getFunLogger() {
        return funLogger;
    }
}
