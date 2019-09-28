package moe.maple.scripts.npc.rien;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.SayBuilder;

/**
 * @author umbreon22
 * Created on 9/27/2019.
 */
@Script(name="talkHelena", description = "Part of the Aran tutorial.")
public class AthenaTalkHelena extends NpcScript {

    private final boolean usePreBBText = false;

    @Override
    protected void work() {
        var saying = new SayBuilder(this)
                .sayIf(usePreBBText, "Oh Aran, you're awake? How's the injury? ...What? Do you want to know what's going on right now?")
                .sayIf(!usePreBBText, "Aran, you're awake! How are you feeling? Hm? You want to know what's been going on?")
                .say("We're almost done preparing for the escape. You don't have to worry. Everyone I could possibly find has boarded the ark, and Shinsoo has agreed to guide the way. We'll head to Victoria Island as soon as we finish the remaining preparations.")
                .ok("The other heroes? They've left to fight the Black Mage. They're buying us time to escape. What? You want to fight with them? No! You can't. You're hurt. You must leave with us!");
        say(saying.build()).andThen(()->{
            user.getQuestHolder().setState(21002, 1);
            reservedEffect("Effect/Direction1.img/aranTutorial/Trio");
        });
    }
}
