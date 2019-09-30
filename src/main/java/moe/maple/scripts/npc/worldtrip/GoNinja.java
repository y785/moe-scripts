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

package moe.maple.scripts.npc.worldtrip;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

@Script(name = "goNinja", description = "Palanquin (9110107), ninja castle teleporter", field = {800000000, 800040000})
public class GoNinja extends NpcScript {

    @Override
    protected void work() {
        var tpTo = field.getId() == 800000000 ? 800040000 : 800000000;
        say("We are the bearers of palankeen~! Let the bearers take you anywhere, even to Sakura's Ninja Castle~!").andThen(() -> {
            askYesNo("Oh what? What is it? Do you want to go visit Ninja Castle", () -> {
                say("Okay, I got it! Just let us do the work, and you'll get there in the blink of an eye! Oh, and this won't cost you any money. Today's a good day for me, so I'll just let you get on it for free! Now, doesn't that make you feel good or what? Anyway, off we go!").andThen(() -> {
                    user.transferField(tpTo);
                });
            });
        });
    }
}
