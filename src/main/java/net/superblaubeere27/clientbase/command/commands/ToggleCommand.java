package net.superblaubeere27.clientbase.command.commands;

import net.superblaubeere27.clientbase.ClientBase;
import net.superblaubeere27.clientbase.command.Command;
import net.superblaubeere27.clientbase.command.CommandException;
import net.superblaubeere27.clientbase.modules.Module;
import net.superblaubeere27.clientbase.utils.ChatUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("toggle", "t");
    }

    @Override
    public void run(String alias, @NotNull String[] args) {
        if (args.length < 1) {
            throw new CommandException("Usage: ." + alias + " <module> [<on/off>]");
        }
        Module mod = ClientBase.INSTANCE.moduleManager.getModule(args[0], false);

        if (mod == null) throw new CommandException("The module '" + args[0] + "' does not exist");

        boolean state = !mod.getState();

        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("on")) state = true;
            else if (args[1].equalsIgnoreCase("off")) state = false;
            else throw new CommandException("Usage: ." + alias + " <module> <on/off>");
        }

        mod.setState(state);

        ChatUtils.success(mod.getName() + " was " + ChatUtils.PRIMARY_COLOR + (state ? "enabled" : "disabled"));
    }

    @Override
    public List<String> autocomplete(int arg, String[] args) {
        String prefix = "";
        boolean flag = false;

        if (arg == 0) {
            flag = true;
        } else if (arg == 1) {
            flag = true;
            prefix = args[0];
        }

        if (flag) {
            String finalPrefix = prefix;
            return ClientBase.INSTANCE.moduleManager.getModules().stream().filter(mod -> mod.getName().toLowerCase().startsWith(finalPrefix)).map(Module::getName).collect(Collectors.toList());
        } else return new ArrayList<>();
    }
}
