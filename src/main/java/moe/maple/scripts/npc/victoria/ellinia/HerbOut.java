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

package moe.maple.scripts.npc.victoria.ellinia;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

/**
 * Translated from BMS
 */
@Script(name = "herb_out", description = "Herb Jump Quest 'out' NPC", field = 101000100)
public class HerbOut extends NpcScript {

    @Override
    protected void work() {
        askYesNo("Do you want to get out of here? Well... this place can really wear you out... I'm used to it, okay. Anyway, remember that if you leave here through me, you'll have to start the mission over. Do you still want to go? ",
                () -> user.transferField(101000000, ""),
                () -> say("Isn't it awful that you have to restart the whole thing? Keep trying... the more you explore, the better you will know this whole place. Soon you will be able to walk around with your eyes closed, hehe."));
    }
}
