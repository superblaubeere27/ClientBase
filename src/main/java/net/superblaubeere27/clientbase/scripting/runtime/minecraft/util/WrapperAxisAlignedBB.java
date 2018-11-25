/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.util;

import net.minecraft.util.AxisAlignedBB;

public class WrapperAxisAlignedBB {
    private AxisAlignedBB real;

    public WrapperAxisAlignedBB(AxisAlignedBB var1) {
        this.real = var1;
    }

    public AxisAlignedBB unwrap() {
        return this.real;
    }

    public WrapperAxisAlignedBB union(WrapperAxisAlignedBB var1) {
        return new WrapperAxisAlignedBB(this.real.union(var1.unwrap()));
    }

    public double calculateXOffset(WrapperAxisAlignedBB var1, double var2) {
        return this.real.calculateXOffset(var1.unwrap(), var2);
    }

    public double calculateYOffset(WrapperAxisAlignedBB var1, double var2) {
        return this.real.calculateYOffset(var1.unwrap(), var2);
    }

    public double calculateZOffset(WrapperAxisAlignedBB var1, double var2) {
        return this.real.calculateZOffset(var1.unwrap(), var2);
    }

    public boolean intersectsWith(WrapperAxisAlignedBB var1) {
        return this.real.intersectsWith(var1.unwrap());
    }

    public boolean isVecInside(WrapperVec3 var1) {
        return this.real.isVecInside(var1.unwrap());
    }

    public double getAverageEdgeLength() {
        return this.real.getAverageEdgeLength();
    }


    public String toString() {
        return this.real.toString();
    }

    public double getMinX() {
        return this.real.minX;
    }

    public double getMinY() {
        return this.real.minY;
    }

    public double getMinZ() {
        return this.real.minZ;
    }

    public double getMaxX() {
        return this.real.maxX;
    }

    public double getMaxY() {
        return this.real.maxY;
    }

    public double getMaxZ() {
        return this.real.maxZ;
    }
}
