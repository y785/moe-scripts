package moe.maple.scripts.npc.misc;

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
