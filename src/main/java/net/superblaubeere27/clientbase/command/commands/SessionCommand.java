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
