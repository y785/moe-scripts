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

package moe.maple.scripts.npc.victoria;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.ScriptStringBuilder;

/**
 * @author umbreon22
 * Created on 9/26/2019.
 */
@Script(name="mTaxi", description = "Taking the passenger to the ant tunnel park from Lith Harbor (?) or multiple towns, maybe, who knows?")
public class VipCab extends NpcScript {
    final int standardFare = 10000;
    @Override
    protected void work() {
        final int antTunnelPark = 105070001;
        say("Hi there! This cab is for #eVIP customers only#n. Instead of just taking you to different towns like the regular cabs, we offer a much better service worthy of VIP class.",
            Moematter.format("It's a bit pricey, but... for only #r{}#k mesos, we'll take you safely to the #b{}#k.", Moematter
                    .formatWithLocale(standardFare), Moematter.map(antTunnelPark))
        ).andThen(()->{
            final int mobileStore = 1061001;
            final int fare;
            var question = new ScriptStringBuilder();
            if(user.isBeginner()) {
                question.append("We have a special 90% discount for beginners!");
                fare = standardFare / 10;
            } else {
                question.append("The regular fee applies for adventurers of your calibre.");
                fare = standardFare;
            }
            question.newLine().append("The ant tunnel is located deep inside in the dungeon that's placed at the center of the Victoria Island, where the ")
                    .blue().appendNpcName(mobileStore).black().append(" is.").newLine(2)
                    .append("Would you like to go there for ").blue().appendWithLocale(fare).append(" mesos #k?");
            askYesNo(question.build(), ()->{
                if(user.decreaseMoney(fare)) {
                    user.transferField(antTunnelPark, "");
                } else say("Looks like you're a bit short on the fare. Sorry, but only paying customers can ride the taxi.");
            }, Moematter.format("#b#m{}##k also has a lot to offer. Seek us if and when you feel the need to go to the #b#m{}##k.", user.getFieldId(), antTunnelPark));
        });
    }
}
