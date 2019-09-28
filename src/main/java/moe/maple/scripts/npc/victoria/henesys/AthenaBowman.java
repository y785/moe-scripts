package moe.maple.scripts.npc.victoria.henesys;

import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.helper.Exchange;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.npc.misc.job.ExplorerJobInstructor;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;
import java.util.Optional;

/**
 * Created on 9/16/2019.
 */
@Script(name="bowman", field = 100000201, description = "Athena Pierce (1012100) | Bowman Instructional School | Job Advancement")
public class AthenaBowman extends ExplorerJobInstructor {

    @Override
    protected String getCategoryName() {
        return "Bowman";
    }

    @Override
    protected String getCategoryPlural() {
        return "Bowmen";
    }

    @Override
    protected String getCreepyText() {//lewd..
        return "Don't you want to feel the #eexcitement#n of hunting down the monsters from out of nowhere? #ePiercing#n through them with insane accuracy and your #ebig, long#n arrow? Only the Bowman can do that...";
    }

    @Override
    protected Optional<String> getJobText(int jobId) {
        String jobText = null;
        switch(jobId) {
            case Jobs.Hunter:
                jobText = "Ahhh... it's you. What do you think? How's the life of a #bHunter#k? You seem to be able to handle the explosive arrows with ease... keep trying.";
                break;
            case Jobs.Crossbowman:
                jobText = "Ahhh... it's you. What do you think? How's the life of a #bCrossbowman#k? Be careful with these rotating arrows. You will not want to hurt innocent people with them.";
                break;
            case Jobs.Ranger:
            case Jobs.Bowmaster:
            case Jobs.Sniper:
            case Jobs.Marksman:
                jobText = ahhYouFinally(Jobs.getName(jobId));
                break;
        }
        return Optional.ofNullable(jobText);
    }

    @Override
    protected String getGeneralIntro() {
        return "Do you have any questions regarding the life of the Bowman?";
    }

    @Override
    protected List<Tuple<String, List<String>>> getGeneralInfo() {
        return Tuple.listOf(
            "What does it mean to be a Bowman?", List.of(
                "This is what being a bowman is all about. The bowman possesses just enough stamina and strength. Their most important ability to use is DEX. They don't have much of a stamina, so please avoid close combat if possible.",
                "The main advantage is that you can attack from afar, enabling you to avoid many close attacks by the monsters. Not only that, but with high dexterity, you can avoid quite attacks up close. The higher the DEX, the more damage you can dish out."
            ),
            "What weapons do bowmen (or bowwomen) use?", List.of(
                "I'll explain the weapons that bowman use. Instead of using weapons to strike or slash the opponents, they use long-distance weapons such as bows and rockbows to kill the monsters. They both have their share of advantages and disadvantages.",
                "Bows aren't as powerful as the rockbows, but they are much quicker to attack with. Rockbows, on the other hand, are more powerful with less quickness. It'll be hard for you to make a decision on this...",
                "Good arrows and rockbows are available through monsters, so it's a must that you hunt often. It won't be easy to obtain, however. Train yourself harder each and everyday, and you'll see some success coming your way ..."
            ),
            "What armors can bowmen wear?", List.of(
                "I'll explain the armors the bowman use. They need to move around quickly so it won't be any good to put on huge, elaborate armor. Clothes with long cumbersome laces are definitely off limits.",
                "But if you wear huge stiff armor that the warriors don, you'll be surrounded by the enemies in no time. Equip yourself with simple, comfortable armor that fits you just fine and still does the job. It'll help you a great deal when hunting down the monsters."
            ),
            "What skills do bowmen use?", List.of(
                "For bowman, skills that are available are the ones that puts their high accuracy and dexterity to good use. It's a must for the Bowman to acquire skills that allows them to attack the enemies accurately.",
                "There are two kinds of offensive skills for the bowman: #bArrow Blow#k and #bDouble Shot#k. Arrow Blow is a nice, basic skills that allows you to highly damage the enemy with minimal use of MP.",
                "On the other hand, Double Shot allows you to attack the enemy twice using some MP. You'll only be able to get it after boosting Arrow Blow to at least higher than 1, so remember that. Whatever the choice, make it your own."
            )
        );
    }

    @Override
    protected int getFirstJobLevelRequirement() {
        return 10;
    }

    @Override
    protected short getFirstJobId() {
        return Jobs.Archer;
    }

    @Override
    protected Exchange getFirstJobExchangeRewards() {
        return new Exchange(0, Tuple.listOf(1452051, 1, 2060000, 6000));
    }

    @Override
    protected String getFirstJobAskPrefix() {
        return "After checking you out a bit, I'd say you look qualified. You've got a great pair of eyes! With a little training you'll be able to spot out any monster and acquire the coldhearted skills needed to shoot an arrow through them... I could use someone like that.";
    }

    @Override
    protected String getFirstJobRewardSuffix() {
        return "How? Because I said so... haha. Here's a little bit of my power... #eHaahhhh#n!";
    }

    @Override
    protected String getFirstJobClosingStatement() {
        return "Train harder and you may one day reach the very peak of the Bowman. I'll be watching you from afar. Please work hard.";
    }

    @Override
    protected int getSecondJobLevelRequirement() {
        return 30;
    }

    @Override
    protected int getSecondJobLetterId() {
        return 4031010;
    }

    @Override
    protected int getSecondJobInstructorId() {
        return 1072002;
    }

    @Override
    protected int getSecondJobTown() {
        return Victoria.Henesys;
    }

    @Override
    protected int getSecondJobLocation() {
        return 106010000;
    }

    @Override
    protected String getSecondJobYouHaveGrown() {
        return "Hmmm... you have grown a lot since I last saw you. I don't see the weakling I saw before. Instead, you look much more like a proper bowman";
    }

    @Override
    protected String getSecondJobGenderSubject() {
        return "She";
    }

    @Override
    protected List<Tuple<String, List<String>>> getSecondJobChoicesAndExplanations() {
        return Tuple.listOf(
                "Hunter", List.of(
                    "Ok. This is what being the Hunter is all about. Hunters have skills such as Bow Mastery and Bow Booster that enables you to use bows well. There's also a skill called Soul Arrow : Bow for the Hunters that waste quite a few arrows. It allows you to fire away arrows for a long period of time without actually wasting the arrows, so if you may have spent some mesos before on arrows, this may be just for you...",
                    "I'll explain to you more about one of the skills of the Hunter, #bPower Knock-Back#k. No one beats Hunter in terms of long-range attacks, but it's a whole different story when there's a lot of enemies or if you need to attack them up close. Therefore, it makes this skill very important to acquire. It allows you not only to strike the enemy up close, but also send multiple monsters far back. It's a very important skill to have to acquire some much-needed space.",
                    "I'll explain to you the offensive skill of the Hunter, #bArrow Bomb : Bow#k. It's a skill that allows you to fire away arrows with bombs. If struck just right, the bomb will go off on the enemy, damaging those around it and temporarily knocking them out. Combine that skill with the Critical Shot, the first level skill, and the damage will be incredible. You should try becoming the Hunter for your job advancement."
                ),
                "Crossbowman", List.of(
                    "Ok. This is what being the Crossbowman is all about. For the Crossbowman, skills like Crossbow Mastery and Crossbow Booster are available along with Soul Arrow : Bow for those who wastes the bows by shooting a lot and missing a lot. This skill enables the player to shoot the arrows for a long period of time without wasting the bows, so if you have been spending a lot of mesos on bows, you may want to check it out...",
                    "Ok. One of the skills that the Crossbowman can have is #bPower Knock-Back#k. No one can approach the long-distance attacks of the Crossbowman, but it's a different story altogether when talking about close combats or facing lots of enemies at once. For that, this is a very important skill to acquire. It allows you to strike down the enemy with full force, sending a number of enemies far back in the process. A very important skill that provides you with some much-needed space.",
                    "Ok, I'll explain to you one of the attacking skills for the Crossbowman, #bIron Arrow : Crossbow#k. This skill enables you to attack multiple enemies, as the arrow that hits a monster will go through it and hit another monster behind one. The damage decreases an arrow goes through an enemy, but it can still attack a number of enemies at once, a very Threaten skill to have. And...if it's combined with Critical Shot...that will be just incredible."
                )
        );
    }

    @Override
    protected String[][] getSecondJobFinale() {
        return new String[][] {//I know these are very similar and could probably be condensed, but I am bored of this :(
                {//Hunter
                    "Alright, you're the #bHunter#k from here on out. Hunters are the intelligent bunch with incredible vision, able to pierce the arrow through the heart of the monsters with ease...please train yourself each and everyday. We'll help you become even stronger than you already are.",
                    "I have just given you a book that gives you the the list of skills you can acquire as a hunter. Also your etc. inventory has expanded by adding another row to it. Your max HP and MP have also increased, too. Go check and see for it yourself.",
                    "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                    "Hunters need to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further. I'll be waiting for you."
                },
                {//Crossbowguy
                    "Alright, you're the #bCrossbowman#k from here on out. Crossbowman are the intelligent bunch with incredible vision, able to pierce the arrow through the heart of the monsters with ease...please train yourself each and everyday. We'll help you become even stronger than you already are.",
                    "I have just given you a book that gives you the the list of skills you can acquire as a hunter. Also your etc. inventory has expanded by adding another row to it. Your max HP and MP have also increased, too. Go check and see for it yourself.",
                    "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                    "Crossbowmen need to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further. I'll be waiting for you."
                }
        };
    }


    @Override
    protected boolean isValidSecondJob(int jobId) {
        return jobId == Jobs.Hunter || jobId == Jobs.Crossbowman;
    }

    @Override
    protected int getThirdJobInstructorId() {
        return 2020010;
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
