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

import net.minecraft.client.gui.GuiSlider;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.WrapperMinecraft;

public class WrapperGuiSlider extends WrapperGuiButton {
    private GuiSlider real;

    public WrapperGuiSlider(GuiSlider var1) {
        super(var1);
        this.real = var1;
    }

    public GuiSlider unwrap() {
        return this.real;
    }

    public float func_175220_c() {
        return this.real.func_175220_c();
    }

    public void func_175218_a(float var1, boolean var2) {
        this.real.func_175218_a(var1, var2);
    }

    public float func_175217_d() {
        return this.real.func_175217_d();
    }

    public void func_175219_a(float var1) {
        this.real.func_175219_a(var1);
    }

    public boolean mousePressed(WrapperMinecraft var1, int var2, int var3) {
        return this.real.mousePressed(var1.unwrap(), var2, var3);
    }

    public void mouseReleased(int var1, int var2) {
        this.real.mouseReleased(var1, var2);
    }

    public boolean IsMouseDown() {
        return this.real.isMouseDown;
    }

    public void setIsMouseDown(boolean var1) {
        this.real.isMouseDown = var1;
    }
}
