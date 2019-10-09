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

package moe.maple.scripts.npc.xmas;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.Naughty;
import moe.maple.scripts.util.fields.Victoria;

@Script(name = "go_victoria", description = "Rupi(2002000): Happyville exit", field = 209000000)
public class GoVictoria extends NpcScript {

    private void work(int returnTo, boolean xmas) {
        askYesNo(String.format("Oh, you have finished your business here? Would you like to get back to #m%d#?  I can send you have to #m%d# any time you want. Would you like to go back now?", returnTo, returnTo), () -> {
            user.removeItem(1472063); // Snow Mitten
            user.removeItem(2060005); // Snowball
            user.removeItem(2060006); // Big Snowball

            if (xmas)
                user.transferField(returnTo, "xmas00");
            else
                user.transferField(returnTo);
        }, () -> {
            say("You must have some business to settle here, right? That's probably the best idea to take a break in this area before going back.");
        });
    }

    @Override
    protected void work() {
        var xmas = user.getScriptVariable("happyexit");
        if (xmas == null || xmas.isEmpty())
            work(Naughty.toInt(user.getScriptVariable("unityPortal"), Victoria.Ellinia), false);
        else
            work(Naughty.toInt(xmas, Victoria.Ellinia), true);
    }
}
