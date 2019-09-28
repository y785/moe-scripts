package moe.maple.scripts.npc.ereve;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

/**
 * @author umbreon22
 */
@Script(name="createCygnus", field=130000000, description = "Shinsoo (1101001) | Gives baby cygnus bois a baby buff")
public class ShinsooCreateCygnus extends NpcScript {

    @Override
    protected void work() {
        boolean isCygnus = user.getJobId() >= 1000 && user.getJobId() < 2000;
        final String text;
        if(isCygnus) {
            final int buffItemId = 2022458;//Shinsoo's Blessing
            user.giveBuffItem(buffItemId);
            text = "Don't stop training. Every ounce of your energy is required to protect the Maple World...";//That the Maple World may become stronger. (Post-BB text, but quite boring)
        } else text = "If you are intent on protecting the Maple World on your own accord, then there's nothing I can say that will convince you otherwise...";
        say(text);
    }
    
}
