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

import moe.maple.api.script.logic.chain.BasicActionChain;
import moe.maple.api.script.model.QuestScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.ScriptFormatter;

@Script(name = "q1021s", description = "Roger's apple :wink:")
public class Start1021 extends QuestScript {

    private final int apple = 2010007;
    private final int health = 25;

    private BasicActionChain intro() {
        var msg1 = user.isMale() ? "Hey, Man~ What's up? Haha! I am Roger who can teach you adorable new Maplers lots of information."
                : "Hey, Girl~ What's up? Haha! I'm #b#pRoger##k and it's my job to #efeed#n you, new travelers, a lot of information.";
        var msg2 = "You are asking who made me do this? Ahahahaha!\\r\\nMyself! I wanted to do this and just be kind to you new travellers.";
        return say(msg1, msg2);
    }

    private void rogered() {
        if (user.hasItem(apple)) {
            slapUser();
        } else {
            if (user.exchange(0, apple, 1)) {
                if (startQuest()) {
                    slapUser();
                } else {
                    say("Hmm.. I've detected a disturbance in the force.");
                }
            } else {
                say("Hey... #ryou're carrying a lot of things#k.\r\nHow is that possible anyway? You've got like, maybe two pockets tops.");
            }
        }
    }

    private void slapUser() {
        user.decreaseHealth(health);
        say(ScriptFormatter.format("Surprised? If your HP reaches #r0#k, then you are in trouble.\\r\\nTake this apple, we'll call it #r#t{}##k. Consume it and you will feel stronger.\\r\\nOpen the item window and double click it, come on!\\r\\nHey, it's very simple to open the item window...\\r\\nJust press #eI#n on your keyboard.", apple),
                ScriptFormatter.format("Go ahead and take the #r#t${}##k I gave you. You can see the red HP bar increase on the bottom of the screen. Talk to me again when you recover up to ${} HP.#I", apple, health));
    }

    @Override
    protected void work() {
        intro().andThen(() -> {
            askAccept("So... Let me cheer things up a bit! Abracadabra ~!", this::rogered, () -> {
                say("No? To think you just turned down a nice guy like me!");
            });
        });
    }
}
