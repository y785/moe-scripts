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

package moe.maple.scripts.npc.boats;

import moe.maple.api.script.logic.event.PolledScriptEvent;
import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Script(name = "crane",
        description = "The crane NPC. He's lewd.",
        field = {250000100, 200000141, 251000000})
public class Crane extends NpcScript {

    private static final Logger log = LoggerFactory.getLogger( Crane.class );

    private void workFieldSet(int cost, String fsName) {
        if (user.hasMoney(cost)) {
            server.getFieldSet(fsName).ifPresentOrElse(fs -> {
                var response = fs.enter(user.getId(), 0);
                if (response != 0)
                    say("Someone else is going to Mu Lung right now. Talk to me in a moment.");
                else
                    user.decreaseMoney(cost);
            }, () -> {
                log.warn("FieldSet({}) is missing, this is bad. Script isn't going to work.", fsName);
            });
        } else {
            say("Are you sure you have enough mesos?");
        }
    }

    private void workMu(boolean beginner) {
        setEscapeEvent((PolledScriptEvent)(s) -> say("Ok. If you ever change your mind, please let me know."));
        workFieldSet(beginner ? 600 : 6000, "Crane_SS");

        var costToOrbis = beginner ? 600 : 6000;
        var costToHerb = beginner ? 500 : 1500;

        askMenu("Heya! You having fun with your travels? It must suck to not have wings, but I'm here to help. Where do you wanna go?", String.format("Orbis (%,d mesos)", costToOrbis), String.format("Herb Town (%,d mesos)", costToHerb)).andThen(sel -> {
            if (sel == 0)
                workFieldSet(costToOrbis, "Crane_MR");
            else
                if (user.decreaseMoney(costToHerb))
                    user.transferField(251000000);
                else
                    say("Are you sure you have enough mesos?");
        });
    }

    private void workOrbis(boolean beginner) {
        setEscapeEvent((PolledScriptEvent)(s) -> say("Ok. If you ever change your mind, please let me know."));

        var cost = beginner ? 600 : 6000;
        askMenu("Heya! You having fun with your travels? It must suck to not have wings, but I'm here to help. Where do you wanna go?", String.format("Mu Lung(%,d mesos)", cost)).andThen(sel -> {
            workFieldSet(cost, "Crane_MR");
        });
    }

    private void workHerb(boolean beginner) {
        var cost = beginner ? 500 : 1500;

        askYesNo(String.format("Hello there? I'm the crane that flies from #bOrbis#k to #bMu Lung#k and back. I fly around all the time, so I figured, why not make some money by taking travelers like you along for a small fee? It's good business for me. Anyway, what do you think? Do you want to fly to #bMu Lung#k right now? I only charge #b%,d mesos#k.", cost), () -> {
            if (user.decreaseMoney(cost))
                user.transferField(250000100);
            else
                say("Are you sure you have enough mesos?");
        }, () -> {
            say("Ok. If you ever change your mind, please let me know.");
        });
    }

    @Override
    protected void work() {
        var beginner = user.isBeginner();
        var fieldId = user.getFieldId();

        if (fieldId == 250000100) // Mu Lung Temple: Mu Lung
            workMu(beginner);
        else if (fieldId == 200000141) // Cabin<To Mu Lung>: Orbis
            workOrbis(beginner);
        else if (fieldId == 251000000) // Herb Town: Herb Town
            workHerb(beginner);
    }
}
