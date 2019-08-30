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

package moe.maple.scripts.portal.freemarket;

import moe.maple.api.script.model.PortalScript;
import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.Naughty;

/**
 * Oh boy! All the portals in one!
 */
@Script(name = {"market00", "market01", "market02", "market03", "market04", "market05", "market06", "market07", "market08", "market09", "market10", "market11", "market12", "market13", "market14", "market15", "market16", "market17", "market18", "market19", "market20", "market21", "market22", "market23", "market24"}, description = "Free Market exit portal")
public class Market00 extends PortalScript {
    @Override
    protected void work() {
        var variable = user.getScriptVariable("market");

        var returnMap = Naughty.toInt(variable, 100000100);
        var portal = returnMap == 600000000 || returnMap == 103050000 ? "market00" : "st00";

        playPortalSE();
        user.transferField(returnMap, portal);
    }
}
