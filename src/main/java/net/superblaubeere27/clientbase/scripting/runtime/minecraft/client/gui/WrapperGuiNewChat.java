/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.scripting.runtime.minecraft.client.gui;

import net.minecraft.client.gui.GuiNewChat;
import net.superblaubeere27.clientbase.scripting.runtime.minecraft.util.WrapperIChatComponent;

import java.util.List;

public class WrapperGuiNewChat extends WrapperGui {
    private GuiNewChat real;

    public WrapperGuiNewChat(GuiNewChat var1) {
        super(var1);
        this.real = var1;
    }

    public static int calculateChatboxWidth(float var0) {
        return GuiNewChat.calculateChatboxWidth(var0);
    }

    public static int calculateChatboxHeight(float var0) {
        return GuiNewChat.calculateChatboxHeight(var0);
    }

    public GuiNewChat unwrap() {
        return this.real;
    }

    public void drawChat(int var1) {
        this.real.drawChat(var1);
    }

    public void clearChatMessages() {
        this.real.clearChatMessages();
    }

    public void printChatMessage(WrapperIChatComponent var1) {
        this.real.printChatMessage(var1.unwrap());
    }

    public void printChatMessageWithOptionalDeletion(WrapperIChatComponent var1, int var2) {
        this.real.printChatMessageWithOptionalDeletion(var1.unwrap(), var2);
    }

    public void refreshChat() {
        this.real.refreshChat();
    }

    public List getSentMessages() {
        return this.real.getSentMessages();
    }

    public void addToSentMessages(String var1) {
        this.real.addToSentMessages(var1);
    }

    public void resetScroll() {
        this.real.resetScroll();
    }

    public void scroll(int var1) {
        this.real.scroll(var1);
    }

    public WrapperIChatComponent getChatComponent(int var1, int var2) {
        return new WrapperIChatComponent(this.real.getChatComponent(var1, var2));
    }

    public boolean getChatOpen() {
        return this.real.getChatOpen();
    }

    public void deleteChatLine(int var1) {
        this.real.deleteChatLine(var1);
    }

    public int getChatWidth() {
        return this.real.getChatWidth();
    }

    public int getChatHeight() {
        return this.real.getChatHeight();
    }

    public float getChatScale() {
        return this.real.getChatScale();
    }

    public int getLineCount() {
        return this.real.getLineCount();
    }
}
