/*
 * Copyright 2019 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.gui.clickgui;

public abstract class AbstractComponent {
    protected int x;
    protected int y;
    protected IRenderer renderer;
    private int width;
    private int height;
    private boolean sizeChanged;

    public AbstractComponent(IRenderer renderer) {
        this.renderer = renderer;
    }

    public abstract void render();

    public int getEventPriority() {
        return 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (this.x != x) setSizeChanged(true);

        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (this.y != y) setSizeChanged(true);

        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (this.width != width) setSizeChanged(true);

        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (this.height != height) setSizeChanged(true);

        this.height = height;
    }

    public boolean isSizeChanged() {
        return sizeChanged;
    }

    public void setSizeChanged(boolean sizeChanged) {
        this.sizeChanged = sizeChanged;
    }

    public boolean keyPressed(int key, char c) {
        return false;
    }

    public boolean mouseReleased(int button, int x, int y, boolean offscreen) {
        return false;
    }

    public boolean mouseMove(int x, int y, boolean offscreen) {
        return false;
    }

    public boolean mousePressed(int button, int x, int y, boolean offscreen) {
        return false;
    }

    public boolean mouseWheel(int change) {
        return false;
    }

}
