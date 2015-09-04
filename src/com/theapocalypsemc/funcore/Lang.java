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

package com.theapocalypsemc.funcore;/*
 * Copyright (C) 2015 SirFaizdat
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.theapocalypsemc.funcore.json.JsonConfiguration;
import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;

/**
 * Provides localization support by reading from the lang.json file and
 * storing the messages in a Map for easy access. This also provides a variable
 * system which replaces variable IDs in the language string with the appropriate
 * index from the array of variables provided. For example, providing the words
 * "foo" and "bar" in the get method for this langauge message: "Your %0 is at the %1"
 * will return "Your foo is at the bar".
 *
 * @author SirFaizdat
 */
public class Lang {

    private FunPlugin plugin;
    private JsonConfiguration configuration;

    public Lang(FunPlugin plugin) {
        this.plugin = plugin;
        this.configuration = new JsonConfiguration(new File(this.plugin.getDataFolder(), "lang.json"));
    }

    /**
     * Loads all messages from lang.json.
     *
     * @throws IOException If lang.json couldn't be loaded.
     */
    public void load() throws IOException {
        configuration.load();
    }

    /**
     * Gets a message from the lang.json list, and then replace the variables in it
     * with the variables passed in to the method.
     *
     * @param key       The key of the message.
     * @param variables (Varargs, specify as many as you want) The variables to insert, if any.
     * @return The formatted String, or null if the message couldn't be found.
     */
    public String get(String key, String... variables) {
        Validate.notNull(key);
        String message = this.configuration.getString(key);
        if (message == null) {
            this.plugin.getFunLogger().severe("The message " + key + " could not be found in lang.json.");
            return null;
        }
        int index = 0;
        while (message.contains("%" + index)) {
            message = message.replaceAll("%" + index, variables[index]);
            index++;
        }
        return FunCore.color(message);
    }

    /**
     * Gets a message from the lang.json list, replace the variables in it
     * with the variables passed in to the method, and send it to the specified sender.
     *
     * @param sender    The CommandSender to send it to.
     * @param key       The key of the message.
     * @param variables (Varargs, specify as many as you want) The variables to insert, if any.
     */
    public void send(CommandSender sender, String key, String... variables) {
        sender.sendMessage(get(key, variables));
    }

    /**
     * Gets a message from the lang.json list, replace the variables in it
     * with the variables passed in to the method, and send it to the specified sender.
     *
     * @param sender    The FunPlayer to send it to.
     * @param key       The key of the message.
     * @param variables (Varargs, specify as many as you want) The variables to insert, if any.
     */
    public void send(FunPlayer sender, String key, String... variables) {
        sender.getBukkitPlayer().sendMessage(get(key, variables));
    }


    /**
     * Add a default message to lang.json. All defaults should be added before
     * calling the load() method.
     *
     * @param key   The name of this message (you'll access the message with this later).
     * @param value The message to associate with the key. If the message contains color codes, it will be colorized.
     */
    public void addDefault(String key, String value) {
        configuration.addDefault(key, value);
    }
}
