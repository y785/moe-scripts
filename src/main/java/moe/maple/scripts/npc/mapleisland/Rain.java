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

package moe.maple.scripts.npc.mapleisland;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

@Script(name = "rein", description = "Amherst | Rain (Pantsu grill)")
public class Rain extends NpcScript {
    @Override
    protected void work() {
        say("This is the town called #bAmherst#k, located at the northeast part of the Maple Island. You know that Maple Island is for beginners, right? I'm glad there are only weak monsters around this place.",
                "If you want to get stronger, then go to #bSouthperry#k where there's a harbor. Ride on the gigantic ship and head to the place called #bVictoria Island#k. It's incomparable in size compared to this tiny island.",
                "At the Victoria Island, you can choose your job. Is it called #bPerion#k...? I heard there's a bare, desolate town where warriors live. A highland...what kind of a place would that be?");
    }
}
