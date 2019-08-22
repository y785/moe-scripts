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

package moe.maple.scripts.npc.victoria.amoria;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.ScriptFormatter;

@Script(name = "EngageRing", description = "Moony in Amoria")
public class EngageRing extends NpcScript {

    private void engagement() {
        // todo
    }

    private void annulment() {
        final int cost = 500000;
        say("The only way for a married couple to annul the marriage is by destroying their Wedding rings, and that's where I come in. If you are really sure of breaking this eternal bond, then please remove the rings from your hand, and then let me know.").andThen(() -> {
            askYesNo(ScriptFormatter.format("How unfortunate. I can end your marriage via annulment by breaking your ring, but please reemmber you will no longer be married after this. My annulment fee for marriage is #b{} mesos#k. As you know, I don't take checks. Are you sure you want to get your marriage annulled?", String.format("%,d", cost)), () -> {
                // todo, check rings, remove money, do the dew
                say("All done... remember to head back to Amoria if you find true love!");
            }, () -> {
                say("Fair enough, I can do it anytime, provided you're ready to go through with it. Come back if you change your mind.");
            });
        });
    }

    @Override
    public void work() {
        askMenu("Have you found true love? If so, I can make you a ring worthy of your devotion...", "I would like to make an engagement ring for my lover.", "I want an annulment.").andThen(sel -> {
            if (sel == 0) {
                engagement();
            } else {
                annulment();
            }
        });
    }
}
