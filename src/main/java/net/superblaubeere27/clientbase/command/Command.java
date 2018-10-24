package net.superblaubeere27.clientbase.command;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {
    private String name;
    private String[] aliases;

    protected Command(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public abstract void run(String alias, String[] args);

    public abstract List<String> autocomplete(int arg, String[] args);

    boolean match(String name) {
        for (String alias : aliases) {
            if (alias.equalsIgnoreCase(name)) return true;
        }
        return this.name.equalsIgnoreCase(name);
    }

    @NotNull List<String> getNameAndAliases() {
        List<String> l = new ArrayList<>();
        l.add(name);
        l.addAll(Arrays.asList(aliases));

        return l;
    }

}
