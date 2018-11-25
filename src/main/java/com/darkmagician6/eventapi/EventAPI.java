/*
 * Copyright (c) 2018 superblaubeere27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/**
 * This is an API used for handling events across your java based projects.
 * It's meant to be simple to use without sacrificing performance and extensibility.
 * <p>
 * Currently the API is in beta phase but it's stable and ready to be used.
 * <p>
 * If you have any suggestion for improvements/fixes for shit,
 * feel free to make a pull request on the bitbucket: https://bitbucket.org/DarkMagician6/eventapi/overview.
 * <p>
 * For information on how to use the API take a look at the wiki:
 * https://bitbucket.org/DarkMagician6/eventapi/wiki/Home
 *
 * @Todo Improve/update the wiki.
 */
package com.darkmagician6.eventapi;

/**
 * Main class for the API.
 * Contains various information about the API.
 *
 * @author DarkMagician6
 * @since July 31, 2013
 */
public final class EventAPI {

    /**
     * The current version of the API.
     */
    public static final String VERSION = String.format("%s-%s", "0.7", "beta");
    /**
     * Array containing the authors of the API.
     */
    public static final String[] AUTHORS = {
            "DarkMagician6"
    };

    /**
     * No need to create an Object of this class as all Methods are static.
     */
    private EventAPI() {
    }

}
