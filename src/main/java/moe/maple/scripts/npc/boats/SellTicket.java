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

@Script(name = "sell_ticket",
        description = "Ticket salesperson for boats. Orbis <-> Ariant, Orbis <-> Ellinia, Orbis <-> Leafre, Orbis <-> Ludibrium",
        field = {101000300, 200000100, 240000100, 220000100})
public class SellTicket extends NpcScript {

    private void sellTicket(int ticket, int price, int time) {
        sellTicket(ticket, price, time, "Orbis");
    }

    private void sellTicket(int ticket, int price, int time, String movingTo) {
        sellTicket(ticket, price, String.format("Hi there! I'm in charge of selling tickets for this station. The ship to #b%s#k departs every %d minutes, starting #bat the top of the hour and every %d minutes afterwards#k, and will cost you #b%,d mesos#k. Are you sure you want to buy #b#t%d##k?", movingTo, time, time, price, ticket));
    }

    private void sellTicket(int ticket, int price, String message) {
        askYesNo(message, () -> {
            if (!user.exchange(-price, ticket, 1))
                 say(String.format("Are you sure you have #b%,d mesos#k? If so, then you should check if your ETC inventory is full.", price));
        }, () -> say("Alright. You must still have some business to deal with here."));
    }

    private void workOrbisStation(boolean beginner) {
        var prompt = "Hello, I'm in charge of selling tickets for the ship ride for every destination. Which ticket would you like to purchase?";
        var menu = new String[] {"Ellinia of Victoria Island", "Ludibrium", "Leafre of Minar Forest", "Nihal Desert" };
        askMenu(prompt, menu).andThen(sel -> {
            if (sel == 0)      // Ellinia
                sellTicket(beginner ? 4031046 : 4031047, beginner ? 1000 : 5000, 15, "Ellinia");
            else if (sel == 1) // Ludibrium
                sellTicket(beginner ? 4031073 : 4031074, beginner ? 2000 : 6000, 10, "Ludibrium");
            else if (sel == 2) // Leafre
                sellTicket(beginner ? 4031330 : 4031331, beginner ? 10000 : 30000, 10, "Leafre of Minar Forest");
            else if (sel == 3) // Nihal
                sellTicket(beginner ? 4031575 : 4031576, beginner ? 2000 : 6000, 10, "Nihal Desert");
            else
                say("Hmm...well, okay.");
        });
    }

    @Override
    protected void work() {
        var beginner = user.isBeginner();
        if (field.getId() == 101000300)      // Ellinia
            sellTicket(beginner ? 4031044 : 4031045, beginner ? 1000 : 5000, 15);
        else if (field.getId() == 200000100) // Orbis
            workOrbisStation(beginner);
        else if (field.getId() == 240000100) // Leafre
            sellTicket(beginner ? 4031044 : 4031045, beginner ? 10000 : 30000, 10);
        else if (field.getId() == 220000100) // Ludi
            sellTicket(beginner ? 4031044 : 4031045, beginner ? 2000 : 6000, 10);
        else
            say("I'm not sure what to do...");
    }
}
