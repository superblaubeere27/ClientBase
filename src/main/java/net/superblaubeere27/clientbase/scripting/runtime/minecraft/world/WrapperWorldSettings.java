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

import net.minecraft.world.WorldSettings;

public class WrapperWorldSettings {
    private WorldSettings real;

    public WrapperWorldSettings(WorldSettings var1) {
        this.real = var1;
    }

    public WorldSettings unwrap() {
        return this.real;
    }

    public WrapperWorldSettings enableBonusChest() {
        return new WrapperWorldSettings(this.real.enableBonusChest());
    }

    public WrapperWorldSettings enableCommands() {
        return new WrapperWorldSettings(this.real.enableCommands());
    }

    public boolean isBonusChestEnabled() {
        return this.real.isBonusChestEnabled();
    }

    public long getSeed() {
        return this.real.getSeed();
    }

    public boolean getHardcoreEnabled() {
        return this.real.getHardcoreEnabled();
    }

    public boolean isMapFeaturesEnabled() {
        return this.real.isMapFeaturesEnabled();
    }

    public WrapperWorldType getTerrainType() {
        return new WrapperWorldType(this.real.getTerrainType());
    }

    public boolean areCommandsAllowed() {
        return this.real.areCommandsAllowed();
    }

    public String getWorldName() {
        return this.real.getWorldName();
    }

    public WrapperWorldSettings setWorldName(String var1) {
        return new WrapperWorldSettings(this.real.setWorldName(var1));
    }
}
