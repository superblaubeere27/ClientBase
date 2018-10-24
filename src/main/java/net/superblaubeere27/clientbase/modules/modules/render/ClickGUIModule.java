package net.superblaubeere27.clientbase.modules.modules.render;

import net.superblaubeere27.clientbase.gui.clickgui.ClickGUI;
import net.superblaubeere27.clientbase.modules.Module;
import net.superblaubeere27.clientbase.modules.ModuleCategory;
import org.lwjgl.input.Keyboard;

public class ClickGUIModule extends Module {
    private static final ClickGUI clickGui = new ClickGUI();

    public ClickGUIModule() {
        super("ClickGUI", "The click gui", ModuleCategory.RENDER, true, true, Keyboard.KEY_RSHIFT);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(clickGui);
        setState(false);
    }
}
