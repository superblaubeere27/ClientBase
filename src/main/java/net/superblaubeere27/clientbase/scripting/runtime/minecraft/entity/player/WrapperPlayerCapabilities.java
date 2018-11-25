/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.entity.player;

import net.minecraft.entity.player.PlayerCapabilities;

public class WrapperPlayerCapabilities {
    private PlayerCapabilities real;

    public WrapperPlayerCapabilities(PlayerCapabilities var1) {
        this.real = var1;
    }

    public PlayerCapabilities unwrap() {
        return this.real;
    }

    public float getFlySpeed() {
        return this.real.getFlySpeed();
    }

    public void setFlySpeed(float var1) {
        this.real.setFlySpeed(var1);
    }

    public float getWalkSpeed() {
        return this.real.getWalkSpeed();
    }

    public void setPlayerWalkSpeed(float var1) {
        this.real.setPlayerWalkSpeed(var1);
    }

    public boolean isDisableDamage() {
        return this.real.disableDamage;
    }

    public void setDisableDamage(boolean var1) {
        this.real.disableDamage = var1;
    }

    public boolean IsFlying() {
        return this.real.isFlying;
    }

    public void setIsFlying(boolean var1) {
        this.real.isFlying = var1;
    }

    public boolean isAllowFlying() {
        return this.real.allowFlying;
    }

    public void setAllowFlying(boolean var1) {
        this.real.allowFlying = var1;
    }

    public boolean IsCreativeMode() {
        return this.real.isCreativeMode;
    }

    public void setIsCreativeMode(boolean var1) {
        this.real.isCreativeMode = var1;
    }

    public boolean isAllowEdit() {
        return this.real.allowEdit;
    }

    public void setAllowEdit(boolean var1) {
        this.real.allowEdit = var1;
    }
}
