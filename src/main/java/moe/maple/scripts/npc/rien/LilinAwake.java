package moe.maple.scripts.npc.rien;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.SayBuilder;

/**
 * @author umbreon22
 * Created on 9/7/2019.
 */
@Script(name="awake", description = "Part of the Aran tutorial.")
public class LilinAwake extends NpcScript {
    @Override
    protected void work() {
        var qr = user.getQuestHolder();
        if(!qr.getEx(21019, "helper").equals("clear")) {
            var saying = new SayBuilder(this)
                .say("You've finally awoken...!")
                .sayAsUser("And you are?")
                .say("The hero who fought against the Black Mage... I've been waiting for you to wake up!")
                .sayAsUser("Who... Who are you? And what are you talking about?",  "And who am I...? I can't remember anything... Ouch, my head hurts!")
                .build();
            say(saying).andThen(()->{
                reservedEffect("Effect/Direction1/aranTutorial/ClickLilin");//'Click on me'
                reservedEffect("Effect/Direction1/aranTutorial/face");//Forces F1
                self.setAction("penguin");
                qr.setEx(21019, "helper", "clear");
                //'Dont worry.. i'm gonna help you out.?
            });
        } else {
            var saying = new SayBuilder(this)
                    .say("Are you alright?")
                    .sayAsUser("I can't remember anything. Where am I? and who are you?")
                    .say(
                        "Stay calm. There is no need to panic. You can't remember anything because the curse of the Black Mage erased your memory. I'll tell you everyhing you need to know... step by step.",
                        "You're a hero who fought the Black Mage and saved the Maple World hundreds of years ago. But at the very last moment, the curse of the Black Mage put you to sleep for a long, long time. That's when you lost all of your memories.",
                        "This island is called Rien, and it's where the Black Mage trapped you. Despite its name, this island is always covered in ice and snow because of the Black Mage's curse. You were found deep inside the Ice Cave.",
                        "My name is Lilin and I belong to the clan of Rien. The Rien Clan has been waiting for a hero to return for a long time now, an we finally found you. You've finally returned!",
                        "I've said too much, it's okay if you don't really understand everything I just told you. You'll get it eventually. For now, #byou should head to town#k, I'll stay by your side and help you until you get there."
                    ).build();
            say(saying).andThen(() -> {
                user.hireTutor();
                int coldForest1 = 140090100;
                user.transferField(coldForest1, 0);
            });
        }
    }
}
