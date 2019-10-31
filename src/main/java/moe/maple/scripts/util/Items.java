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

/**
 * Common items, values used across multiple scripts.
 */
public class Items {

    public static final int ALL_CURE = 2050004;

    public static final int EOS_ROCK_SCROLL = 4001020;
    public static final int MAGIC_ROCK = 4006000;
    public static final int POWER_ELIXIRS = 2000005;
    public static final int SUMMONING_ROCK = 4006001;

    // Arrows
    public static final int BASIC_ARROW_BOW = 2060000;
    public static final int BRONZE_ARROW_BOW = 2060001;
    public static final int STEEL_ARROW_BOW = 2060002;
    public static final int RED_ARROW_BOW = 2060003;
    public static final int DIAMOND_ARROW_BOW = 2060004;
    public static final int BASIC_ARROW_XBOW = 2061000;
    public static final int BRONZE_ARROW_XBOW = 2061001;
    public static final int STEEL_ARROW_XBOW = 2061002;
    public static final int RED_ARROW_XBOW = 2061003;
    public static final int DIAMOND_ARROW_XBOW = 2061004;
    public static final int[] ARROWS = {
            BASIC_ARROW_BOW, BASIC_ARROW_XBOW,
            BRONZE_ARROW_BOW, BRONZE_ARROW_XBOW,
            STEEL_ARROW_BOW, STEEL_ARROW_XBOW,
            RED_ARROW_BOW, RED_ARROW_XBOW,
            DIAMOND_ARROW_BOW, DIAMOND_ARROW_XBOW
    };

    // Bullets
    public static final int BASIC_BULLET = 2330000;
    public static final int SPLIT_BULLET = 2330001;
    public static final int MIGHTY_BULLET = 2330002;
    public static final int VITAL_BULLET = 2330003;
    public static final int SHINY_BULLET = 2330004;
    public static final int ETERNAL_BULLET = 2330005;
    public static final int NOVICE_BULLET = 2330006;
    public static final int[] BULLETS = {
            BASIC_BULLET, SPLIT_BULLET,
            MIGHTY_BULLET, VITAL_BULLET,
            SHINY_BULLET, ETERNAL_BULLET,
            NOVICE_BULLET
    };

    // Throwing Stars
    public static final int SUBI_THROWING_STARS = 2070000;
    public static final int WOLBI_THROWING_STARS = 2070001;
    public static final int MOKBI_THROWING_STARS = 2070002;
    public static final int KUMBI_THROWING_STARS = 2070003;
    public static final int TOBI_THROWING_STARS = 2070004;
    public static final int STEELY_THROWING_STARS = 2070005;
    public static final int ILBI_THROWING_STARS = 2070006;
    public static final int HWABI_THROWING_STARS = 2070007;
    public static final int SNOWBALL_THROWING_STARS = 2070008;
    public static final int WOODEN_TOP_THROWING_STARS = 2070009;
    public static final int ICICLE_TOP_THROWING_STARS = 2070010;
    public static final int MAPLE_THROWING_STARS = 2070011;
    public static final int PAPER_THROWING_STARS = 2070012;
    public static final int ORANGE_THROWING_STARS = 2070013;
    // public static final int DEVIL_RAIN_THROWING_STARS = 2070014; // ??
    public static final int BEGINNER_THIEF_THROWING_STARS = 2070015;
    public static final int CRYSTAL_ILBI_THIEF_THROWING_STARS = 2070016;
    public static final int BALANCED_FURY_THIEF_THROWING_STARS = 2070018;

    public static final int[] THROWING_STARS = {
            Items.SUBI_THROWING_STARS, Items.WOLBI_THROWING_STARS,
            Items.MOKBI_THROWING_STARS, Items.KUMBI_THROWING_STARS,
            Items.TOBI_THROWING_STARS, Items.STEELY_THROWING_STARS,
            Items.HWABI_THROWING_STARS, Items.SNOWBALL_THROWING_STARS,
            Items.WOODEN_TOP_THROWING_STARS, Items.ICICLE_TOP_THROWING_STARS,
            Items.MAPLE_THROWING_STARS, Items.PAPER_THROWING_STARS,
            Items.ORANGE_THROWING_STARS, Items.BEGINNER_THIEF_THROWING_STARS,
            Items.CRYSTAL_ILBI_THIEF_THROWING_STARS, Items.BALANCED_FURY_THIEF_THROWING_STARS
    };
}
