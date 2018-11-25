/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.profiler;

import net.minecraft.profiler.Profiler;

import java.util.List;

public class WrapperProfiler {
    private Profiler real;

    public WrapperProfiler(Profiler var1) {
        this.real = var1;
    }

    public Profiler unwrap() {
        return this.real;
    }

    public void clearProfiling() {
        this.real.clearProfiling();
    }

    public void startSection(String var1) {
        this.real.startSection(var1);
    }

    public void endSection() {
        this.real.endSection();
    }

    public List getProfilingData(String var1) {
        return this.real.getProfilingData(var1);
    }

    public void endStartSection(String var1) {
        this.real.endStartSection(var1);
    }

    public String getNameOfLastSection() {
        return this.real.getNameOfLastSection();
    }

    public boolean isProfilingEnabled() {
        return this.real.profilingEnabled;
    }

    public void setProfilingEnabled(boolean var1) {
        this.real.profilingEnabled = var1;
    }
}
