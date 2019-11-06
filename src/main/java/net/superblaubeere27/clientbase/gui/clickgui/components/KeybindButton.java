/*
 * Copyright 2019 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.gui.clickgui.components;

import net.superblaubeere27.clientbase.gui.clickgui.IRenderer;
import org.lwjgl.input.Keyboard;

import java.util.function.Function;

public class KeybindButton extends Button {
    private ValueChangeListener<Integer> listener;
    private boolean listening;
    private Function<Integer, String> keyNameResolver;
    private int value;

    public KeybindButton(IRenderer renderer, int preferredWidth, int preferredHeight, Function<Integer, String> keyNameResolver) {
        super(renderer, "", preferredWidth, preferredHeight);
        this.keyNameResolver = keyNameResolver;

        initialize();
    }

    public KeybindButton(IRenderer renderer, Function<Integer, String> keyNameResolver) {
        super(renderer, "");
        this.keyNameResolver = keyNameResolver;

        initialize();
    }

    private void initialize() {
        setOnClickListener(() -> {
            listening = !listening;

            updateState();
        });

        updateState();
    }

    @Override
    public void setOnClickListener(ActionEventListener listener) {
        if (getOnClickListener() != null) {
            ActionEventListener old = getOnClickListener();

            super.setOnClickListener(() -> {
                listener.onActionEvent();
                old.onActionEvent();
            });
        } else {
            super.setOnClickListener(listener);
        }

    }

    @Override
    public boolean keyPressed(int key, char c) {
        if (listening) {
            listening = false;

            if (Keyboard.getEventKey() != 256 && Keyboard.getEventCharacter() != 0) {
                int newValue = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();


                if (listener != null)
                    if (listener.onValueChange(newValue))
                        this.value = newValue;
            }

            updateState();
        }

        return super.keyPressed(key, c);
    }

    @Override
    public int getEventPriority() {
        return listening ? super.getEventPriority() + 1 : super.getEventPriority();
    }

    private void updateState() {
        if (listening) {
            setTitle("Press a button...");
        } else {
            setTitle(keyNameResolver.apply(value));
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;

        updateState();
    }

    public Function<Integer, String> getKeyNameResolver() {
        return keyNameResolver;
    }

    public void setKeyNameResolver(Function<Integer, String> keyNameResolver) {
        this.keyNameResolver = keyNameResolver;
    }

    public void setListener(ValueChangeListener<Integer> listener) {
        this.listener = listener;
    }
}
