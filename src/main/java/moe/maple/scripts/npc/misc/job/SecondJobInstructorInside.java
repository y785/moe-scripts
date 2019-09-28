package moe.maple.scripts.npc.misc.job;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.SayBuilder;
import moe.maple.api.script.util.tuple.Tuple;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
public abstract class SecondJobInstructorInside extends NpcScript {

    @Override
    protected void work() {
        if(user.getJobId() == allowedJob() && user.getLevel() >= 30) {
            int proof = 4031012, darkMarble = 4031013, marbleCount = user.getItemCount(darkMarble);
            if(marbleCount >= 30) {
                var saying = new SayBuilder(this)
                        .sayf("Ohhhhh... you collected all 30 #v{}#!!\r\nThat is no small feat... just incredible!", darkMarble)
                        .sayf("You've passed the test and for that, I'll reward you #b#t{}##k. Take this #v{}# and go back to #bPerion#k.", proof, proof);
                say(saying.build()).andThen(()->{
                    if(user.exchange(0, Tuple.listOf(darkMarble, marbleCount, letter(), -1, proof, 1))) {
                        leave();
                        say("Good luck my friend! You have worked hard for this!");
                    } else sayf("Hey pal... Do you really have 30 #t{}#, #t{}#, and an empty slot in the ETC tab?", darkMarble, letter());
                });
            } else {
                String askPrompt = Moematter.format("What's going on? Doesn't look like you have collected 30 #b#t{}##k, yet...\r\nIf you're having problems with it, then you can leave, come back and try it again. So...do you want to give up and get out of here?", darkMarble);
                askYesNo(askPrompt, ()->{
                    leave();
                    say("Really... alright, I'll let you out. Please don't give up, though. You can always try again, so do not give up. Until then, bye...");
                }, Moematter.format("That's the spirit! Quit acting weak and #bshow me the marbles#k.\r\nTalk to me when you have collected 30 #b#t{}##k.", darkMarble));
            }
        } else {
            if(user.getFieldId() >= 108000000 && user.getFieldId() <= 108100000) leave();
            say("What? How did you get here?\r\n... How strange. Well, I'll let you out. This is a very dangerous place. Get out of here already.");
        }
    }

    private void leave() {
        user.transferField(returnMap(), "");
    }

    protected abstract int allowedJob();
    protected abstract int returnMap();
    protected abstract int letter();

}
