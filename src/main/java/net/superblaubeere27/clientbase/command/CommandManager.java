/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.command;

import net.superblaubeere27.clientbase.command.commands.*;
import net.superblaubeere27.clientbase.utils.ChatUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandManager {
    @NotNull
    private List<Command> commands = new ArrayList<Command>();

    public void addCommands() {
        addCommand(new ToggleCommand());
        addCommand(new BindCommand());
        addCommand(new ValueCommand());
        addCommand(new LoginCommand());
        addCommand(new SessionCommand());
        addCommand(new ConnectCommand());
        addCommand(new ScriptCommand());
    }

    private void addCommand(Command cmd) {
        commands.add(cmd);
    }

    public boolean executeCommand(@NotNull String string) {
        String raw = string.substring(1);
        String[] split = raw.split(" ");

        if (split.length == 0) return false;

        String cmdName = split[0];

        Command command = null;
        for (Command cmd : commands) {
            if (cmd.match(cmdName)) {
                command = cmd;
                break;
            }
        }

        try {
            if (command == null) {
                ChatUtils.send("\u00a7c'" + cmdName + "' doesn't exist");
                return false;
            } else {
                String[] args = new String[split.length - 1];

                System.arraycopy(split, 1, args, 0, split.length - 1);

                command.run(split[0], args);
                return true;
            }
        } catch (CommandException e) {
            ChatUtils.send("\u00a7c" + e.getMessage());
        }
        return true;
    }

    public Collection<String> autoComplete(@NotNull String currCmd) {
        String raw = currCmd.substring(1);
        String[] split = raw.split(" ");

        List<String> ret = new ArrayList<String>();


        Command currentCommand;
        if (split.length >= 1) {
            Command found = null;
            for (Command cmd : commands) {
                if (cmd.match(split[0])) {
                    found = cmd;
                    break;
                }
            }
            currentCommand = found;
        } else {
            currentCommand = null;
        }

        if (split.length >= 2 || currentCommand != null && currCmd.endsWith(" ")) {

            if (currentCommand == null) return ret;

            String[] args = new String[split.length - 1];

            System.arraycopy(split, 1, args, 0, split.length - 1);

            List<String> autocomplete = currentCommand.autocomplete(args.length + (currCmd.endsWith(" ") ? 1 : 0), args);

            return autocomplete == null ? new ArrayList<String>() : autocomplete;
        } else if (split.length == 1) {
            for (Command command : commands) {
                ret.addAll(command.getNameAndAliases());
            }

            List<String> list = new ArrayList<String>();
            for (String str : ret) {
                String s = "." + str;
                if (s.toLowerCase().startsWith(currCmd.toLowerCase())) {
                    list.add(s);
                }
            }
            return list;
        }

        return ret;
    }
}
