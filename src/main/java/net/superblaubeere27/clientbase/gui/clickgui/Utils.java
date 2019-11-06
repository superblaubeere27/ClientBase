/*
 * Copyright 2019 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.gui.clickgui;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class Utils {

    public static String formatTime(long l) {
        long minutes = l / 1000 / 60;

        l -= minutes * 1000 * 60;

        long seconds = l / 1000;

        l -= seconds * 1000;

        StringBuilder sb = new StringBuilder();

        if (minutes != 0) sb.append(minutes).append("min ");
        if (seconds != 0) sb.append(seconds).append("s ");

        if (l != 0 || minutes == 0 && seconds == 0) {
            sb.append(l).append("ms ");
        }

        return sb.substring(0, sb.length() - 1);
    }

    /*
     * By DarkStorm
     */
    public static Point calculateMouseLocation() {
        Minecraft minecraft = Minecraft.getMinecraft();
        int scale = minecraft.gameSettings.guiScale;
        if (scale == 0)
            scale = 1000;
        int scaleFactor = 0;
        while (scaleFactor < scale && minecraft.displayWidth / (scaleFactor + 1) >= 320 && minecraft.displayHeight / (scaleFactor + 1) >= 240)
            scaleFactor++;
        return new Point(Mouse.getX() / scaleFactor, minecraft.displayHeight / scaleFactor - Mouse.getY() / scaleFactor - 1);
    }
}
