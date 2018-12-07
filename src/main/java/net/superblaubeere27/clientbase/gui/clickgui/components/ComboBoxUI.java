/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.gui.clickgui.components;

import net.minecraft.client.gui.Gui;
import net.superblaubeere27.clientbase.gui.clickgui.ClickGUI;
import net.superblaubeere27.clientbase.utils.GLUtil;
import net.superblaubeere27.clientbase.utils.Utils;
import net.superblaubeere27.clientbase.valuesystem.ModeValue;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class ComboBoxUI extends AbstractComponentUI<ModeValue> {
    private boolean isSelected = false;
    private int width = 0;
    private int height;

    public ComboBoxUI(ModeValue value) {
        super(-1, -1, 0, 0, value);

        height = fontRenderer.FONT_HEIGHT + 4;
    }

    @Override
    public void draw() {
        GL11.glPushMatrix();
        glTranslated(x + 2, y, 0);
        Rectangle area = new Rectangle(x, y, width, height);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_CULL_FACE);

        int extendedHeight = 0;

        if (isSelected) {
            String[] elements = value.getModes();
            for (int i = 0; i < elements.length - 1; i++)
                extendedHeight += fontRenderer.FONT_HEIGHT + 2;
            extendedHeight += 2;
        }

        GLUtil.setColor(ClickGUI.PANEL_SECONDARY_COLOR);

        GL11.glLineWidth(2.0f);

        GLUtil.drawRect(GL11.GL_LINE_LOOP, 0, 0, area.width, area.height + extendedHeight, ClickGUI.PANEL_SECONDARY_COLOR.getRGB());
        GLUtil.setColor(ClickGUI.PANEL_MAIN_COLOR);

        Gui.drawRect(0, 0, area.width, area.height + extendedHeight, GLUtil.toRGBA(ClickGUI.PANEL_MAIN_COLOR));
        Point mouse = Utils.calculateMouseLocation();
        glColor4f(0.0f, 0.0f, 0.0f, Mouse.isButtonDown(0) ? 0.5f : 0.3f);

        if (area.contains(mouse)) {
            Gui.drawRect(0, 0, area.width, area.height, GLUtil.toRGBA(new Color(0, 0, 0, Mouse.isButtonDown(0) ? 125 : 70)));
        } else if (isSelected && mouse.x >= area.x
                && mouse.x <= area.x + area.width) {
            int offset = height;
            String[] elements = value.getModes();
            for (int i = 0; i < elements.length; i++) {
                if (i == getSelectedIndex())
                    continue;
                int height = fontRenderer.FONT_HEIGHT + 2;
                if ((getSelectedIndex() == 0 ? i == 1 : i == 0)
                        || (getSelectedIndex() == elements.length - 1 ? i == elements.length - 2
                        : i == elements.length - 1))
                    height++;
                if (mouse.y >= area.y + offset
                        && mouse.y <= area.y + offset + height) {
                    Gui.drawRect(0, offset, area.width, area.height + offset - 1, GLUtil.toRGBA(new Color(0, 0, 0, Mouse.isButtonDown(0) ? 125 : 70)));
                    break;
                }
                offset += height;
            }
        }
        glColor4f(0.0f, 0.0f, 0.0f, 1.0f);


        glLineWidth(2.0f);

        if (isSelected) {
            glBegin(GL_LINES);
            {
                glVertex2d(2, area.height);
                glVertex2d(area.width - 2, area.height);
            }
            glEnd();
        }

        GLUtil.setColor(Color.white);

        int index = value.getObject();

        fontRenderer.drawString(value.getModes()[index], 2,
                area.height / 2 - fontRenderer.FONT_HEIGHT / 2,
                GLUtil.toRGBA(Color.white));

        if (isSelected) {
            int offset = area.height + 2;
            String[] elements = value.getModes();
            for (int i = 0; i < elements.length; i++) {
                if (i == getSelectedIndex())
                    continue;

                GLUtil.setColor(Color.white);
                fontRenderer.drawString(elements[i], 2, offset,
                        GLUtil.toRGBA(Color.white));
                offset += fontRenderer.FONT_HEIGHT + 2;
            }
        }

        glEnable(GL_CULL_FACE);
        glDisable(GL_BLEND);
        GL11.glPopMatrix();
    }

    private int getSelectedIndex() {
        return value.getObject();
    }

    @Override
    public void update() {
        int width = 0;
        for (String element : value.getModes())
            width = Math.max(width, fontRenderer.getStringWidth(element));

        this.width = width + 8 + fontRenderer.FONT_HEIGHT;
        super.width = width + 12 + fontRenderer.FONT_HEIGHT;

        if (isSelected) {
            super.height = 0;
            for (String ignored : value.getModes()) {
                height += fontRenderer.FONT_HEIGHT + 2;
            }
            super.height = height;
        } else {
            super.height = fontRenderer.FONT_HEIGHT + 4;
        }
        height = fontRenderer.FONT_HEIGHT + 4;
    }


//    @Override
//    protected void handleComponentInteraction(ComboBox component,
//                                              Point location, int button) {
//
//    }

    @Override
    public boolean onMouseClick(int x, int y, int mouseButton) {
        Point mouse = new Point(x, y);
        Rectangle area = new Rectangle(getX(), getY(), width, height);

        if (mouseButton != 0)
            return false;

        if (area.contains(mouse)) {
            isSelected = !isSelected;
            return true;
        } else if (mouse.x <= area.getWidth() + getX() && isSelected) {
            int offset = (int) (area.getHeight() + getY() + 2);
            String[] elements = value.getModes();
            for (int i = 0; i < elements.length; i++) {
                if (i == getSelectedIndex())
                    continue;

                if (mouse.y >= offset
                        && mouse.y <= offset
                        + fontRenderer.FONT_HEIGHT) {
                    value.setObject(i);
                    isSelected = false;
                    break;
                }
                offset += fontRenderer.FONT_HEIGHT + 2;
            }
            return true;
        }
        return false;
    }
}
