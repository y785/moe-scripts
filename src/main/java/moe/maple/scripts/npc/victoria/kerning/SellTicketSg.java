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

package moe.maple.scripts.npc.victoria.kerning;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.ScriptFormatter;

@Script(name = "sellticket_sg", description = "Irene in kerning")
public class SellTicketSg extends NpcScript {

    final int ticket = 4031731;

    private void sellTicket() {
        final int cost = 20000;

        askYesNo(ScriptFormatter.format("The ticket will cost you #b{} mesos.#k Will you purchase the ticket?", String.format("%,d", cost)), () -> {
            if (!user.exchange(cost, ticket, 1)) {
                say("Your inventory is full or you don't have the mesos required.");
            }
        }, () -> {
            say("I am here for a long time. Please talk to me again when you change your mind.");
        });
    }

    private void goToDeparture() {
        askYesNo("Would you like to go in now? You will lose your ticket once you go in~ Thank you for choosing Wizet Airline.", () -> {
            // todo take ticket and contistate/fieldset
        }, () -> {
            say("Please confirm the departure time you wish to leave. Thank you.");
        });
    }

    @Override
    public void work() {
        askMenu("Hello there~ I am Irene from Changi Airport. I was transferred to Kerning City to celebrate the new opening of our service! How can I help you?",
                "I would like to buy a plane ticket to Singapore.",
                "Let me go to the departure point").andThen(sel -> {
                    if (sel == 0) {
                        sellTicket();
                    } else {
                        goToDeparture();
                    }
        });
    }
}
