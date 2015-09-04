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

import java.util.*;
import java.util.Map.Entry;

public class Arguments {
    private List<String> arguments;
    private int argCounter = 0;

    private Map<Flag, List<String>> flags = new HashMap<>();
    private Map<Flag, Integer> flagCounter = new HashMap<>();

    public Arguments(String[] args, Map<String, Flag> flags) throws CommandError {
        List<String> largs = new ArrayList<>(Arrays.asList(args));
        //Find the flags
        for (Entry<String, Flag> entry : flags.entrySet()) {
            Flag flag = entry.getValue();

            int flagIndex = largs.indexOf("-" + flag.getIdentifier());
            if (flagIndex == -1)
                continue;

            largs.remove(flagIndex);

            int endIndex = flag.getArguments().size() + flagIndex;

            if (endIndex > largs.size())
                throw new CommandError("The flag -" + flag.getIdentifier() + " does not have the required parameters.");

            flagCounter.put(flag, 0);

            List<String> flagArgs = new ArrayList<String>();
            this.flags.put(flag, flagArgs);

            for (int i = flagIndex; i < endIndex; i++) {
                flagArgs.add(largs.remove(flagIndex));
            }
        }

        //The rest is normal arguments
        arguments = largs;
    }

    public boolean flagExists(Flag flag) {
        return flags.get(flag) != null;
    }

    public boolean hasNext() {
        return argCounter < size();
    }

    public boolean hasNext(Flag flag) {
        Integer c = flagCounter.get(flag);
        if (c == null)
            return false;

        return c < size(flag);
    }

    public String nextArgument() {
        String arg = arguments.get(argCounter);
        argCounter++;
        return arg;
    }

    public String nextFlagArgument(Flag flag) {
        List<String> args = flags.get(flag);

        if (args == null)
            return null;

        return args.get(flagCounter.put(flag, flagCounter.get(flag) + 1));
    }

    public int over() {
        return size() - argCounter;
    }

    public int over(Flag flag) {
        return size(flag) - flagCounter.get(flag);
    }

    public int size() {
        return arguments.size();
    }

    public int size(Flag flag) {
        List<String> args = flags.get(flag);

        if (args == null)
            return 0;

        return args.size();
    }
}
