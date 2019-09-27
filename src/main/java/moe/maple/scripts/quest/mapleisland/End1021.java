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

package moe.maple.scripts.quest.mapleisland;

import moe.maple.api.script.model.QuestScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.ScriptFormatter;
import moe.maple.api.script.util.tuple.Tuple;

@Script(name = "q1021e", description = "Roger's apple :wink:")
public class End1021 extends QuestScript {

    private final int apple = 2010007;
    private final int health = 25;

    @Override
    protected void work() {
        if (user.hasItem(2010007)) {
            // say("JUST EAT THE APPLE YOU SLU**Ahem**");
            say("Yo... I told you to eat the #r#t{}##k I gave you. Open the item wndow (press the #eI#n key) and click on the #bUSE# tab. Locate the #t{}#, it takes two clicks to use.", apple, apple);
        } else {
            if (user.getHealthCurrent() >= health) {
                final var file = "#fUI/UIWindow.img/QuestIcon/";
                say("Told you! How easy was it to consume the item? You can set a #bhotkey#k by dragging active items to the bottom right 'quick slot' menu. Bet you didn't know that!\\r\\nOh, and if you are a beginner, HP will automatically recover itself as time goes by. It takes a while but it's a strategy that may help you in the future.",
                        "Alright! Now that you have learned alot, I will give you a present. This is a must for your travel in Maple World, so thank me! Please use this under emergency cases!",
                        Moematter.format("Okay, this is all I can teach you. I know it's sad but it is time to say good bye. Well take care if yourself and Good luck my friend!\\r\\n\\r\\n{}4/0#\\r\\n#v2010000# 3 #t2010000#\\r\\n#v2010009# 3 #t2010009#\\r\\n\\r\\n{}8/0# 10 exp", file, file)).andThen(() -> {
                            if (user.exchange(0, Tuple.of(2010000, 9), Tuple.of(2010009, 9))) {
                                if (completeQuest()) {
                                    say("Move along to the portal!");
                                    user.increaseExp(10);
                                } else {
                                    say("Oh no! I'm broken!");
                                }
                            } else {
                                say("Wow! Many items in your inventory ....");
                            }
                });
            } else {
                say("Hey, your HP has not fully recovered yet. Did you eat every #t{}# I gave you?", apple);
            }
        }
    }
}
