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

package moe.maple.scripts.npc.mapleisland;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

@Script(name = "begin7", description = "Southperry | Shanks")
public class Begin7 extends NpcScript {
    @Override
    protected void work() {
        final int letter = 4031801;
        final int cost = 150;
        final int warpTo = 2010000;

        askYesNo("Take this ship and you'll head off to a bigger continent. For #e150 mesos#n, I'll take you to #bVictoria Island#k. The thing is, once you leave this place, you can't ever come back. What do you think? Do you want to go to Victoria Island?", () -> {
            if (user.getLevel() >= 7) {
                if (user.hasItem(4031801)) {
                    say("Okay, now give me 150 mesos... Hey, what's that? Is that the recommendation letter from Lucas, the chief of Amherst? Hey, you should have told me you had this. I, Shanks, recognize greatness when I see one, and since you have been recommended by Lucas, I see that you have a great, great potential as an adventurer. No way would I charge you for this trip!",
                            "Since you have the recommendation letter, I won't charge you for this. Alright, buckle up, because we're going to head to Victoria Island right now, and it might get a bit turbulent!!").andThen(() -> {
                        if (user.removeItem(letter)) {
                            user.transferField(warpTo);
                        } else {
                            say("What? You're telling me you wanted to go without the letter? You're one weirdo...");
                        }
                    });
                } else {
                    say("Bored of this place? Here... Give me #e{} mesos#n first...", cost).andThen(() -> {
                        if (user.decreaseMoney(cost)) {
                            say("Awesome! #e{}#n mesos accepted! Alright, off to Victoria Island!", cost).andThen(() -> {
                                user.transferField(warpTo);
                            });
                        } else {
                            say("What? You're telling me you wanted to go without any money? You're one weirdo...");
                        }
                    });
                }
            } else {
                say("Let's see... I don't think you are strong enough. You'll have to be at least #bLevel 7#k to go to #bVictoria Island.#k");
            }
        }, () -> {
            say("Hmm... I guess you still have things to do here?");
        });
    }
}
