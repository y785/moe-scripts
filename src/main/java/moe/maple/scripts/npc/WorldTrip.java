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

package moe.maple.scripts.npc;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.With;
import moe.maple.api.script.util.builder.ScriptStringBuilder;
import moe.maple.scripts.util.Naughty;

import java.util.function.Predicate;

import static moe.maple.scripts.util.Naughty.numeric;

@Script(name = "world_trip", description = "Spinel - World Tour Guide")
public class WorldTrip extends NpcScript {

    private boolean isCorrectField(int fieldId) {
        switch (fieldId) {
            case 100000000:
            case 101000000:
            case 102000000:
            case 103000000:
            case 104000000:
            case 200000000:
            case 220000000:
            case 680000000:
            case 250000000:
            case 240000000:
                return true;
            default:
                return false;
        }
    }

    private void workCorrectField() {
        var ssb = new ScriptStringBuilder();
        final int cost = user.isBeginner() ? 300 : 3000;

        ssb.append("If you're tired of the monotony of daily life, it might be time for a change, and the #bMaple Travel Agency#k is here to give your life a new sense of adventure! For a low, low fee, we can offer you a #bWorld Tour#k that will make your dreariest days sparkle! ");
        if (user.isBeginner()) {
            ssb.appendFormat("The #bMaple Travel Agency#k has a special offer for beginners - a fee of #b{} mesos#k", cost);
        } else {
            ssb.appendFormat("Our standard travel package is ONLY #b{} mesos!#k", cost);
        }
        ssb.append("You can view our always expanding list of destinations for your traveling pleasure below. Make your selection and I'll be there to serve you as your travel guide.");
        ssb.blue().newLine().appendMenu("Take me to the #bMushroom Shrine of Japan#k");

        askMenu(ssb.toString()).andThen(sel -> {
            if (sel == 0) {
                say("You're interested in the #bMushroom Shrine of Japan#k? If you desire to feel the essence of Japan, there's nothing like visitng the Shrine, a Japanese cultural melting pot. #bMushroom Shrine#k is a mythical place that serves the incomparable #eMushroom God#n from ancient times.",
                        "Be sure to check out the cute female shaman serving the Mushroom God. I strongly recommend trying Takoyaki, Yakisoba, and other delcicious food sold in the streets of Japan. Now, let's head over to #bMushroom Shrine#k, a mythical place if there ever was one.").andThen(() -> {
                    if (user.decreaseMoney(cost)) {
                        user.setScriptVariable(Constants.VARIABLE_ID, user.getFieldId());
                        user.transferField(800000000, "st00");
                    } else {
                        say("...what's this? You can't pay the fee..? Well, the #bMaple Travel Agency#k is a business after all. It isn't very expensive, just {} mesos. Perhaps you could perform some odd job quests and get back to me?", cost);
                    }
                });
            }
        });
    }

    @Override
    public void work() {
        var variable = user.getScriptVariable(Constants.VARIABLE_ID);

        final int returnMap = Naughty.toInt(variable, 100000000);

        if (isCorrectField(user.getFieldId())) {
            workCorrectField();
        } else if (user.getFieldId() == 800000000) {
            askMenu("How's the traveling? Are you enjoying it?", "Yes, I'm done with traveling. Can I go back to #m" + returnMap + "#?", "No, I'd like to continue exploring this place.").andThen(sel -> {
                if (sel == 0) {
                    user.setScriptVariable(Constants.VARIABLE_ID, "");
                    user.transferField(returnMap);
                } else {
                    say("If you ever change your mind, please let me know.");
                }
            });
        } else {
            say("I'm not scripted for this map. Tell someone on staff where you found me, please.");
        }
    }

    private static class Constants {
        public static final String VARIABLE_ID = "world_trip";
    }
}
