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

package moe.maple.scripts.npc.victoria.ellinia;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

/* Translated directly from BMS */
@Script(name = "herb_in", description = "Herb Jump Quest 'in' NPC")
public class HerbIn extends NpcScript {

    @Override
    protected void work() {
        final var noMessage = "You want to go in? Must have heard that there's a precious medicinal herb in here, huh? But I can't let some stranger like you who doesn't know that I own this land in. I'm sorry but I'm afraid that's all there is to it.";

        var qh = user.getQuestHolder();
        var state1 = qh.getState(2050);
        var state2 = qh.getState(2051);

        if (state2 == 0) {
            if (state1 == 1) {
                var price = user.getLevel() * 100;
                askYesNo(String.format("So you came here at the request of #b#p1061005##k to take the medicinal herb? Well...I inherited this land from my father and I can't let some stranger in just like that... But, with #r%,d mesos#k, it's a whole different story... So, do you want to pay your way in?", price), () -> {
                    if (user.decreaseMoney(price)) user.transferField(101000100);
                    else say(String.format("Lacking mesos by any chance? Make sure you have more than #r%,d mesos#k on hand. Don't expect me to give you any discounts.", price));
                }, () -> say("I see... but understand that you can't get in here for free."));
            } else if (state1 == 2) {
                say("It's you again. Is #p1061005# busy making diet pills? Anyway, honestly, I was a little shocked that you were able to get through this place. For that, I'll let you enter free of charge. You might be able to get some precious items deep inside...")
                        .andThen(() -> user.transferField(101000100));
            } else {
                say(noMessage);
            }
        } else if (state2 == 1) {
            var price = user.getLevel() * 200;
            askYesNo(String.format("It's you from the other day. Did #b#p1061005##k make another request to you? What? You need to go in further this time? Hmm... it's pretty dangerous in there, but... alright, for #r%,d mesos#k I'll let you in that deep. So, do you want to pay your way in?", price), () -> {
                if (user.decreaseMoney(price)) user.transferField(101000102);
                else say(String.format("Lacking mesos by any chance? Make sure you have more than #r%,d mesos#k on hand. Don't expect me to give you any discounts.", price));
            }, () -> say("I see... but understand that you can't get in here for free."));
        } else if (state2 == 2) {
            say("It's you again. Is #p1061005# busy making anti-aging serum? Anyway, honestly, I was a little shocked that you were able to get through this place. For that, I'll let you enter free of charge. You might be able to get some precious items deep inside...",
                    "Oh, by the way... once, #p1032100# of this town had secretly gone inside, and when I caught her, she was so taken aback that she lost her #p1032100# in there. I tried to look for it but couldn't figure out where it was... Do you think you can go in and find it?")
                    .andThen(() -> user.transferField(101000102));
        } else {
            say(noMessage);
        }
    }
}
