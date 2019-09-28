package moe.maple.scripts.npc.victoria.ellinia;

import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.helper.Exchange;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.npc.misc.job.ExplorerJobInstructor;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;
import java.util.Optional;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name="magician", field = 101000003, description = "Grendel the Really Old (1032001) | Magic Library | Job Advancement")
public class GrendelMagician extends ExplorerJobInstructor {
    @Override
    protected String getCategoryName() {
        return "Magician";
    }

    @Override
    protected String getCategoryPlural() {
        return "Mages";
    }

    @Override
    protected String getCreepyText() {
        return "Would you like to have the power of nature in itself in your hands? It may be a #elong, hard#n road to be on, but you'll surely be rewarded in the end, reaching the very top of wizardry...";
    }

    @Override
    protected Optional<String> getJobText(int jobId) {
        final String text;
        switch (jobId) {
            case Jobs.FirePoisonWizard: text = "Ahhh... it's you. What do you think? How do you like being a #bFire/Poison Wizard#k? You... you seem used to these fire arrows now... please, work hard and train more."; break;
            case Jobs.IceLightningWizard: text = "Ahhh... it's you. What do you think? How do you like being a #bIce/Lightning Wizard#k? You... you seem used to ice and light now... please, work hard and train more."; break;
            case Jobs.Cleric: text = "Ahhh... it's you. What do you think? How do you like being a #bCleric#k?  You... you seem used to holy magic now... please, work hard and train more."; break;
            case Jobs.IceLightningMage:
            case Jobs.IceLightningArchmage:
            case Jobs.FirePoisonMage:
            case Jobs.FirePoisonArchmage:
            case Jobs.Priest:
            case Jobs.Bishop:
                text = ahhYouFinally(Jobs.getName(jobId)); break;
            default: return Optional.empty();
        }
        return Optional.of(text);
    }

    @Override
    protected String getGeneralIntro() {
        return "Any questions about being a magician?";
    }

    @Override
    protected List<Tuple<String, List<String>>> getGeneralInfo() {
        return Tuple.listOf(
                "What does it mean to be a Magician?", List.of("I'll tell you more about being a Magician. Magicians put high levels of magic and intelligence to good use. They can use the power of nature all around us to kill the enemies, but they are very weak in close combat. Our stamina isn't very high either, so tread with caution and avoid death at all cost.",
                    "You can cast spells to attack monsters from afar. Try boosting up your INT if you want increase your magical prowess and accurately battle with your magic. The higher your intelligence, the better you'll be able to handle magic..."
                ),
                "What weapons do magicians use?", List.of("I'll tell you more about the weapons that Magicians use. Actually, it doesn't mean much for Magicians to attack the opponents with weapons. Magicians lack power and dexterity, so you will have a hard time even defeating a snail.",
                    "If we're talking about the magical powers, then THAT's a whole different story. The weapons that Magicians use are blunt weapons, staff, and wands. Blunt weapons are good for, well, blunt attacks, but...I would not recommend that on Magicians, period...",
                    "Rather, staffs and wands are the main weaponry of choice. These weapons have special magical powers in them, so it enhances the Magicians' effectiveness. It'll be wise for you to carry a weapon with a lot of magical powers in it..."
                ),
                "What armors can magicians wear?", List.of("I'll tell you more about the armors that Magicians can wear. Honestly, the Magicians don't have much armor to wear since they are weak in physical strength and low in stamina. Its defensive abilities isn't great either, so I don't know if it helps a lot or not...",
                    "Some armors, however, have the ability to eliminate the magical power, so it can guard you from magic attacks. It won't help much, but still better than not warning them at all...so buy them if you have time..."
                ),
                "What skills are available for magicians?", List.of("The skills available for Magicians use the high levels of intelligence and magic that Magicians have. Also available are Magic Guard and Magic Armor, which help Magicians with weak stamina prevent from dying.",
                    "The offensive skills are #bEnergy Bolt#k and #bMagic Claw#k. First, Energy Bolt is a skill that applies a lot of damage to the opponent with minimal use of MP.",
                    "Magic Claw, on the other hand, uses up a lot of MP to attack one opponent TWICE. But, you can only use Energy Bolt once it's more than 1, so keep that in mind. Whatever you choose to do, it's all up to you..."
                )
        );
    }

    @Override
    protected int getFirstJobLevelRequirement() {
        return 10;
    }

    @Override
    protected short getFirstJobId() {
        return Jobs.Magician;
    }

    @Override
    protected Exchange getFirstJobExchangeRewards() {
        return new Exchange(0, List.of(Tuple.of(1372043, 1)));
    }

    @Override
    protected String getFirstJobAskPrefix() {
        return "You definitely have the look of a Magician. You may not be there yet, but I can see the Magician in you...";
    }

    @Override
    protected String getFirstJobRewardSuffix() {
        return "Alright, you're a Magician from here on out, since I, Grendel the Really old, the head Magician, allow you so.";
    }

    @Override
    protected String getFirstJobClosingStatement() {
        return "You have just equipped yourself with much more magicial power.";
    }

    @Override
    protected int getSecondJobLevelRequirement() {
        return 30;
    }

    @Override
    protected int getSecondJobLetterId() {
        return 4031009;
    }

    @Override
    protected int getSecondJobInstructorId() {
        return 1072005;
    }

    @Override
    protected int getSecondJobTown() {
        return Victoria.Ellinia;
    }

    @Override
    protected int getSecondJobLocation() {
        return 101020000;
    }

    @Override
    protected String getSecondJobYouHaveGrown() {
        return "Hmmm... You have grown quite a bit since you first became a Magician. You used to look so weak and small... but now, I feel your presence as a Magician...";
    }

    @Override
    protected String getSecondJobGenderSubject() {
        return "she";
    }

    @Override
    protected List<Tuple<String, List<String>>> getSecondJobChoicesAndExplanations() {
        return Tuple.listOf(
                "Fire/Poison Wizard", List.of("Allow me to explain the Wizard of Fire and Poison. They specialize in fire and poision magic. Skills like #bMeditation#k, that allows you and your whole party's magic ability to increase for a time being, and #bMP Eater#k, which allows you a certain probability of absorbing some of your enemy's MP, are essential to all the attacking Magicians.",
                        "I'll explain to you a magic attack called #bFire Arrow#k. It fires away flamearrows to the enemies, making it the most powerful skill available for the skills in the 2nd level. It'll work best on enemies that are weak against fire in general, for the damage will be much bigger. On the other hand, if you use them on enemies that are strong against fire, the damage will only be half of what it usually is, so keep that in mind.",
                        "I'll explain to you a magic attack called #bPoison Breath#k. It fires away venomous bubbles on the enemies, poisoning them in the process. Once poisoned, the enemy's HP will decrease little by little over time. If the magic doesn't work too well or the monster has high HP, it may be a good idea to fire enough to kill them with the overdose of poison."
                ),
                "Ice/Lightning Wizard", List.of("Allow me to explain the Wizard of Ice and Lightning. They specialize in ice and lightning magic. Skills like #bMeditation#k, that allows you and your whole party's magic ability to increase for a time being, and #bMP Eater#k, which allows you a certain probability of absorbing some of your enemy's MP, are essential to all the attacking Magicians.",
                        "I'll explain to you a magic attack called #bCold Beam#k. It fires away pieces of ice at the enemies, and although not quite as powerful as Fire Arrow, whoever's struck by it will be frozen for a short period of time. The damage increases much more if the enemy happens to be weak against ice. The opposite holds true, too, in that if the enemy is used to ice, the damage won't quite be as much, so keep that in mind.",
                        "I'll explain to you a magic attack called #bThunder Bolt#k. It's the only 2nd-level skill for Magicians that can be considered the Total Spell, affecting a lot of monsters at once. It may not dish out a lot of damage, but the advantage is that it damages all the monsters around you. You can only attack upto six monsters at once, though. Still, it's a pretty incredible attack."
                ),
                "Cleric", List.of("Allow me to explain the Cleric. Clerics use religious magic on monsters through prayers and incantation. Skills like #bBless#k, which temporarily improves the weapon def., magic def., accuracy, avoidability, and #bInvincible#k, which decreases the weapon damage for a certain amount, help magicians overcome their shortcomings ...",
                        "Cleric is the only Wizard that can perform recovering magic. Clerics are the only one that can do recovery magic. It's called #bHeal#k, and the more MP, INT's, and the skill level for this skill you have, the more HP you may recover. It also affects your party close by so it's a very useful skill, enabling you to continue to hunt without the help of the potion.",
                        "Clerics also have a magic attack called #bHoly Arrow#k. It's a spell that allows the Cleric to fire away phantom arrows at the monsters. The damage isn't too great, but it can apply tremendous damage to the undead's and other evil-based monsters. Those monsters are very weak against holy attack. What do you think, isn't it interesting, right?",
                        "pray to zygod bitches"
                )
        );
    }

    @Override
    protected String[][] getSecondJobFinale() {
        return new String[][]{
                {//FP
                    "From here on out, you have become the #bWizard of Fire and Poison#k... Wizards use high intelligence and the power of nature all around us to take down the enemies...please continue your studies, for one day I may make you much more powerful with my own power...",
                    "I have just given you a book that gives you the list of skills you can acquire as the Wizard of Fire and Poison...I've also extended your etc. inventory by added a whole row to it, along with your maximum MP...go see it for yourself.",
                    "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                    "The Wizards have to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further ..."
                },
                {//IL
                    "From here on out, you have become the #bWizard of Ice and Lightning#k... Wizards use high intelligence and the power of nature all around us to take down the enemies...please continue your studies, for one day I may make you much more powerful with my own power...",
                    "I have just given you a book that gives you the list of skills you can acquire as the Wizard of Ice and Lightning...I've also extended your etc. inventory by added a whole row to it. Your maximum MP has gone up, too. Go see for it yourself.",
                    "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                    "The Wizards have to be strong. But remember that you can't abuse that power and use it on a weakling. Please use your enormous power the right way, because...for you to use that the right way, that is much harder than just getting stronger. Find me after you have advanced much further. I'll be waiting ..."
                },
                {//Cleric
                    "Alright, you're a #bCleric#k from here on out. Clerics blow life into every living organism here with their undying faith in God. Never stop working on your faith...then one day, I'll help you become much more powerful...",
                    "I have just given you a book that gives you the list of skills you can acquire as the Cleric...I've also extended your etc. inventory by added a whole row to it, along with your maximum MP...go see it for yourself.",
                    "I have also given you a little bit of #bSP#k. Open the #bSkill Menu#k located at the bottomleft corner. You'll be able to boost up the newly-acquired 2nd level skills. A word of warning though: You can't boost them up all at once. Some of the skills are only available after you have learned other skills. Make sure to remember that.",
                    "The Cleric needs more faith than anything else. Keep your strong faith in God and treat everyone with respect and dignity they deserve. Keep working hard and you may one day earn more religious magic power...alright...please find me after you have made more strides. I'll be waiting for you..."
                }
        };
    }

    @Override
    protected boolean isValidSecondJob(int jobId) {
        return jobId == Jobs.FirePoisonWizard || jobId == Jobs.IceLightningWizard || jobId == Jobs.Cleric;
    }

    @Override
    protected int getThirdJobInstructorId() {
        return 2020009;//Robeira
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
