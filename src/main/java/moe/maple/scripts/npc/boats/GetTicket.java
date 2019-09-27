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

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.FieldObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * There are quite a few differences compared to GMS.
 * 1) Map capacity isn't checked.
 * 2) Boat state instead of an off-the-hour approach.
 */
@Script(name = "get_ticket")
public class GetTicket extends NpcScript {

    private void work(int targetField, int ticketId) {
        final var FULL_RIDE_CAPACITY = 50;

        if (server.getField(targetField).map(FieldObject::getUserCount).orElse(0) >= FULL_RIDE_CAPACITY) {
            say("I'm sorry, but this ride is already FULL. We can't accept more passengers. Please board the next boat.");
        } else {
            askYesNo("It looks like there's plenty of room for this ride. Please have your ticket ready so I can let you in. The ride will be long, but you'll get to your destination just fine. What do you think? Do you want to get on this ride?", () -> {
                if (user.exchange(0, ticketId, -1)) {
                    user.transferField(targetField);
                }
            }, () -> say("You must have some business to deal with here, right?"));
        }
    }

    @Override
    protected void work() {
        var beginner = user.isBeginner();
        var fid = field.getId();
        var state = server.getContiState(fid);

        if (state == 2) {
            // todo handle period between boarding -> moving
        } else if (state == 1) {
            var target = fid + 1;
            if (fid == 101000300 || fid == 200000111) {
                // Orbis <-> Ellinia
                var ticket = fid == 101000300 ? 4031045 : 4031047;
                if (beginner) --ticket;
                work(target, ticket);
            } else if (fid == 200000121 || fid == 220000110) {
                // Orbis <-> Ludibrium
                var ticket = fid == 200000121 ? 4031074 : 4031045;
                if (beginner) --ticket;
                work(target, ticket);
            } else if (fid == 200000131 || fid == 240000110) {
                // Orbis <-> Leafre
                var ticket = fid == 200000131 ? 4031331 : 4031045;
                if (beginner) --ticket;
                work(target, ticket);
            } else if (fid == 200000151 || fid == 260000100) {
                // Orbis <-> Ariant
                var ticket = fid == 200000151 ? 4031576 : 4031045;
                if (beginner) -- ticket;
                work(target, ticket);
            }
        } else {
            say("We will begin boarding 5 minutes before departure. Please be patient and wait a few minutes.");
        }
    }
}
