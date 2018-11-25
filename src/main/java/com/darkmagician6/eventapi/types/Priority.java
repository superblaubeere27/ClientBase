/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.darkmagician6.eventapi.types;

import org.jetbrains.annotations.NotNull;

/**
 * The priority for the dispatcher to determine what method should be invoked first.
 * Ram was talking about the memory usage of the way I store the data so I decided
 * to just use bytes for the priority because they take up only 8 bits of memory
 * per value compared to the 32 bits per value of an enum (Same as an integer).
 *
 * @author DarkMagician6
 * @since August 3, 2013
 */
public final class Priority {

    public static final byte
            /**
             * Highest priority, called first.
             */
            HIGHEST = 0,
    /**
     * High priority, called after the highest priority.
     */
    HIGH = 1,
    /**
     * Medium priority, called after the high priority.
     */
    MEDIUM = 2,
    /**
     * Low priority, called after the medium priority.
     */
    LOW = 3,
    /**
     * Lowest priority, called after all the other priorities.
     */
    LOWEST = 4;

    /**
     * Array containing all the prioriy values.
     */
    @NotNull
    public static final byte[] VALUE_ARRAY;

    /**
     * Sets up the VALUE_ARRAY the first time anything in this class is called.
     */
    static {
        VALUE_ARRAY = new byte[]{
                HIGHEST,
                HIGH,
                MEDIUM,
                LOW,
                LOWEST
        };
    }

}
