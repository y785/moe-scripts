/*
 * Copyright (C) 2019, http://github.com/y785/moe-scripts
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package moe.maple.scripts.util;

import moe.maple.api.script.model.object.user.QuestObject;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Predicate;

public class Naughty {

    public static Predicate<CharSequence> numeric = cs -> {
        for (int i = 0; i < cs.length(); ++i) {
            if (!Character.isDigit(cs.charAt(i)))
                return false;
        }
        return true;
    };

    public static int toInt(String value, int def) {
        if (value == null || value.isEmpty() || !numeric.test(value))
            return def;
        return Integer.parseInt(value);
    }

    public static long toLong(String value, long def) {
        if (value == null || value.isEmpty() || !numeric.test(value))
            return def;
        return Long.parseLong(value);
    }
}
