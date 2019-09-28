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

package moe.maple.scripts.npc.victoria.nlc;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.helper.MenuItem;
import moe.maple.api.script.util.builder.ScriptStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Taken from tcg3_1.1.s
 * The original script is ~600 lines long, hopefully this is slightly better.
 */
@Script(name = "TCG3")
public class TCG3 extends NpcScript {
    private final int[] BOWS;
    private final int[] XBWS;
    private final int[] DAGS; // Luk & Str
    private final int[] CLWS;
    private final int[] STFS;

    private final int CAPE;
    private final int FEATHER;
    private final int TOTEM;

    private final int LILY, CRYSTAL;

    private final int SUMA_RITUAL_LILY_COST = 10;
    private final int SUMA_RITUAL_CRYS_COST = 5;
    private final int SUMA_RITUAL_FTHR_COST = 1;

    private final int MAGNA_RITUAL_LILY_COST = 50;
    private final int MAGNA_RITUAL_CRYS_COST = 10;
    private final int MAGNA_RITUAL_FTHR_COST = 2;

    private final Map<Integer, Integer> cache;

    public TCG3() {
        // Bows: Akha
        BOWS = new int[]{1452054, 1452055, 1452056};
        // XBows: Xaru
        XBWS = new int[]{1462047, 1462048, 1462049};
        // Daggers: Maku, Luk & Str
        DAGS = new int[]{1332067, 1332068, 1332069, 1332070, 1332071, 1332072};
        // Claws: Kuma
        CLWS = new int[]{1472065, 1472066, 1472067};
        // Staffs: Umaru
        STFS = new int[]{1382054, 1382055, 1382056};

        CAPE = 1102165;
        FEATHER = 4031936;
        TOTEM = 4031755;

        LILY =  4031937;
        CRYSTAL = 4005001;

        cache = new HashMap<>();
    }

    private int cacheCount(int itemId) {
        if (!cache.containsKey(itemId))
            cache.put(itemId, user.getItemCount(itemId));
        return cache.get(itemId);
    }

    private boolean has(int itemId) {
        return cacheCount(itemId) != 0;
    }

    private boolean has(int[] arr) {
        for (var i : arr) {
            if (cacheCount(i) != 0)
                return true;
        }
        return false;
    }

    private boolean hasTaruGear() {
        return has(BOWS[0]) || has(XBWS[0]) || has(DAGS[0]) || has(DAGS[3]) || has(CLWS[0]) || has(STFS[0]);
    }

    private boolean hasSumaGear() {
        return has(BOWS[1]) || has(XBWS[1]) || has(DAGS[1]) || has(DAGS[4]) || has(CLWS[1]) || has(STFS[1]);
    }

    private boolean hasMagnaGear() {
        return has(BOWS[2]) || has(XBWS[2]) || has(DAGS[2]) || has(DAGS[5]) || has(CLWS[2]) || has(STFS[2]);
    }

    private boolean hasTaruStuff() {
        return hasTaruGear() || hasSumaGear()
                || has(CAPE) || has(FEATHER) || has(TOTEM);
    }

    // =================================================================================================================

    private void aboutTaru() {
        say("I am Corine.  And this lovable orange scruff of fur is my friend, Khafre. <Khafre growls> Oh, be quiet, Khaffy... you know I think you look quite handsome and are really not the least bit scruffy.  Khafre is quite proud even for a tiger and can be a bit sensitive sometimes. In answer to your question, I speak Taru because it is the language of my ancestors; I am a Ranger descended from the Taru Tribe of ancient Masteria.  Even though not many people speak it today, I speak it because it is part of who I am. ").andThen(() -> {
            askMenu("It's important to remember the traditions of your ancestors and keep them alive, don't you think?", MenuItem.of("Yes, we can learn a lot about ourselves from our past to help guide us for the future.", () -> {
                askMenu("My, my! Such wisdom from one so young!  What do you think of this one, Khafre? <Khafre eyes you and purrs> That's what I think too. I meet many people through my travels, but not all of them show the maturity that you have with your words. Allow me to tell you about Khafre and myself. We are originally from Henesys, but have traveled back here to Masteria to the homeland of my ancestors.", MenuItem.of("Please.. tell me more about your ancestors, the Taru.", () -> {
                    say("Surely. In ancient Masteria, hundreds and hundreds of years ago, there was a proud tribe of mystical jungle warriors named the Taru. They inhabited what is now known as the Krakian Jungle.  Even though they could be fierce fighters, the Taru were a peaceful people, and lived in harmony with the jungle and the creatures which dwelled within.", "They believed that every human soul had an animal counterpart, and accordingly, every Taru warrior was bonded with an animal companion at birth. The two remained inseparable through life, learning to live and fight together. Such was the nature of that bond, that the strength of the relationship between the warrior and his or her pet actually became manifest in the power of the warrior's weapon.").andThen(() -> {
                        askMenu("", MenuItem.of("So what happened to them? Are there still Taru in the Krakian Jungle today?", () -> {
                            say("I...Sadly, I must say no. Even before the disappearance of the Masterian continent a thousand years ago, the Taru had vanished from the jungle. All I know is that Masterian history says that it was the Krakians that drove them from the jungle...and perhaps wiped them out. But this makes no sense because the Krakians were peaceful, gentle creatures, and the Taru lived in peace alongside them for hundreds of years!", "I know that in the millenium that has passed, finding a clue here now to their disappearance is like pinpointing a whisper in the dark.  But in my heart, I believe that something of my ancestor's tribe still remains in the Jungle!  It must!  You may not believe me, but in my blood, I can hear the Jungle Spirit calling to me... I heard her from across the sea in Henesys, the night that Masteria returned.  This is what brings me here to my ancestor's homeland... to learn what happened to my people and the facts behind their disappearance!  This is my Spirit Quest.").andThen(() -> {
                                askMenu("", MenuItem.of("If I find anything in my travels in the jungle relating to the Taru, I'll let you know.", () -> {
                                    say("Thank you, friend. Perhaps the Jungle Spirit has brought our paths together for a reason. I look forward to speaking again with you soon.  O'hana taru'teha... which means, May the Jungle Spirit guide your path.");
                                }));
                            });
                        }), MenuItem.of("Speaking of a weapon, that's a powerful looking bow you've got there..", () -> {
                            say("This? Yes.  This bow is called an Akhamagna, such a Taru weapon.  I have used it all my life.  It has been with me since I was a little girl... just like Khafre here. <Khafre purrs> Yes, my furry orange friend, it has been a good journey... Sorry, As I was saying...", "As with their animal companion, a Taru brave was introduced to his or her weapon at an early age.  When a Taru babe was old enough to crawl, the child was placed in front of an array of weapons: a bow, a crossbow, a claw, and so forth.  Whichever the babe would crawl to first would be the one in which the child would be trained, and became the child's chosen weapon through life.  The Taru brave kept her weapon throughout her development as a warrior... and warrior, weapon and pet would grow together.").andThen(() -> {
                                askMenu("", MenuItem.of("What do you mean 'grow'?", () -> {
                                    say("You see, Taru weapons are very special in that they were created from the Jungle. And because of this, the weapons carry the spirit of the jungle within them.  As I said, the Taru warrior and her pet were inseparable, and their life journeys brought them even closer together. The spirit within the Taru warrior's weapon could sense this bond, and when the time was right, the Taru brave and her animal companion could undertake a special quest called the Spirit Quest.  When the two finally returned from their quest, a mystic ritual could be performed, allowing the spirit within the weapon to recognize the stronger bond between warrior and pet.  The spirit within could thus attain an enhanced 'Suma' form, or even more powerful 'Magna' form, making the warrior's weapon even stronger than before.").andThen(() -> {
                                        askMenu("", MenuItem.of("Wow..so all Taru weapons can be upgraded, so to speak?", () -> {
                                            say("Yes, that is so.  However, not every brave was allowed to undertake this Spirit Quest. Only those who were judged to be worthy, those who had been given a #bSpirit Feather#k, could do so. ", "There are two stages of the Spirit Quest, the #bSuma#k and then the #bMagna#k. The Suma stage required #bone such feather#k to begin, and the subsequent Magna stage required #btwo#k. Khafre and I, we have undertaken this Spirit Quest to the Magna stage, and accordingly, my Akhamagna is many times more powerful than the simple Akha I wielded as a young girl. Should you find a Taru weapon or object in your travels, bring it back to me, and I shall tell you more...");
                                        }));
                                    });
                                }));
                            });
                        }), MenuItem.of("Sorry, Corine, but if the Taru Tribe disappeared from Masteria over a thousand years ago, how do you know you are descended from them?", () -> {
                            askMenu("<Khafre growls menacingly> Khafre!  Stop that.  Our friend asks a legitimate question.  To tell you the truth, I thought myself to be like any other Victorian Islander when I was born, and did not discover who my ancestors were until I was much older.  You see, I did not know my parents, and was raised by my nana, my grandmother, who passed away a few years ago.", MenuItem.of("I am sorry to hear that...", () -> {
                                say("The Jungle Spirit teaches us that death is a part of life, and not a sorrowful occasion when the one passing on has come to the end of a long and full journey, but I thank you for your sentiment, friend.",
                                        "My nana never told me of my heritage until she lay on her deathbed, but even so, she raised me in the old Taru ways, pairing me with Khaffy when I was a baby, giving me an Akha, telling me Taru legends in the form of bedtime stories, and teaching me to love and respect the natural order of things.  Before she passed on to the next world, she gave me an ancient charm, which she claimed belonged to a legendary Taru chieftain named Teharu.  She said it was my birthright, and that one day, I had to leave Henesys and follow it. ",
                                        "At first, I didn't understand and thought it hard to believe her... but then one night, I felt it in my blood, and the next morning, I heard news that the lost continent of Masteria had returned.  I do not claim to know everything about the Taru, or what my purpose in this life to be, but I know that my place is here, in the land of my ancestors. ");
                            }));
                        }));
                    });
                }));
            }), MenuItem.of("I've never heard of the Taru. Ancient history, eh? Out with the old and in with the new, is what I say!",
                    () -> say("<shakes her head> There is an old Taru saying: if one does not mark one's path, one is doomed to walk in circles. After all, how can you tell where you're going, if you don't know where you're starting from? I bid you goodbye. May the Jungle Spirit guide your path. ")));
        });
    }

    private void aboutTaruGear(String... messages) {
        say(messages).andThen(() -> {
            user.getQuestHolder().setState(8215, 1);
            user.increaseExp(500);
        });
    }

    private void aboutTaruGear() {
        final var fmt = "Can you tell me more about this #t%d#?";
        var ssb = new ScriptStringBuilder();
        ssb.append("Look, Khafre!  Our friend has found something that is from our ancestral tribe! <Khafre gives a small roar> You wish for me to tell about this Taru object of yours?  Certainly!  It swells my heart to tell others about my people and their culture.  What do you wish to know about?");
        ssb.newLine().blue();
        if (has(BOWS[0])) ssb.appendMenuItemLine(1, String.format(fmt, BOWS[0]));
        if (has(BOWS[1])) ssb.appendMenuItemLine(2, String.format(fmt, BOWS[1]));
        // if (has(bows[2])) ssb.appendMenuItemLine(3, String.format(fmt, bows[2]));
        if (has(XBWS[0])) ssb.appendMenuItemLine(4, String.format(fmt, XBWS[0]));
        if (has(XBWS[1])) ssb.appendMenuItemLine(5, String.format(fmt, XBWS[1]));
        // if (has(xbows[2])) ssb.appendMenuItemLine(6, String.format(fmt, xbows[2]));
        if (has(DAGS[0])) ssb.appendMenuItemLine(7, String.format(fmt, DAGS[0]));
        if (has(DAGS[1])) ssb.appendMenuItemLine(8, String.format(fmt, DAGS[1]));
        // if (has(daggers[2])) ssb.appendMenuItemLine(9, String.format(fmt, daggers[2]));
        if (has(DAGS[3])) ssb.appendMenuItemLine(10, String.format(fmt, DAGS[3]));
        if (has(DAGS[4])) ssb.appendMenuItemLine(11, String.format(fmt, DAGS[4]));
        // if (has(daggers[5])) ssb.appendMenuItemLine(12, String.format(fmt, daggers[5]));
        if (has(CLWS[0])) ssb.appendMenuItemLine(13, String.format(fmt, CLWS[0]));
        if (has(CLWS[1])) ssb.appendMenuItemLine(14, String.format(fmt, CLWS[1]));
        // if (has(claws[2])) ssb.appendMenuItemLine(15, String.format(fmt, claws[2]));
        if (has(STFS[0])) ssb.appendMenuItemLine(16, String.format(fmt, STFS[0]));
        if (has(STFS[1])) ssb.appendMenuItemLine(17, String.format(fmt, STFS[1]));
        // if (has(staffs[1])) ssb.appendMenuItemLine(18, String.format(fmt, staffs[1]));
        if (has(CAPE)) ssb.appendMenuItemLine(19, String.format(fmt, CAPE));
        if (has(FEATHER)) ssb.appendMenuItemLine(20, String.format(fmt, FEATHER));
        if (has(TOTEM)) ssb.appendMenuItemLine(23, String.format(fmt, TOTEM));

        if (hasTaruStuff()) {
            askMenu(ssb.toString()).andThen(sel -> {
                if (sel == 1) {
                    aboutTaruGear("The Akha is a Taru bow, like the one I wield, in its most basic form.  It is crafted from the Gobo tree found deep in the Krakian Jungle, which is sacred to the Jungle Spirit.  The wood from the Gobo tree is so hard and resilient that even sharpened stone cannot cut through it.  Before a Taru bowyer could begin crafting an Akha from Gobo wood, he would first have to 'persuade' the wood to work with him through prayers to the Jungle Spirit; only then, would the wood allow itself to be shaped. ",
                            " If you find favor with the Jungle Spirit and come into possession of a #bSpirit Feather#k, I can send you on a Spirit Quest to allow your Akha to attain its more powerful Akhasuma form. ");
                } else if (sel == 2) {
                    aboutTaruGear("I gather that you wish to upgrade your Akhasuma into the even more powerful Akhamagna! If you can attain two more #bSpirit Feathers#k, I can tell you about the next stage of the Spirit Quest. ");
                } else if (sel == 4) {
                    aboutTaruGear("Even though some historians would say that my people were primitive, Taru objects such as the Xaru prove these historians wrong.  Just look at the ingenious design of this crossbow: powerful, but compact and lightweight!  Even with simple materials, the Taru were able to build very efficient tools such as this weapon.  I wish more people would do their research before drawing hasty conclusions. ",
                            "If you find favor with the Jungle Spirit and come into possession of a #bSpirit Feather#k, I can send you on a Spirit Quest to allow your Xaru to attain its more powerful Xarusuma form. ");
                } else if (sel == 5) {
                    aboutTaruGear("I gather that you wish to upgrade your Xarusuma into the even more powerful Xarumagna!  If you can attain two more #bSpirit Feathers#k, I can tell you about the next stage of the Spirit Quest. ");
                } else if (sel == 7 || sel == 10) {
                    aboutTaruGear("The Maku is the traditional Taru hunting dagger.  It was rarely used as a weapon, but this does not mean it is not effective as one, quite the contrary.  In fact, one of the most exalted Taru Chieftains, Teharu, favored the Maku as his weapon, over all the other ranged weapons traditionally preferred by the Taru.  If you find favor with the Jungle Spirit and come into possession of a #bSpirit Feather#k, I can send you on a Spirit Quest to allow your Maku to attain its more powerful Makusuma form. ");
                } else if (sel == 8 || sel == 11) {
                    aboutTaruGear("I gather that you wish to upgrade your Makusuma into the even more powerful Makumagna!  If you can attain two more #bSpirit Feathers#k, I can tell you about the next stage of the Spirit Quest. ");
                } else if (sel == 13) {
                    aboutTaruGear("The Kuma is a wrist-mounted projectile slinger, similar to the claws that thieves now use.  This weapon allowed the Taru brave to keep his or her hands free, while still having access to a quick ranged attack.  It was originally made to throw mahekis, which are sharpened obsidian flakes, but I'm sure it can be retro-fitted to throw the stars that are commonly available today.  If you find favor with the Jungle Spirit and come into possession of a #bSpirit Feather#k, I can send you on a Spirit Quest to allow your Kuma to attain its more powerful Kumasuma form. ");
                } else if (sel == 14) {
                    aboutTaruGear("I gather that you wish to upgrade your Kumasuma into the even more powerful Kumamagna!  If you can attain two more #bSpirit Feathers#k, I can tell you about the next stage of the Spirit Quest.");
                } else if (sel == 16) {
                    aboutTaruGear("Some Taru children were chosen by the Jungle Spirit to become her disciples, to teach others the ways of the jungle.  These Taru would be given an Umaru, and trained to become shamans for the tribe. These staves were attuned to the jungle, and allowed the Taru shaman to draw upon the natural energies of his surroundings.  If you find favor with the Jungle Spirit and come into possession of a #bSpirit Feather#k, I can send you on a Spirit Quest to allow your Umaru to attain its more powerful Umarusuma form. ");
                } else if (sel == 17) {
                    aboutTaruGear("I gather that you wish to upgrade your Umarusuma into the even more powerful Umarumagna!  If you can attain two more #bSpirit Feathers#k, I can tell you about the next stage of the Spirit Quest.");
                } else if (sel == 19) {
                    aboutTaruGear("This is an item that is very sacred to the Taru, for it is made from the Jungle herself. These capes were awarded by the Taru Chieftain to only the bravest of the Taru warriors and are extremely rare. The Taru Spirit Cape lends its wearer not only the protective camouflage of the jungle, but also its primal strength.");
                } else if (sel == 20) {
                    askMenu("The strongest of the Taru braves who had performed great deeds for the tribe were given #bSpirit Feathers#k by the Taru Chieftain. The braves wore these feathers as badges of high honor.  Taru warriors thus decorated were allowed to undertake the Spirit Quest: a ritual that would allow them to make their weapons more powerful.  Through this ritual, traditional Taru weapons could achieve their enhanced Suma, and ultimately, their all-powerful Magna forms.",
                            MenuItem.of("Go on...", () -> {
                                aboutTaruGear("Because you have somehow found one, I can only assume that you are marked for brave deeds, for those done in the past, or for those to be done in the future.  Hold onto these rare and precious symbols.  Perhaps one day, you too will undertake a Spirit Quest.  When the time is right, and you have a proper Taru weapon, ask me and I will show you the way.  Until then, o'hana taru'teha, friend.");
                            }));
                } else if (sel == 23) {
                    say("Before their disappearance, the Taru became engaged in a terrible war, forced to defend their homes against their formerly peaceful neighbors, the flora creatures known as Krakians.  Many braves and their animal companions fell in battle to the Krakians' terrible weapons.  To remember their sacrifice, the forms of fallen Taru warriors were carved into totems by other tribe members.  These totems were then carried into combat by the living, to inspire others to their bravery and courage.");
                }
            });
        } else { // ????
            say("well, friend.  I bid you a safe journey.  I eagerly await your next Taru discovery!  ");
        }
    }

    private void spiritQuestSumaRitual(int taruWeaponId, int sumaWeaponId, int featherCost, int crystalCost, int lilyCost) {
        askMenu("Very well, young brave... Step forward with your pet companion and bring me the things I have asked for. I must warn you: any enchantments that you have placed upon your base Taru weapon will be negated once we perform the Suma ritual.  Are you and your companion ready?", MenuItem.of("Here are the items you asked for... we are ready.", () -> {
            say("Let us begin the Spirit Ritual, brave ones...<Corine sets the Jungle Lilies alight, giving off a fragrant smoke.> Kala'u ona'kay, o'hana akha kahana! Toh amana'kay, tala una'nay! O'hana suma kana,teh'aru taru'teha!<The Wisdom Crystals glow brighter and brighter, then disappear in a flash of green light!>").andThen(() -> {
                if (user.exchange(0, taruWeaponId, -1, FEATHER, -featherCost, CRYSTAL, -crystalCost, LILY, -lilyCost, sumaWeaponId, 1)) {
                    user.increaseExp(50000);
                    say("Young brave, your weapon has become stronger to reflect the growing bond between you and your pet.  Continue to use it wisely and the Jungle Spirit will reward you");
                } else {
                    say("Please check and see if you have all the items you need, or if your etc. inventory is full or not.");
                }
            });
        }), MenuItem.of("On second thought... we are not quite ready for this yet.", () -> {
            say("<nods> It is a mark of wisdom for the warrior to admit if he or she is not ready.  Return when you are...  Khafre and I will be here.");
        }));
    }

    private void spiritQuestSuma() {
        if (has(FEATHER) || has(LILY) || has(CRYSTAL)) {
            askMenu("Why, what's this, Khafre? It seems as if our friend has found a #bSpirit Feather#k!", MenuItem.of("Does this mean I can undertake the Spirit Quest and upgrade my Taru weapon?", () -> {
                askMenu("Why... yes.  If you have a #bSpirit Feather#k, then you have been marked for great deeds", MenuItem.of("I want to undertake the Spirit Quest.  What do I need to do?", () -> {
                    askMenu("The Spirit Quest is not a specific quest per se, but rather, a journey you undertake with your pet companion.  Needless to say, you must have a pet in order to begin the quest.  What you do during this quest is really up to you and your pet... you must follow your own path on this.  The most important thing to achieve during this time is that you improve your bond with your pet companion, accomplished through shared experiences and conversation. #b[Your pet must become Level 15 or higher to complete the Spirit Quest.]#k",
                            MenuItem.of("Anything else?", () -> {
                                askMenu("The second task to complete the Spirit Quest is to gather materials for the weapon enhancement ritual: you must journey into the Krakian Jungle and bring me #b10 Jungle Lilies#k.  These rare, beautiful flowers grew only in special conditions through the valley, but with the influx of these mechanical monsters that now plague the jungle, many of their fragile vines have been trampled and uprooted, making them even harder to find.  However, I know they are still out there in the jungle... I have found some myself.  In addition to the lilies, you must also bring me #b5 Wisdom Crystals#k.",
                                        MenuItem.of("And then?", () -> {
                                            askMenu("Once these tasks are completed, return to me with your pet companion, and bring me the Lilies and Crystals, along with your #bSpirit Feather#k.  With these, I will perform the Suma Spirit ritual, which will imbue your weapon with strength of the bond between you and your pet, and this stage of your Spirit Quest will be complete. #b[Upon completion, your base Taru weapon will then become a Level 40 weapon.]#k",
                                                    MenuItem.of("What happens after my weapon is upgraded?", () -> {
                                                        say("Once you have completed the Suma stage of the Spirit Quest, you can eventually undertake the Magna stage of the quest for your weapon, allowing it to become even more powerful.  But first things first!");
                                                    }));
                                        }));
                            }));
                }), MenuItem.of("I have fulfilled the Spirit Quest and am ready to perform the Suma ritual for my weapon.", () -> {
                    final var fmt = "Perform the Suma ritual for the #t%d#?";

                    var ssb = new ScriptStringBuilder();
                    ssb.append("That is great news.  Which Taru weapon will we be performing the ritual upon? #b[NOTE: This action will turn your Level 10 weapon into a clean Level 40 weapon.]#k").newLine().blue();
                    if (has(BOWS[0])) ssb.appendMenuItemLine(1, String.format(fmt, BOWS[0]));
                    if (has(XBWS[0])) ssb.appendMenuItemLine(4, String.format(fmt, XBWS[0]));
                    if (has(DAGS[0])) ssb.appendMenuItemLine(7, String.format(fmt, DAGS[0]));
                    if (has(DAGS[3])) ssb.appendMenuItemLine(10, String.format(fmt, DAGS[3]));
                    if (has(CLWS[0])) ssb.appendMenuItemLine(13, String.format(fmt, CLWS[0]));
                    if (has(STFS[0])) ssb.appendMenuItemLine(16, String.format(fmt, STFS[0]));

                    askMenu(ssb.toString()).andThen(sel -> {
                        if (sel == 1) {
                            spiritQuestSumaRitual(BOWS[0], BOWS[1], SUMA_RITUAL_FTHR_COST, SUMA_RITUAL_CRYS_COST, SUMA_RITUAL_LILY_COST);
                        } else if (sel == 4) {
                            spiritQuestSumaRitual(XBWS[0], XBWS[1], SUMA_RITUAL_FTHR_COST, SUMA_RITUAL_CRYS_COST, SUMA_RITUAL_LILY_COST);
                        } else if (sel == 7) {
                            spiritQuestSumaRitual(DAGS[0], DAGS[1], SUMA_RITUAL_FTHR_COST, SUMA_RITUAL_CRYS_COST, SUMA_RITUAL_LILY_COST);
                        } else if (sel == 10) {
                            spiritQuestSumaRitual(DAGS[3], DAGS[4], SUMA_RITUAL_FTHR_COST, SUMA_RITUAL_CRYS_COST, SUMA_RITUAL_LILY_COST);
                        } else if (sel == 13) {
                            spiritQuestSumaRitual(CLWS[0], CLWS[1], SUMA_RITUAL_FTHR_COST, SUMA_RITUAL_CRYS_COST, SUMA_RITUAL_LILY_COST);
                        } else if (sel == 16) {
                            spiritQuestSumaRitual(STFS[0], STFS[1], SUMA_RITUAL_FTHR_COST, SUMA_RITUAL_CRYS_COST, SUMA_RITUAL_LILY_COST);
                        }
                    });
                }));
            }));
        } else {
            say(String.format("We will need #b%d Jungle Lilies#k, #b%d Wisdom Crystals#k, and #b%d Spirit Feather#k to perform the Suma Ritual.  You have not collected all of these things yet.  Please return when you have... I will be waiting.", SUMA_RITUAL_LILY_COST, SUMA_RITUAL_CRYS_COST, SUMA_RITUAL_FTHR_COST));
        }
    }

    private void spiritQuestMagnaRitual(int sumaWeaponId, int magnaWeaponId, int featherCost, int crystalCost, int lilyCost) {
        askMenu("Very well, young brave... Step forward with your pet companion and bring me the things I have asked for. I must warn you: any enchantments that you have placed upon your Suma-level Taru weapon will be negated once we perform the Magna ritual.  Are you and your companion ready?", MenuItem.of("Here are the items you asked for... we are ready.", () -> {
            say("Let us begin the Spirit Ritual, brave ones...<Corine sets the Jungle Lilies alight, giving off a fragrant smoke.> Kala'u ona'kay, o'hana akha kahana! Toh amana'kay, tala una'nay! O'hana suma kana,teh'aru taru'teha!<The Wisdom Crystals glow brighter and brighter, then disappear in a flash of green light!>").andThen(() -> {
                if (user.exchange(0, sumaWeaponId, -1, FEATHER, -featherCost, CRYSTAL, -crystalCost, LILY, -lilyCost, magnaWeaponId, 1)) {
                    user.increaseExp(150000);
                    user.getQuestHolder().setState(8215, 1);
                    say("The ritual is complete, young brave, and your weapon has become even stronger!  You have been blessed by the Jungle Spirit, who looks upon the friendship you have forged with your pet companion with great favor. Is there another stage of the Spirit Quest beyond this one, you ask?  Legends hint at it, but I know not for certain.  I myself have only attained the Magna level and my knowledge of the Spirit Quest ends there.  Perhaps, you or I will discover the path beyond!  Until then, may the Jungle Spirit give you strength, and watch over you and your pet companion.");
                } else {
                    say("Please check and see if you have all the items you need, or if your etc. inventory is full or not.");
                }
            });
        }), MenuItem.of("On second thought... we are not quite ready for this yet.", () -> {
            say("<nods> It is a mark of wisdom for the warrior to admit if he or she is not ready.  Return when you are...  Khafre and I will be here.");
        }));
    }

    private void spiritQuestMagna() {
        if (has(FEATHER) || has(LILY) || has(CRYSTAL)) {
            askMenu("Why, what's this, Khafre?  It seems as if our friend has found two more #bSpirit Feathers#k!  Truly, the Jungle Spirit must have you marked for great things!", MenuItem.of("I want to undertake the next stage of the Spirit Quest. What do I need to do?", () -> {
                say("Young brave, you must continue your journey with your pet companion. #b[Your pet must achieve Level 30 to complete the Magna stage.]#k The second task for collecting the ritual ingredients has become more difficult: to perform the Magna ritual for your weapon, I'll need #b50 Jungle Lilies#k and #b15 Wisdom Crystals#k.",
                        "Once these tasks are completed, return to me with your pet companion, and bring me the Lilies, the Crystals, and your #b2 Spirit Feathers#k.  With these, I will perform the Magna Spirit ritual, and your weapon will become even stronger to reflect the deepening bond between you and your pet companion. #b[Your Level 40 Taru weapon will become a Level 80 weapon.]#k");
            }), MenuItem.of("I have fulfilled this next stage of the Spirit Quest and am ready for you to perform the Magna ritual on my weapon.", () -> {
                final var fmt = "Perform the Magna ritual for the #t%d#?";
                var ssb = new ScriptStringBuilder();
                ssb.append(" That is great news.  Which Taru weapon will we be performing the Magna ritual upon? #b[NOTE: The Magna ritual will turn your Level 40 Suma weapon into a clean Level 80 weapon.]#k").newLine().blue();
                if (has(BOWS[1])) ssb.appendMenuItemLine(2, String.format(fmt, BOWS[1]));
                if (has(XBWS[1])) ssb.appendMenuItemLine(5, String.format(fmt, XBWS[1]));
                if (has(DAGS[1])) ssb.appendMenuItemLine(8, String.format(fmt, DAGS[1]));
                if (has(DAGS[4])) ssb.appendMenuItemLine(11, String.format(fmt, DAGS[4]));
                if (has(CLWS[1])) ssb.appendMenuItemLine(14, String.format(fmt, CLWS[1]));
                if (has(STFS[1])) ssb.appendMenuItemLine(17, String.format(fmt, STFS[1]));

                askMenu(ssb.toString()).andThen(sel -> {
                    if (sel == 2) {
                        spiritQuestMagnaRitual(BOWS[1], BOWS[2], MAGNA_RITUAL_FTHR_COST, MAGNA_RITUAL_CRYS_COST, MAGNA_RITUAL_LILY_COST);
                    } else if (sel == 5) {
                        spiritQuestMagnaRitual(XBWS[1], XBWS[2], MAGNA_RITUAL_FTHR_COST, MAGNA_RITUAL_CRYS_COST, MAGNA_RITUAL_LILY_COST);
                    } else if (sel == 8) {
                        spiritQuestMagnaRitual(DAGS[1], DAGS[2], MAGNA_RITUAL_FTHR_COST, MAGNA_RITUAL_CRYS_COST, MAGNA_RITUAL_LILY_COST);
                    } else if (sel == 11) {
                        spiritQuestMagnaRitual(DAGS[4], BOWS[5], MAGNA_RITUAL_FTHR_COST, MAGNA_RITUAL_CRYS_COST, MAGNA_RITUAL_LILY_COST);
                    } else if (sel == 14) {
                        spiritQuestMagnaRitual(CLWS[1], CLWS[2], MAGNA_RITUAL_FTHR_COST, MAGNA_RITUAL_CRYS_COST, MAGNA_RITUAL_LILY_COST);
                    } else if (sel == 17) {
                        spiritQuestMagnaRitual(STFS[1], STFS[2], MAGNA_RITUAL_FTHR_COST, MAGNA_RITUAL_CRYS_COST, MAGNA_RITUAL_LILY_COST);
                    }
                });
            }));
        } else {
            say(String.format("We will need #b%d Jungle Lilies#k, #b%d Wisdom Crystals#k, and #b%d Spirit Feathers#k to perform the Magna Ritual.  You have not collected all of these things.  Please return when you have... I will be waiting.", MAGNA_RITUAL_LILY_COST, MAGNA_RITUAL_CRYS_COST, MAGNA_RITUAL_FTHR_COST));
        }
    }

    // =================================================================================================================

    @Override
    protected void work() {
        var qh = user.getQuestHolder();
        var state = qh.getState(8215);

        var ssb = new ScriptStringBuilder();
        ssb.append("Salu o'kahari, friend!  Oh.. what does that mean, you ask?  Forgive me, it simply means 'Hello' in the Taru tongue.");
        ssb.newLine().blue();

        if (state == 0) {
            if (hasTaruStuff()) ssb.appendMenuItemLine(1, "Hey, I found something that looks similar to your Taru gear...");
            else ssb.appendMenuItemLine(0, "Um, saloo oh-ka.. er, hello to you too!  Who are you, may I ask, and why are you speaking Taru?");
        } else if (state == 1) {
            if (hasTaruGear()) ssb.appendMenuItemLine(2, "Taru Spirit Quest ''Suma''");
            if (hasSumaGear()) ssb.appendMenuItemLine(3, "Taru Spirit Quest ''Magna''");
        }

        askMenu(ssb.toString()).andThen(sel -> {
            if (sel == 0)
                aboutTaru();
            else if (sel == 1)
                aboutTaruGear();
            else if (sel == 2)
                spiritQuestSuma();
            else if (sel == 3)
                spiritQuestMagna();
        });
    }
}
