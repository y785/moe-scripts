package moe.maple.scripts.npc.rien;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

/**
 * @author umbreon22
 * Created on 9/7/2019.
 */
@Script(name="PurotalkRie", description = "A cute little NPC to pass the time during boat rides")
public class PuroTalk extends NpcScript {
    @Override
    protected void work() {
        askMenu("Ahhhh, this is so boring... The whale is controlling the shiip so I'm left with nothing to do but look up and stare at the clouds. Hey, I'm trying to pass time. Would you like me to tell you about Rien or something? Are you interested?#b",
            "Sure",
            "I'm not interested"
        ).andThen(choice->{
            if(choice == 0) {
                say( "Rien is a tiny island located right next to the biggest island in all of the Maple World, Victoria Island. It's about a minute west of Lith Harbor if you ride the whale.",
                    "Although Rien is quite far from the northern regions, it's frozen at all times because the temperature is unusually low there. You can say that Rien consists of ice and little else. I hear that this anomaly isn't a natural phenomenon but a man-made condition.",
                    "Plants are rare since the entire island is covered in ice and hardly any of those plants bear fruit. It's the perfect place for penguins but not for humans. That's probably why every human but one girl has left the town.",
                    "But Rien is still a very active town since the hard-working penguins diligently dig through the ice, hoping to discover something new and amazing.",
                    "#b(Puro went on and on and on. He must have been extremely bored.)"
                );
            } else say("Oh... okay then, that's fine too.");//Made up. But how dare you.
        });
    }
}
