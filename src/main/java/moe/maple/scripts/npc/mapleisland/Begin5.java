/*
 * Copyright (C) 2019, http://github.com/y785/moe-scripts
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package moe.maple.scripts.npc.mapleisland;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.tuple.Tuple;

@Script(name = "begin5", description = "Maple Road: Inside the Dangerous Forest (50000) | Robin")
public class Begin5 extends NpcScript {

    private void askHelp() {
        askMenu("You can ask me any questions you may have about your adventure here at Maple Island!",
                Tuple.of("How do I move?", () -> {
                    say("Use the #bleft and right arrow#k keys to move around the flatland and slanted roads. Press the #bAlt#k key to jump.\r\n\r\n#bHere's a little tip:#k A select number of shoes improve your speed and jumping abilities.").andThen(this::askHelp);
                }), Tuple.of("How do I hug monsters? (to death)", () -> {
                    say("Every monster possesses a certain number of HP (hit points). You can lower their HP by... well, attacking them. The stronger they are, the harder it is to take them down.",
                            "In order to attack the monsters, you'll need to be equipped with a weapon. When equipped, press the #bCtrl#k key to use your weapon. With the right timing, you'll be able take down monsters in no time.",
                            "Once you achieve job advancement, you'll acquire all sorts of new skills to use against monsters. You can assign these to hotKeys for easier access.\r\nIf it's an attacking skill, you don't need to press Ctrl to attack; just press the button assigned as a hotKey.").andThen(this::askHelp);
                }), Tuple.of("How can I pick up an item?", () -> {
                    say("Once you take down a monster, an item will be dropped to the ground. When that happens, stand in front of the item and press #bZ#k or #b0 on the NumPad#k to acquire the item.",
                            "Remember, though, that if your item inventory is full, you won't be able to acquire more. So if you have an item you don't need, sell it so you can make something out of it. The inventory may expand once you make the job advancement.").andThen(this::askHelp);
                }), Tuple.of("What happens when I die?", () -> {
                    say("Curious to find out what happens when you die? You'll become a ghost when your HP reaches 0. There will be a tombstone in that place and you won't be able to move, although you still will be able to chat.",
                            "There isn't much to lose when you die if you are just a beginner. Once you have a job, however, it's a different story. (Tell that to my mother LOL)\r\nYou'll lose a portion of your EXP when you die, so make sure you avoid danger and death at all cost.").andThen(this::askHelp);
                }), Tuple.of("When can I choose a job?", () -> {
                    say("When do you get to choose your job? Hahaha, take it easy, my friend. Each job has a requirement set for you to meet. Normally a level between 8 and 10 will do, so work hard.",
                            "Level isn't the only thing that determines the advancement, though. You also need to boost up the levels of a particular ability based on the occupation. For example, to be a warrior, your STR has to be over 35, and so forth, you know what I'm saying? Make sure you boost up the abilities that has direct implications to your job.").andThen(this::askHelp);
                }), Tuple.of("Tell me more about this island!?", () -> {
                    say("Want to know about this island? It's called Maple Island and it floats in the air. It's been floating in the sky for a while so the nasty monsters aren't really around. It's a very peaceful island, perfect for beginners!",
                            "But, if you want to be a powerful player, better not think about staying here for too long. You won't be able to get a job anyway. Underneath this island lies an enormous island called Victoria Island. That place is so much bigger than here, it's not even funny.",
                            "How do you get to Victoria Island? On the east of this island there's an harbor called #m60000#. There, you'll find a ship that flies in the air. In front of the ship stands the captain. Ask him about it.",
                            "Oh yeah! One last piece of information before you go. If you are not sure where you are, always press #bW#k. The world map will pop up with the locator showing where you stand. You won't have to worry about getting lost with that.").andThen(this::askHelp);
                }), Tuple.of("What should I do to become a #eWarrior#n?", () -> {
                    say("You want to become a #bwarrior#k? Hmmm, then I suggest you head over to Victoria Island. Head over to a warrior-town called #rPerion#k and see #bDances with Balrog#k. He'll teach you all about becoming a true warrior. Ohh, and one VERY important thing: You'll need to be at least level 10 in order to become a warrior!!").andThen(this::askHelp);
                }), Tuple.of("What should I do to become a #eBowman#n?", () -> {
                    say("You want to become a #bbowman#k? You'll need to go to Victoria Island to make the job advancement. Head over to a bowman-town called #rHenesys#k and talk to the beautiful #bAthena Pierce#k and learn the in's and out's of being a bowman. Ohh, and one VERY important thing: You'll need to be at least level 10 in order to become a bowman!!").andThen(this::askHelp);
                }), Tuple.of("What should I do to become a #eThief#n?", () -> {
                    say("You want to become a #bthief#k? In order to become one, you'll have to head over to Victoria Island. Head over to a thief-town called #rKerning City#k, and on the shadier side of town, you'll see a thief's hideaway. There, you'll meet #bDark Lord#k who'll teach you everything about being a thief. Ohh, and one VERY important thing: You'll need to be at least level 10 in order to become a thief!!").andThen(this::askHelp);
                }), Tuple.of("What should I do to become a #eMagician#n?", () -> {
                    say("You want to become a #bmagician#k? For you to do that, you'll have to head over to Victoria Island. Head over to a magician-town called #rEllinia#k, and at the very top lies the Magic Library. Inside, you'll meet the head of all wizards, #bGrendel the Really Old#k, who'll teach you everything about becoming a wizard.",
                        "Oh by the way, unlike other jobs, to become magician you only need to be at level 8. What comes with making the job advancement early also comes with the fact that it takes a lot to become a true powerful mage. Think long and carefully before choosing your path.").andThen(this::askHelp);
                }), Tuple.of("What should I do to become a #ePirate#n?", () -> {
                    say("Do you wish to become a #bPirate#k? In order to make a job advancement, you MUST head to Victoria Island. Head to the #bNautilus#k, a strange-looking submarine currently docked on the island, head inside, and find #bKyrin#k. She'll help you with the rest.",
                            "By the way! Don't forget you have to be over level 10 and over 20 DEX to become a pirate!!").andThen(this::askHelp);
                }), Tuple.of("How do I raise my stats?", () -> {
                    say("You want to know how to raise your character's ability points? First press S to check out the ability window. Every time you level up, you'll be awarded a number of ability points aka AP's. Assign those AP's to the ability of your choice. It's that simple.").andThen(this::askHelp);
                }), Tuple.of("How do I view the items I picked up?", () -> {
                    say("You want to know how to check out the items you've picked up, huh? When you defeat a monster, it'll drop an item on the ground, and you may press Z to pick up the item. That item will then be stored in your item inventory, and you can take a look at it by simply press I.").andThen(this::askHelp);
                }), Tuple.of("How can I equip an item?", () -> {
                    say("You want to know how to wear the items, right? Press I to check out your item inventory. Place your mouse cursor on top of an item and double-click on it to put it on your character. If you find yourself unable to wear the item, chances are your character does not meet the level & stat requirements. You can also put on the item by opening the equipment inventory (E) and dragging the item into it. To take off an item, double-click on the item at the equip. inventory.").andThen(this::askHelp);
                }), Tuple.of("How do I check out the items that I'm wearing?", () -> {
                    say("You want to check on the equipped items, right? Press E to open the equipment inventory, where you'll see exactly what you are wearing right at that moment. To take off an item, double-click on the item. The item will then be sent to the item inventory.").andThen(this::askHelp);
                }), Tuple.of("What are skills?", () -> {
                    say("The special 'abilities' you get after acquiring a job are called skills. You'll acquire skills that are specifically for that job. You're not at that stage yet, so you don't have any skills yet, but just remember that to check on your skills, press K to open the skill book. It'll help you down the road.").andThen(this::askHelp);
                }), Tuple.of("How do I get outta here?", () -> {
                    say("You can head over to Victoria Island through a ship ride from Southperry that heads to Lith Harbor. Press W to see the World Map, and you'll see where you are on the island. Locate Southperry and that's where you'll need to go. You'll also need some mesos for the ride, so you may need to hunt some monsters around here.").andThen(this::askHelp);
                }), Tuple.of("What are mesos?", () -> {
                    say("It's the currency used in MapleStory. You may purchase items through mesos. To earn them, you may either defeat the monsters, sell items at the store, or complete quests..").andThen(this::askHelp);
                }));
    }
    @Override
    public void work() {
        askHelp();
    }
}
