/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.WrapperMinecraft;

public class WrapperGuiButton extends WrapperGui {
    private GuiButton real;

    public WrapperGuiButton(GuiButton var1) {
        super(var1);
        this.real = var1;
    }

    public GuiButton unwrap() {
        return this.real;
    }

    public void drawButton(WrapperMinecraft var1, int var2, int var3) {
        this.real.drawButton(var1.unwrap(), var2, var3);
    }

    public void mouseReleased(int var1, int var2) {
        this.real.mouseReleased(var1, var2);
    }

    public boolean mousePressed(WrapperMinecraft var1, int var2, int var3) {
        return this.real.mousePressed(var1.unwrap(), var2, var3);
    }

    public boolean isMouseOver() {
        return this.real.isMouseOver();
    }

    public void drawButtonForegroundLayer(int var1, int var2) {
        this.real.drawButtonForegroundLayer(var1, var2);
    }

    public int getButtonWidth() {
        return this.real.getButtonWidth();
    }

    public void setWidth(int var1) {
        this.real.setWidth(var1);
    }

    public int getXPosition() {
        return this.real.xPosition;
    }

    public void setXPosition(int var1) {
        this.real.xPosition = var1;
    }

    public int getYPosition() {
        return this.real.yPosition;
    }

    public void setYPosition(int var1) {
        this.real.yPosition = var1;
    }

    public String getDisplayString() {
        return this.real.displayString;
    }

    public void setDisplayString(String var1) {
        this.real.displayString = var1;
    }

    public int getId() {
        return this.real.id;
    }

    public void setId(int var1) {
        this.real.id = var1;
    }

    public boolean isEnabled() {
        return this.real.enabled;
    }

    public void setEnabled(boolean var1) {
        this.real.enabled = var1;
    }

    public boolean isVisible() {
        return this.real.visible;
    }

    public void setVisible(boolean var1) {
        this.real.visible = var1;
    }
}
