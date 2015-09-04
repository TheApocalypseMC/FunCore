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

package com.theapocalypsemc.funcore.hud;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.theapocalypsemc.funcore.FunCore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * FunScoreboard allows for easy creation of scoreboards.
 * This class was originally made by RainoBoy97, and modified by SirFaizdat (added
 * documentation and color support).
 *
 * @author RainoBoy97
 * @author SirFaizdat
 */
public class FunScoreboard {

    // == Variables

    private Scoreboard scoreboard;

    private String title;
    private Map<String, Integer> scores;
    private List<Team> teams;

    // == Constructor

    /**
     * Creates a new FunScoreboard with a title.
     *
     * @param title The title of this board (color codes supported).
     */
    public FunScoreboard(String title) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.title = FunCore.color(title);
        this.scores = Maps.newLinkedHashMap();
        this.teams = Lists.newArrayList();
    }

    // == Methods

    /**
     * Create a new blank line in the scoreboard.
     */
    public void blankLine() {
        add(" ");
    }

    /**
     * Add a line of text to the scoreboard.
     *
     * @param text The text to add. Note that this cannot be longer than 48 characters.
     */
    public void add(String text) {
        add(text, null);
    }

    /**
     * Add a line of text to the scoreboard, as well as a score.
     *
     * @param text  The text to add. Note that this cannot be longer than 48 characters.
     * @param score The score to assign to this text.
     */
    public void add(String text, Integer score) {
        Preconditions.checkArgument(text.length() < 48, "text cannot be over 48 characters in length");
        text = fixDuplicates(text);
        scores.put(text, score);
    }

    /**
     * Build the scoreboard. This should be called before sending it to any players.
     */
    public void build() {
        Objective obj = scoreboard.registerNewObjective((title.length() > 16 ? title.substring(0, 15) : title), "dummy");
        obj.setDisplayName(title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int index = scores.size();

        for (Map.Entry<String, Integer> text : scores.entrySet()) {
            Map.Entry<Team, String> team = createTeam(text.getKey());
            Integer score = text.getValue() != null ? text.getValue() : index;
            OfflinePlayer player = Bukkit.getOfflinePlayer(team.getValue());
            if (team.getKey() != null)
                team.getKey().addPlayer(player);
            obj.getScore(player).setScore(score);
            index -= 1;
        }
    }

    /**
     * Reset the scoreboard and all of its entries.
     */
    public void reset() {
        title = null;
        scores.clear();
        for (Team t : teams)
            t.unregister();
        teams.clear();
    }

    /**
     * Send the scoreboard to the specified players.
     *
     * @param players The players to send the scoreboard to.
     */
    public void send(Player... players) {
        for (Player p : players)
            p.setScoreboard(scoreboard);
    }

    // == Private methods (UNDOCUMENTED)

    private String fixDuplicates(String text) {
        while (scores.containsKey(text))
            text += "§r"; // Reset the color
        if (text.length() > 48)
            text = text.substring(0, 47); // Trim it to <48
        return text;
    }

    private Map.Entry<Team, String> createTeam(String text) {
        String result = "";
        if (text.length() <= 16)
            return new AbstractMap.SimpleEntry<>(null, text);
        Team team = scoreboard.registerNewTeam("text-" + scoreboard.getTeams().size());
        Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
        team.setPrefix(iterator.next());
        result = iterator.next();
        if (text.length() > 32)
            team.setSuffix(iterator.next());
        teams.add(team);
        return new AbstractMap.SimpleEntry<>(team, result);
    }

    // == Getters and setters

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

}