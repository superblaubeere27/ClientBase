/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.world;

import net.minecraft.world.WorldType;

public class WrapperWorldType {
    private WorldType real;

    public WrapperWorldType(WorldType var1) {
        this.real = var1;
    }

    public static WrapperWorldType parseWorldType(String var0) {
        return new WrapperWorldType(WorldType.parseWorldType(var0));
    }

    public WorldType unwrap() {
        return this.real;
    }

    public String getWorldTypeName() {
        return this.real.getWorldTypeName();
    }

    public String getTranslateName() {
        return this.real.getTranslateName();
    }

    public int getGeneratorVersion() {
        return this.real.getGeneratorVersion();
    }

    public WrapperWorldType getWorldTypeForGeneratorVersion(int var1) {
        return new WrapperWorldType(this.real.getWorldTypeForGeneratorVersion(var1));
    }

    public boolean getCanBeCreated() {
        return this.real.getCanBeCreated();
    }

    public boolean isVersioned() {
        return this.real.isVersioned();
    }

    public int getWorldTypeID() {
        return this.real.getWorldTypeID();
    }

    public boolean showWorldInfoNotice() {
        return this.real.showWorldInfoNotice();
    }

    public WorldType[] getWorldTypes() {
        return WorldType.worldTypes;
    }

    public WrapperWorldType getDEFAULT() {
        return new WrapperWorldType(WorldType.DEFAULT);
    }

    public WrapperWorldType getFLAT() {
        return new WrapperWorldType(WorldType.FLAT);
    }

    public WrapperWorldType getLARGE_BIOMES() {
        return new WrapperWorldType(WorldType.LARGE_BIOMES);
    }

    public WrapperWorldType getAMPLIFIED() {
        return new WrapperWorldType(WorldType.AMPLIFIED);
    }

    public WrapperWorldType getCUSTOMIZED() {
        return new WrapperWorldType(WorldType.CUSTOMIZED);
    }

    public WrapperWorldType getDEBUG_WORLD() {
        return new WrapperWorldType(WorldType.DEBUG_WORLD);
    }

    public WrapperWorldType getDEFAULT_1_1() {
        return new WrapperWorldType(WorldType.DEFAULT_1_1);
    }
}
