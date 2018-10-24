package net.superblaubeere27.clientbase.command.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.superblaubeere27.clientbase.command.Command;
import net.superblaubeere27.clientbase.command.CommandException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ConnectCommand extends Command {

    public ConnectCommand() {
        super("connect", "join", "goto");
    }

    @Override
    public void run(String alias, @NotNull String[] args) {
        if (args.length != 1) {
            throw new CommandException("Usage: ." + alias + " <server-address>");
        }

        // See https://github.com/Wurst-Imperium/Wurst-MC-1.12/blob/master/shared-src/net/wurstclient/bot/commands/JoinCmd.java
        Minecraft.getMinecraft().addScheduledTask(() -> Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(new GuiMainMenu(), Minecraft.getMinecraft(),
                new ServerData("", args[0], false))));
    }

    @NotNull
    @Override
    public List<String> autocomplete(int arg, String[] args) {
        return new ArrayList<>();
    }
}
