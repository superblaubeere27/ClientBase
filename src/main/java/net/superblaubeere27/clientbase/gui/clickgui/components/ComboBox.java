/*
 * Copyright 2019 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.gui.clickgui.components;

import net.superblaubeere27.clientbase.gui.clickgui.AbstractComponent;
import net.superblaubeere27.clientbase.gui.clickgui.IRenderer;
import net.superblaubeere27.clientbase.gui.clickgui.Window;

public class ComboBox extends AbstractComponent {
    private static final int PREFERRED_WIDTH = 180;
    private static final int PREFERRED_HEIGHT = 22;

    private int preferredWidth;
    private int preferredHeight;
    private boolean hovered;
    private boolean hoveredExtended;
    private ValueChangeListener<Integer> listener;
    private String[] values;
    private int selectedIndex;

    private boolean opened;
    private int mouseX;
    private int mouseY;

    public ComboBox(IRenderer renderer, int preferredWidth, int preferredHeight, String[] values, int selectedIndex) {
        super(renderer);

        this.preferredWidth = preferredWidth;
        this.preferredHeight = preferredHeight;

        this.values = values;
        this.selectedIndex = selectedIndex;

        setWidth(preferredWidth);
        updateHeight();
    }

    public ComboBox(IRenderer renderer, String[] values, int selectedIndex) {
        this(renderer, PREFERRED_WIDTH, PREFERRED_HEIGHT, values, selectedIndex);
    }

    private void updateHeight() {
        if (opened) setHeight(preferredHeight * values.length + 4);
        else setHeight(preferredHeight);
    }

    @Override
    public void render() {
        updateHeight();

        renderer.drawRect(x, y, getWidth(), getHeight(), Window.TERTIARY_FOREGROUND);

        if (hovered)
            renderer.drawRect(x, y, getWidth(), preferredHeight, Window.SECONDARY_FOREGROUND);
        else if (hoveredExtended) {
            int offset = preferredHeight + 4;

            for (int i = 0; i < values.length; i++) {
                if (i == selectedIndex)
                    continue;

                int height = preferredHeight;

                if ((selectedIndex == 0 ? i == 1 : i == 0)
                        || (selectedIndex == values.length - 1 ? i == values.length - 2
                        : i == values.length - 1))
                    height++;

                if (mouseY >= getY() + offset
                        && mouseY <= getY() + offset + preferredHeight) {
                    renderer.drawRect(x, y + offset, getWidth(), preferredHeight, Window.SECONDARY_FOREGROUND);
                    break;
                }
                offset += height;
            }
        }
        // Draw triangle background
        renderer.drawRect(x + getWidth() - preferredHeight, y, preferredHeight, getHeight(), (hovered || opened) ? Window.TERTIARY_FOREGROUND : Window.SECONDARY_FOREGROUND);
        // Draw triangle
//        renderer.drawTriangle(
//                x + getWidth() - getHeight() + getHeight() / 4.0, y + getHeight() / 4.0,
//                x + getWidth() - getHeight() + getHeight() / 2.0, y + getHeight() / 4.0 + getHeight() / 2.0,
//                x + getWidth() - getHeight() + getHeight() / 4.0, y + getHeight() / 4.0,
//                Window.FOREGROUND);

        renderer.drawTriangle(
                x + getWidth() - preferredHeight + preferredHeight / 4.0, y + preferredHeight / 4.0,
                x + getWidth() - preferredHeight + preferredHeight / 2.0, y + preferredHeight * 3.0 / 4.0,
                x + getWidth() - preferredHeight + preferredHeight * 3.0 / 4.0, y + preferredHeight / 4.0,
                Window.FOREGROUND);

        renderer.drawOutline(x, y, getWidth(), getHeight(), 1.0f, (hovered && !opened) ? Window.SECONDARY_OUTLINE : Window.SECONDARY_FOREGROUND);

        String text = values[selectedIndex];

        renderer.drawString(x + 4, y + renderer.getStringHeight(text) / 4, text, Window.FOREGROUND);


        if (opened) {
            int offset = preferredHeight + 4;

            for (int i = 0; i < values.length; i++) {
                if (i == selectedIndex)
                    continue;

                renderer.drawString(x + 4, y + offset, values[i], Window.FOREGROUND);

                offset += preferredHeight;
            }
        }
    }

    @Override
    public boolean mouseMove(int x, int y, boolean offscreen) {
        updateHovered(x, y, offscreen);

        return false;
    }

    private void updateHovered(int x, int y, boolean offscreen) {
        hovered = !offscreen && x >= this.x && y >= this.y && x <= this.x + getWidth() && y <= this.y + preferredHeight;
        hoveredExtended = !offscreen && x >= this.x && y >= this.y && x <= this.x + getWidth() && y <= this.y + getHeight();

        mouseX = x;
        mouseY = y;
    }

    @Override
    public boolean mousePressed(int button, int x, int y, boolean offscreen) {
        updateHovered(x, y, offscreen);

        if (button != 0)
            return false;

        if (hovered) {
            setOpened(!opened);
            updateHeight();
            return true;
        }
        if (hoveredExtended && opened) {
            int offset = this.y + preferredHeight + 4;

            for (int i = 0; i < values.length; i++) {
                if (i == selectedIndex)
                    continue;

                if (y >= offset
                        && y <= offset
                        + preferredHeight) {
                    setSelectedChecked(i);
                    setOpened(false);
                    break;
                }
                offset += preferredHeight;
            }
            updateHovered(x, y, offscreen);
            return true;
        }
        return false;
    }

    private void setSelectedChecked(int i) {
        boolean change = true;

        if (listener != null) {
            change = listener.onValueChange(i);
        }
        if (change) selectedIndex = i;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public void setListener(ValueChangeListener<Integer> listener) {
        this.listener = listener;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
        updateHeight();
    }
}
