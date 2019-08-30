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

package moe.maple.scripts.portal;

import moe.maple.api.script.model.PortalScript;
import moe.maple.api.script.model.Script;

@Script(name = "rankRoom")
public class RankRoom extends PortalScript {
    @Override
    protected void work() {
        var portal = "out00";
        var warpTo = 0;

        switch (user.getFieldId()) {
            case 130000000: // KoC
                portal = "east00";
                warpTo = 130000100;
            case 130000200: // KoC
                portal = "west00";
                warpTo = 130000100;
                break;
            case 100000201: // Bowman
                warpTo = 100000204;
                break;
            case 101000003: // Magician
                warpTo = 101000004;
                break;
            case 102000003: // Warrior
                warpTo = 101000004;
                break;
            case 103000003: // Thief
                warpTo = 103000008;
                break;
            case 140010100: // Aran
                warpTo = 140010110;
                break;
            default:
                balloon("Heyo! I don't know where I'm meant to go, message staff.");
                return;
        }

        playPortalSE();
        user.transferField(warpTo, portal);
    }
}
