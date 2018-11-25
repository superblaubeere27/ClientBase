/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.entity;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.util.WrapperBlockPos;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.util.WrapperIChatComponent;

public class WrapperEntityOtherPlayerMP extends WrapperAbstractClientPlayer {
    private EntityOtherPlayerMP real;

    public WrapperEntityOtherPlayerMP(EntityOtherPlayerMP var1) {
        super(var1);
        this.real = var1;
    }

    public EntityOtherPlayerMP unwrap() {
        return this.real;
    }

    public void setPositionAndRotation2(double param1, double param3, double param5, float param7, float param8, int param9, boolean param10) {
        real.setPositionAndRotation2(param1, param3, param5, param7, param8, param9, param10);
    }

    public void onUpdate() {
        this.real.onUpdate();
    }

    public void onLivingUpdate() {
        this.real.onLivingUpdate();
    }

    public void addChatMessage(WrapperIChatComponent var1) {
        this.real.addChatMessage(var1.unwrap());
    }

    public boolean canCommandSenderUseCommand(int var1, String var2) {
        return this.real.canCommandSenderUseCommand(var1, var2);
    }

    public WrapperBlockPos getPosition() {
        return new WrapperBlockPos(this.real.getPosition());
    }
}
