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

import net.minecraft.client.gui.GuiMainMenu;

public class WrapperGuiMainMenu extends WrapperGuiScreen {
    private GuiMainMenu real;

    public WrapperGuiMainMenu(GuiMainMenu var1) {
        super(var1);
        this.real = var1;
    }

    public GuiMainMenu unwrap() {
        return this.real;
    }

    public void updateScreen() {
        this.real.updateScreen();
    }

    public boolean doesGuiPauseGame() {
        return this.real.doesGuiPauseGame();
    }

    public void initGui() {
        this.real.initGui();
    }

    public void confirmClicked(boolean var1, int var2) {
        this.real.confirmClicked(var1, var2);
    }

    public void drawScreen(int var1, int var2, float var3) {
        this.real.drawScreen(var1, var2, var3);
    }

    public String getField_96138_a() {
        return GuiMainMenu.field_96138_a;
    }
}
