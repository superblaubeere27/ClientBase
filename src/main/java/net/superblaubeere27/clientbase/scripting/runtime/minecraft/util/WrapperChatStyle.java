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

import net.minecraft.util.ChatStyle;

public class WrapperChatStyle {
    private ChatStyle real;

    public WrapperChatStyle(ChatStyle var1) {
        this.real = var1;
    }

    public ChatStyle unwrap() {
        return this.real;
    }

    public boolean getBold() {
        return this.real.getBold();
    }

    public WrapperChatStyle setBold(Boolean var1) {
        return new WrapperChatStyle(this.real.setBold(var1));
    }

    public boolean getItalic() {
        return this.real.getItalic();
    }

    public WrapperChatStyle setItalic(Boolean var1) {
        return new WrapperChatStyle(this.real.setItalic(var1));
    }

    public boolean getStrikethrough() {
        return this.real.getStrikethrough();
    }

    public WrapperChatStyle setStrikethrough(Boolean var1) {
        return new WrapperChatStyle(this.real.setStrikethrough(var1));
    }

    public boolean getUnderlined() {
        return this.real.getUnderlined();
    }

    public WrapperChatStyle setUnderlined(Boolean var1) {
        return new WrapperChatStyle(this.real.setUnderlined(var1));
    }

    public boolean getObfuscated() {
        return this.real.getObfuscated();
    }

    public WrapperChatStyle setObfuscated(Boolean var1) {
        return new WrapperChatStyle(this.real.setObfuscated(var1));
    }

    public boolean isEmpty() {
        return this.real.isEmpty();
    }

    public String getInsertion() {
        return this.real.getInsertion();
    }

    public WrapperChatStyle setInsertion(String var1) {
        return new WrapperChatStyle(this.real.setInsertion(var1));
    }

    public WrapperChatStyle setParentStyle(WrapperChatStyle var1) {
        return new WrapperChatStyle(this.real.setParentStyle(var1.unwrap()));
    }

    public String getFormattingCode() {
        return this.real.getFormattingCode();
    }

    public String toString() {
        return this.real.toString();
    }

    public boolean equals(Object var1) {
        return this.real.equals(var1);
    }

    public int hashCode() {
        return this.real.hashCode();
    }

    public WrapperChatStyle createShallowCopy() {
        return new WrapperChatStyle(this.real.createShallowCopy());
    }

    public WrapperChatStyle createDeepCopy() {
        return new WrapperChatStyle(this.real.createDeepCopy());
    }
}
