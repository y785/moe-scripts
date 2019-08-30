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

package moe.maple.scripts.portal.victoria;

import moe.maple.api.script.model.PortalScript;
import moe.maple.api.script.model.Script;

@Script(name = "enterMagicLibrar")
public class EnterMagicLibrary extends PortalScript {
    @Override
    protected void work() {
        // todo implement librar
        playPortalSE();
        if (user.getQuestHolder().isInProgress(20718)) { // "Maybe It's Grendel!"
            user.transferField(910110000, 8);
            // An alternate Magic Library used for the Cygnus Quest
            // Supposed to have a 10 minute time limit. Maybe a FieldSet?
        } else {
            user.transferField(101000003, 8);
        }
    }
}
