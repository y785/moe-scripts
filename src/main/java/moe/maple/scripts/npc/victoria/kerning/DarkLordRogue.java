package moe.maple.scripts.npc.victoria.kerning;

import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.helper.Exchange;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.npc.misc.job.ExplorerJobInstructor;
import moe.maple.scripts.util.Items;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;
import java.util.Optional;

/**
 * Created on 9/23/2019.
 */
@Script(name="rogue", description = "Dark Lord | Thieves Hideout | Job Advancement")
public class DarkLordRogue extends ExplorerJobInstructor {

    @Override
    protected String getCategoryName() {
        return "Thief";
    }

    @Override
    protected String getCategoryPlural() {
        return "Thieves";
    }

    @Override
    protected String getCreepyText() {
        return "Exploring is good, and getting stronger better... but don't you want to enjoy living life as you know it? How about becoming a Rogue like us and really LIVE the life? Sounds fun, isn't it?";
    }

    @Override
    protected Optional<String> getJobText(int jobId) {
        final String text;
        switch (jobId) {
            case Jobs.Assassin: text = "Ahhh... it's you. What do you think? How's the life of an #bAssasin#k? You seem to be able to handle the throwing stars with ease... keep trying."; break;
            case Jobs.Bandit: text = "Ahhh... it's you. What do you think? How's the life of a #bBandit#k? Don't forget to stick em with the pointy end!"; break;
            case Jobs.Hermit:
            case Jobs.NightLord:
            case Jobs.ChiefBandit:
            case Jobs.Shadower:
                text = ahhYouFinally(Jobs.getName(jobId)); break;
            default: return Optional.empty();
        }
        return Optional.of(text);
    }

    @Override
    protected String getGeneralIntro() {
        return "Do you have anything you want to ask about thieves?";
    }

    @Override
    protected List<Tuple<String, List<String>>> getGeneralInfo() {
        return Tuple.listOf(
            "What does it mean to be a Thief?", List.of("Let me explain to you what being a thief means. Thieves have just the right amount of stamina and strength to survive. We don't recommend training for strength just like those warriors. What we do need are LUK and DEX...",
                        "If you raise the level of luck and dexterity, that will increase the damage you'll dish out to the enemies. Thieves also differentiate themselves from the rest by using such throwing weapons as throwing stars and throwing knives. They can also avoid many attacks with high dexterity."
                ),
            "What weapons do thieves use?", List.of( "Thieves use these weapons. They have just the right amount of intelligence and power...what we do have are quick movements and even quick brain...",
                        "Because of that, we usually use daggers or throwing weapons. Daggers are basically small swords but they are very quick, enabling you to squeeze in many attacks. If you fight in a close combat, use the dagger to quickly eliminate the enemy before it reacts to your attack.",
                        "For throwing weapons there are throwing-stars and throwing-knives available. You can't just use them by themselves, though. Go to the weapon store at Kerning City, and they'll sell an claw call Garnier. Equip yourself with it, then you'll be able to throw the throwing stars that's in the use inventory.",
                        "Not only is it important to choose the right Claw to use, but it's just as important to choose the right kind of throwing stars to use. Do you want to know where to get those stars? Go check out the armor store around this town...there's probably someone that handles those specifically..."
                ),
            "What armors can thieves wear?", List.of("Let me explain to you the armors that the thieves can wear. Thieves value quick so you need clothes that fit you perfectly. But then again, they don't need chained armors like the bowman, because it won't help you one bit.",
                        "Instead of flashy clothes or tough, hard gold-plated armor, try putting on simple, comfortable clothes that fit you perfectly and still do its job in the process. It'll help you a lot in hunting the monsters."
                ),
            "What skills do thieves use?", List.of("For thieves there are skills that supports high accuracy and dexterity we have in general. A good mix of skills are available, for both the throwing stars and daggers. Choose your weapon carefully, for skills are determined by it.",
                        "If you are using throwing-stars, skills like #bKeen Eyes#k or #bLucky Seven#k are perfect. Lucky Seven allows you to throw multiple throwing-stars at once, so it'll help you greatly in hunting down enemies.",
                        "If you are using daggers choose #bDisorder#k or #bDouble Stab#k as skills. Double Stab, in fact, will be a great skills to use in that it enables you to attack an enemy in a blinding flurry of stabs, a definate stunner indeed."
                )
        );
    }

    @Override
    protected int getFirstJobLevelRequirement() {
        return 10;
    }

    @Override
    protected short getFirstJobId() {
        return Jobs.Rogue;
    }

    @Override
    protected Exchange getFirstJobExchangeRewards() {
        return new Exchange(0, Tuple.listOf(1472061, 1, 1332063, 1, Items.BEGINNER_THIEF_THROWING_STARS, 3000));
    }

    @Override
    protected String getFirstJobAskPrefix() {
        return "Oh...! You look upside down! HAHA! Someone like you can definitely be a part of us... all you need is a #esinister mind#n, and... yeah...";
    }

    @Override
    protected String getFirstJobRewardSuffix() {
        return "You are part of us! You'll be living the life of a wanderer at first, but just be patient and soon, you'll be living the high life. Alright, it ain't much, but I'll give you some of my abilities... #eBELIEVE IT!!#n";
    }

    @Override
    protected String getFirstJobClosingStatement() {
        return "Train harder and you may one day reach the very peak of the Thief. I'll be watching you from afar. Please work hard.";
    }

    @Override
    protected int getSecondJobLevelRequirement() {
        return 30;
    }

    @Override
    protected int getSecondJobLetterId() {
        return 4031011;
    }

    @Override
    protected int getSecondJobInstructorId() {
        return 1072003;
    }

    @Override
    protected int getSecondJobTown() {
        return Victoria.Kerning;
    }

    @Override
    protected int getSecondJobLocation() {
        return 102040000;
    }

    @Override
    protected String getSecondJobYouHaveGrown() {
        return "Hmmm... you seem to have gotten a whole lot stronger. You are no longer your old, weak self... You look much more like a thief now";
    }

    @Override
    protected String getSecondJobGenderSubject() {
        return "He";
    }

    @Override
    protected List<Tuple<String, List<String>>> getSecondJobChoicesAndExplanations() {
        return Tuple.listOf(
                "Assassin", List.of("Let me explain the role of the Assassin. Assassin is the Thief that uses throwing stars or daggers. Skills like #bClaw Mastery#k and #bCritical Throw#k will help you use your throwing stars better. Boost Claw Mastery up more and your maximum number of throwing stars increases, so it'll be best to learn it. Please remember that.",
                        "I'll explain to you one of the skills of the Assassin, #bHaste#k. It temporarily boosts your party's abilities and moving speed, perfect when facing enemies that are really fast. It's also useful when walking to a place far far away. Wouldn't it be much nicer to get to your destination on time as opposed to taking a whole day just to get there?",
                        "And this is another skill available for the Assassin, #bDrain#k. It allows you to take back a portion of the damage you dished out on an enemy and absorb it as HP! The more the damage, the more you'll regain health...how awesome is that? Remember the most you can absorb at once is half of your maximum HP. The higher the enemy's HP, the more you can take away."
                ),
                "Bandit", List.of("This is what being the Bandit is all about. Bandits are thieves who specialize in using daggers. Skills like #bDagger Mastery#k and #bDagger Booster#k will help you use your dagger better. Daggers have quick attacking speed to begin with, and if you add that with a booster, then...oh my! Fast enough to scare the crap out of the monsters!",
                        "I'll explain to you what #bSteal#k does for Bandits. It gives you a certain probability to let you steal an item from an enemy. You may only steal once from one enemy, but you can keep trying until you succeed from it. The stolen item will be dropped onto the ground; make sure you pick it up first because it's anyone's to grab once it's dropped.",
                        "I'll explain to you what #bSavage Blow#k does for Bandits. It uses up HP and MP to attack the enemy 6 TIMES with the dagger. The higher the skill level, the more the attacks may occur. You'll cut up the enemy to pieces with the dagger...ooooh, isn't it sweet? What do you think? Want to become a Bandit and feel the adrenaline rush that comes with it?"
                )
            );
    }

    @Override
    protected String[][] getSecondJobFinale() {
        return new String[][] {
                {//Assassin
                        "Alright, from here on out you are the #bAssassin#k. Assassins revel in shadows and darkness, waiting until the right time comes for them to stick a dagger through the enemy's heart, suddenly and swiftly...please keep training. I'll make you even more powerful than you are right now!",
                        "I have just given you a book that lists the skills you can acquire as an assasin... a Skill Book, if you will. I've also added a row to your USE inventory. Your max HP and MP have also been extended... go see it for yourself.",
                        "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                        "Assassins have to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further."
                },
                {//Bandit
                        "Alright from here on out, you're the #bBandit#k. Bandits have quick hands and quicker feet to dominate their enemies. Please keep training. I'll make you even more powerful than you are right now.",
                        "I have just given you a book that lists the skills you can acquire as a bandit... a Skill Book, if you will. I've also added a row to your USE inventory. Your max HP and MP have also been extended... go see it for yourself.",
                        "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                        "Assassins have to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further."
                }
        };
    }

    @Override
    protected boolean isValidSecondJob(int jobId) {
        return jobId == Jobs.Assassin || jobId == Jobs.Bandit;//maybe db 430 here too
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
        return "him";
    }
}
