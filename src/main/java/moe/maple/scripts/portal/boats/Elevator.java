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

package moe.maple.scripts.portal.boats;

import moe.maple.api.script.model.PortalScript;
import moe.maple.api.script.model.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Script(name = "elevator", description = "Elevator script for Helios Tower 99th -> 2nd floor")
public class Elevator extends PortalScript {

    private static final Logger log = LoggerFactory.getLogger( Elevator.class );

    @Override
    protected void work() {
        var fid = field.getId();
        var topFloor = 222020200;
        var btmFloor = 222020100;

        if (fid == topFloor)
            if (server.getContiState(topFloor) == 1) user.transferField(222020210, "st00");
            else balloon("At the moment, the elevator is not available. Please try again later.");
        else if (fid == btmFloor)
            if (server.getContiState(btmFloor) == 1) user.transferField(222020110, "st00");
            else balloon("At the moment, the elevator is not available. Please try again later.");
        else log.warn("Unhandled script condition... Field: {}", fid);
    }
}
