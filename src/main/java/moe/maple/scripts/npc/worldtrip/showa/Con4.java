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

@Script(name = "con4", description = "Npc: 9120203", field = 801040101)
public class Con4 extends NpcScript {

    private void outro() {
        askYesNo("Do you want to return to Showa Town? ",
                () -> user.transferField(801000000),
                () -> say("That toughness of yours! That was awesome!! Well, if you  need to return to Showa Town, let me know. "));
    }

    @Override
    protected void work() {
        final var BIG_BOSS_FLASHLIGHT = 4000141;
        final var ELIXIR = 2000004;

        say("Oh wow, you did it! You know, that man sure stood firm. Hopefully this'll lead to some much-needed peace here, but I keep fearing for the worst. In any case, I'm just glad he's gone now. ")
                .andThen(() -> {
                    var count = user.getItemCount(BIG_BOSS_FLASHLIGHT);
                    if (count >= 1) {
                        say("That's right! The flashlight that the boss drops will be taken care of by me for future purposes. Now that we know who that really is, I feel like the peaceful days may be on its way. I have to admit, finding out the monster is indeed him... that caught me off guard.")
                                .andThen(() -> {
                                    if (user.exchange(0, BIG_BOSS_FLASHLIGHT, -count, ELIXIR, 100))
                                        say("CHEERS!").andThen(this::outro);
                                    else
                                        say("Check your item inventory and see if it is full or not. ");
                                });
                    } else {
                        outro();
                    }
                });
    }
}
