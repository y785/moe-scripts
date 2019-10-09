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

package moe.maple.scripts.npc.victoria.perion.gpq;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

@Script(name = "guildquest1_board")
public class GuildQuestBoard extends NpcScript {

    @Override
    protected void work() {
        if (field.getId() == 101030104)
            say("<Notice> \r Are you the Guild that possesses an ample amount of courage and trust? Then take on the Guild Quest and challenge yourselves!\r \r #bTo Participate :#k\r 1. The Guild must consist of at least 6 people!\r 2. The leaders of the Guild should be the Master and the Jr. Master of the Guild!\r 3. The Guild Quest may end early if the guildmembers participating reaches below 6, or if the leader decides to end it early!");
        else if (field.getId() == 990000000)
            say("#r<Warning>#k Danger! A force of evil is running through the Sharenian Castle!\r 1. There's a force of evil currently running through the Sharenian Castle, so don't get too close, or risk death.\r 2. Use the Returning Rock if you want to end the journey early!");
    }
}
