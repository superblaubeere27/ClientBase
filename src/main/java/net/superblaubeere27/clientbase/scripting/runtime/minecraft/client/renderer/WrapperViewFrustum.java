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

import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;

public class WrapperViewFrustum {
    private ViewFrustum real;

    public WrapperViewFrustum(ViewFrustum var1) {
        this.real = var1;
    }

    public ViewFrustum unwrap() {
        return this.real;
    }

    public void deleteGlResources() {
        this.real.deleteGlResources();
    }

    public void markBlocksForUpdate(int var1, int var2, int var3, int var4, int var5, int var6) {
        this.real.markBlocksForUpdate(var1, var2, var3, var4, var5, var6);
    }

    public RenderChunk[] getRenderChunks() {
        return this.real.renderChunks;
    }

    public void setRenderChunks(RenderChunk[] var1) {
        this.real.renderChunks = var1;
    }
}
