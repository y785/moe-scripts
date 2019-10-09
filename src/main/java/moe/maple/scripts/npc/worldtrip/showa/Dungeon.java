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

package moe.maple.scripts.npc.worldtrip.showa;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.FieldSetObject;
import moe.maple.api.script.model.object.user.QuestObject;
import moe.maple.scripts.util.Naughty;

import java.time.Duration;
import java.time.Instant;

@Script(name = "s_dungeon", description = "Npc: 9120201")
public class Dungeon extends NpcScript {

    private final int ITEM_COMB = 4000138;
    private final int ITEM_BIG_BOS_FLASHLIGHT = 4000141;

    private void work(FieldSetObject fieldset) {
        var qh = user.getQuestHolder();

        var qTime = Naughty.toLong(user.getScriptVariable("shouwaBoss"), Instant.MIN.toEpochMilli());
        var time = Duration.between(Instant.ofEpochMilli(qTime), Instant.now()).getSeconds();

        var users = fieldset.getUserCount();
        var result = fieldset.getVar("shouwaBoss");

        if (user.hasItem(ITEM_COMB)) {
            if (time >= 1440) { // Every 24 hours buddy.
                if (result == "yes") {
                    say("I'm sorry, but the battle has already begun, and for your safety, I must ask that you remain outside for now.");
                } else {
                    askYesNo("Hey hey! That item you have there...isn't that our boss's comb!? Holy cow! I knew it! As soon as I saw you, I knew you would be the one defiant enough to take on the Boss. Are you sure? He's not going to give up without a fight--the evil spirit within him will ensure that. Do you want to take on the Boss?", () -> {
                        askYesNo("Are you sure you're going to enter this room? Just remember, you can't stay here forever, and if you place our boss's comb on top of the treasure chest, the thugs will pounce on you, so be careful! ", () -> {
                            if (users >= 20) {
                                user.setScriptVariable("shouwaBoss", String.valueOf(Instant.now().toEpochMilli()));
                                user.transferField(801040100);
                            } else {
                                say("A lot of brave fighters are currently inside battling the evil spirits who've possessed our leaders. The room can only hold 20 people at once. You'll have to wait your turn for now.");
                            }
                        }, () -> say("Really? Then let me know if you ever change your mind. "));
                    }, () -> say("Really? Then let me know if you ever change your mind. "));
                }
            } else {
                say("Our enemies are outside! Let's wait here!");
            }
        } else {
            say("So you've made it here. Not bad. You'll be taking on the boss now! I'm concerned as to whether you take on the mighty boss with your abilities ... don't get me wrong, our Boss couldn't handle her either. If you, by any chance, take down the boss and bring back her comb with you, then I'll take you to the next stage. ");
        }
    }

    private void precheck(FieldSetObject fieldset) {
        if (user.removeItem(ITEM_BIG_BOS_FLASHLIGHT)) {
            say("Hey, hey! It's dangerous to carry around a flashlight like that! It's going to cause a fire! I'll take care of it. cant' be too careful around here...")
                    .andThen(() -> work(fieldset));
        } else {
            work(fieldset);
        }
    }

    @Override
    protected void work() {
        server.getFieldSet("shouwaBoss").ifPresentOrElse(fs -> work(), () -> {
            say("I'm missing a fieldset. This is a bug, contact a developer.");
        });
    }
}
