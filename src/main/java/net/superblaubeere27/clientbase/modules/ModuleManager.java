package net.superblaubeere27.clientbase.modules;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.superblaubeere27.clientbase.ClientBase;
import net.superblaubeere27.clientbase.events.KeyEvent;
import net.superblaubeere27.clientbase.modules.modules.render.ClickGUIModule;
import net.superblaubeere27.clientbase.modules.modules.render.HUD;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    @NotNull
    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        EventManager.register(this);
    }


    public void addModules() {
        addModule(new HUD());
        addModule(new ClickGUIModule());
    }

    private void addModule(@NotNull Module module) {
        modules.add(module);
        EventManager.register(module);
        ClientBase.INSTANCE.valueManager.registerObject(module.getName(), module);
    }

    @NotNull
    public List<Module> getModules() {
        return modules;
    }

    @NotNull
    public <T extends Module> T getModule(Class<T> clazz) {
        return (T) modules.stream().filter(mod -> mod.getClass() == clazz).findFirst().orElse(null);
    }

    public Module getModule(@NotNull String name, boolean caseSensitive) {
        return modules.stream().filter(mod -> !caseSensitive && name.equalsIgnoreCase(mod.getName()) || name.equals(mod.getName())).findFirst().orElse(null);
    }

    @EventTarget
    private void onKey(@NotNull KeyEvent event) {
        for (Module module : modules) if (module.getKeybind() == event.getKey()) module.setState(!module.getState());
    }
}
