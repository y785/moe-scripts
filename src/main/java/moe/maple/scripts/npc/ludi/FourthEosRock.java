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

package moe.maple.scripts.npc.ludi;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.Items;

@Script(name = "ludi017", description = "the 4th eos rock : 2040027, 1st floor", field = 2040027)
public class FourthEosRock extends NpcScript {

    @Override
    protected void work() {
        if (user.hasItem(Items.EOS_ROCK_SCROLL)) {
            askYesNo("You can use #b#t"+Items.EOS_ROCK_SCROLL+"##k to activate #b#p2040027##k. Will you head over to #b#p2040026##k at the 41st floor?", () ->
                    exchange(() -> user.transferField(221021700, "go00"), String.format("You cannot enable #b#p2040027##k without #b#t%d##k.", Items.EOS_ROCK_SCROLL),
                            0, Items.EOS_ROCK_SCROLL, -1));
        } else {
            say("There's a rock that will enable you to teleport to #b#p2040027##k, but it cannot be activated without the scroll.");
        }
    }
}
