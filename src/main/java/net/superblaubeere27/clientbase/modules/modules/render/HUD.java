/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HUD extends Module {
    @NotNull
    private TabGui<Module> tabGui = new TabGui<Module>();
    @NotNull
    private List<Integer> fps = new ArrayList<Integer>();

    @NotNull
    private NumberValue<Integer> fpsStatisticLength = new NumberValue<Integer>("FPSStatisticLength", 250, 10, 500);

    // Since we are using a JRE > 10 to run the client this classes will be available at runtime.
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public HUD() {
        super("HUD", "The Overlay", ModuleCategory.RENDER, false, true, Keyboard.KEY_NONE);
        setState(true);


        HashMap<ModuleCategory, java.util.List<Module>> moduleCategoryMap = new HashMap<ModuleCategory, List<Module>>();

        for (Module module : ClientBase.INSTANCE.moduleManager.getModules()) {
            if (!moduleCategoryMap.containsKey(module.getCategory())) {
                moduleCategoryMap.put(module.getCategory(), new ArrayList<Module>());
            }

            moduleCategoryMap.get(module.getCategory()).add(module);
        }

        List<Map.Entry<ModuleCategory, List<Module>>> toSort = new ArrayList<Map.Entry<ModuleCategory, List<Module>>>();
        for (Map.Entry<ModuleCategory, List<Module>> cat : moduleCategoryMap.entrySet()) {
            toSort.add(cat);
        }
        Collections.sort(toSort, new Comparator<Map.Entry<ModuleCategory, List<Module>>>() {
            @Override
            public int compare(Map.Entry<ModuleCategory, List<Module>> o1, Map.Entry<ModuleCategory, List<Module>> o2) {
                int x = o1.getKey().toString().hashCode();
                int y = o2.getKey().toString().hashCode();

                return (x < y) ? -1 : ((x == y) ? 0 : 1);
            }
        });
        for (Map.Entry<ModuleCategory, List<Module>> cat : toSort) {
            Tab<Module> tab = new Tab<Module>(cat.getKey().toString());

            for (Module module : cat.getValue()) {
                tab.addSubTab(new SubTab<Module>(module.getName(), new SubTab.TabActionListener<Module>() {
                    @Override
                    public void onClick(SubTab<Module> subTab) {
                        subTab.getObject().setState(!subTab.getObject().getState());
                    }
                }, module));
            }

            tabGui.addTab(tab);
        }

    }

    private static int rainbow(int delay) {
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

        final FontRenderer fontRenderer = mc.fontRendererObj;

        ScaledResolution res = new ScaledResolution(mc);

        int blackBarHeight = fontRenderer.FONT_HEIGHT * 2 + 4;

        Gui.drawRect(0, res.getScaledHeight() - blackBarHeight, res.getScaledWidth(), res.getScaledHeight(), GLUtil.toRGBA(new Color(0, 0, 0, 150)));

        GL11.glScaled(2.0, 2.0, 2.0);
        int i = fontRenderer.drawString(ClientBase.CLIENT_NAME, 2, 2, rainbow(0), true);
        int initialSize = fontRenderer.drawString(ClientBase.CLIENT_INITIALS, 1, res.getScaledHeight() / 2.0f - fontRenderer.FONT_HEIGHT, rainbow(0), true);
        GL11.glScaled(0.5, 0.5, 0.5);

        fontRenderer.drawString(ClientBase.CLIENT_VERSION, i * 2, fontRenderer.FONT_HEIGHT * 2 - 7, rainbow(100), true);
        fontRenderer.drawString("by " + ClientBase.CLIENT_AUTHOR, 4, fontRenderer.FONT_HEIGHT * 2 + 2, rainbow(200), true);

        double currSpeed = Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);

        int fpsWidth = fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), initialSize * 2 + 2, res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 2, -1, true);
        fpsWidth = Math.max(fpsWidth, fontRenderer.drawString(String.format("BPS: %.2f", currSpeed), initialSize * 2 + 2, res.getScaledHeight() - fontRenderer.FONT_HEIGHT * 2 - 2, -1, true));


        LocalDateTime now = LocalDateTime.now();
        String date = dateFormat.format(now);
        String time = timeFormat.format(now);


        fontRenderer.drawString(date, res.getScaledWidth() - fontRenderer.getStringWidth(date), res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 2, -1, true);
        fontRenderer.drawString(time, res.getScaledWidth() - fontRenderer.getStringWidth(time), res.getScaledHeight() - fontRenderer.FONT_HEIGHT * 2 - 2, -1, true);


        AtomicInteger offset = new AtomicInteger(3);
        AtomicInteger index = new AtomicInteger();

        List<Module> toSort = new ArrayList<Module>();
        for (Module mod : ClientBase.INSTANCE.moduleManager.getModules()) {
            if (mod.getState() && !mod.isHidden()) {
                toSort.add(mod);
            }
        }

        Collections.sort(toSort, new Comparator<Module>() {
            @Override
            public int compare(Module o1, Module o2) {
                int x = -fontRenderer.getStringWidth(o1.getName());
                int y = -fontRenderer.getStringWidth(o2.getName());

                return (x < y) ? -1 : ((x == y) ? 0 : 1);
            }
        });
        for (Module mod : toSort) {
            fontRenderer.drawString(mod.getName(), res.getScaledWidth() - fontRenderer.getStringWidth(mod.getName()) - 3, offset.get(), rainbow(index.get() * 100), true);

            offset.addAndGet(fontRenderer.FONT_HEIGHT + 2);
            index.getAndIncrement();
        }

        NotificationManager.render();
        tabGui.render(5, (2 + fontRenderer.FONT_HEIGHT) * 3);


        boolean seen = false;
        Integer best = null;
        for (Integer fp : fps) {
            if (!seen || fp.compareTo(best) > 0) {
                seen = true;
                best = fp;
            }
        }
        int max = seen ? best : 1;
        double transform = blackBarHeight / 2.0 / (double) max;

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glLineWidth(1.0f);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        GL11.glBegin(GL11.GL_LINE_STRIP);

        fpsWidth += 3;

        double v = ((res.getScaledWidth() / 2.0 - 100) - fpsWidth) / (double) fps.size();

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
