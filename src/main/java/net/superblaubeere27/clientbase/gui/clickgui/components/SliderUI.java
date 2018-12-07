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
import net.superblaubeere27.clientbase.valuesystem.NumberValue;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class SliderUI extends AbstractComponentUI<NumberValue> {
    private boolean isValueChanging = false;

    public SliderUI(NumberValue value) {
        super(0, 0, 0, 0, value);
    }

    @Override
    public void draw() {
        GL11.glPushMatrix();
        glTranslatef(x, y, 0);
        glEnable(GL_BLEND);
        glDisable(GL_CULL_FACE);

        final Rectangle area = new Rectangle(x, y, getWidth(), getHeight());

//        GLUtil.drawRect(GL11.GL_LINE_LOOP, 1, 1, area.width, area.height, GLUtil.toRGBA(ClickGUI.PANEL_MAIN_COLOR));

        glTranslatef(2, 2, 0);

        final int fontSize = fontRenderer.FONT_HEIGHT;
        GLUtil.setColor(Color.white);
        fontRenderer.drawString(getValue().getName(), 0, 0, GLUtil.toRGBA(Color.white));
        String content = null;

        if (getValue().getObject() instanceof Integer || getValue().getObject() instanceof Long) {
            content = getValue().getObject().toString();
        } else if (getValue().getObject() instanceof Float || getValue().getObject() instanceof Double) {
            content = String.format("%.2f", (Number) getValue().getObject());
        }

        if (content != null) {
            GLUtil.setColor(Color.white);
            fontRenderer.drawString(content, getWidth() - fontRenderer.getStringWidth(content) - 3, 0,
                    GLUtil.toRGBA(Color.white));
        }

        GLUtil.setColor(ClickGUI.PANEL_MAIN_COLOR);
        glLineWidth(0.9f);


        final double sliderPercentage = (((Number) getValue().getObject()).doubleValue() - getValue().getMin().doubleValue())
                / (getValue().getMax().doubleValue() - getValue().getMin().doubleValue());

        GLUtil.setColor(ClickGUI.PANEL_SECONDARY_COLOR);

        Gui.drawRect(0, fontSize + 2, (int) (area.width * sliderPercentage) - 2, area.height - 4, ClickGUI.PANEL_SECONDARY_COLOR.getRGB());
        GLUtil.drawRect(GL11.GL_LINE_LOOP, 0, fontSize + 2, (int) (area.width * sliderPercentage) - 2, area.height - 4, ClickGUI.PANEL_MAIN_COLOR.getRGB());


        GL11.glPopMatrix();
    }

    @Override
    public boolean onMouseClick(int x, int y, int mouseButton) {
        if (new Rectangle(getX(), fontRenderer.FONT_HEIGHT + 2 + getY(), getWidth(),
                getHeight() - fontRenderer.FONT_HEIGHT).contains(x, y) && mouseButton == 0) {
            if (Mouse.isButtonDown(0) && !isValueChanging) {
                isValueChanging = true;
            } else if (!Mouse.isButtonDown(0) && isValueChanging) {
                isValueChanging = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        if (isValueChanging) {
            final Point mouse = Utils.calculateMouseLocation();
            if (!Mouse.isButtonDown(0) || !new Rectangle(getX(), 0, getWidth(),
                    Integer.MAX_VALUE).contains(mouse)) {
                isValueChanging = false;
                return;
            }
            mouse.translate(-getX(), -getY());
            final double percent = (double) mouse.x / (double) getWidth();
            final double value = getValue().getMin().doubleValue()
                    - percent * (getValue().getMin().doubleValue() - getValue().getMax().doubleValue());

            if (getValue().getObject() instanceof Integer) {
                getValue().setObject((int) Math.round(value));
            }
            if (getValue().getObject() instanceof Float) {
                getValue().setObject((float) value);
            }
            if (getValue().getObject() instanceof Double) {
                getValue().setObject(value);
            }
            if (getValue().getObject() instanceof Long) {
                getValue().setObject((long) value);
            }
        }
        width = Math.max(100, fontRenderer.getStringWidth(value.getName()) + 25);
        height = 12 + fontRenderer.FONT_HEIGHT;
    }
}
