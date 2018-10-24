package net.superblaubeere27.clientbase.modules.modules.render;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.superblaubeere27.clientbase.ClientBase;
import net.superblaubeere27.clientbase.events.KeyEvent;
import net.superblaubeere27.clientbase.events.Render2DEvent;
import net.superblaubeere27.clientbase.gui.tabgui.SubTab;
import net.superblaubeere27.clientbase.gui.tabgui.Tab;
import net.superblaubeere27.clientbase.gui.tabgui.TabGui;
import net.superblaubeere27.clientbase.modules.Module;
import net.superblaubeere27.clientbase.modules.ModuleCategory;
import net.superblaubeere27.clientbase.notifications.NotificationManager;
import net.superblaubeere27.clientbase.utils.GLUtil;
import net.superblaubeere27.clientbase.valuesystem.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HUD extends Module {
    @NotNull
    private TabGui<Module> tabGui = new TabGui<>();
    @NotNull
    private List<Integer> fps = new ArrayList<>();

    @NotNull
    private NumberValue<Integer> fpsStatisticLength = new NumberValue<>("FPSStatisticLength", 250, 10, 500);

    public HUD() {
        super("HUD", "The Overlay", ModuleCategory.RENDER, false, true, Keyboard.KEY_NONE);
        setState(true);


        HashMap<ModuleCategory, java.util.List<Module>> moduleCategoryMap = new HashMap<>();

        for (Module module : ClientBase.INSTANCE.moduleManager.getModules()) {
            if (!moduleCategoryMap.containsKey(module.getCategory())) {
                moduleCategoryMap.put(module.getCategory(), new ArrayList<>());
            }

            moduleCategoryMap.get(module.getCategory()).add(module);
        }

        moduleCategoryMap.entrySet().stream().sorted(Comparator.comparingInt(cat -> cat.getKey().toString().hashCode())).forEach(cat -> {
            Tab<Module> tab = new Tab<>(cat.getKey().toString());

            for (Module module : cat.getValue()) {
                tab.addSubTab(new SubTab<>(module.getName(), subTab -> subTab.getObject().setState(!subTab.getObject().getState()), module));
            }

            tabGui.addTab(tab);
        });

    }

    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.8f, 0.7f).getRGB();
    }

    @EventTarget
    private void render2D(Render2DEvent event) {
        if (!getState()) return;

        fps.add(Minecraft.getDebugFPS());

        while (fps.size() > fpsStatisticLength.getObject()) {
            fps.remove(0);
        }

        FontRenderer fontRenderer = mc.fontRendererObj;

        ScaledResolution res = new ScaledResolution(mc);

        int blackBarHeight = fontRenderer.FONT_HEIGHT * 2 + 4;

        Gui.drawRect(0, res.getScaledHeight() - blackBarHeight, res.getScaledWidth(), res.getScaledHeight(), GLUtil.toRGBA(new Color(0, 0, 0, 150)));

        GL11.glScaled(2.0, 2.0, 2.0);
        int i = fontRenderer.drawString(ClientBase.CLIENT_NAME, 2, 2, rainbow(0), true);
        int initialSize = fontRenderer.drawString(ClientBase.CLIENT_INITIALS, 1, res.getScaledHeight() / 2.0f - fontRenderer.FONT_HEIGHT, rainbow(0), true);
        GL11.glScaled(0.5, 0.5, 0.5);

        fontRenderer.drawString(ClientBase.CLIENT_VERSION, i * 2, fontRenderer.FONT_HEIGHT * 2 - 7, rainbow(100), true);
        fontRenderer.drawString("by " + ClientBase.CLIENT_AUTHOR, 4, fontRenderer.FONT_HEIGHT * 2 + 2, rainbow(200), true);

        int fpsWidth = fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), initialSize * 2 + 2, res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 2, -1, true);


        AtomicInteger offset = new AtomicInteger(3);
        AtomicInteger index = new AtomicInteger();

        ClientBase.INSTANCE.moduleManager.getModules().stream().filter(mod -> mod.getState() && !mod.isHidden()).sorted(Comparator.comparingInt(mod -> -fontRenderer.getStringWidth(mod.getName()))).forEach(mod -> {
            fontRenderer.drawString(mod.getName(), res.getScaledWidth() - fontRenderer.getStringWidth(mod.getName()) - 3, offset.get(), rainbow(index.get() * 100), true);

            offset.addAndGet(fontRenderer.FONT_HEIGHT + 2);
            index.getAndIncrement();
        });

        NotificationManager.render();
        tabGui.render(5, (2 + fontRenderer.FONT_HEIGHT) * 3);


        int max = fps.stream().max(Integer::compareTo).orElse(1);
        double transform = blackBarHeight / 2.0 / (double) max;

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glLineWidth(1.0f);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glBegin(GL11.GL_LINE_STRIP);

        fpsWidth += 3;

        double v = (res.getScaledWidth() - fpsWidth * 2.5) / 4.0 / (double) fps.size();

        for (int j = 0; j < fps.size(); j++) {
            int currFPS = fps.get(j);

            GL11.glVertex2d(fpsWidth + j * v, res.getScaledHeight() - transform * currFPS);
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    @EventTarget
    public void onKey(@NotNull KeyEvent event) {
        tabGui.handleKey(event.getKey());
    }
}
