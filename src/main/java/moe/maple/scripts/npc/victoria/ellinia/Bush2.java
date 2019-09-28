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

import java.util.concurrent.ThreadLocalRandom;

/**
 * See {@link HerbIn}
 */
@Script(name = "bush2")
public class Bush2 extends NpcScript {
    @Override
    protected void work() {
        var qh = user.getQuestHolder();
        var state = qh.getState(2051);

        if (user.getLevel() > 49) {
            if (state == 1) {
                askYesNo("Are you sure you want to take #b#t4031032##k with you?", () -> {
                    exchange(() -> user.transferField(101000000),
                            "Your etc. inventory seems to be full. Please make room in order to take the item."
                            , 0, 4031032, 1);
                });
            } else if (state == 2) {
                var itemId = 0;
                var itemCount = 2;
                var rand = ThreadLocalRandom.current().nextInt(1, 31);
                if (rand > 0 && rand < 11) {
                    itemId = 4020007;
                } else if (rand > 10 && rand < 21) {
                    itemId = 4020008;
                } else if (rand > 20 && rand < 30) {
                    itemId = 4010006;
                } else {
                    itemId = 1032013;
                    itemCount = 1;
                }
                exchange(() -> user.transferField(101000000),
                        "You need to make some room for your equipment and etc. inventory in order to take in the items you've discovered at the herbal bush. Please check again after making the adjustment.",
                        0, itemId, itemCount);
            } else {
                say("In the bush you find herbs with mysterious energy, but since there was no explanation from #b#p1061005##k about them, there is no way to know which herb to take...");
            }
        } else {
            say("In the bush you find herbs with mysterious energy, but a strange aura around them makes it impossible for us to pick them up.");
        }
    }
}
