/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.gui.clickgui;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.superblaubeere27.clientbase.ClientBase;
import net.superblaubeere27.clientbase.modules.ModuleCategory;
import net.superblaubeere27.clientbase.utils.GLUtil;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends GuiScreen {
    @NotNull
    public static Color PANEL_MAIN_COLOR = new Color(0, 0, 0, 200);
    @NotNull
    public static Color PANEL_SECONDARY_COLOR = new Color(0x4286F4);
    @NotNull
    private List<Panel> panels = new ArrayList<>();

    public ClickGUI() {
        int x = 202;

        for (final ModuleCategory moduleCategory : ModuleCategory.values()) {
            final Panel panel = new Panel(moduleCategory.toString(), x, 50, 100);
            ClientBase.INSTANCE.moduleManager.getModules().stream().filter(module -> module.getCategory() == moduleCategory).forEach(module -> panel.addButton(new Button(panel, module)));

            if (panel.getButtons().size() > 0) panels.add(panel);


        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for (final Panel panel : panels) {
            // Draw panel
            Gui.drawRect(panel.getX() - 2, panel.getY() - 2, panel.getX() + panel.getWidth() + 2, panel.getY() + 20, PANEL_SECONDARY_COLOR.hashCode());


            GL11.glLineWidth(panel.isDrag() ? 2.0f : 1.0f);

            GLUtil.drawRect(GL11.GL_LINE_LOOP, panel.getX() - 2, panel.getY() - 2, panel.getX() + panel.getWidth() + 2, panel.getY() + 20, PANEL_MAIN_COLOR.hashCode());
            fontRendererObj.drawString(panel.getPanelName(), panel.getX() + 2, panel.getY() + 5, 0xffffff);

            for (int i = 0; i < panel.getButtons().size(); i++) {
                final Button button = panel.getButtons().get(i);
                Gui.drawRect(panel.getX(), panel.getY() + 20 + (20 * i), panel.getX() + panel.getWidth(), panel.getY() + (20 * i) + 40, PANEL_MAIN_COLOR.getRGB());
                fontRendererObj.drawString((!button.getModule().getState() ? "§7" : "§r") + button.getModule().getName(), panel.getX() + 2, panel.getY() + 20 + (20 * i) + 7, 0xffffff);
                button.renderExtended(panel.getX() + panel.getWidth(), panel.getY() + 20 + (20 * i) + 7);
            }

            // Drag panel
            if (panel.isDrag()) {
                panel.setX(mouseX + panel.getDragX());
                panel.setY(mouseY + panel.getDragY());
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton != 0 && mouseButton != 1) {
            return;
        }

        for (Panel panel : panels) {
            for (Button button : panel.getButtons()) {
                button.onMouseClick(mouseX, mouseY, mouseButton);
            }
        }


        for (int index = panels.size() - 1; index >= 0; index--) {
            final Panel panel = panels.get(index);


            // Drag panel
            if (panel.isHoverHead(mouseX, mouseY) && mouseButton == 0) {
                panel.setDrag(true);
                panel.setDragX(panel.getX() - mouseX);
                panel.setDragY(panel.getY() - mouseY);
                panels.remove(panel);
                panels.add(panel);
                break;
            }

            //
            for (int buttonIndex = 0; buttonIndex < panel.getButtons().size(); buttonIndex++) {
                final Button button = panel.getButtons().get(buttonIndex);

                if (button.isHover(panel.getX(), panel.getY() + 20 + (20 * buttonIndex), panel.getWidth(), 20, mouseX, mouseY) && mouseButton == 0) {
                    button.getModule().setState(!button.getModule().getState());
                    this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("random.bow"), 1.0f));
                }
                if (button.isHover(panel.getX(), panel.getY() + 20 + (20 * buttonIndex), panel.getWidth(), 20, mouseX, mouseY) && mouseButton == 1) {
                    button.setExtended(!button.isExtended());
                    this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("random.bow"), 1.0f));
                }
            }
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (final Panel panel : panels) {
            panel.setDrag(false);
        }

        super.mouseReleased(mouseX, mouseY, state);
    }
}