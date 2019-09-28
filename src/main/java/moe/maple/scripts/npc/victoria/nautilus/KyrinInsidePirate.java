package moe.maple.scripts.npc.victoria.nautilus;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;
import moe.maple.scripts.util.Jobs;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name="inside_pirate", description = "Kyrin | 2nd job advancement test exit")
public class KyrinInsidePirate extends NpcScript {
    @Override
    protected void work() {
        var qr = user.getQuestHolder();
        boolean brawlerQuest = qr.isInProgress(2191);
        boolean gunslingerQuest = qr.isInProgress(2192);
        int crystalId = brawlerQuest ? 4031856 : gunslingerQuest ? 4031857 : 0;//Might be smarter to check by fieldId.
        if(crystalId == 0 || user.getJobId() != Jobs.Pirate) {//Pirates only buddy
            say("You're after my booty, aren't you?! Everyone is, but you can't have it.").andThen(this::leave);
        } else {
            int reqCrystal = 15;
            int acquiredCrystal = user.getItemCount(crystalId);
            if(acquiredCrystal >= reqCrystal) {
                say(//nexon says tought :|
                Moematter.format("Ohh... So you managed to gather up {} #b#t{}#s#k! Wasn't it tough? That's amazing... alright then, now let's talk aboard The Nautilus.", reqCrystal, crystalId),
                        //`These crystals can only be used here, so I'll just take them back` => Nexon dumb af.. They're removed in the quest completion.
                        "It's time to head back, you can carry the crystals until we get there."
                ).andThen(this::leave);
            } else askYesNo(Moematter.format("You only need {} more.\r\nOr are you giving up already?", reqCrystal-acquiredCrystal), ()->{
                leave(); say("Come back when you're ready, I'll be waiting. Show me what kind of pirate you are.");
                }, "Stay determined!"
            );
        }
    }

    private void leave() {
        user.transferField(120000101, "tutorial");//Back to the Nautilus Navigation Room
    }
}
