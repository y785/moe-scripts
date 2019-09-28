package moe.maple.scripts.npc.victoria.ellinia;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;

/**
 * @author umbreon22
 */
@Script(name="giveSap", field=101010103, description = "Npc: Small Tree Stump (1032111) | Field: Top of the Tree That Grew | Quest: Maybe It's Arwen! | Using uncredited text from some script online. Couldn't find a video.")
public class SmallTreeStump extends NpcScript {
    @Override
    protected void work() {
        var treeSap = 4032142;
        var maybeItsArwen = 20716;
        var cost = 100;
        boolean canGiveSap = user.getQuestHolder().isInProgress(maybeItsArwen) &&  user.getItemCount(treeSap) <= 0;
        if(canGiveSap) {
            askAccept("Toss "+cost+" mesos into the stump?", ()->{
                if(user.exchange(-cost, treeSap, 1)) {
                    say(Moematter.format("A bottle of clear tree sap emerges from the stump.\r\n\r\n#i{}#"), treeSap);
                } else flow();
            }, this::flow);
        } else flow();
    }

    private void flow() {
        say("A never ending flow of sap is coming from this small tree stump.");
    }
}
