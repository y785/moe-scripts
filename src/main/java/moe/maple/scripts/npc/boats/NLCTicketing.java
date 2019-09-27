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

package moe.maple.scripts.npc.boats;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

@Script(name = "NLC_ticketing", description = "Subway ticket usher for Kerning City <-> NLC subway")
public class NLCTicketing extends NpcScript {

    @Override
    protected void work() {
        var fid = field.getId();
        if (fid == 600010002 || fid == 600010004) {
            var cityName = fid == 600010002 ? "New Leaf City" : "Kerning City";
            var target = fid == 600010004 ? 103000100 : 600010001;
            askYesNo(String.format("Do you want to go back to the %s station?", cityName), () -> {
                user.transferField(target);
            }, () -> say("You must have some business to take care of here, right?"));
        } else {
            var toKerning = fid == 600010001;
            var cityName = toKerning ? "Kerning City of Victoria Island" : "New Leaf City of Masteria";

            askMenu("Hello, would you like to buy a ticket for the subway?", cityName).andThen(sel -> {
                final var beginner = user.isBeginner();
                final var ticket = toKerning ? beginner ? 4031712 : 4031713 : beginner ? 4031710 : 4031711;
                final var cost = beginner ? 500 : 5000;

                askYesNo(String.format("The ride to %s takes off every 10 minutes, beginning on the hour, and it'll cost you #b%,d mesos#k. Are you sure you want to purchase #b#t%d#", cityName, cost, ticket), () -> {
                    if (!user.exchange(cost, ticket, 1))
                        say(String.format("Are you sure you have #b%,d mesos#k? If so, then I urge you to check your etc. inventory, and see if it's full or not.", cost));
                }, () -> {
                    say("Alright, see you next time. Take care.");
                });
            });
        }
    }
}
