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

package se.ranzdo.bukkit.methodcommand;

import org.bukkit.command.CommandSender;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    /**
     * The description of this command
     */
    String description() default "";

    /**
     * The identifier describes what command definition this will bind to. Spliced by spaces, you can define as many sub commands as you want, as long as the first command (the root) is defined in the plugin.yml file.<br><br>
     * Example: {@code @HGCommands(identifier="root sub1 sub2")}<br>
     * The first command "root" needs to be defined in the plugin.yml. The user will be able to access the command by writing (if the root command does not choose an alias instead):<br>
     * {@code /root sub1 sub2}<br>
     */
    String identifier();

    /**
     * If this command can only be executed by players (default true).<br>
     * If you turn this to false, the first parameter in the method must be the {@link CommandSender} to avoid {@link ClassCastException}
     */
    boolean onlyPlayers() default true;

    /**
     * The permissions to check if the user have before execution. If it is empty the command does not require any permission.<br><br>
     * If the user don't have one of the permissions, they will get an error message stating that they do not have permission to use the command.
     */
    String[] permissions() default {};
}
