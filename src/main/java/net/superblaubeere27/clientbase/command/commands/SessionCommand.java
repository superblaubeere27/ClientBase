/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.command.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.superblaubeere27.clientbase.command.Command;
import net.superblaubeere27.clientbase.command.CommandException;
import net.superblaubeere27.clientbase.injection.interfaces.IMixinMinecraft;
import net.superblaubeere27.clientbase.utils.ChatUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

public class SessionCommand extends Command {
    public SessionCommand() {
        super("session");
    }

    @Override
    public void run(String alias, @NotNull String[] args) {
        if (args.length != 1) {
            throw new CommandException("Usage: ." + alias + "<copy/cliArgs/load> or <session>");
        }
        if (args[0].equalsIgnoreCase("copy")) {
            Session session = ((IMixinMinecraft) Minecraft.getMinecraft()).getSession();

            String text = session.getUsername() + "/" + session.getPlayerID() + "/" + session.getToken();

            try {
                StringSelection stringSelection = new StringSelection(text);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                ChatUtils.success("Copied to clipboard");
            } catch (Exception e) {
                throw new CommandException("Failed to copy to clipboard: " + e);
            }
        } else if (args[0].equalsIgnoreCase("cliArgs")) {
            Session session = ((IMixinMinecraft) Minecraft.getMinecraft()).getSession();

            String text = "--username " + session.getUsername() + " --uuid " + session.getPlayerID() + " --accessToken " + session.getToken();

            try {
                StringSelection stringSelection = new StringSelection(text);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                ChatUtils.success("Copied to clipboard");
            } catch (Exception e) {
                throw new CommandException("Failed to copy to clipboard: " + e);
            }
        } else {
            String[] split = args[0].split("/");

            if (split.length != 3) {
                throw new CommandException("Invalid session format. (username/uuid/accessToken)");
            }

            ((IMixinMinecraft) Minecraft.getMinecraft()).setSession(new Session(split[0], split[1], split[2], "mojang"));
            ChatUtils.success("Session loaded");
        }
    }

    @NotNull
    @Override
    public List<String> autocomplete(int arg, String[] args) {
        return arg == 1 ? java.util.Arrays.asList("copy", "cliArgs", "load") : new ArrayList<>();
    }
}
