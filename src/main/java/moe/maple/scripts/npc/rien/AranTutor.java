package moe.maple.scripts.npc.rien;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.tuple.Tuple;

import java.util.List;

/**
 * @author umbreon22
 * Created on 9/7/2019.
 */
@Script(name="aranTutor", description = "When double clicking on a tutor, this NPC should be opened.")
public class AranTutor extends NpcScript {
    @Override
    protected void work() {
        List<Tuple<String, Object>> choices = List.of(
                Tuple.of("Who am I?", "You are one of the heroes that saved the Maple World from the black Mage hundreds of years ago. You've lost your memory due to the curse of the Black Mage."),
                Tuple.of("Where am I?", "This island is called Rien, and this is where the Black Mage's curse put you to sleep. It's a small island covered in ice and snow, and the majority of the residents are Penguins."),
                Tuple.of("Who are you?", "I'm Lilin, a clan member of Rien, and I've been waiting for your return as the prophecy foretold. I'll be your guide for now."),
                Tuple.of("Tell me what I have to do.", "Let's not waste any more time and just get to town, I'll give you the details when we get there."),
                Tuple.of("Tell me about my inventory.", 14),
                Tuple.of("How do I advance my skills?", 15),
                Tuple.of("I want to know how to equip items.", 16),
                Tuple.of("How do I use quick slots?", 17),
                Tuple.of("How can I break open breakable containers?", 18),
                Tuple.of("I want to sit in a chair but I forgot how.", 19),
                Tuple.of("I want you to sit on my face", "You degenerate. You really clicked that?")
        );
        askMenu("Is there anything you're still curious about? If so, I'll try to explain it better.#b", choices.stream().map(Tuple::left).toArray(String[]::new)).andThen(index->{
            Object choice = choices.get(index).right();
            if(choice instanceof String) {
                say((String)choice);
            } else if(choice instanceof Integer) {
                user.tutorMessage((int)choice);
            } else say("Something has gone terribly wrong. And I'm not just talking about your life choices. [" + choice+']');
        });
    }

}
