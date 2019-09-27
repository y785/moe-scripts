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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 200000122 : waiting at the ride for Orbis to Ludibrium
 * 220000111 : waiting at the ride for Ludibrium to Orbis
 * 101000301 : waiting at the ride for Ellinia to Orbis
 * 200000112 : waiting at the ride for Orbis to Ellinia
 * 240000111 : waiting at the ride for Leafre to Orbis
 * 200000132 : waiting at the ride for Orbis to Leafre
 * todo ariant
 */
@Script(name = "goOutWaitingRoom", description = "Most boats have an exit npc. This is that npc.")
public class GoOutWaitingRoom extends NpcScript {

    private static final Logger log = LoggerFactory.getLogger( GoOutWaitingRoom.class );

    @Override
    protected void work() {
        askYesNo("Do you want to leave? You can, but there wont be a ticket refund. Are you sure you want to leave?", () -> {
            switch (user.getFieldId()) {
                case 200000122: // Orbis -> Ludibrium
                    user.transferField(200000100);
                    break;
                case 220000111: // Ludibrium -> Orbis
                    user.transferField(220000100);
                    break;
                case 101000301: // Ellinia -> Orbis
                    user.transferField(101000300);
                    break;
                case 200000112: // Orbis -> Ellinia
                    user.transferField(200000100);
                    break;
                case 240000111: // Leafre -> Orbis
                    user.transferField(240000100);
                    break;
                case 200000132: // Orbis -> Leafre
                    user.transferField(200000100);
                    break;
                default:
                    log.warn("Unsupport field: {}", user.getFieldId());
                    break;
            }
        }, () -> {
            say("You'll get to your destination soon. Go ahead and talk to the others, and before you know it, you'll be there.");
        });
    }
}
