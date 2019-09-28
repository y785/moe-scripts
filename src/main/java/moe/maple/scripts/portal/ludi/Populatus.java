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

package moe.maple.scripts.portal.ludi;

import moe.maple.api.script.model.PortalScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.FieldSetObject;
import moe.maple.scripts.util.Naughty;

import java.time.Duration;
import java.time.Instant;

@Script(name = "Populatus00",
        description = "The portal that leads to the boss map")
public class Populatus extends PortalScript {

    private final int LUDIBRIUM_MEDAL = 4031172;

    private final String USER_SCRVAR_LAST_ENTER = "ludibossT";
    private final String USER_SCRVAR_LAST_COUNT = "ludibossC";

    private void enter() {
        playPortalSE();
        user.transferField(220080001, "st00");
    }

    private void work(FieldSetObject fs) {
        var now = System.currentTimeMillis();
        var result = fs.getVar("ludiboss");
        var userCount = fs.getUserCount();

        var lastRun = Naughty.toLong(user.getScriptVariable(USER_SCRVAR_LAST_ENTER), 0L);
        var count = user.getScriptVariable(USER_SCRVAR_LAST_COUNT);

        if (result.equals("yes")) {
            balloon("The battle against Papulatus has already begun, so you may not enter this place.");
        } else if (userCount < 12) {
            balloon("The room is already in full capacity with people battling against Papulatus.");
        } else if (user.hasItem(LUDIBRIUM_MEDAL)) {
            var diff = (now - lastRun) / 1000;
            if (diff >= 1440 * 60 || count.equals("")) {
                user.setScriptVariable(USER_SCRVAR_LAST_ENTER, String.valueOf(now));
                user.setScriptVariable(USER_SCRVAR_LAST_COUNT, "1");
                enter();
            } else {
                if (count.equals("1")) {
                    user.setScriptVariable(USER_SCRVAR_LAST_COUNT, "2");
                    enter();
                } else {
                    balloon("You can only enter The Origin of Clocktower twice a day.");
                }
            }
        }
    }

    @Override
    protected void work() {
        server.getFieldSet("Populatus").ifPresentOrElse(this::work, () -> {
            balloon("I'm missing the populatus fieldset UwU");
        });
    }
}
