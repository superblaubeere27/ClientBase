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

import net.minecraft.client.gui.FontRenderer;

import java.util.List;
import java.util.Random;

public class WrapperFontRenderer {
    private FontRenderer real;

    public WrapperFontRenderer(FontRenderer var1) {
        this.real = var1;
    }

    public static String getFormatFromString(String var0) {
        return FontRenderer.getFormatFromString(var0);
    }

    public FontRenderer unwrap() {
        return this.real;
    }

    public int drawStringWithShadow(String var1, float var2, float var3, int var4) {
        return this.real.drawStringWithShadow(var1, var2, var3, var4);
    }

    public int drawString(String var1, int var2, int var3, int var4) {
        return this.real.drawString(var1, var2, var3, var4);
    }

    public int drawString(String var1, float var2, float var3, int var4, boolean var5) {
        return this.real.drawString(var1, var2, var3, var4, var5);
    }

    public int getStringWidth(String var1) {
        return this.real.getStringWidth(var1);
    }

    public int getCharWidth(char var1) {
        return this.real.getCharWidth(var1);
    }

    public String trimStringToWidth(String var1, int var2) {
        return this.real.trimStringToWidth(var1, var2);
    }

    public String trimStringToWidth(String var1, int var2, boolean var3) {
        return this.real.trimStringToWidth(var1, var2, var3);
    }

    public void drawSplitString(String var1, int var2, int var3, int var4, int var5) {
        this.real.drawSplitString(var1, var2, var3, var4, var5);
    }

    public int splitStringWidth(String var1, int var2) {
        return this.real.splitStringWidth(var1, var2);
    }

    public boolean getUnicodeFlag() {
        return this.real.getUnicodeFlag();
    }

    public void setUnicodeFlag(boolean var1) {
        this.real.setUnicodeFlag(var1);
    }

    public List listFormattedStringToWidth(String var1, int var2) {
        return this.real.listFormattedStringToWidth(var1, var2);
    }

    public boolean getBidiFlag() {
        return this.real.getBidiFlag();
    }

    public void setBidiFlag(boolean var1) {
        this.real.setBidiFlag(var1);
    }

    public int getColorCode(char var1) {
        return this.real.getColorCode(var1);
    }

    public int getFONT_HEIGHT() {
        return this.real.FONT_HEIGHT;
    }

    public void setFONT_HEIGHT(int var1) {
        this.real.FONT_HEIGHT = var1;
    }

    public Random getFontRandom() {
        return this.real.fontRandom;
    }

    public void setFontRandom(Random var1) {
        this.real.fontRandom = var1;
    }
}
