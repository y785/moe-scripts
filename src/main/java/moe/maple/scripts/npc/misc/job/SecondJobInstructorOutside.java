package moe.maple.scripts.npc.misc.job;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.SayBuilder;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
public abstract class SecondJobInstructorOutside extends NpcScript {

    @Override
    protected void work() {
        int userJob = user.getJobId();
        int allowedJob = allowedJob();
        String category = jobCategory();
        if(userJob == allowedJob) {
            if(user.getLevel() >= 30) {
                int darkMarble = 4031013;
                int proof = 4031012;
                int requiredProofCount = 30;
                int darkMarbleCount = user.getItemCount(darkMarble);
                int letter = letterItemId();
                if(user.hasItem(letter)) {
                    String instructions = Moematter.format("Defeat the monsters inside, collect #b{} #t{}#s#k, then strike up a conversation with a colleague of mine inside.\r\n\r\nHe'll give you #b#t{}##k, the proof that you've passed the test. Best of luck to you.",
                            requiredProofCount, darkMarble, proof
                    );
                    if(darkMarbleCount <= 0) {
                        var saying = new SayBuilder(this)
                                .sayf("Hmmm...\r\n\r\n#v{}# What is this?", letter)
                                .sayf("A letter from #b#p{}##k? I see, so you came all the way here to take the test for #b{}'s 2nd job advancement#k.\r\n\r\nAlright, I'll explain the test to you. Don't sweat it too much, it's not that complicated.",
                                        instructorNpcId(), category
                                )
                                .say("I'll send you to a #bhidden map#k. You'll see monsters you don't normally see. They look the same as regular ones, but they've got a totally different attitude. They neither boost your experience, nor provide you with normal items.")
                                .sayf("Instead, you'll be able to acquire a special item called #b#t{}##k from knocking down these monsters.\r\nIt's made out of their sinister, evil minds.\r\n\r\nCollect #b{}#k and speak to a colleague of mine in there. That's how you pass the test.",
                                    proof, requiredProofCount
                                );
                        say(saying.build()).andThen(()->askYesNo("Once you go inside, #eyou can't leave#n until you take care of your mission. #rIf you die, your experience will decrease#k; buckle up and get ready. Are you prepared to go?", ()->{
                                user.transferField(getRandomMap());
                                sayf("Alright I'll let you in! {}", instructions);
                            }, "You don't seem too prepared for this. Find me when you #eARE#n ready. #rThere are neither portals nor stores inside, so you better be 100% ready for it.")
                        );
                    } else {
                        askYesNo("So, you've given up in the middle of your exam. Don't worry about it! You can still retake the test when you're ready.\r\n\r\nDo you want to go back in and try again?", ()->{
                            sayf("Alright! I'll let you in! Sorry to say this, but I have to take away all your marbles first.\r\n\r\nJust like before... {}", instructions).andThen(()->{
                                if(user.exchange(0, darkMarbleCount, -darkMarbleCount)) user.transferField(getRandomMap());
                                else say("I've lost my marbles! You've lost your marbles! We've all lost our marbles!!!");
                            });
                        }, "Playing it safe? Go prepare yourself and see me again.");
                    }
                } else {
                    sayf("Do you want to be a better {}? I can take care of that for you. You look definitely qualified for it. But first, visit #b#p{}##k of {}.", category, instructorNpcId(), townName());
                }
            } else say("Do you want to be a better {}? You seem pretty #eweak#n. Get stronger and maybe I'll consider it. I don't test rookies.", category);
        } else if(userJob > allowedJob && userJob < (allowedJob + 100)) {
            sayf("Hmmm... it was you who took on my test!! What do you think? Have you become stronger since then?? Good! Now I can definitely see your strength as a {}.", category);
        } else {//Hercules reference ftw
            sayf("Do you wanna be a #b{}#k, kid? Well, whoop-dee-doo! I've been around the block before with blockheads just like you. Each and every one a disappointment. Pain for which there ain't no ointment. So much for excuses, Thou-a-kid-a-Noob's. Asking me to jump into the fray? My answer is two words...\r\n\r\n#eNO WAY!", category);
        }
    }

    protected int getRandomMap() {
        return firstFieldId() + ThreadLocalRandom.current().nextInt(0, 3);
    }

    protected abstract int allowedJob();
    protected abstract String jobCategory();
    protected abstract String townName();
    protected abstract int letterItemId();
    protected abstract int instructorNpcId();
    protected abstract int firstFieldId();

}
