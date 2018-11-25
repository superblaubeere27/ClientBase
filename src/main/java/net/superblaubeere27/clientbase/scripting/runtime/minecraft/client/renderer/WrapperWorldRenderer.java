/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.renderer;

import net.minecraft.client.renderer.WorldRenderer;

import java.nio.ByteBuffer;

public class WrapperWorldRenderer {
    private WorldRenderer real;

    public WrapperWorldRenderer(WorldRenderer var1) {
        this.real = var1;
    }

    public WorldRenderer unwrap() {
        return this.real;
    }

    public void reset() {
        this.real.reset();
    }

    public WrapperWorldRenderer tex(double param1, double param3) {
        real.tex(param1, param3);
        return this;
    }

    public WrapperWorldRenderer lightmap(int var1, int var2) {
        return new WrapperWorldRenderer(this.real.lightmap(var1, var2));
    }

    public void putBrightness4(int var1, int var2, int var3, int var4) {
        this.real.putBrightness4(var1, var2, var3, var4);
    }

    public void putPosition(double param1, double param3, double param5) {
        real.putPosition(param1, param3, param5);
    }

    public void putColorMultiplier(float var1, float var2, float var3, int var4) {
        this.real.putColorMultiplier(var1, var2, var3, var4);
    }

    public void putColorRGB_F(float var1, float var2, float var3, int var4) {
        this.real.putColorRGB_F(var1, var2, var3, var4);
    }

    public WrapperWorldRenderer color(float var1, float var2, float var3, float var4) {
        return new WrapperWorldRenderer(this.real.color(var1, var2, var3, var4));
    }

    public WrapperWorldRenderer color(int var1, int var2, int var3, int var4) {
        return new WrapperWorldRenderer(this.real.color(var1, var2, var3, var4));
    }

    public void addVertexData(int[] var1) {
        this.real.addVertexData(var1);
    }

    public void endVertex() {
        this.real.endVertex();
    }

    public WrapperWorldRenderer pos(double param1, double param3, double param5) {
        real.pos(param1, param3, param5);

        return this;
    }

    public void putNormal(float var1, float var2, float var3) {
        this.real.putNormal(var1, var2, var3);
    }

    public WrapperWorldRenderer normal(float var1, float var2, float var3) {
        return new WrapperWorldRenderer(this.real.normal(var1, var2, var3));
    }

    public void setTranslation(double param1, double param3, double param5) {
        real.setTranslation(param1, param3, param5);
    }

    public void finishDrawing() {
        this.real.finishDrawing();
    }

    public ByteBuffer getByteBuffer() {
        return this.real.getByteBuffer();
    }

    public int getVertexCount() {
        return this.real.getVertexCount();
    }

    public int getDrawMode() {
        return this.real.getDrawMode();
    }

    public void putColor4(int var1) {
        this.real.putColor4(var1);
    }

    public void putColorRGB_F4(float var1, float var2, float var3) {
        this.real.putColorRGB_F4(var1, var2, var3);
    }
}
