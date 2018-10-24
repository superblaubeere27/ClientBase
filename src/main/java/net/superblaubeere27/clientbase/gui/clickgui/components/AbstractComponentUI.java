package net.superblaubeere27.clientbase.gui.clickgui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public abstract class AbstractComponentUI<T> {
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected boolean shown = false;
    protected T value;
    protected FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;

    public AbstractComponentUI(int width, int height, int x, int y, T value) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public abstract void draw();

    public abstract void onMouseClick(int x, int y, int mouseButton);

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract void update();
}
