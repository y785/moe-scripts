package moe.maple.scripts.npc.victoria.nautilus;

import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.helper.Exchange;
import moe.maple.api.script.util.builder.SayBuilder;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.npc.misc.job.ExplorerJobInstructor;
import moe.maple.scripts.util.Jobs;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 9/23/2019.
 */
@Script(name="kairinT", field = 1200000101, description = "Kyrin (1090000) | Navigation Room | Job Advancement")
public class KyrinKairinT extends ExplorerJobInstructor {

    @Override
    protected void promptForSecondJob() {
        var qr = user.getQuestHolder();
        int brawlerState = qr.getState(2191);//How to become a Brawler
        int slingerState = qr.getState(2192);//How to become a Gunslinger
        int crystalId = brawlerState > 0 ? 4031856 : slingerState > 0 ? 4031857 : 0;
        int count = crystalId > 0 ? user.getItemCount(crystalId) : -1;
        if(brawlerState == 1 || slingerState == 1) {//2nd Job quest in progress
            var sb = new SayBuilder(this)
                    .sayf("Okay, now I'll take you to the test room. Here are the instructions: defeat the Octopirates and gather #b15 #z{}##k. The Octopirates you'll see here are highly trained and are very quick, so I suggest you really buckle down and get ready for this.", crystalId)
                    .sayf("Oh, and for the sake of training {}, those Octopirates will not be affected unless hit with #b{}#k.", brawlerState == 1 ? "Brawler" : "Gunslinger", brawlerState == 1 ? "Flash Fist" : "Double Shot")
                    .sayf("And one more thing, when you enter the test room, I'll remove all the #z{}# you have. Yes, you'll be starting off from scratch.", crystalId)
                ;
            say(sb.build()).andThen(()->{
                final boolean pass;
                if(count > 0) pass = user.exchange(0, crystalId, -count);
                else pass = count == 0;
                if(pass) {//108000500, 1 Double Shot //108000502, 3 Flash Fist
                    int nextField = (brawlerState > 0 ? 108000502 : 108000500) + ThreadLocalRandom.current().nextInt(0, 1);
                    user.transferField(nextField, "sp");
                } else say("Bugs. Bugs bunny.");//Something went wrong on the quest end of things.
            });
        } else if(brawlerState == 2 || slingerState == 2) {//2nd Job quest complete
            int index = brawlerState == 2 ? 0 : 1;//slingerState == 2 always true
            String jobName = index == 0 ? "Brawler" : "Gunslinger";
            sayf("I knew you'd pass the test as expected. You have some impressive moves in there. Not bad at all! Now, as promised, you will become a #b{}#k.", jobName).andThen(()->{
                if(count <= 0 || user.exchange(0, crystalId, -count)) {//Honestly who cares, they already passed the quest?
                    short advancedJob = (short) (getFirstJobId() + (index+1) * 10);
                    user.setJob(advancedJob, true);
                    user.increaseSkillPoints(1);
                    var saying = new SayBuilder(this);
                    if(!user.increaseSlotCountForEtc(4)) {
                        saying.say("I was unable to fully extend your #b[Use Inventory]#k. You already did it yourself, so there's nothing I can do about it, you know?");
                    }//Error message for increasing slots! Heyyyy what a rare get!
                    if(!user.increaseSlotCountForEtc(4)) {
                        saying.say("I was unable to fully extend your #b[Etc Inventory]#k. You already did it yourself, so there's nothing I can do about it, you know?");
                    }
                    say(saying.say(getSecondJobFinale()[index]).build());
                } else say("You don't have 15 of the crystals I asked for.");
            });
        } else promptForGeneralInfo();
    }

    @Override
    protected void promptForThirdJob() {//todo
        say("Third job advancement for pirates still needs to be researched.").andThen(this::promptForGeneralInfo);
    }

    @Override
    protected String getCategoryName() {
        return "Pirate";
    }

    @Override
    protected String getCategoryPlural() {
        return "Pirates";
    }

    @Override
    protected String getCreepyText() {
        return "You're not the first adventurer to propose to me. Sorry, but I'm a pirate with high standards. This booty ain't your treasure.";
    }

    @Override
    protected Optional<String> getJobText(int jobId) {
        final String text;
        switch (jobId) {
            case Jobs.Brawler: text = "Ahhh... it's you. What do you think? How's the life of an #bBrawler#k? Keep training under 100x gravity and you might just go Super Saiyan one day."; break;
            case Jobs.Gunslinger: text = "Ahhh... it's you. What do you think? How's the life of a #bGunslinger#k? Guns, am I right?"; break;
            case Jobs.Marauder:
            case Jobs.Buccaneer:
            case Jobs.Outlaw:
            case Jobs.Corsair:
                text = ahhYouFinally(Jobs.getName(jobId)); break;
            default: return Optional.empty();
        }
        return Optional.of(text);
    }

    @Override
    protected String getGeneralIntro() {
        return "Do you have something that piques you about being a Pirate...?#b";
    }

    @Override
    protected List<Tuple<String, List<String>>> getGeneralInfo() {
        return Tuple.listOf(
                "What does it mean to be a Pirate?", List.of(
                    "Here's what you need to know about being a Pirate. You can think of a pirate as one big road that offers multiple paths. If you want to dominate monsters with brute force, focus on improving STR. If you want to outsmart the monsters with long-range attacks, I suggest you focus on improving DEX.",
                    "It's a job that changes based on what you do with it. Y ou should think way ahead and determine what you want to become later on, so you can start focusing on which of the two stats you want to improve on, STR or DEX. If you want to become a Brawler, boost STR. Gunslinger, boost DEX."
                ),
                "What weapons do pirates use?", List.of(
                    "Unlike other jobs, being a Pirate will allow you to fight monsters with a bare first. If you want to maximize your attacking abilities, however, I suggest you use a Knucker or Gun.",
                    "If you want to engage in melee attacks and stun the monsters, use a Knuckler. It looks similar to the Claws that the thieves use , but it is made with a much sturdier material to simultaneously protect and strengthen the fist.",
                    "If you want to take on opponents long range, use the Gun. Of course, the Gun itself won't do it for you. You'll need bullets. You can get those at most convenient stores nearby."
                ),
                "What armors can pirates wear?", List.of(
                    "Pirates are usually fleet-footed, utilizing quickness to attack dazed opponents. Yes, this also means the armors have to be light, as well. This is the reason why most of the clothes for the Pirates are made out of fabric.",
                    "It may be a thin fabric, but you better not underestimate its capabilities. It's as durable and protective as the best leather!"
                ),
                "What skills do pirates use?", List.of(
                    "For pirates, there are skills that support the accuracy and avoidability needed to be effectiv. Some of the attacking skills involve either only bare fists or Guns, so you may want to choose one of the two attacking methods and stick to it, when leveling up your skills.",
                    "If you want to use Guns, then I suggest you use the skill #bDouble Shot.#k Double Shot allows you to fire two bullets at once, which will enable you to attack monsters from long range.",
                    "If you are using your bare fists or Knucklers, then concentrate on #bSommersault Kick#k and/or #bFlash Fist.#k Alternate these two skills to maximize your attacking capabilities. You may also use these skills while carrying a Gun, but it's simply not as effective as using Knucklers."
                ),
                "What is the greatest pirate treasure?", List.of(
                    "There once was a man named Gold Roger, who was King of the Pirates. He had fame, power, and wealth beyond your wildest dreams.",
                    "Before they hung him from the gallows, these were the final words he said:\r\n\r\n\"My fortune is yours for the taking, but you'll have to find it first I left everything I own in One Piece...\""
                )
        );
    }

    @Override
    protected int getFirstJobLevelRequirement() {
        return 10;
    }

    @Override
    protected short getFirstJobId() {
        return Jobs.Pirate;
    }

    @Override
    protected Exchange getFirstJobExchangeRewards() {
        return new Exchange(0, Tuple.listOf(1492000, 1, 1482000, 1, 2330000, 1000));
    }

    @Override
    protected String getFirstJobAskPrefix() {
        return "You seem more than qualified to become one of us!";//Not confirmed text :(
    }

    @Override
    protected String getFirstJobRewardSuffix() {
        return "Welcome to the band of Pirates! You have to spend some time as a wanderer first, but better days will certainly dawn upon you, sooner than you think! You're now my #eNAKAMAAAA!!#n";
    }

    @Override
    protected String getFirstJobClosingStatement() {
        return "Please, find the One Piece. AHAHAHA just kidding, keep it up kid!";
    }

    @Override
    protected int getSecondJobLevelRequirement() {
        return 30;
    }

    @Override
    protected int getSecondJobLetterId() {
        throw new UnsupportedOperationException("Pirates do not use this function.");
    }

    @Override
    protected int getSecondJobInstructorId() {
        throw new UnsupportedOperationException("Pirates do not use this function.");
    }

    @Override
    protected int getSecondJobTown() {
        throw new UnsupportedOperationException("Pirates do not use this function.");
    }

    @Override
    protected int getSecondJobLocation() {
        throw new UnsupportedOperationException("Pirates do not use this function.");
    }

    @Override
    protected String getSecondJobYouHaveGrown() {
        throw new UnsupportedOperationException("Pirates do not use this function.");
    }

    @Override
    protected String getSecondJobGenderSubject() {
        return "She";
    }

    @Override
    protected List<Tuple<String, List<String>>> getSecondJobChoicesAndExplanations() {
        throw new UnsupportedOperationException("Pirates do not use this function.");
    }

    @Override
    protected String[][] getSecondJobFinale() {
        return new String[][]{
            {//Brawler
                    "Okay, from here on out, you are a #bBrawler#k. Brawlers rule the world with the power of their bare fists... which means they need to train their bodyt more than others. If you have any trouble training, I'll be more than happy to help.",
                    "I have just given you a skill book that entails Brawler skills, you'll find it very helpful. You have also gained additional slots for Use items, a full row in fact. I also boosted your max health and mana. Check it out for yourself!" ,
                    "I have given you a little bit of #bSP#k, so I suggest you open the skill menu quickly! You'll be able to enhance your newly-acquired 2nd Job skills. Beware that not all skills can be enhanced from the get go. There are some skills that you can only acquire after mastering basic skills.",
                    "Brawlers need to be a powerful force, but that doesn't mean they have the right to bully the weak. True Brawlers use their immense power in positive ways, which is much harder than just training to gain strength. I hope you follow this creed as you lerave your mark in this world as a Brawler. I will see you when you have accomplished everything you can as a Brawler. I'll be waiting for you here."
            },
            {//Gunslinger
                "From here on out, you are a #bGunslinger#k. Gunslingers are notable for their long-range attacks with sniper-like accuracy and of course, using guns as their primary weapon. You should continue training to truly master your skills. If you are having trouble training, I'll be there to help.",
                "I have just given you a skill book that entails the skills for Gunslingers, one that you'll find very helpful. You have also gained additional slots for Use items, a full row in fact. I also boosted your max health and mana. Check it out for yourself!",
                "I have given you a little bit of #bSP#k, so I suggest you open the skill menu quickly! You'll be able to enhance your newly-acquired 2nd Job skills. Beware that not all skills can be enhanced from the get go. There are some skills that you can only acquire after mastering basic skills.",
                "Gunslingers are deadly at ranged combat, but that doesn't mean they have the right to bully the weak. Gunslingers will need to use their immense power in positive ways, and that is actually harder than just training to gain strength. I hope you follow this creed as you leave your mark in this world as a Gunslinger. I will see you when you have accomplished everything you can as a Gunslinger. I'll be waiting for you here."
            }
        };
    }

    @Override
    protected boolean isValidSecondJob(int jobId) {
        return jobId == Jobs.Gunslinger || jobId == Jobs.Brawler;
    }

    @Override
    protected int getThirdJobInstructorId() {
        return 2020011;
    }

    @Override
    protected String getThirdJobDoorLocation() {
        return "Cursed Temple";
    }

    @Override
    protected String getThirdJobGenderObject() {
        return "her";
    }
}
