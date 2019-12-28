/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.gui.tabgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class TabGui<T> {
    static final int OFFSET = 3;
    @NotNull
    static Color BACKGROUND = new Color(0, 0, 0, 175);
    @NotNull
    static Color BORDER = new Color(0, 0, 0, 255);
    @NotNull
    static Color SELECTED = new Color(38, 164, 78, 200);
    static Color FOREGROUND = Color.white;
    @NotNull
    private List<Tab<T>> tabs = new ArrayList<Tab<T>>();
    private int selectedTab = 0;
    private int selectedSubTab = -1;

    public static void drawRect(int glFlag, int left, int top, int right, int bottom, int color) {
        if (left < right) {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            int j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(glFlag, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double) left, (double) bottom, 0.0D).endVertex();
        worldrenderer.pos((double) right, (double) bottom, 0.0D).endVertex();
        worldrenderer.pos((double) right, (double) top, 0.0D).endVertex();
        worldrenderer.pos((double) left, (double) top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public void addTab(Tab<T> tab) {
        tabs.add(tab);
    }

    public void render(int x, int y) {
        glTranslated(x, y, 0);

        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

        int height = (font.FONT_HEIGHT + OFFSET) * tabs.size();

        int width = 0;

        for (Tab<T> tab : tabs) {
            if (font.getStringWidth(tab.getText()) > width) {
                width = font.getStringWidth(tab.getText());
            }
        }

        width += 2 + 2;

        drawRect(GL_QUADS, 0, 0, width, height, BACKGROUND.getRGB());


        int offset = 2;

        int i = 0;

        for (Tab<T> tab : tabs) {
            if (selectedTab == i) {
                drawRect(GL_QUADS, 0, offset - 2, width, offset + font.FONT_HEIGHT + OFFSET - 2, SELECTED.getRGB());

                if (selectedSubTab != -1) {
                    tab.renderSubTabs(width, offset - 2, selectedSubTab);
                }
            }

            font.drawString(tab.getText(), 2, offset, FOREGROUND.getRGB());
            offset += font.FONT_HEIGHT + OFFSET;
            i++;
        }
        glLineWidth(1.0f);
        drawRect(GL_LINE_LOOP, 0, 0, width, height, BORDER.getRGB());

        glTranslated(-x, -y, 0);
    }

    public void handleKey(int keycode) {
        if (keycode == Keyboard.KEY_DOWN) {
            if (selectedSubTab == -1) {
                selectedTab++;

                if (selectedTab >= tabs.size()) {
                    selectedTab = 0;
                }
            } else {
                selectedSubTab++;

                if (selectedSubTab >= tabs.get(selectedTab).getSubTabs().size()) {
                    selectedSubTab = 0;
                }
            }
        } else if (keycode == Keyboard.KEY_UP) {
            if (selectedSubTab == -1) {
                selectedTab--;

                if (selectedTab < 0) {
                    selectedTab = tabs.size() - 1;
                }
            } else {
                selectedSubTab--;

                if (selectedSubTab < 0) {
                    selectedSubTab = tabs.get(selectedTab).getSubTabs().size() - 1;
                }
            }
        } else if (keycode == Keyboard.KEY_LEFT) {
            selectedSubTab = -1;
        } else if (selectedSubTab == -1 && (keycode == Keyboard.KEY_RETURN || keycode == Keyboard.KEY_RIGHT)) {
            selectedSubTab = 0;
        } else if (keycode == Keyboard.KEY_RETURN || keycode == Keyboard.KEY_RIGHT) {
            tabs.get(selectedTab).getSubTabs().get(selectedSubTab).press();
        }
    }


}
