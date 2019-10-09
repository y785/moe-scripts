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

@Script(name = "ludi016", description = "3rd eos rock : 2040026, 41st floor", field = 2040026)
public class ThirdEosRock extends NpcScript {

    @Override
    protected void work() {
        if (user.hasItem(Items.EOS_ROCK_SCROLL)) {
            askMenu("You can use #b#t"+Items.EOS_ROCK_SCROLL+"##k to activate #b#p2040026##k. Which of these rocks would you like to teleport to?", "#p2040025#(71st floor)", "#p2040027#(1st floor)")
                    .andThen(sel -> {
                        var fieldId = sel == 0 ? 221022900 : 221020000;
                        var stoneId = sel == 0 ? 2040025 : 2040027;
                        var floor = sel == 0 ? "71st" : "1st";

                        askYesNo(String.format("You can use #b#t%d##k to activate #b#p2040026##k. Will you teleport to #b#p%d##k at the %s floor?", Items.EOS_ROCK_SCROLL, stoneId, floor), () -> {
                            exchange(() -> user.transferField(fieldId, "go00"), String.format("You cannot enable #b#p2040025##k without #b#t%d##k.", Items.EOS_ROCK_SCROLL),
                                    0, Items.EOS_ROCK_SCROLL, -1);
                        });
                    });
        } else {
            say("There's a rock that will enable you to teleport to #b#p2040025##k or #b#p2040027##k, but it cannot be activated without the scroll.");
        }
    }
}
