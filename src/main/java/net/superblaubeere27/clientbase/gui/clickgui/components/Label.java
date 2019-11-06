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

import net.superblaubeere27.clientbase.gui.clickgui.AbstractComponent;
import net.superblaubeere27.clientbase.gui.clickgui.IRenderer;
import net.superblaubeere27.clientbase.gui.clickgui.Window;

public class Label extends AbstractComponent {
    private String text;

    public Label(IRenderer renderer, String text) {
        super(renderer);
        setText(text);
    }

    @Override
    public void render() {
//        glClearDepthf(1.0f);
//        glClear(GL_DEPTH_BUFFER_BIT);
//        glColorMask(false, false, false, false);
//        glDepthFunc(GL_LESS);
//        glEnable(GL_DEPTH_TEST);
//        glDepthMask(true);

//        renderer.drawRect(x, y, getWidth() / 1.5, getHeight(), Color.white);


//        glColorMask(true, true, true, true);
//        glDepthMask(true);
//        glDepthFunc(GL_EQUAL);

        renderer.drawString(x, y, text, Window.FOREGROUND);

//        glDisable(GL_DEPTH_TEST);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        setWidth(renderer.getStringWidth(text));
        setHeight(renderer.getStringHeight(text));

        this.text = text;
    }
}
