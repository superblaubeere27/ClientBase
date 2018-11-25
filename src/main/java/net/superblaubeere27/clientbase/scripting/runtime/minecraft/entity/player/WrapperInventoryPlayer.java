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

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.block.WrapperBlock;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.item.WrapperItem;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.util.WrapperIChatComponent;

public class WrapperInventoryPlayer {
    private InventoryPlayer real;

    public WrapperInventoryPlayer(InventoryPlayer var1) {
        this.real = var1;
    }

    public static int getHotbarSize() {
        return InventoryPlayer.getHotbarSize();
    }

    public InventoryPlayer unwrap() {
        return this.real;
    }

    public int getFirstEmptyStack() {
        return this.real.getFirstEmptyStack();
    }

    public void setCurrentItem(WrapperItem var1, int var2, boolean var3, boolean var4) {
        this.real.setCurrentItem(var1.unwrap(), var2, var3, var4);
    }

    public void changeCurrentItem(int var1) {
        this.real.changeCurrentItem(var1);
    }

    public void decrementAnimations() {
        this.real.decrementAnimations();
    }

    public boolean consumeInventoryItem(WrapperItem var1) {
        return this.real.consumeInventoryItem(var1.unwrap());
    }

    public boolean hasItem(WrapperItem var1) {
        return this.real.hasItem(var1.unwrap());
    }

    public float getStrVsBlock(WrapperBlock var1) {
        return this.real.getStrVsBlock(var1.unwrap());
    }

    public int getSizeInventory() {
        return this.real.getSizeInventory();
    }

    public String getName() {
        return this.real.getName();
    }

    public boolean hasCustomName() {
        return this.real.hasCustomName();
    }

    public WrapperIChatComponent getDisplayName() {
        return new WrapperIChatComponent(this.real.getDisplayName());
    }

    public int getInventoryStackLimit() {
        return this.real.getInventoryStackLimit();
    }

    public boolean canHeldItemHarvest(WrapperBlock var1) {
        return this.real.canHeldItemHarvest(var1.unwrap());
    }

    public int getTotalArmorValue() {
        return this.real.getTotalArmorValue();
    }

    public void damageArmor(float var1) {
        this.real.damageArmor(var1);
    }

    public void dropAllItems() {
        this.real.dropAllItems();
    }

    public void markDirty() {
        this.real.markDirty();
    }

    public boolean isUseableByPlayer(WrapperEntityPlayer var1) {
        return this.real.isUseableByPlayer(var1.unwrap());
    }

    public void openInventory(WrapperEntityPlayer var1) {
        this.real.openInventory(var1.unwrap());
    }

    public void closeInventory(WrapperEntityPlayer var1) {
        this.real.closeInventory(var1.unwrap());
    }

    public void copyInventory(WrapperInventoryPlayer var1) {
        this.real.copyInventory(var1.unwrap());
    }

    public int getField(int var1) {
        return this.real.getField(var1);
    }

    public void setField(int var1, int var2) {
        this.real.setField(var1, var2);
    }

    public int getFieldCount() {
        return this.real.getFieldCount();
    }

    public void clear() {
        this.real.clear();
    }

    public ItemStack[] getMainInventory() {
        return this.real.mainInventory;
    }

    public void setMainInventory(ItemStack[] var1) {
        this.real.mainInventory = var1;
    }

    public ItemStack[] getArmorInventory() {
        return this.real.armorInventory;
    }

    public void setArmorInventory(ItemStack[] var1) {
        this.real.armorInventory = var1;
    }

    public int getCurrentItem() {
        return this.real.currentItem;
    }

    public WrapperEntityPlayer getPlayer() {
        return new WrapperEntityPlayer(this.real.player);
    }

    public void setPlayer(WrapperEntityPlayer var1) {
        this.real.player = var1.unwrap();
    }

    public boolean isInventoryChanged() {
        return this.real.inventoryChanged;
    }

    public void setInventoryChanged(boolean var1) {
        this.real.inventoryChanged = var1;
    }
}
