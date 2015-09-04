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

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Registers players as FunPlayers when they join. All FunPlayers should
 * be accessed from here.
 * <b>This class should only be used from FunCore.getPlayerRegistry(). Do not make your own instance!</b>
 *
 * @author SirFaizdat
 */
public class PlayerRegistry implements Listener {

    // == Variables

    private FunCore core;
    private Map<String, FunPlayer> playersByName;
    private Map<UUID, FunPlayer> playersByUUID;

    // == Constructor

    protected PlayerRegistry(FunCore core) {
        this.core = core;
        playersByName = new HashMap<>();
        playersByUUID = new HashMap<>();

        this.core.getServer().getPluginManager().registerEvents(this, this.core);
    }

    // == Listeners

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        FunPlayer player = new FunPlayer(e.getPlayer());
        playersByName.put(e.getPlayer().getName(), player);
        playersByUUID.put(e.getPlayer().getUniqueId(), player);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        FunPlayer player = getPlayer(e.getPlayer().getUniqueId());
        if (player != null) {
            playersByName.remove(e.getPlayer().getName());
            playersByUUID.remove(e.getPlayer().getUniqueId());
        }
    }

    // == Methods

    /**
     * Gets a player by their name.
     *
     * @param name The player's name.
     * @return The FunPlayer object of the player, or null if the player was not found or is not online.
     */
    public FunPlayer getPlayer(String name) {
        return playersByName.get(name);
    }

    /**
     * Gets a player by their UUID.
     *
     * @param uuid The UUID of the player.
     * @return The FunPlayer object of the player, or null if the player was not found or is not online.
     */
    public FunPlayer getPlayer(UUID uuid) {
        return playersByUUID.get(uuid);
    }

    /**
     * Clear the player lists (to avoid memory leaks and to aid in garbage collection on server shutdown).
     * This method should only be called by FunCore.
     */
    protected void clear() {
        playersByName.clear();
        playersByUUID.clear();
    }

}
