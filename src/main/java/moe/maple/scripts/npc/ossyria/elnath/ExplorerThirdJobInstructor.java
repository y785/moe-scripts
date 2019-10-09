package moe.maple.scripts.npc.ossyria.elnath;

import moe.maple.api.script.logic.action.BasicScriptAction;
import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.helper.MenuItem;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.FontColor;
import moe.maple.api.script.util.builder.SayBuilder;
import moe.maple.api.script.util.builder.ScriptStringBuilder;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.util.Jobs;

import java.util.*;

/**
 * @author umbreon22
 * Created on 9/21/2019.
 */
public abstract class ExplorerThirdJobInstructor extends NpcScript {

    private final int zakumLvReq = 50;
    private final int thirdLvReq = 70;
    private final int THIRD_JOB_QUEST_ID = 7500;

    private final Set<Integer> secondJobs, greaterJobs;

    ExplorerThirdJobInstructor(List<Integer> secondJobs, List<Integer> greaterJobs) {
        this.secondJobs = generateJobs(secondJobs);
        this.greaterJobs = generateJobs(greaterJobs);
    }

    @Override
    protected void work() {
        int level = user.getLevel();
        int job = user.getJobId();
        if(isCorrectJobCategory(job)) {
            if (level >= zakumLvReq) {//Okay, I'll talk to the noob.
                List<MenuItem> menuItems = new LinkedList<>();
                if(level >= thirdLvReq) menuItems.add(MenuItem.of("About my job...", this::doThirdJob));
                menuItems.add(MenuItem.of("About the Zakum Dungeon Quest...", this::doZakum));
                askMenu("Can I help you?#b", menuItems.toArray(MenuItem[]::new));
            } else {//HAHAHAHAHA go away.
                say("There's nothing I can do to help you. Come to me again when you are stronger.");
            }
        } else sayf("Welcome. I'm #b#p{}##k, the chief of all {}.", self.getTemplateId(), getBasicJobNamePlural());
    }

    private void doThirdJob() {
        int level = user.getLevel();
        if(level >= thirdLvReq) {
            int job = user.getJobId();
            if(secondJobs.contains(job)) {
                attemptThirdJobAdvance();
            } else sayJobText(job);
        } else sayf("You're not qualified. It'll take at least #e{}#n year{} before you'll be strong enough for the 3rd job advancement.", (thirdLvReq-level), level == thirdLvReq-1 ? "" : "s");
    }

    private void sayJobText(int job) {
        String jobName = Jobs.getName(job);
        var ssb = new ScriptStringBuilder();
        if(secondJobs.contains(job)) {
            ssb.append("You're the one who passed the tests and moved up to the third level.(Nice translation btw) How's the life of a ")
                .blue().append(jobName).black()
                .append("? You will have to continue training as you journey through this place. Ossyria is full of powerful monsters that even I do not know. If you have any questions, talk to me at the end of this road. I wish you good luck.")
            ;
        } else if(greaterJobs.contains(job)) {
                ssb.append("It's you, who managed to become the strongest of the ")
                        .append(getBasicJobNamePlural())
                        .append(". You have to serve as an example for all as a ")
                        .appendWithColor(FontColor.BLUE, jobName)
                        .append(". There are still a lot of secrets in this world. The power of ")
                        .append(getBasicJobNamePlural())
                        .append(" like you will be of great help, so keep training.")
                ;
        } else ssb.append("I'm ").blue().appendNpcName(self.getTemplateId())
                    .black().append(", the chief of all ").append(getBasicJobNamePlural()).append(". But you do not look like a worthy ").append(getBasicJobName()).append('.')
                    .newLine(2).append("This room is full of instructors with their respective classes. If you need anything, talk to them.")
                ;
        say(ssb.build());
    }

    private void attemptThirdJobAdvance() {
        var questState = user.getQuestHolder().getState(THIRD_JOB_QUEST_ID);
        var advanceValue = user.getQuestHolder().getValue(THIRD_JOB_QUEST_ID);
        var necklaceOfStr = 4031057;
        var necklaceOfWis = 4031058;
        switch (questState) {//The below is ugly but you're welcome to clean it up (:
            case 0://not started
                askYesNo(Moematter.format("Welcome. I'm #b#p{}##k, the chief of all {}, {}. You seem like the kind of {} that wants to make the leap forward, the one ready to take on the challenges of the 3th job advancement.\\r\\nI've seen countless {} eager to make the jump just like you, only to see them fail. But what of you? Are you ready to be tested and make the 3th job advancement?",
                    self.getTemplateId(), getBasicJobNamePlural(), Moematter.format("in charge of bringing out the best in each and every {} that needs my guidance", getBasicJobName()), getBasicJobName(), getBasicJobNamePlural()
                ), ()->{
                    user.getQuestHolder().setValue(THIRD_JOB_QUEST_ID, "s");
                    var first = Moematter.format("Good. You will be tested on two important aspects of the {}: strength and wisdom. I'll now explain to you the physical half of the test. Remember #b#p{}##k from #m{}#? Go see them, and they'll give you the details on the first half of the test. Please complete the mission, and get #b#t{}##k from #p{}#.",
                        getBasicJobName(), getFirstJobInstructor(), getJobHome(), necklaceOfStr, getFirstJobInstructor()
                    );
                    var second = Moematter.format("The mental half of the test can only start after you pass the physical part of the test. #b#t{}##k will be the proof that you have indeed passed the test. I'll let #b#p{}##k know in advance that you're making your way there, so get ready. It won't be easy, but I have the utmost faith in you. Good luck.",
                        necklaceOfStr, getFirstJobInstructor()
                    );
                    say(first, second);
                }, ()->say("I see you're not ready to face the challenges that lie ahead. Return only when you've convinced yourself otherwise."));
                break;
            case 1://quest in progress
                if("s".equals(advanceValue) || "p1".equals(advanceValue)) {//STR in progress, but not yet completed
                    say(Moematter.format("You do not have #b#t{}##k with you. Go see #b#p{}##k of #m{}#, pass the test and bring #b#t{}##k with you. Then, and only then, you will be given the second test. Best of luck to you.", necklaceOfStr, getFirstJobInstructor(), getJobHome(), necklaceOfStr));
                } else if("p2".equals(advanceValue)) {//PHY
                    if(user.hasItem(necklaceOfStr)) {
                        say("Congratulations on completing the physical part of the test. I knew you could do it. Now that you have passed the first part of the test, you can do the second part. Give me the necklace first.").andThen(()->{
                            if(user.exchange(0, necklaceOfStr, -1)) {//Complete Strength
                                user.getQuestHolder().setValue(THIRD_JOB_QUEST_ID, "end1");
                                say("Here's the 2nd half of the test. This test will determine whether you are smart enough to take the next step towards greatness. There is a dark, snow-covered area called the Holy Ground at the snowfield in Ossyria, where even the monsters can't reach. On the center of the area lies a huge stone called the Holy Stone. You'll need to offer a special item as the sacrifice, then the Holy Stone will test your wisdom right there on the spot.",
                                    Moematter.format("You'll need to answer each and every question given to you with honesty and conviction. If you correctly answer all the questions, then the Holy Stone will formally accept you and hand you #b#t{}##k. Bring back the necklace, and I will help you to the next step forward. Good luck.", necklaceOfWis)
                                );
                            } else sayf("Are you sure you received #b#t{}##k from #b#p{}##k?", necklaceOfStr, getFirstJobInstructor());
                        });
                    } else say(Moematter.format("You do not have #b#t{}##k with you. Go see #b#p{}##k of #m{}#, pass the test and bring #b#t{}##k with you. Then, and only then, you will be given the second test. Best of luck to you.", necklaceOfStr, getFirstJobInstructor(), getJobHome(), necklaceOfStr));
                } else if("end1".equals(advanceValue)) {//WIS
                    //I see a pattern. p2 / end1 do essentially same thing with different text, can be abstracted.
                    if(user.hasItem(necklaceOfWis)) {
                        say("Great job completing the mental part of the test. You have wisely answered all the questions correctly. I must say, I am quite impressed with the level of wisdom you have displayed there. Please hand me the necklace first, before we take on the next step.").andThen(()->{
                            if(user.exchange(0, necklaceOfWis, -1)) {
                                user.getQuestHolder().complete(THIRD_JOB_QUEST_ID);
                                promptForThirdJobAdvance();
                            } else sayf("Are you sure you have #t{}# granted by the Holy Stone? If you are sure, do not forget to leave space in your ETC inventory.", necklaceOfWis);
                        });
                    } else {
                        sayf("You don't have #b#t{}##k with you. Please go see #bHoly Stone#k in the Holy Ground, pass the test and bring #b#t{}##k back with you. Then, and only then, I will help you make the next leap forward. Best of luck to you.", necklaceOfWis, necklaceOfWis);
                    }
                } else say("This should not happen. Value: [" + advanceValue+"]");
                break;
            case 2://quest complete
                promptForThirdJobAdvance();
                break;
            default:
                say("Invalid quest state: " + questState);
                break;
        }
    }

    private void promptForThirdJobAdvance() {
        String askPrompt = Moematter.format("Okay! Now, I'll transform you into a much more powerful {}. Before that, however, please make sure your SP has been thoroughly used. You'll need to use at least all the SP gained up to level {} in order for you to make the 3rd job advancement. Oh, and since you've already chosen class path in 2nd job advancement, this won't require much thought. Do you wish to make the job advancement right now?", getBasicJobName(), thirdLvReq);
        askYesNo(askPrompt, ()->{
            int psp = (user.getLevel() - thirdLvReq) * 3;
            if (user.getSkillPoints() <= psp) {
                short job = (short) (user.getJobId() + 1);
                user.setJob(job, true);
                user.increaseAbilityPoints(5);
                user.increaseSkillPoints(1);
                var saying = new SayBuilder(this);
                var jobText = getThirdJobIntroText(job);
                saying.say(Moematter.format("{} #b{}#k{}. {}", jobText.preText, jobText.jobName, jobText.postText, jobText.skillGuide)).sayf("I've also given you additional Skill and Ability points to help you get started. Please apply them when you get a chance. You have now become a formidable {}, indeed. Remember, though, that this will open up a whole new world of difficult journeys. Once you feel like you cannot reach a higher place, then come see me. I'll be here waiting.", getBasicJobName());
                say(saying.build());
            } else {
                say("Hmmm... you seem to have too much SP. You can't make the 3rd job advancement with so much SP. Use more in the 1st and 2nd advancement before returning here.");
            }
        }, ()->say("You've passed the test, so there's nothing to worry about... well, come and talk to me when you make your decision. As soon as you're ready, you can advance to the third job."));
    }

    abstract JobText getThirdJobIntroText(int jobId);
    protected final JobText dummyJobText = new JobText("error", "error", "error", "error");
    protected class JobText {
        final String preText;
        final String jobName;
        final String postText;
        final String skillGuide;
        JobText(String preText, String jobName, String postText, String skillGuide) {
            this.preText = preText;
            this.jobName = jobName;
            this.postText = postText;
            this.skillGuide = skillGuide;
        }
    }

    /**
     * By getting to this function, we've already assumed the user is the correct job and above level requirement for Zakum.
     * But nexon checks again, so they have text responses and such I'll add below.
     */
    private void doZakum() {
        int someZakumQuest = 7000;//I didn't really code zakum quests yet, so i have the bare minimums here.
        int adobis = 2030008;
        var zakumQuestVal = user.getQuestHolder().getValue(someZakumQuest);
        final String text;
        if(zakumQuestVal.isEmpty()) {
            if(user.getLevel() >= zakumLvReq) {
                if (isCorrectJobCategory(user.getJobId())) {
                    user.getQuestHolder().setValue(someZakumQuest, "s");
                    text = "You want to be permitted to do the Zakum Dungeon Quest, right? Must be #b#p" + adobis + "##k... Ok, alright! I'm sure you'll be fine roaming around the dungeon. Here's hoping you'll be careful there...";
                } else text = Moematter.format("You don't look like a {}. Try asking your class instructor.", getBasicJobName());
            } else text = Moematter.format("You want to be permitted to do the Zakum Dungeon Quest, right? You're not quite strong enough yet, train some more and come back when you're at least level {}.", zakumLvReq);
        } else text ="How are things with the Zakum Dungeon Quest? From what I've heard, there's an incredible monster at the innermost part of that place... anyway, good luck. I'm sure you can do it.";
        say(text);
    }

    private boolean isCorrectJobCategory(int jobId) {
        return Math.floor(jobId / 100) == getJobCategory();
    }

    private Set<Integer> generateJobs(Collection<Integer> jobSuffixes) {
        Set<Integer> ret = new HashSet<>(jobSuffixes.size());
        for(int suffix : jobSuffixes) {
            ret.add(getJobCategory()*100 + suffix);
        }
        return ret;
    }

    abstract String getBasicJobName();
    abstract String getBasicJobNamePlural();
    abstract int getJobHome();
    abstract int getFirstJobInstructor();
    abstract int getJobCategory();

}
