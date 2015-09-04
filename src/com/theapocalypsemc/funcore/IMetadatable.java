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

import java.util.List;

/**
 * Represents a class that stoers metadata. This is not limited just to actual metadata,
 * it can also be, for example, in a configuration, since it contains get methods.
 *
 * @author SirFaizdat
 */
public interface IMetadatable {

    /**
     * Get a value from the metadata.
     *
     * @param key The metadata's key.
     * @return the Object.
     */
    Object get(String key);

    /**
     * Gets a value from the metadata
     *
     * @param key   The key of this metadata.
     * @param clazz The class to cast the metadata object to.
     * @return The object casted to the class provided.
     */
    <T> T get(String key, Class<T> clazz);

    /*
     * I don't think the following requires any documentation.
     */

    int getInt(String key);

    short getShort(String key);

    double getDouble(String key);

    long getLong(String key);

    String getString(String key);

    List<Integer> getIntList(String key);

    List<Short> getShortList(String key);

    List<Double> getDoubleList(String key);

    List<Long> getLongList(String key);

    List<String> getStringList(String key);

}
