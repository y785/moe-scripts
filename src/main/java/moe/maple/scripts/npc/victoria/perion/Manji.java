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

package moe.maple.scripts.npc.victoria.perion;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.ScriptFormatter;

@Script(name = "Manji", description = "Manji in perion, at the top of the map, being emo")
public class Manji extends NpcScript {
    @Override
    protected void work() {
        var cost = 10000;

        askYesNo(ScriptFormatter.format("What? You want a shot at sealing Balrog? A weakling like you might not make it back in one piece! Well, I suppose it isn't my business. Alright, you need to pay a fee of #b{} Mesos#k. Do you have enough Mesos on you?", String.format("%,d", cost)), () -> {
            say("Alright, don't disappoint me now. You'll be able to participate in the Expedition Team if you visit my apprentice #bMu Young#k, upon your arrival.").andThen(() -> {
                if (user.decreaseMoney(cost)) {
                    user.transferField(105100100);
                } else {
                    say("Ah, you're aware of how precious life is, don't you? Stop wasting my time and leave.");
                }
            });
        }, () -> {
            say("Ah, you're aware of how precious life is, don't you? Stop wasting my time and leave.");
        });
    }
}
