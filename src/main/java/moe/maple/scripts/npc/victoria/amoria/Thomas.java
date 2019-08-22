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
import moe.maple.scripts.util.Naughty;

@Script(name = "Thomas", description = "Thomas the trai-- Teleporter from hene <-> amoria")
public class Thomas extends NpcScript {

    private final int amoria = 680000000;

    @Override
    public void work() {
        // Normally this is only ever Henesys <-> Amoria
        // But :shrug:
        var variable = user.getScriptVariable("amoria");
        var returnMap = Naughty.toInt(variable, 100000000);

        if (user.getFieldId() == amoria) {
            askYesNo("I can take you back to your original location. Are you ready to go?", () -> {
             say("I hope you had a great time! See you around!").andThen(() -> {
                 user.transferField(returnMap);
             });
            }, () -> {
                say("Ok, feel free to hang around until you're ready to go.");
            });
        } else {
            askYesNo("I can take you to Amoria Village. Are you ready to go?", () -> {
                user.setScriptVariable("amoria", user.getFieldId());
                user.transferField(amoria);
            });
        }
    }
}
