package moe.maple.scripts.npc.victoria.sleepywood;

import moe.maple.api.script.model.NpcScript;
import moe.maple.scripts.util.fields.Victoria;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author umbreon22
 * Created on 9/27/2019.
 */
public abstract class Viola extends NpcScript {

    protected void work(int questId, int flowerId, int flowerAmount, int... randomRewards) {
        var state = user.getQuestHolder().getState(questId);
        if(state == 0) sayf("There are many flowers here, and something about #b#t{}##k too.", flowerId);
        else if(state == 2) reward(randomRewards);
        else progress(flowerId, flowerAmount);
    }

    private void progress(int flowerId, int flowerAmount) {
        if(!user.hasItem(flowerId)) {
            if(user.exchange(0, flowerId, flowerAmount)) {
                returnToSleepywood();
            } else say("I don't think I can carry all of these flowers... if only I had more ETC inventory space.");
        } else sayf("Many flowers are blooming, but I shouldn't take any more. I need to bring these to #bJohn#k in #b#m{}##k", Victoria.LithHarbor);
    }

    private void reward(int[] randomRewards) {
        int rand = ThreadLocalRandom.current().nextInt(0, randomRewards.length);
        int reward = randomRewards[rand];
        if(user.exchange(0, reward, 2)) {
            returnToSleepywood();
        } else say("It seems there are some ores on underneath the flowers. I need to make some inventory space so I can carry them out of here!");
    }

    private void returnToSleepywood() {
        user.transferField(105040300, "in00");
    }

}
