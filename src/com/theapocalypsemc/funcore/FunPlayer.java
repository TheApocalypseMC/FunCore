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

import com.theapocalypsemc.funcore.json.GsonUtil;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A JSON-serializable Player class that also allows metadata to be stored easily.
 *
 * @author SirFaizdat
 */
public class FunPlayer implements IMetadatable {

    // == Variables

    private Player player;
    private Map<String, Object> metadata;

    // == Static methods

    /**
     * Deserializes a player file.
     *
     * @param file The File to deserialize.
     * @return The FunPlayer object if it could be deserialized.
     * @throws IOException If the file couldn't be deserialized.
     */
    public static FunPlayer deserialize(File file) throws IOException {
        return GsonUtil.deserialize(file, FunPlayer.class);
    }

    // == Constructor

    public FunPlayer(Player player) {
        this.player = player;
        this.metadata = new HashMap<>();
    }

    // == Methods

    public void addMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    public void removeMetadata(String key) {
        metadata.remove(key);
    }


    /**
     * Serializes the player into a file.
     *
     * @param file The File to serialize to. If it doesn't exist, it will be created.
     * @throws IOException If the file couldn't be serialized.
     */
    public void serialize(File file) throws IOException {
        GsonUtil.serialize(file, this);
    }

    // == Getters and setters

    public Player getBukkitPlayer() {
        return player;
    }

    @Override
    public Object get(String key) {
        return metadata.get(key);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(get(key));
    }

    @Override
    public int getInt(String key) {
        return (int) get(key);
    }

    @Override
    public short getShort(String key) {
        return (short) get(key);
    }

    @Override
    public double getDouble(String key) {
        return (double) get(key);
    }

    @Override
    public long getLong(String key) {
        return (long) get(key);
    }

    @Override
    public String getString(String key) {
        return (String) get(key);
    }

    @Override
    public List<Integer> getIntList(String key) {
        return (List<Integer>) get(key);
    }

    @Override
    public List<Short> getShortList(String key) {
        return (List<Short>) get(key);
    }

    @Override
    public List<Double> getDoubleList(String key) {
        return (List<Double>) get(key);
    }

    @Override
    public List<Long> getLongList(String key) {
        return (List<Long>) get(key);
    }

    @Override
    public List<String> getStringList(String key) {
        return (List<String>) get(key);
    }

}
