package moe.maple.scripts.npc.ossyria.elnath;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author umbreon22
 * Created on 9/9/2019.
 */
@Script(name="holyStone", field = 211040401, description = "Holy Ground At The Snow Field | Third Job Quiz")
public class HolyStoneQuiz extends NpcScript {
    private final int darkCrystalSacrifice = 4005004, wisdomNecklace = 4031058;
    private final int HOW_MANY_QUESTIONS = 6;
    @Override
    protected void work() {
        var val = user.getQuestHolder().getValue(7500);//3rd job quest
        if("end1".equals(val)) {
            askYesNo(Moematter.format("... ... ... Do you wish to offer #b#t{}##k as a #rsacrifice#k to test your wisdom?", darkCrystalSacrifice),
                this::tryStartingQuiz,
                ()->say("... ... ... Come back when you're ready.")
            );
        } else say("... ... ...");
    }

    private void tryStartingQuiz() {
        if(user.getSlotCount(4) > user.getHoldCount(4)) {//slotCount - Total slots. HoldCount - free space. 4 - ETC inventory.
            if(user.getItemCount(wisdomNecklace) > 0) {
                say("... ... ... You already have #b#t"+wisdomNecklace+"##k... Go back now...");
            } else {
                if(user.exchange(0, darkCrystalSacrifice, -1)) {//This bitch took my crystal...  better not fail
                    say("... ... ... Alright...\r\n\r\nI'll be testing out your #bwisdom#k here. Answer all the questions correctly, and you will pass the test...\r\nBUT, if you even lie to me once, then you'll have to start over again. Okay, here we go~")
                    .andThen(()->onWizQuestion(makeHolyQuestions(HOW_MANY_QUESTIONS)));
                } else sayf("... ... ... If you want to test your wisdom, you will have to offer #b#t{}##k as a #rsacrifice#k.", darkCrystalSacrifice);
            }
        } else say("You've come quite unprepared for this test. Clear out some inventory space, then try again...");
    }

    private void onWizQuestion(List<HolyQuestion> questions) {
        if(!questions.isEmpty()) {
            var question = questions.remove(0);
            askMenu(question.question+"?", question.choices).andThen(choice->{
                if(choice != question.answer) {
                    say("Wrong... Try again later.");
                } else {
                    onWizQuestion(questions);
                }
            });
        } else {
            if(user.exchange(0, wisdomNecklace, 1)) {
                say("... ... ... Alright. All your answers have been correct. Your wisdom has been proven.\\r\\nTake this necklace and go back.");
            } else say("How unfortunate..");
        }
    }

    /**
    * Some questions ripped from HeavenMS (Thanks Ronan!) & hidden street https://bbb.hidden-street.net/3rd-job-questions#8. Others taken straight from BMS... But I got bored of translating so oh well.
    * Removed some not so great ones. Can add new ones easily.
    * BMS does not randomize these, but I think randomizing them makes it a bit more fun.
    * Mostly untested, unverified, and I don't care enough to check! But oh my a lot of them needed grammar adjustments.
    * These question: answer combinations need to be validated.
    * Showerthought: If we plan on shuffling them as the default, we don't need to manually input the answer index. We can make the first answer the correct answers, then shuffle it later.
    */
    private List<HolyQuestion> makeHolyQuestions(int howMany) {
        List<HolyQuestion> questions = new LinkedList<>();//Probably a better way to do this, but it'll do
        addHolyQuestion(questions, "Which NPC cannot typically be found in Henesys", 1,"#p1002001#", "#p1010100#", "#p1012100#", "#p1012102#");
        addHolyQuestion(questions, "Which of the following monsters will you not see on Maple Island", 3,"#o100101#", "#o1210102#", "#o130101#", "#o1210100#", "#o120100#");
        addHolyQuestion(questions, "Which item does Maya at Henesys need in order to cure her sickness", 4,"Red Potion", "All Cure Potion", "Chinese Medicine", "Maya's Cure Potion", "#t4031006#");
        addHolyQuestion(questions, "Which stat requirement for 1st job advancement is incorrect", 3,"Pirate - 20 DEX or more", "Archer - 25 DEX or more", "Thief - 20 LUK or more", "Swordman - 35 STR or more");
        addHolyQuestion(questions, "Which stat requirement for 1st job advancement is correct", 4,"Pirate - 25 LUK", "Magician - Level 10", "Thief - 25 LUK", "Warrior - 30 STR", "Bowman - 25 DEX");
        addHolyQuestion(questions, "Which of the following Monster - Drop combination is correct", 4,"Royal cactus - Needle", "Wild Boar - Boar fang", "Lazy Buffy - Buffy hat", "Chipmunk - Nut", "Stirge - Stirge's wing");
        addHolyQuestion(questions, "Which of the following Monster - Drop combination is incorrect", 1, "Greatest Oldies - Greatest oldies", "Nependeath - Nependeath's leaf", "Ghost stump - Seedling", "Sparker - Seal tooth", "Miner Zombie - Zombie's lost tooth");
        addHolyQuestion(questions, "Which of the following potion effects are correct", 4, "Warrior Elixir - Attack +5 for 3 minutes", "Pure Water - Recover 700 MP", "Cake - Recover 150 HP & MP", "Salad - Recover 300 MP", "Pizza - Recover 400 HP");
        addHolyQuestion(questions, "Which of the following potion effects are incorrect", 4, "Mana Elixir - Recover 300 MP", "Tonic - Cures state of weakness", "Apple - Recover 30 HP", "Sunrise Dew - Recover 3000 MP", "Ramen - Recover 1000 HP");
        addHolyQuestion(questions, "Green Mushroom, Tree Stump, Bubbling, Axe Stump, Octopus. Which is strongest of them all, in terms of level?", 2, "Tree Stump", "Bubbling", "Axe Stump", "Octopus", "Green Mushroom");
        addHolyQuestion(questions, "Which monster can be seen on the Orbis/Ellinia ship ride", 2, "Werewolf", "Slime", "Crimson Balrog", "Zakum", "Star Pixie");
        addHolyQuestion(questions, "Which monster does not reside on Victoria Island", 1, "Evil Eye", "Sentinel", "Jr. Balrog", "Ghost Stump", "Snail");
        addHolyQuestion(questions, "Which monster does not reside in El Nath territory", 1, "Dark Yeti", "Dark Ligator", "Yeti & Pepe", "Bain", "Coolie Zombie");
        addHolyQuestion(questions, "Which of these monsters will you NOT be facing in Ossyria", 3, "Lunar Pixie", "Lioner", "Cellion", "Croco", "Hector");
        addHolyQuestion(questions, "Which monster does not appear on Maple Island", 2, "Snail", "Shroom", "Evil Eye", "Orange Mushroom", "Blue Snail");
        addHolyQuestion(questions,  "Which of following quests can be repeated", 3, "Mystery of Niora Hospital", "Rightful Donation Culture", "The Ghost Whereabout", "Arwen and the Glass Shoe", "Maya and the Weird Medicine");
        addHolyQuestion(questions, "Which material is not required for Awakening Hero's Gladius", 4, "Flaming Feather", "Old Gladius", "Piece of Ice", "Ancient Scroll", "Fairy Wing");
        addHolyQuestion(questions, "Which of following is not a 2nd job advancement", 0, "Mage", "Cleric", "Assassin", "Gunslinger", "Fighter");
        addHolyQuestion(questions, "Of the following quests, which has the highest level requirement", 2, "Cupid's Courier", "Lost in the Ocean", "Alcaster and the Dark Crystal", "Eliminating the Drumming Bunny", "War of Pang Pang");
        addHolyQuestion(questions, "Which location pairing is not part of Victoria Island?", 0, "Amherst and Southperry", "Kerning and Henesys", "Perion and Ellinia", "Sleepywood and Ant Tunnel");
        addHolyQuestion(questions, "Who is the first NPC you meet on the Maple Island tutorial?", 1, "Sera", "Heena", "Lucas", "Roger", "Shanks");
        addHolyQuestion(questions, "Which NPC cannot be found in El Nath?", 1, "Vogen", "Sophia", "Pedro", "Master Sergeant Fox", "Rumi");
        addHolyQuestion(questions, "Which NPC cannot be found in El Nath" , 4, "Hidden Rock", "Glibber", "Jeff", "Holy Stone", "Elma the Housekeeper");
        addHolyQuestion(questions, "Which NPC cannot be found in Perion", 3, "Ayan", "Sophia", "Mr. Smith", "Francois", "Manji");
        addHolyQuestion(questions, "Which NPC cannot be found in Ellinia", 2, "Mr. Park", "Mar the Fairy", "Roel", "Ria", "Shane");
        addHolyQuestion(questions, "Which NPC cannot be found in Kerning City", 3, "Dr. Faymus", "Mong from Kong", "Ervine", "Luke", "Nella");
        addHolyQuestion(questions, "Which NPC is not related to pets", 1, "Doofus", "Vicious", "Patricia", "Weaver", "Cloy");
        addHolyQuestion(questions, "Who is the father of Alex, the runaway kid in Kerning City", 0, "Chief Stan", "JM From tha Streetz", "Dr. Faymus", "Vicious", "Luke", "You");
        addHolyQuestion(questions, "Which NPC doesn't belong to Alpha Platoon's Network of Communication", 4, "Staff Sergeant Charlie", "Sergeant Bravo", "Corporal Easy", "Master Sergeant Fox", "Peter");
        addHolyQuestion(questions, "What did you receive from giving 30 Dark Marbles to your 2nd job advancement instructor", 3, "Old Ring", "Memory Powder", "Fairy Dust", "The Proof of Hero", "Scroll of Secrets");
        addHolyQuestion(questions, "Which NPC does not create or refine items", 2, "Neve", "Serryl", "Shane", "Francois", "JM From tha Streetz");
        addHolyQuestion(questions, "Which NPC cannot be seen in Maple Island", 1, "Bari", "Teo", "Pio", "Sid", "Maria");
        addHolyQuestion(questions, "Who is displayed on Kyrin's navigation room monitor", 1, "Lucas", "Dr. Kim", "Chief Stan", "Scadur", "Professor Foxwit");
        addHolyQuestion(questions, "You know that total babe, Athena Pierce in Henesys? What color are her eyes", 1, "Blue","Green", "Brown", "Red", "Black");
        addHolyQuestion(questions, "How many feathers are there on Dances with Barlog's Hat", 3, "7", "8", "3", "13", "16");
        addHolyQuestion(questions, "What color is Grendel the Really Old's marble", 2, "White", "Orange", "Blue", "Purple", "Green");
        addHolyQuestion(questions, "I don't have a question for you this time. I'm sick of questions, I need a break from it all. I just want someone to ask about my day for once, you know? All people ever care about is getting their Proof of Wisdom. What about me? About my feelings? Don't I deserve to be loved too...", 0, "uhh");
        addHolyQuestion(questions, "Who is the cutest", 0, "You are!");
        addHolyQuestion(questions, "What is Odyssey's release date?", 2, "October 14, 2019", "January 1, 2020", "soontm", "HAHAHAHAHAHHAHAHAHHAHAHAHAHAHAHAHAHAHA");
        addHolyQuestion(questions, "How was your day?", 0, "\r\nGreat\r\nGood\r\nOkay\r\nBad\r\nTerrible");
        howMany = Math.min(howMany, questions.size());
        Collections.shuffle(questions);//Not efficient, but lazy. If anyone would like to clean this up a bit it'd be greatly appreciated <3
        return questions.subList(0, howMany);
    }

    private void addHolyQuestion(List<HolyQuestion> questions, String question, int answer, String... choices) {
        questions.add(new HolyQuestion(question, choices, answer));
    }

    static class HolyQuestion {
        protected final String question;
        protected final String[] choices;
        protected final int answer;
        HolyQuestion(String question, String[] choices, int answer) {
            if(answer < 0 || answer >= choices.length) throw new IllegalArgumentException("Invalid answer for choices " + Arrays.toString(choices));
            this.question = question;
            this.choices = choices;
            this.answer = answer;
        }
    }

    static class ShufflingHolyQuestion extends HolyQuestion {//Untested... But I think it'd be cool if the answers shuffle too!
        ShufflingHolyQuestion(String question, String[] choices, int answer) {
            super(question, choices, shuffle(choices, answer));
        }

        /**
         * Shuffles the input array using Fisher-Yates
         * @param choices
         * @param answer
         * @return the new answer after shuffling the array
         */
        private static int shuffle(String[] choices, int answer) {
            String correct = choices[answer];
            Collections.shuffle(Arrays.asList(choices));//Arrays.asList uses choices as the backing array -> should be good!
            for (int i = 0; i < choices.length; i++) {
                String choice = choices[i];
                if (choice.equals(correct)) return i;
            }
            return -1;//Impossible question?!
        }
    }
}
