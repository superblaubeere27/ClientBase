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

public class Button extends AbstractComponent {
    private static final int PREFERRED_WIDTH = 180;
    private static final int PREFERRED_HEIGHT = 22;

    private String title;
    private int preferredWidth;
    private int preferredHeight;
    private boolean hovered;
    private ActionEventListener listener;

    public Button(IRenderer renderer, String title, int preferredWidth, int preferredHeight) {
        super(renderer);

        this.preferredWidth = preferredWidth;
        this.preferredHeight = preferredHeight;

        setTitle(title);
    }

    public Button(IRenderer renderer, String title) {
        this(renderer, title, PREFERRED_WIDTH, PREFERRED_HEIGHT);
    }

    @Override
    public void render() {
        renderer.drawRect(x, y, getWidth(), getHeight(), hovered ? Window.SECONDARY_FOREGROUND : Window.TERTIARY_FOREGROUND);
        renderer.drawOutline(x, y, getWidth(), getHeight(), 1.0f, hovered ? Window.SECONDARY_OUTLINE : Window.SECONDARY_FOREGROUND);

        renderer.drawString(x + getWidth() / 2 - renderer.getStringWidth(title) / 2, y + renderer.getStringHeight(title) / 4, title, Window.FOREGROUND);
    }

    @Override
    public boolean mouseMove(int x, int y, boolean offscreen) {
        updateHovered(x, y, offscreen);

        return false;
    }

    private void updateHovered(int x, int y, boolean offscreen) {
        hovered = !offscreen && x >= this.x && y >= this.y && x <= this.x + getWidth() && y <= this.y + getHeight();
    }

    @Override
    public boolean mousePressed(int button, int x, int y, boolean offscreen) {
        if (button == 0) {
            updateHovered(x, y, offscreen);

            if (hovered && listener != null) {
                listener.onActionEvent();

                return true;
            }
        }

        return false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        setWidth(Math.max(renderer.getStringWidth(title), preferredWidth));
        setHeight(Math.max(renderer.getStringHeight(title) * 5 / 4, preferredHeight));
    }

    public ActionEventListener getOnClickListener() {
        return listener;
    }

    public void setOnClickListener(ActionEventListener listener) {
        this.listener = listener;
    }
}
