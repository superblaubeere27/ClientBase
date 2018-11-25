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
import net.superblaubeere27.clientbase.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

public class LoginCommand extends Command {

    public LoginCommand() {
        super("login", "alt");
    }

    @Override
    public void run(String alias, @NotNull String[] args) {
        if (args.length < 1) {
            throw new CommandException("Usage: ." + alias + " <username:password> or <username> <password>");
        }
        String username;
        String password;

        if (args.length == 1) {
            if (!args[0].contains(":"))
                throw new CommandException("Usage: ." + alias + " <username:password> or <username> <password>");

            String split[] = args[0].split(":");

            if (split.length != 2) {
                throw new CommandException("Usage: ." + alias + " <username:password> or <username> <password>");
            }

            username = split[0];
            password = split[1];
        } else {
            username = args[0];
            password = args[1];
        }
        try {
            Session session = Utils.createSession(username, password, Proxy.NO_PROXY);

            ((IMixinMinecraft) Minecraft.getMinecraft()).setSession(session);

            ChatUtils.success("Logged in. New IGN: " + session.getUsername());
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

    }

    @NotNull
    @Override
    public List<String> autocomplete(int arg, String[] args) {
        return new ArrayList<>();
    }
}
