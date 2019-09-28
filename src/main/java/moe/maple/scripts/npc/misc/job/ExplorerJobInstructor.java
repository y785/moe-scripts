package moe.maple.scripts.npc.misc.job;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.SayBuilder;
import moe.maple.api.script.util.builder.ScriptStringBuilder;
import moe.maple.api.script.util.tuple.Tuple;
import odyssey.scripts.util.IntegerToWord;
import odyssey.scripts.util.Jobs;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 9/16/2019.
 */
public abstract class ExplorerJobInstructor extends NpcScript {

    private static final int THIRD_JOB_QUEST_ID = 7500;

    @Override
    protected void work() {
        int jobId = user.getJobId();
        if(jobId == getFirstJobId()) {
            promptForSecondJob();
        } else if(jobId == Jobs.Beginner) {
            promptForFirstJob();
        } else if(isValidSecondJob(jobId) && isThirdJobQuestInProgress()) {
            promptForThirdJob();
        } else if(user.getLevel() >= 200) {
            say("Level 200 ranking thingy, if rank between 0 and 20 do neat stuff.");
        } else say(getJobText(jobId).orElse(getCreepyText()));
    }

    //global stuff
    protected abstract String getCategoryName();
    protected abstract String getCategoryPlural();
    protected abstract String getCreepyText();
    protected abstract Optional<String> getJobText(int jobId);
    protected String ahhYouFinally(String jobName) {
        return Moematter.format(
                "Ahh... You finally became a #b{}#k. I knew you would not let me down.\\r\\n\\r\\nSo what do you think of life as a #b{}#k? Please, dedicate yourself and train even more!",
                jobName, jobName
        );
    }

    //general stuff
    protected abstract String getGeneralIntro();
    protected abstract List<Tuple<String, List<String>>> getGeneralInfo();//Question, Answers[]

    //first job stuff


    private void promptForFirstJob() {
        String firstJobRequirement = new ScriptStringBuilder().blue().appendf("You need to be at least level {}.", getFirstJobLevelRequirement()).black().build();
        String letsSee = new ScriptStringBuilder().append("So you wish to become a ").blue()
                                                  .append(getCategoryName()).black().append("? In that case, you'll need to meet the criteria in order to do so.")
                                                  .newLine(2).blue().append(firstJobRequirement).build();
        say(letsSee).andThen(()-> {
            if (user.getLevel() >= getFirstJobLevelRequirement()) {
                askYesNo(Moematter.format("{}.\\r\\nWhat do you think? Do you want to become a #b{}?#k", getFirstJobAskPrefix(), getCategoryName()),
                        this::onFirstJobAdvancement, () -> say("You need more time think it over? I see... Well, selecting a new job is a very important choice to make. Come talk to me again once you have made a decision!")////By all means... this is #rnot something you should take lightly#k.\r\n\r\nCome talk to me once you have made a decision.`);
                );
            } else say(Moematter.format(
                    "You think you have what it takes to become a {}? I like your spirit! But don't think being a {} is a walk in the park... {} Get stronger and come see me.",
                    getCategoryName(), getCategoryName(), firstJobRequirement
            ));
        });
    }

    private void onFirstJobAdvancement() {
        say(Moematter.format("From here on out, you are going to be a #b{}#k! {}", getCategoryName(), getFirstJobRewardSuffix())).andThen(()->{
            var rewards = getFirstJobExchangeRewards();
            //if(slotCount(1) > holdCount(1) // isFull ? //Hmm ... Check if there is an empty slot in your Equip window. I'm trying to give you a weapon as a reward for your first advancement.
            if(user.exchange(0, rewards)) {
                user.setJob(getFirstJobId(), true);
                var sayBuilder = new SayBuilder(this);
                int additionalSp = 1;//You get 1 no matter what!
                if(user.getLevel() >= 30) {
                    sayBuilder.say("I think you've made the job advancement way too late. Usually, for beginners under Level 29 that were late in making job advancements, we compensate them with lost Skill Points, that weren't rewarded, but...I think you're a little too late for that. I am so sorry, but there's nothing I can do.");
                } else {
                    additionalSp += (user.getLevel() - 10) * 3;
                }
                user.increaseSkillPoints(additionalSp);
                int maxHpIncrease = ThreadLocalRandom.current().nextInt(200, 250);
                user.increaseHealthMax(maxHpIncrease);
                IntStream.range(1, 4).forEach(inventoryType->user.increaseSlotCount(inventoryType, 4));
                sayBuilder.say(getFirstJobClosingStatement(),
                        "You've gotten much stronger now. As a welcoming gift, I've increased your inventory capacities all across the board... by a whole row, to be exact. Go see for yourself.\r\n\r\nI've also given you a little bit of #bSP#k. Use them wisely!",
                        "Look at the #bSkill menu#k to find some skills, and use your SP to learn them. Beware that not all skills can be enhanced from the get go. There are some skills that you can only acquire after mastering basic skills.",
                        "One more additional extra other warning. Now that you have selected a job, try to stay alive as much as you can. From here on out, when you die, you will lose some experience points. You wouldn't want to lose your hard-earned EXP, would you?",
                        "This is all I can teach you... from here on out, it's all about pushing yourself to become better. See me after you feel that you have gotten much more powerful than you are right now... say, in another "+Math.max(0, 30-user.getLevel())+" levels or so.",
                        "Before you go... if you have questions about being a "+getCategoryName()+", feel free to ask. I don't know #eEVERYTHING#n, but I can guide you a bit.\r\n\r\nTil then..."
                    );
                say(sayBuilder.build());
            } else onFirstJobExchangeFailure(rewards);
        });
    }

    private void onFirstJobExchangeFailure(List<Tuple<Integer, Integer>> rewards) {
        int firstReward = rewards.get(0).left();
        if(user.getItemCount(firstReward) > 0) {//The weapons are one of a kind, by the way.
            say(Moematter.format("Hey you... drop the #i{}#.. It's one of a kind, but I'm going to give you another shh don't think about it too hard you're bugged mate.", firstReward));
        } else {
            var ssb = new ScriptStringBuilder().append("Hmm... Please make sure you have enough room for these in your inventory...").newLine(2);
            for(var reward : rewards) {
                ssb.appendf("[#i{}#", reward.left());
                if(reward.right() > 1) {
                    ssb.appendf(" X {}", reward.right());
                }
                ssb.append("] ");
            }
            if(!rewards.isEmpty()) ssb.newLine(2);
            ssb.append("I'd like to give you a reward for your first job advancement.");
            say(ssb.build());
        }
    }

    protected abstract int getFirstJobLevelRequirement();
    protected abstract short getFirstJobId();
    protected abstract List<Tuple<Integer, Integer>> getFirstJobExchangeRewards();
    protected abstract String getFirstJobAskPrefix();
    protected abstract String getFirstJobRewardSuffix();
    protected abstract String getFirstJobClosingStatement();


    private final int proofOfAHero = 4031012;//This seems the same across all jobs.
    //second job stuff
    protected void promptForSecondJob() {
        if(user.getLevel() >= getSecondJobLevelRequirement()) {
            if(user.hasItem(getSecondJobLetterId())) {
                say(Moematter.format(
                    "Still haven't met them? Find #b#p{}##k around #b#m{}##k in #b#m{}##k. Deliver the letter and they'll know what to do.",
                    getSecondJobInstructorId(), getSecondJobLocation(), getSecondJobTown()
                ));
            } else if(user.hasItem(proofOfAHero)) {
                var lowerCategory = getCategoryName().toLowerCase();
                say(Moematter.format(
                    "Oh! You came back safe! I knew you'd make it...\\r\\nI'll admit you are a strong, formidable {}. Alright, I'll make you an even stronger {}. Welcome to the crossroads of the {}... I wonder which path will you take?",
                    lowerCategory, lowerCategory, lowerCategory
                )).andThen(this::beginSecondJobAdvancement);
            } else {
                askYesNo(Moematter.format(
                    "{}. \\r\\nSo, what do you think? Do you want to become even stronger? Pass a simple test, and I can help you further. Wanna give it a shot?",
                    getSecondJobYouHaveGrown()
                ), this::giveSecondJobLetter, ()->say("#eReally?#n It would help you out a great deal on your journey...\\r\\nIf you change your mind, please feel free to come back. I can sense a lot of latent potential in you, but you require my help to unlock it."));
            }
        } else promptForGeneralInfo();
    }

    private void giveSecondJobLetter() {
        var memes = List.of("thinking", "choice", "decision");
        var memeWord = memes.stream().skip(ThreadLocalRandom.current().nextInt(memes.size()-1)).findFirst().orElse("error");
        sayf("Good {}. You look strong, don't get me wrong, but there's still a need to test your strength and see if you are for real. The test isn't too difficult, so you'll do just fine..\\r\\n#i{}#\\r\\nHere, I've written this letter for you. Don't lose it.",
            memeWord, getSecondJobLetterId()
        ).andThen(()-> {
            if(user.exchange(0, getSecondJobLetterId(), 1)) {
              sayf("Deliver this letter to #b#p{}##k, somewhere around #b#m{}##k in #b#m{}##k. {} will be your next instructor, testing you in my place. Good luck to you.",
                getSecondJobInstructorId(), getSecondJobLocation(), getSecondJobTown(), getSecondJobGenderSubject()
              );
            } else say("Wha- #eDon't eat it!#n\\r\\nYou can't even hold a letter... Make some #rETC#k inventory space, then come see me again.");
        });
    }


    private void beginSecondJobAdvancement() {
        var choicesAndExplanations = getSecondJobChoicesAndExplanations();
        List<String> choices = choicesAndExplanations.stream().map(Tuple::left).map(choice->"Please explain the characteristics of the " + choice + ".").collect(Collectors.toList());
        choices.add("I'm ready to choose!");
        var prompt = Moematter.format("It's finally time! I can explain any of the {} jobs to you. When you have made your decision, click on #b[{}]#k at the very bottom.\\r\\n#b",
            IntegerToWord.convert(choicesAndExplanations.size()), choices.get(choices.size()-1)
        );
        askMenu(prompt, choices).andThen(choice->{
            if(choice >= choicesAndExplanations.size()) {
                var jobs = choicesAndExplanations.stream().map(Tuple::left).collect(Collectors.toList());
                askMenu("Have you decided? Tell me what job you'd like to advance to.#b", jobs).andThen(jobIndex->{
                    askYesNo(Moematter.format("So you want to make the 2nd job advancement as a #b{}#k? Once you make that decision #eyou can't go back#n and choose another job...do you still wanna do it?",
                        jobs.get(jobIndex)),
                        ()->onSecondJobAdvancement(jobIndex),
                        ()->say("Really? So you need to think about it a little more? Take your time...remember #rthis is not something that you should take lightly#k... let me know when you have made your decision, okay?")
                    );
                });
            } else {
                var explanations = choicesAndExplanations.get(choice).right();
                say(explanations.toArray(String[]::new)).andThen(this::beginSecondJobAdvancement);
            }
        });
    }

    private void onSecondJobAdvancement(int jobIndex) {
        int targetSp = (user.getLevel() - 30) * 3;
        short secondJob = (short) (user.getJobId() + ((jobIndex+1)*10));//100 selects 0 -> 110
        if(user.getSkillPoints() > targetSp) {//Not sure if this check is valid. Would like to remove it and just give them their proper SP in the future.
            say("`Hmmm... you have too much SP. You can't make the 2nd job advancement with that many SP in store\\r\\nGo use up some additional SP and return.");
        } else if(user.removeItem(proofOfAHero, -user.getItemCount(proofOfAHero)) && user.setJob(secondJob, true)) {
            user.increaseSkillPoints(1);
            //todo: increase random stats.
            // switch(secondJob) {//differs by job
            //     case 110:
            //         const incHp = random(300, 350);
            //         target.incMHP(incHp, 0);
            //         break;
            //     case 120:
            //     case 130:
            //         const incMp = random(300, 350);
            //         target.incMMP(incMp, 0);
            //         break;
            // case bowguys:
            //    300, 350 Hp
            //    150, 200 Mp
            // etc...
            switch(secondJob) {//todo: support the rest
                case Jobs.Hunter:
                case Jobs.Crossbowman:
                    var rand = ThreadLocalRandom.current();
                    user.increaseHealthMax(rand.nextInt(300, 350));
                    user.increaseManaMax(rand.nextInt(150, 200));
                    break;
            }
            user.increaseSlotCountForUse(4);
            user.increaseSlotCountForEtc(4);
            say(getSecondJobFinale()[jobIndex]);
        } else say("Are you sure you have #v"+proofOfAHero+"#?");
    }

    protected void promptForGeneralInfo() {
        askMenu(getGeneralIntro(),getGeneralInfo().stream().map(Tuple::left).collect(Collectors.toList())).andThen(this::answerGeneralQuestion);
    }

    private void answerGeneralQuestion(int generalIndex) {
        var answer = getGeneralInfo().get(generalIndex).right();
        say(answer.toArray(String[]::new)).andThen(this::promptForGeneralInfo);
    }

    protected abstract int getSecondJobLevelRequirement();
    protected abstract int getSecondJobLetterId();
    protected abstract int getSecondJobInstructorId();
    protected abstract int getSecondJobTown();
    protected abstract int getSecondJobLocation();
    protected abstract String getSecondJobYouHaveGrown();
    protected abstract String getSecondJobGenderSubject();
    protected abstract List<Tuple<String, List<String>>> getSecondJobChoicesAndExplanations();
    protected abstract String[][] getSecondJobFinale();


    //third job stuff
    protected void promptForThirdJob() {
        int blackCharm = 4031059, necklaceOfStrength = 4031057;//These seem to be the same across all job advancements.
        int doorOfDimension = 1061009;
        String giveItToThirdInstructor = Moematter.format("Deliver this necklace to #b#p{}##k in El Nath and you will be able to take the next job advancement test. Good Luck~", getThirdJobInstructorId());
        var qr = user.getQuestHolder();
        var thirdJobProgress = qr.getValue(THIRD_JOB_QUEST_ID);
        switch (thirdJobProgress) {
            case "s"://Start
                qr.setValue(THIRD_JOB_QUEST_ID, "p1");
                say(Moematter.format(
                        "I've been waiting for you. I heard about you from #b#p{}##k of Ossyria a few days ago. Well... I'd like to test your strength. You will find a #b{}#k deep inside the #b{}#k. Only a budding {} like you can cross it.  When you're inside, you'll find #emy other self#n. Defeat {} and bring the #b#t{}##k to me.",
                        getThirdJobInstructorId(), Moematter.npc(doorOfDimension), getThirdJobDoorLocation(), getCategoryName().toLowerCase(), getThirdJobGenderObject(), blackCharm
                        ),
                        Moematter.format("#eMy other self#n is quite strong. They use many special abilities unlike you have ever seen. Be wary when you face them. You cannot stay in the secret passage for too long, so it's essential that you defeat my other self as quickly as possible. Well... Good luck! I look forward to you to bringing the #b#t{}##k to me.", blackCharm)
                );
                break;
            case "p1"://Part 1 ==> Let's code the quest on the other end :(
                if (user.hasItem(blackCharm)) {
                    var compliment = List.of("Wow", "Nice work", "Good job");
                    sayf("{}... You beat #emy other self#n and brought the #b#t{}##k here to me safely. Good! This surely proves your strength... I believe you are ready to advance to 3rd job.\\r\\nAs promised, I will give you #b#t{}##k.",
                            compliment.get(ThreadLocalRandom.current().nextInt(0, compliment.size())), blackCharm, necklaceOfStrength
                    ).andThen(() -> {
                        if (user.exchange(0, Tuple.listOf(blackCharm, -1, necklaceOfStrength, 1))) {
                            qr.setValue(THIRD_JOB_QUEST_ID, "p2");
                            say(giveItToThirdInstructor);
                        } else
                            sayf("Hmm.. how strange. Are you sure you have #b#t{}##k? If so, make sure you have an empty slot in the ETC tab.", blackCharm);
                    });
                } else say(Moematter.format("There is a secret passage near the #b{}#k, inside the #b#p{}##k. Only a {} like you can cross it. When you're inside, you'll find my other self. Defeat {} and bring the #b#t{}##k to me.", getThirdJobDoorLocation(), doorOfDimension, getCategoryName().toLowerCase(), getThirdJobGenderObject(), blackCharm));
                break;
            case "p2"://Part 2
                if(!user.hasItem(necklaceOfStrength)) {
                    sayf("Ahh! You lost #b#t{}##k, huh? I told you to be careful... For Zygod's sake, I'll give you another... AGAIN.\\r\\nPlease be careful this time. Without this, you will not be able take the 3rd job advancement exam.", necklaceOfStrength).andThen(()->{
                        if(user.addItem(necklaceOfStrength)) {
                            say(giveItToThirdInstructor);
                        } else say("Make some room in your ETC inventory before speaking to me again.");
                    });
                } else say(giveItToThirdInstructor);
                break;
            default://uhhh
                say(giveItToThirdInstructor);
                break;
        }
    }


    private boolean isThirdJobQuestInProgress() {
        var value = user.getQuestHolder().getValue(THIRD_JOB_QUEST_ID);//Confirmed Third Job Advancement quest id across all explorers.
        if(value.isEmpty()) return false;
        return value.equals("s") || value.equals("p1") || value.equals("p2");//BMS values
    }

    protected abstract boolean isValidSecondJob(int jobId);
    protected abstract int getThirdJobInstructorId();
    protected abstract String getThirdJobDoorLocation();
    protected abstract String getThirdJobGenderObject();

}
