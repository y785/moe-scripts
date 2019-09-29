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
import moe.maple.api.script.model.helper.MenuItem;
import moe.maple.scripts.util.Items;

@Script(name = "ludi015", description = "2nd eos rock : 2040025, 71st floor", field = 2040025)
public class SecondEosRock extends NpcScript {

    @Override
    protected void work() {
        if (user.hasItem(Items.EOS_ROCK_SCROLL)) {
            askMenu("You can use #b#t"+Items.EOS_ROCK_SCROLL+"##k to activate #b#p2040025##k. Which of these rocks would you like to teleport to?", "#p2040024#(100th floor)", "#p2040026#(41st floor)")
                    .andThen(sel -> {
                        var fieldId = sel == 0 ? 221024400 : 221021700;
                        var stoneId = sel == 0 ? 2040024 : 2040026;
                        var floor = sel == 0 ? "100th" : "41st";

                        askYesNo(String.format("You can use #b#t%d##k to activate #b#p2040025##k. Will you teleport to #b#p%d##k at the %s floor?", Items.EOS_ROCK_SCROLL, stoneId, floor), () -> {
                            exchange(() -> user.transferField(fieldId, "go00"), String.format("You cannot enable #b#p2040024##k without #b#t%d##k.", Items.EOS_ROCK_SCROLL),
                                    0, Items.EOS_ROCK_SCROLL, -1);
                        });
                    });
        } else {
            say("There's a rock that will enable you to teleport to #b#p2040024##k or #b#p2040026##k, but it cannot be activated without the scroll.");
        }
    }
}
