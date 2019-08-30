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

package moe.maple.scripts.npc.aqua;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.ScriptFormatter;

@Script(name = "aqua_taxi", description = "The shady dolphin in aqua")
public class AquaTaxi extends NpcScript {
    final int ticket = 4031242;
    final int cost = 10000;

    private void coupon() {
        final int villageCost = user.isBeginner() ? cost / 10 : cost;
        askMenu("The oceans are all connected to each other. The places you can not walk to can easily go by the sea. What do you think of taking the #bTaxi-Dolphin#k with us today?",
                ScriptFormatter.format("I will use #e#t{}##n to go to #eThe Sharp Unknown#n.", ticket),
                ScriptFormatter.format("Go to #eHerb Village#n after paying {} mesos.", String.format("%,d", villageCost))).andThen(sel -> {
                    if (sel == 0) {
                        if (user.exchange(0, ticket, -1)) {
                            user.transferField(230030200, "st00");
                        } else {
                            say("I don't think you have #b#t{}## with you. There must be a way to get the #b#t{}## through Camilla of Henesys...", ticket, ticket);
                        }
                    } else {
                        if (user.decreaseMoney(villageCost)) {
                            user.transferField(251000100);
                        } else {
                            say("I don't think you have enough money...");
                        }
                    }
        });
    }

    private void normal() {
        final int villageCost = user.isBeginner() ? cost / 10 : cost;
        final int unknownCost = (villageCost / 10) / (user.isBeginner() ? 10 : 1);

        var message = user.isBeginner() ? "The oceans are all connected to each other. The places where you can not go on foot you can easily go by the sea. What do you think about taking the #bTaxi-Dolphin#k with us today? We have special tickets with 90% discount for beginners!" : "The oceans are all connected to each other. The places where you can not go on foot you can easily go by the sea. What do you think about taking the #bTaxi-Dolphin#k with us today?";

        askMenu(message, ScriptFormatter.format("Go to #eThe Sharp Unknown#n for {} mesos", String.format("%,d", unknownCost)), ScriptFormatter.format("Go to #eHerb Village#n after paying {} mesos.", String.format("%,d", villageCost))).andThen(sel -> {
            if (sel == 0) {
                if (user.decreaseMoney(unknownCost)) {
                    user.transferField(230030200, "st00");
                } else {
                    say("I don't think you have enough money...");
                }
            } else {
                if (user.decreaseMoney(villageCost)) {
                    user.transferField(251000100);
                } else {
                    say("I don't think you have enough money...");
                }
            }
        });
    }

    @Override
    protected void work() {
        if (user.hasItem(ticket)) {
            coupon();
        } else {
            normal();
        }
    }
}
