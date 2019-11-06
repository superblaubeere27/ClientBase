/*
 * Copyright 2019 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.superblaubeere27.clientbase.gui.clickgui.layout;

import net.superblaubeere27.clientbase.gui.clickgui.AbstractComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridLayout implements ILayoutManager {
    private static final int DEFAULT_VERTICAL_PADDING = 7;
    private static final int DEFAULT_HORIZONTAL_PADDING = 7;
    private int columns;
    private int verticalPadding;
    private int horizontalPadding;

    public GridLayout(int columns, int verticalPadding, int horizontalPadding) {
        this.columns = columns;
        this.verticalPadding = verticalPadding;
        this.horizontalPadding = horizontalPadding;
    }

    public GridLayout(int columns) {
        this(columns, DEFAULT_VERTICAL_PADDING, DEFAULT_HORIZONTAL_PADDING);
    }


    @Override
    public int[] getOptimalDiemension(List<AbstractComponent> components, int maxWidth) {
        int rows = (int) Math.ceil(components.size() / (double) this.columns);

        AbstractComponent[][] comps = new AbstractComponent[rows][this.columns];

        for (int i = 0; i < components.size(); i++) {
            AbstractComponent component = components.get(i);
            comps[i / this.columns][i % this.columns] = component;
        }

        int[] rowHeight = new int[rows];

        for (int i = 0; i < rows; i++) {
            AbstractComponent[] comp = comps[i];

            int currHeight = -1;

            for (AbstractComponent abstractComponent : comp) {
                if (abstractComponent != null && currHeight < abstractComponent.getHeight() + horizontalPadding * 2) {
                    currHeight = abstractComponent.getHeight() + horizontalPadding * 2;
                }
            }

            rowHeight[i] = currHeight;
        }

        int[] columnWidth = new int[columns];

        for (int i = 0; i < this.columns; i++) {
            int currWidth = -1;

            for (int j = 0; j < rows; j++) {
                AbstractComponent abstractComponent = comps[j][i];

                if (abstractComponent != null && abstractComponent.getWidth() + verticalPadding * 2 > currWidth) {
                    currWidth = abstractComponent.getWidth() + verticalPadding * 2;
                }
            }

            columnWidth[i] = currWidth;
        }

        int width = verticalPadding;
        int height = horizontalPadding;

        for (int i : rowHeight) {
            height += i;
        }
        for (int i : columnWidth) {
            width += i;
        }

        return new int[]{width, height};
    }

    @Override
    public Layout buildLayout(List<AbstractComponent> components, int width, int height) {
        int rows = (int) Math.ceil(components.size() / (double) this.columns);

        AbstractComponent[][] comps = new AbstractComponent[this.columns][rows];

        for (int i = 0; i < components.size(); i++) {
            AbstractComponent component = components.get(i);
            comps[i % this.columns][i / this.columns] = component;
        }

        int[] columnWidth = new int[this.columns];

        for (int i = 0; i < comps.length; i++) {
            AbstractComponent[] comp = comps[i];

            int currWidth = -1;

            for (AbstractComponent abstractComponent : comp) {
                if (abstractComponent != null && currWidth < abstractComponent.getWidth() + verticalPadding * 2) {
                    currWidth = abstractComponent.getWidth() + verticalPadding * 2;
                }
            }

            columnWidth[i] = currWidth;
        }

        {
            int maxWidth = verticalPadding * 3;

            for (int i : columnWidth) {
                maxWidth += i;
            }

            int needed = width - maxWidth;
            int avg = needed / this.columns;
            int count = 0;

            for (int i : columnWidth) {
                if (i < avg) count++;
            }

            if (count != 0) {
                avg = needed / count;

                for (int i1 = 0; i1 < columnWidth.length; i1++) {
                    int i = columnWidth[i1];
                    if (i < avg) {
                        columnWidth[i1] = avg;
                    }
                }
            }
        }

        {
            int maxWidth = verticalPadding * 3;

            for (int i : columnWidth) {
                maxWidth += i;
            }

            int needed = (width - maxWidth);
            int avg = (int) Math.floor(needed / (float) this.columns);

            for (int i = 0; i < columnWidth.length; i++) {
                columnWidth[i] += avg;
            }
        }

        {
            int maxWidth = verticalPadding * 3;

            for (int i : columnWidth) {
                maxWidth += i;
            }

            int needed = (width - maxWidth);
            columnWidth[0] += needed;
        }

        int[] rowHeight = new int[rows];

        for (int i = 0; i < rows; i++) {
            int maxHeight = -1;

            for (int j = 0; j < this.columns; j++) {
                AbstractComponent abstractComponent = comps[j][i];

                if (abstractComponent != null && abstractComponent.getHeight() > maxHeight) {
                    maxHeight = abstractComponent.getHeight();
                }
            }
            rowHeight[i] = maxHeight;
        }

        HashMap<AbstractComponent, int[]> layout = new HashMap<>();

        int currX = verticalPadding;

        for (int i = 0; i < this.columns; i++) {
            int currY = horizontalPadding;

            for (int j = 0; j < rows; j++) {
                layout.put(comps[i][j], new int[]{currX, currY});

                currY += rowHeight[j] + horizontalPadding;
            }

            currX += columnWidth[i] + verticalPadding;
        }

        int maxHeight = -1;

        for (Map.Entry<AbstractComponent, int[]> abstractComponentEntry : layout.entrySet()) {
            if (abstractComponentEntry.getKey() != null && maxHeight < abstractComponentEntry.getValue()[1] + abstractComponentEntry.getKey().getHeight()) {
                maxHeight = abstractComponentEntry.getValue()[1] + abstractComponentEntry.getKey().getHeight();
            }
        }
        maxHeight += horizontalPadding;
        int maxWidth = -1;

        for (Map.Entry<AbstractComponent, int[]> abstractComponentEntry : layout.entrySet()) {
            if (abstractComponentEntry.getKey() != null && maxWidth < abstractComponentEntry.getValue()[0] + abstractComponentEntry.getKey().getWidth()) {
                maxWidth = abstractComponentEntry.getValue()[0] + abstractComponentEntry.getKey().getWidth();
            }
        }

        return new Layout(layout, maxHeight, maxWidth);
    }

    public int getVerticalPadding() {
        return verticalPadding;
    }

    public void setVerticalPadding(int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }

    public int getHorizontalPadding() {
        return horizontalPadding;
    }

    public void setHorizontalPadding(int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }
}
