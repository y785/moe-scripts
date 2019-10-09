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

package moe.maple.scripts.npc.worldtrip.showa;

import moe.maple.api.script.logic.action.BasicScriptAction;
import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.tuple.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Script(name = "boss_cat", description = "shouwa_jp.s")
public class BossCat extends NpcScript {

    private final ArrayList<Tuple<String, Integer>> questions;

    public BossCat() {
        questions = new ArrayList<Tuple<String, Integer>>(30);
        questions.add(0, Tuple.of("Question no.1: Which of these items does the Flaming Raccoon NOT drop? #b\r\n#L0#Raccoon Firewood #l\r\n#L1#Solid Horn #l\r\n#L2#Red Brick #l", 1));
        questions.add(1, Tuple.of("Question no.2: Which one of these items has a mis-matched class or level description? #b\r\n#L0#Bamboo Spear - Warrior-only Weapon #l\r\n#L1#Pico-Pico Hammer - One-handed Sword #l\r\n#L2#Japanese Map - Level 50 equip. #l", 0));
        questions.add(2, Tuple.of("Question no.3: What is the name of NPC that transfers from Kerning city to Mushroom Shrine? #b\r\n#L0# Pelican #l\r\n#L1# Spinel #l\r\n#L2# Transporter #l", 1));
        questions.add(3, Tuple.of("Question no.4: Which of these items do the Thugs NOT drop?#b\r\n#L0#Thug A's Badge#l\r\n#L1#Thug B's Corset#l\r\n#L2#Thug C's Necklace#l", 1));
        questions.add(4, Tuple.of("Question no.5: What is the name of statue in Mushroom Shrine? #b\r\n#L0#Mushroom Statue #l\r\n#L1#Statue of Liberty #l\r\n#L2#Maple Statue #l", 0));
        questions.add(5, Tuple.of("Question no.1: What's the name of the vegetable store owner in Showa Town? #b\r\n#L0#Sami #l\r\n#L1#Kami #l\r\n#L2#Umi #l", 2));
        questions.add(6, Tuple.of("Question no.2: Which of these NPC's does NOT stand in front of the movie theater at Showa Town? #b\r\n#L0#Sky #l\r\n#L1#Furano #l\r\n#L2#Shinta #l", 2));
        questions.add(7, Tuple.of("Question no.3: What is the name of NPC that transfers travelers from Kerning City to the Mushroom Shrine? #b\r\n#L0# Pelican #l\r\n#L1# Spinel #l\r\n#L2# Transporter #l", 1));
        questions.add(8, Tuple.of("Question no.4: Which of these items DO exist? #b\r\n#L0#Cloud Fox's Tooth #l\r\n#L1#Ghost's Bouquet#l\r\n#L2#Nightfox's Tail #l", 2));
        questions.add(9, Tuple.of("Question no.5: Which of these items DO NOT exist?? #b\r\n#L0#Frozen Tuna #l\r\n#L1#Sake #l\r\n#L2#Fly Swatter #l", 2));
        questions.add(10, Tuple.of("Question no.1: Which of these items does the Flaming Raccoon NOT drop? #b\r\n#L0#Raccoon Firewood #l\r\n#L1#Solid Horn #l\r\n#L2#Red Brick #l", 1));
        questions.add(11, Tuple.of("Question no.2: Which NPC is responsible for transporting travelers from Kerning City to Zipangu, and back? #b\r\n#L0#Peli #l\r\n#L1#Spinel#l\r\n#L2#Poli #l", 1));
        questions.add(12, Tuple.of("Question no.3: Which of the items sold at the Mushroom Shrine increases your attack power? #b\r\n#L0#Takoyaki #l\r\n#L1#Yakisoba #l\r\n#L2#Tempura #l", 0));
        questions.add(13, Tuple.of("Question no.4: Which of these items do the Thugs NOT drop? #b\r\n#L0#Thug A's Badge #l\r\n#L1#Thug B's Corset #l\r\n#L2#Thug C's Necklace #l", 1));
        questions.add(14, Tuple.of("Question no.5: Which of these noodles are NOT being sold by Robo at the Mushroom Shrine? #b\r\n#L0#Kinoko Ramen (Pig Skull) #l\r\n#L1#Kinoko Ramen (Salt) #l\r\n#L2#Mushroom Miso Ramen#l", 2));
        questions.add(15, Tuple.of("Question no.1: What is the name of statue in the Mushroom Shrine? #b\r\n#L0#Mushroom Statue #l\r\n#L1#Statue of Liberty #l\r\n#L2#Maple Statue #l", 0));
        questions.add(16, Tuple.of("Question no.2: Which one of these items has a mis-matched class or level description? #b\r\n#L0#Bamboo Spear - Warrior-only Weapon #l\r\n#L1#Pico-Pico Hammer - One-handed Sword #l\r\n#L2#Japanese Map - Level 50 equip. #l", 0));
        questions.add(17, Tuple.of("Question no.3: Which of these noodles are NOT being sold by Robo at the Mushroom Shrine? #b\r\n#L0#Kinoko Ramen (Pig Skull) #l\r\n#L1#Kinoko Ramen (Salt) #l\r\n#L2#Mushroom Miso Ramen#l", 2));
        questions.add(18, Tuple.of("Question no.4: Which of these NPC's do NOT stand in front of Showa Movie Theater? #b\r\n#L0#Skye #l\r\n#L1#Furano #l\r\n#L2#Shinta #l", 2));
        questions.add(19, Tuple.of("Question no.5: Which NPC is responsible for transporting travelers from Kerning City to Zipang, and back? #b\r\n#L0#Peli #l\r\n#L1#Spinel #l\r\n#L2#Poli #l", 1));
        questions.add(20, Tuple.of("Question no.1: Which of these items do the Thugs NOT drop?#b\r\n#L0#Thug A's Badge #l\r\n#L1#Thug B's Corset #l\r\n#L2#Thug C's Necklace #l", 1));
        questions.add(21, Tuple.of("Question no.2: Which of these items DO NOT exist?? #b\r\n#L0#Frozen Tuna #l\r\n#L1#Sake #l\r\n#L2#Fly Swatter #l", 2));
        questions.add(22, Tuple.of("Question no.3: Which of the items sold at the Mushroom Shrine increases your attack power? #b\r\n#L0#Takoyaki #l\r\n#L1#Yakisoba #l\r\n#L2#Tempura #l", 0));
        questions.add(23, Tuple.of("Question no.4: What's the name of the vegetable store owner in Showa Town? #b\r\n#L0#Sami #l\r\n#L1#Kami #l\r\n#L2#Umi #l", 2));
        questions.add(24, Tuple.of("Question no.5: Which of these items DO exist? #b\r\n#L0#Cloud Fox's Tooth #l\r\n#L1#Ghost's Bouquet#l\r\n#L2#Nightfox's Tail #l", 2));
        questions.add(25, Tuple.of("Question no.1: Which of these items do the Thugs NOT drop? #b\r\n#L0#Thug A's Badge #l\r\n#L1#Thug B's Corset #l\r\n#L2#Thug C's Necklace #l", 1));
        questions.add(26, Tuple.of("Question no.2: Which of these items does the Flaming Raccoon NOT drop? #b\r\n#L0#Raccoon Firewood #l\r\n#L1#Solid Horn #l\r\n#L2#Red Brick #l", 1));
        questions.add(27, Tuple.of("Question no.3: What is the name of NPC that transfers travelers from Kerning city to the Mushroom Shrine? #b\r\n#L0# Pelican #l\r\n#L1# Spinel #l\r\n#L2# Transporter #l", 1));
        questions.add(28, Tuple.of("Question no.4: Which NPC is responsible for transporting travelers from Kerning City to Zipangu, and back? #b\r\n#L0#Peli #l\r\n#L1#Spinel#l\r\n#L2#Poli #l", 1));
        questions.add(29, Tuple.of("Question no.5: What is the name of statue in Mushroom Shrine? #b\r\n#L0#Mushroom Statue #l\r\n#L1#Statue of Liberty #l\r\n#L2#Maple Statue #l", 0));
    }

    private void onReward() {
        say( "Dang, you answered all the questions right. I may not like humans in general, but I HATE breaking a promise, so, as promised, here's the Orange Marble. ")
                .andThen(() -> {
                    exchange("Our business is concluded, thank you very much! You can leave now!",
                            "Your Etc. inventory is FULL!?! You need to make room there if you want the Orange Marble.",
                            0, 4031064, 1);
                });
    }

    private void askFrom(int idx, int end) {
        if (idx == end) {
            onReward();
        } else {
            var question = questions.get(idx);
            var prompt = question.left();
            var correct = question.right();
            askMenu(prompt).andThen(sel -> {
                if (correct.equals(sel)) askFrom(idx + 1, end);
                else say("Hmmm...all humans make mistakes anyway! If you want to take another crack at it, then bring me 300 Fried Chickens. ");
            });
        }
    }

    @Override
    protected void work() {
        var rand = ThreadLocalRandom.current().nextInt(0, 6);
        var start = rand * 5;

        askFrom(start, start + 5);
    }
}
