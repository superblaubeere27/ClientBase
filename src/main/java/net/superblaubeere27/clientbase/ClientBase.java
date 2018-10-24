package net.superblaubeere27.clientbase;

import net.superblaubeere27.clientbase.command.CommandManager;
import net.superblaubeere27.clientbase.fileSystem.FileManager;
import net.superblaubeere27.clientbase.modules.ModuleManager;
import net.superblaubeere27.clientbase.valuesystem.ValueManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClientBase {
    /*
    Metadata
     */
    @NotNull
    public static final String CLIENT_NAME = "ClientBase";
    @NotNull
    public static final String CLIENT_AUTHOR = "superblaubeere27";
    public static final double CLIENT_VERSION_NUMBER = 1.0;
    @NotNull
    public static final String CLIENT_VERSION = CLIENT_VERSION_NUMBER + "-DEV";
    @NotNull
    public static final String CLIENT_INITIALS;
    public static ClientBase INSTANCE;

    static {
        List<Character> chars = new ArrayList<>();

        for (char c : CLIENT_NAME.toCharArray())
            if (Character.toUpperCase(c) == c) chars.add(c);

        char[] c = new char[chars.size()];

        for (int i = 0; i < chars.size(); i++) {
            c[i] = chars.get(i);
        }

        CLIENT_INITIALS = new String(c);
    }

    public ModuleManager moduleManager;
    public CommandManager commandManager;
    public ValueManager valueManager;
    private FileManager fileManager;

    public ClientBase() {
        INSTANCE = this;
    }

    public void startClient() {
        fileManager = new FileManager();
        valueManager = new ValueManager();
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();

        commandManager.addCommands();
        moduleManager.addModules();

        fileManager.load();
    }

    public void stopClient() {
        try {
            fileManager.save();
        } catch (Exception e) {
            System.err.println("Failed to save settings:");
            e.printStackTrace();
        }
    }

}
