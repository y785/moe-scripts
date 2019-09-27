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

package moe.maple.scripts.npc.victoria.sleepywood;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

@Script(name = "hotel1", description = "Sleepywood hotel", field = 105040400)
public class Hotel extends NpcScript {
    @Override
    protected void work() {
        say("Welcome, We're the #m105040300# Hotel. Our hotel works hard to serve you the best at all times. If you are tired and worn out from hunting, how about a relaxing stay at our hotel?").andThen(() -> {
            askMenu("We offer two kinds of rooms for service. Please choose the one of your liking.", "Regular sauna(499 mesos per use)", "VIP sauna(999 mesos per use)").andThen(sel -> {
                var vip = sel != 0;
                var cost = vip ? 999 : 499;
                var targetField = vip ? 105040402 : 105040401;
                var prompt = vip ? "You've chosen the VIP sauna. Your HP and MP will recover even faster than that of the regular sauna and you can even find a special item in there. Are you sure you want to go in?"
                        : "You've chosen the ordinary sauna. Your HP and MP will recovery quickly and you can even buy some items there. Are you sure you want to go in?";
                askYesNo(prompt, () -> {
                    if (user.decreaseMoney(cost))
                        user.transferField(targetField);
                    else
                        say("Excuse me. It looks like you're poor. It costs at least %d mesos to stay here.", cost); // wtf, why don't you have <1k mesos?
                }, () -> {
                    say("We also offer other types of service. Please think carefully and then decide."); // Other types of "service"? *wink*
                });
            });
        });
    }
}
