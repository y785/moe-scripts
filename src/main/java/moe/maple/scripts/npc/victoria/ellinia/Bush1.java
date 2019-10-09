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
@Script(name = "bush1")
public class Bush1 extends NpcScript {
    private final int[] rewards = new int[]{4010000, 4010001, 4010002, 4010003, 4010004, 4010005, 4020000, 4020001, 4020002, 4020003, 4020004, 4020005, 4020006};

    private int getRandomReward() {
        var rand = ThreadLocalRandom.current().nextInt(0, rewards.length);
        return rewards[rand];
    }

    @Override
    protected void work() {
        var qh = user.getQuestHolder();
        var state = qh.getState(2050);

        if (user.getLevel() > 24) {
            if (state == 1) {
                askYesNo("Are you sure you want to take #b#t4031020##k with you?", () -> {
                    exchange(() -> user.transferField(101000000),
                            "Your etc. inventory seems to be full. Please make room in order to take the item.",
                            0, 4031020, 1);
                });
            } else if (state == 2) {
                if (user.getHoldCount(4) > 0) {
                    var reward = getRandomReward();
                    exchange(() -> user.transferField(101000000),
                            "Your etc. inventory seems to be full. Please make room in order to take the item.",
                            0, reward, 2);
                } else {
                    say("You need to have at least one free slot available on your etc. inventory to keep the item you found in the midst of flowers. Please make room and then try again.");
                }
            } else {
                say("In the bush you find flowers with mysterious energy, but since there was no explanation from #b#p1061005##k about them, there is no way to know which flower to take...");
            }
        } else {
            say("In the bush you find flowers with mysterious energy, but a strange aura around them makes it impossible for us to pick them up.");
        }
    }
}
