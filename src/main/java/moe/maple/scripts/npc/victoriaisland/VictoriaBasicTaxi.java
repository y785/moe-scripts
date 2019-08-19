/*
 * Copyright (C) 2019, y785, http://github.com/y785
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

package moe.maple.scripts.npc.victoriaisland;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.util.ScriptStringBuilder;
import moe.maple.api.script.util.With;
import moe.maple.api.script.util.tuple.Tuple;
import org.slf4j.helpers.MessageFormatter;

public abstract class VictoriaBasicTaxi extends NpcScript {
    protected int discount;

    public VictoriaBasicTaxi() {
        this.discount = 10;
    }

    protected void goTown(int fieldId, int cost) {
        if (cost > 0 && user.decreaseMoney(cost)) {
            user.transferField(fieldId);
            say("Have a safe journey!");
        } else {
            say("Come back when you've got #r{}#k more mesos and we'll talk.", cost - user.getMoney());
        }
    }

    protected void work(Tuple<Integer, Integer>... townsAndPrice) {
        say("How's it going? I drive the #b#p{}##k. If you want to get from one city to another quickly and safely, use our taxi.\r\nWe will be happy to take you wherever you wish, at an affordable price!", getSpeakerTemplateId()).andThen(() -> {
            ScriptStringBuilder ssb = new ScriptStringBuilder();
            var beginner = user.isBeginner();
            if (beginner)
                ssb.append("We have a special discount of {}% for beginners", 100 - discount);
            ssb.append("Select your destination. The rate varies from place to place...\r\n");


            ssb.blue();
            With.index(townsAndPrice, (tp, idx) -> {
                var town = tp.left();
                var cost = beginner ? tp.right() / discount : tp.right();
                ssb.appendMenuItem(idx, MessageFormatter.format("#m{}# ({} mesos)", town, cost).getMessage());
            });
            ssb.black();

            askMenu(ssb.toString()).andThen(idx -> {
                goTown(townsAndPrice[idx].left(), townsAndPrice[idx].right());
            });
        });
    }
}
