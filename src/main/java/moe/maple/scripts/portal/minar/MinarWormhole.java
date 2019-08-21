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

package moe.maple.scripts.portal.minar;

import moe.maple.api.script.model.PortalScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.ScriptFormatter;

@Script(name = "minar_elli")
public class MinarWormhole extends PortalScript {
    @Override
    public void work() {
        var elliniaId = 101010000;
        var leafreId = 240010100; //Minar Forest : West Border

        var magicSeed = 4031346;

        if (user.hasItem(magicSeed) && user.exchange(0, magicSeed, -1)) {
            playPortalSE();
            if (user.getFieldId() == leafreId) {
                user.transferField(elliniaId, "minar00");
                message(ScriptFormatter.format("Using the Magical Seed, you have been transported to another place. You have {} seed(s) left.", user.getItemCount(magicSeed)));
            } else {
                user.transferField(leafreId, "elli00");
                message(ScriptFormatter.format("Using the Magical Seed, you have been transported to another place. You have {} seed(s) left.", user.getItemCount(magicSeed)));
            }
        } else {
            message("The Magical Seed is needed to go through the portal.");
        }
    }
}
