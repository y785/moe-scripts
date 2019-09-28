package moe.maple.scripts.npc.victoria.perion;

import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.npc.misc.job.ExplorerJobInstructor;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;
import java.util.Optional;

/**
 * Created on 9/22/2019.
 */
@Script(name="fighter", field = 102000003, description = "Dances With Balrog | Job Advancement")
public class DancesFighter extends ExplorerJobInstructor {
    @Override
    protected String getCategoryName() {
        return "Warrior";
    }

    @Override
    protected String getCategoryPlural() {
        return "Warriors";
    }

    @Override
    protected String getCreepyText() {
        return "What a magnificent #ephysique#n! What incredible #epower#n! Warriors are the #ebest#n!!!! What do you think? Do you want to " + (Math.random() > 0.75 ? "make me " : "") + " rise up as a #eWarrior#n?";
    }

    @Override
    protected Optional<String> getJobText(int jobId) {
        String jobText = null;
        switch(jobId) {
            case Jobs.Fighter:
                jobText = "Ahh! You! What do you think? How is the life of a #bFighter#k? You.. look a lot stronger than before! Keep on improving, and you'll become a #rCrusader#k!";
                break;
            case Jobs.Page:
                jobText = "Ahh... What do you think? How is the life of a #bPage#k? I know you're still in training, but soon the training will be over, and you'll become a #rKnight#k!";
                break;
            case Jobs.Spearman:
                jobText = "Ahh... What do you think? How is the life of a #bSpearman#k? Keep training with dedication, because one day, you will become an unequaled #rDragon Knight#k...";
                break;
            case Jobs.Crusader:
            case Jobs.WhiteKnight:
            case Jobs.DragonKnight:
            case Jobs.Hero:
            case Jobs.Paladin:
            case Jobs.DarkKnight:
                jobText = ahhYouFinally(Jobs.getName(jobId));
                break;
        }
        return Optional.ofNullable(jobText);
    }

    @Override
    protected String getGeneralIntro() {
        return "Oh, you have a question? I am big warrior man who knows many many warrior things.\\r\\n#eIf you actually read this shit, please screenshot it and submit it to discord.#n";
    }

    @Override
    protected List<Tuple<String, List<String>>> getGeneralInfo() {
        return Tuple.listOf(
                "What does it mean to be a Warrior?", List.of(
                    "Let me explain the role of a Warrior. Warriors possess awesome physical strength and power. They can also defend monsters' attacks, so they excel at fighting up close and personal. With a high level of stamina, you won't be dying easily either.",
                    "The most common weapon types used by Warriors are #bsword, blunt weapon, polearm, spear, and axe#k. Eeach weapon has its advantages and disadvantages, so please take a close look at them before choosing one. For now, try using the ones with high attack rating.."
                ),
                "What weapons do Warriors use?", List.of("Warriors use weapons that allow them to #rslash, stab or strike#k. You won't be able to effectively use weapons like bows and projectile weapons. Same with the small wand things you see gramps using.",
                    "The most common weapon types used by Warriors are #bsword, blunt weapon, polearm, spear, and axe#k. Eeach weapon has its advantages and disadvantages, so please take a close look at them before choosing one. For now, try using the ones with high attack rating.."
                ),
                "What kind of armor do Warriors wear?", List.of("Warriors can wear tough, strong armor. #eNot the most fashionable#n... but they serve their purpose well, the best of the armors.",
                    "You may also choose to pick up the #bshield#k. Remember, though, that you won't be able to use shields while wielding two handed equipment. Tough decision, isn't it?"
                ),
                "What skills do Warriors use?", List.of(
                    "Warrior skills are geared towards their awesome physical strength and power. They'll greatly assist you in closed combat. There's also a skill that allows you to recover your HP. #eI recommend mastering that early on#k.",
                    "Your two basic attacking skills are #bPower Strike#k and #rSlash Blast#k. #bPower Strike#k applies a lot of damage to a single enemy, and you can learn this from the get-go.",
                    "On the other hand, #rSlash Blast#k doesn't deal as much damage as #bPower Strike#k, but instead attacks multiple enemies around the area at once. This skill is available only after learning #bPower Strike#k."
                )
        );
    }

    @Override
    protected int getFirstJobLevelRequirement() {
        return 10;
    }

    @Override
    protected short getFirstJobId() {
        return Jobs.Swordman;
    }

    @Override
    protected List<Tuple<Integer, Integer>> getFirstJobExchangeRewards() {
        return List.of(Tuple.of(1302077,1));
    }

    @Override
    protected String getFirstJobAskPrefix() {
        return "You definitely have the look of a Warrior. You may not be there just yet, but I can see the Warrior in your "+(Math.random()>0.75?"#bbeautiful, breathtakingly gorgeous#k ":"")+"eyes";
    }

    @Override
    protected String getFirstJobRewardSuffix() {
        return "Please continue working hard...\\r\\nI'll enhance your abilities a bit, with the hope of you training yourself to be even stronger going forward.\\r\\n#eHaaaaaap!!#n";
    }

    @Override
    protected String getFirstJobClosingStatement() {
        return "HAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH!";
    }

    @Override
    protected int getSecondJobLevelRequirement() {
        return 30;
    }

    @Override
    protected int getSecondJobLetterId() {
        return 4031008;
    }

    @Override
    protected int getSecondJobInstructorId() {
        return 1072000;
    }

    @Override
    protected int getSecondJobTown() {
        return Victoria.Perion;
    }

    @Override
    protected int getSecondJobLocation() {
        return 102020300;
    }

    @Override
    protected String getSecondJobYouHaveGrown() {
        return "... Is that really you, #b#h ##k? Wow, you really have grown. I can truly feel the presence of a Warrior! Impressive.";
    }

    @Override
    protected String getSecondJobGenderSubject() {
        return "He";
    }

    @Override
    protected List<Tuple<String, List<String>>> getSecondJobChoicesAndExplanations() {
        return Tuple.listOf("a", List.of(), "b", List.of());
    }

    @Override
    protected String[][] getSecondJobFinale() {
        return new String[][]{
                {//Fighter
                        "Alright! You have now become the #bFighter#k! A fighter strives to become the strongest of the strong, and never stops fighting. Don't ever lose that will to fight, and push forward 24/7. It'll help you become even stronger than you already are.",
                        "I have just given you a book that gives you the list of skills you can acquire as the Fighter. In that book, you'll find a bunch of skills the Fighter can learn. Your use and etc. inventories have also been expanded with an additional row of slots also available. Your max MP has also been increased...go check and see for it yourself.",
                        "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                        "Fighters have to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further."
                },
                {//Page
                        "Alright! You have now become the #bPage#k! The Pages have high intelligence and bravery for a Warrior...here's hoping that you'll take the right path with the right mindset...I'll help you become much stronger than you are right now.",
                        "I have just given you a book that gives you the list of skills you can acquire as the Page. In that book, you'll find a bunch of skills the Fighter can learn. Your use and etc. inventories have also been expanded with an additional row of slots also available. Your max MP has also been increased...go check and see for it yourself",
                        "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning you have learned other skills. Make sure to remember that.",
                        "Pages have to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further."
                },
                {//Spearman
                        "Alright! You have now become the #bSpearman#k! The spearman use the power of darkness to take out the enemies, always in shadows...please believe in yourself and your awesome power as you go on in your journey...I'll help you become much stronger than you are right now.",
                        "I have just given you a book that gives you the list of skills you can acquire as the Spearman. In that book, you'll find a bunch of skills the Fighter can learn. Your use and etc. inventories have also been expanded with an additional row of slots also available. Your max MP has also been increased...go check and see for it yourself.",
                        "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                        "Spearmen have to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further."
                }
        };
    }

    @Override
    protected boolean isValidSecondJob(int jobId) {
        return jobId == Jobs.Fighter || jobId == Jobs.Page || jobId == Jobs.Spearman;
    }

    @Override
    protected int getThirdJobInstructorId() {
        return 2020008;//Tylus
    }

    @Override
    protected String getThirdJobDoorLocation() {
        return "Ant Tunnel";
    }

    @Override
    protected String getThirdJobGenderObject() {
        return "him";
    }
}
