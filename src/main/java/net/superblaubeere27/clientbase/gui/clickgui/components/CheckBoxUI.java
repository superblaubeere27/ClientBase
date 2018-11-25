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
import net.superblaubeere27.clientbase.valuesystem.BooleanValue;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class CheckBoxUI extends AbstractComponentUI<BooleanValue> {

    public CheckBoxUI(BooleanValue value) {
        super(-1, -1, 0, 0, value);
    }

    @Override
    public void draw() {
        glTranslated(x, y, 0);
        Rectangle area = new Rectangle(x, y, width, height);
        glEnable(GL_BLEND);
        glDisable(GL_CULL_FACE);

        GLUtil.setColor(ClickGUI.PANEL_MAIN_COLOR);

        int size = area.height - 4;

//        Gui.drawRect(1, 1, size + 2, size + 2, ClickGUI.PANEL_MAIN_COLOR.getRGB());

        if (getValue().getObject()) {
            GLUtil.setColor(ClickGUI.PANEL_SECONDARY_COLOR);
            GLUtil.drawRect(GL11.GL_QUADS, 2, 2, size + 2, size + 2, ClickGUI.PANEL_SECONDARY_COLOR.getRGB());
        }

        glLineWidth(2.0f);

        GLUtil.drawRect(GL11.GL_LINE_LOOP, 2, 2, size + 2, size + 2, ClickGUI.PANEL_MAIN_COLOR.getRGB());
        Point mouse = Utils.calculateMouseLocation();

        if (area.contains(mouse)) {
            glColor4f(0.0f, 0.0f, 0.0f, Mouse.isButtonDown(0) ? 0.5f : 0.3f);
            Gui.drawRect(0, 0, area.width, area.height, GLUtil.toRGBA(new Color(0, 0, 0, Mouse.isButtonDown(0) ? 125 : 70)));
        }

        GLUtil.setColor(Color.white);

        String text = getValue().getName();
        fontRenderer.drawString(text, size + 6,
                area.height / 2 - fontRenderer.FONT_HEIGHT / 2 + 1,
                GLUtil.toRGBA(Color.white));

        glEnable(GL_CULL_FACE);
        glDisable(GL_BLEND);
        glTranslated(-x, -y, 0);
    }

    @Override
    public void onMouseClick(int x, int y, int mouseButton) {
        Point mouse = new Point(x, y);
        Rectangle area = new Rectangle(getX(), getY(), width, height);

        if (area.contains(mouse) && mouseButton == 0) {
            value.setObject(!value.getObject());
        }
    }

    @Override
    public void update() {
        width = fontRenderer.getStringWidth(value.getName()) + fontRenderer.FONT_HEIGHT + 8;
        height = fontRenderer.FONT_HEIGHT + 4;
    }
}
