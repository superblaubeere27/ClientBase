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
