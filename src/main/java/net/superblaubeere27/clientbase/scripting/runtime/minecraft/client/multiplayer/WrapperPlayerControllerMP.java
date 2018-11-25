/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.multiplayer;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.WrapperMinecraft;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.entity.WrapperEntity;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.entity.player.WrapperEntityPlayer;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.util.WrapperBlockPos;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.util.WrapperEnumFacing;

public class WrapperPlayerControllerMP {
    private PlayerControllerMP real;

    public WrapperPlayerControllerMP(PlayerControllerMP var1) {
        this.real = var1;
    }

    public static void clickBlockCreative(WrapperMinecraft var0, WrapperPlayerControllerMP var1, WrapperBlockPos var2, WrapperEnumFacing var3) {
        PlayerControllerMP.clickBlockCreative(var0.unwrap(), var1.unwrap(), var2.unwrap(), var3.unwrap());
    }

    public PlayerControllerMP unwrap() {
        return this.real;
    }

    public void setPlayerCapabilities(WrapperEntityPlayer var1) {
        this.real.setPlayerCapabilities(var1.unwrap());
    }

    public boolean isSpectator() {
        return this.real.isSpectator();
    }

    public void flipPlayer(WrapperEntityPlayer var1) {
        this.real.flipPlayer(var1.unwrap());
    }

    public boolean shouldDrawHUD() {
        return this.real.shouldDrawHUD();
    }

    public boolean onPlayerDestroyBlock(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.onPlayerDestroyBlock(var1.unwrap(), var2.unwrap());
    }

    public boolean clickBlock(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.clickBlock(var1.unwrap(), var2.unwrap());
    }

    public void resetBlockRemoving() {
        this.real.resetBlockRemoving();
    }

    public boolean onPlayerDamageBlock(WrapperBlockPos var1, WrapperEnumFacing var2) {
        return this.real.onPlayerDamageBlock(var1.unwrap(), var2.unwrap());
    }

    public float getBlockReachDistance() {
        return this.real.getBlockReachDistance();
    }

    public void updateController() {
        this.real.updateController();
    }

    public void attackEntity(WrapperEntityPlayer var1, WrapperEntity var2) {
        this.real.attackEntity(var1.unwrap(), var2.unwrap());
    }

    public boolean interactWithEntitySendPacket(WrapperEntityPlayer var1, WrapperEntity var2) {
        return this.real.interactWithEntitySendPacket(var1.unwrap(), var2.unwrap());
    }

    public void sendEnchantPacket(int var1, int var2) {
        this.real.sendEnchantPacket(var1, var2);
    }

    public void onStoppedUsingItem(WrapperEntityPlayer var1) {
        this.real.onStoppedUsingItem(var1.unwrap());
    }

    public boolean gameIsSurvivalOrAdventure() {
        return this.real.gameIsSurvivalOrAdventure();
    }

    public boolean isNotCreative() {
        return this.real.isNotCreative();
    }

    public boolean isInCreativeMode() {
        return this.real.isInCreativeMode();
    }

    public boolean extendedReach() {
        return this.real.extendedReach();
    }

    public boolean isRidingHorse() {
        return this.real.isRidingHorse();
    }

    public boolean isSpectatorMode() {
        return this.real.isSpectatorMode();
    }

}
