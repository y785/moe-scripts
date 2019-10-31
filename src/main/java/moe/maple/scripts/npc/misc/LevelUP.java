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

package moe.maple.scripts.npc.misc;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.helper.MenuItem;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.util.Items;
import moe.maple.scripts.util.Jobs;

import java.util.ArrayList;

@Script(name = "levelUP",
        field = 180000000,
        description = "KIN in GM Map")
public class LevelUP extends NpcScript {

    private final int EXP_AMOUNT = 999_999_999;
    private final int MESOS_AMOUNT = 10_000_000;

    private void levelup() {
        if (user.getLevel() >= 161) {
            user.setScriptVariable("levelUP-ing", "");
            say("I'm sorry but I can't level up you anymore. You have reached the point where it's now up to you to move on and level up.");
        } else {
            user.increaseExp(EXP_AMOUNT);
            askMenu("", MenuItem.of("OK, here you go~"),
                    MenuItem.of("I want to get off of Mr Bone's Wild Ride",
                    () ->user.setScriptVariable("levelUP-ing", "")));
        }
    }

    private void advance(int[] jobs, String prompt) {
        var items = new ArrayList<MenuItem>();
        for (final var i : jobs)
            items.add(MenuItem.of(Jobs.getName(i), () -> user.setJob((short)i, false)));
        askMenu(prompt, items);
    }

    private void giveRechargeables(int[] items, String prompt, int count, int setCount) {
        var menus = new ArrayList<MenuItem>();
        for (int i : items)
            menus.add(MenuItem.of(setCount+" sets of "+Moematter.itemName(i), () -> user.exchange(0, Tuple.fill(i, count, setCount))));
        askMenu(prompt, menus);
    }

    private void menu() {
        askMenu("What do you want to do?",
                MenuItem.of("Please help me level up (up to 160)", () -> {
                    user.setScriptVariable("levelUP-ing", "UwU");
                    this.levelup();
                }),
                MenuItem.of("Please give me some mesos", () -> {
                    user.increaseMoney(MESOS_AMOUNT);
                    say(String.format("OK, here you go~ %,d mesos!", MESOS_AMOUNT));
                }),
                MenuItem.of("Please make me job advance", () -> {
                    askMenu("What can I do for you?",
                            MenuItem.of("Make the 1st job advancement.",
                                    () -> advance(Jobs.JOB_ADVANCEMENTS[0], "What do you want to  make the 1st job advancement as?")),
                            MenuItem.of("Make the 2nd job advancement.",
                                    () -> advance(Jobs.JOB_ADVANCEMENTS[1], "What do you want to make the 2nd job advancement as?")),
                            MenuItem.of("Make the 3rd job advancement.",
                                    () -> advance(Jobs.JOB_ADVANCEMENTS[2], "What do you want to make the 3rd job advancement as?")),
                            MenuItem.of("Make the 4th job advancement.",
                                    () -> advance(Jobs.JOB_ADVANCEMENTS[3], "What do you want to make the 4th job advancement as?"))
                    );
                }),
                MenuItem.of("Increase my skill points", () -> {
                    askMenu("How much do you want?",
                            MenuItem.of("Earn 1 SP", () -> user.increaseSkillPoints(1)),
                            MenuItem.of("Earn 30 SP", () -> user.increaseSkillPoints(30)),
                            MenuItem.of("Earn 100 SP", () -> user.increaseSkillPoints(100)),
                            MenuItem.of("Lose 1 SP", () -> user.increaseSkillPoints(-1))); // :thunk:
                }),
                MenuItem.of("Earn potions, etc", () -> {
                    askMenu("What do you want?",
                            MenuItem.of("200 "+Moematter.itemName(Items.POWER_ELIXIRS),
                                    () -> user.exchange(0, Items.POWER_ELIXIRS, 100, Items.POWER_ELIXIRS, 100)),
                            MenuItem.of("100 "+Moematter.itemName(Items.ALL_CURE),
                                    () -> user.exchange(0, Items.ALL_CURE, 100)),
                            MenuItem.of("100 "+Moematter.itemName(Items.SUMMONING_ROCK),
                                    () -> user.exchange(0, Items.SUMMONING_ROCK, 100)),
                            MenuItem.of("100 "+Moematter.itemName(Items.MAGIC_ROCK),
                                    () -> user.exchange(0, Items.MAGIC_ROCK, 100))
                    );
                }),
                MenuItem.of("Give me some Arrows", () -> {
                    giveRechargeables(Items.ARROWS, "What do you want?", 800, 5);
                }),
                MenuItem.of("Give me some Bullets", () -> {
                    giveRechargeables(Items.BULLETS, "What do you want?", 800, 5);
                }),
                MenuItem.of("Give me some Throwing Stars", () -> {
                    giveRechargeables(Items.THROWING_STARS, "What do you want?", 800, 5);
                }),
                MenuItem.of("Learn 4th job skills", () -> {
                    int[] skills;
                    switch (user.getJobId()) {
                        case Jobs.Hero:
                            skills = new int[]{ 1121000, 1121001, 1121002, 1121003, 1121004, 1121005, 1121006, 1121008, 1121010, 1121011 };
                            break;
                        case Jobs.Paladin:
                            skills = new int[]{ 1221000, 1221001, 1221002, 1221003, 1221004, 1221005, 1221006, 1221007, 1221009, 1221010, 1221011, 1221012 };
                            break;
                        case Jobs.DarkKnight:
                            skills = new int[]{ 1321000, 1321001, 1321002, 1321003, 1321005, 1321006, 1321007, 1321008, 1321009, 1321010};
                            break;
                        case Jobs.FirePoisonArchmage:
                            skills = new int[]{ 2121000, 2121001, 2121002, 2121003, 2121004, 2121005, 2121006, 2121007, 2121008};
                            break;
                        case Jobs.IceLightningArchmage:
                            skills = new int[]{2221000, 2221001, 2221002, 2221003, 2221004, 2221005, 2221006, 2221007, 2221008};
                            break;
                        case Jobs.Bishop:
                            skills = new int[] { 2321000, 2321001, 2321002, 2321003, 2321004, 2321005, 2321006, 2321007, 2321008, 2321009 };
                            break;
                        case Jobs.Bowmaster:
                            skills = new int[]{3121000, 3121002, 3121003, 3121004, 3120005, 3121006, 3121007, 3121008, 3121009};
                            break;
                        case Jobs.Marksman:
                            skills = new int[] { 3221000, 3221001, 3221002, 3221003, 3220004, 3221005, 3221006, 3221007, 3221008 };
                            break;
                        case Jobs.NightLord:
                            skills = new int[] { 4121001, 4121001, 4120002, 4121003, 4121004, 4120005, 4121006, 4121007, 4121008, 4121009 };
                            break;
                        case Jobs.Shadower:
                            skills = new int[] { 4221000, 4221001, 4220002, 4221003, 4221004, 4220005, 4221006, 4221007, 4221008 };
                            break;
                        case Jobs.Buccaneer:
                            skills = new int[] { 5121000, 5121001, 5121002, 5121003, 5121004, 5121005, 5121007, 5121008, 5121009, 5121010 };
                            break;
                        case Jobs.Corsair:
                            skills = new int[] { 5221000, 5221001, 5221002, 5221003, 5221004, 5221006, 52210007, 5221008, 5221009, 5221010, 5221011 };
                            break;
                        default:
                            skills = new int[0];
                            break;
                    }

                    if (skills.length == 0) {
                        say("Hmm.. I don't know what to do about your job("+Jobs.getName(user.getJobId())+")...");
                    } else {
                        for (int i : skills) user.learnSkill(i);
                    }
                }),
                MenuItem.of("Acquire 4th  job skill books", () -> {
                    switch (user.getJobId()) {
                        case Jobs.Hero:
                            user.exchange(0, 2290096, 1, 2290000, 1, 2290001, 1, 2290002, 1, 2290003, 1, 2290004, 1, 2290005, 1, 2290006, 1, 2290007, 1, 2290014, 1, 2290015, 1, 2280007, 1, 2290008, 1, 2290009, 1, 2290010, 1, 2290011, 1, 2290016, 1, 2290017, 1);
                            break;
                        case Jobs.Paladin:
                            user.exchange(0, 2290096, 1, 2290000, 1, 2290001, 1, 2290002, 1, 2290003, 1, 2290004, 1, 2290005, 1, 2280008, 1, 2290006, 1, 2290007, 1, 2290012, 1, 2290013, 1 ,2290014, 1, 2290015, 1, 2290018, 1, 2290019, 1, 2290020, 1, 2290021, 1);
                            break;
                        case Jobs.DarkKnight:
                            user.exchange(0, 2290096, 1, 2290000, 1, 2290001, 1, 2290002, 1, 2290003, 1, 2290004, 1, 2290005, 1, 2290006, 1, 2290007, 1, 2290022, 1, 2290023, 1);
                            break;
                        case Jobs.FirePoisonArchmage:
                            user.exchange(0, 2290096, 1, 2290024, 1, 2290025, 1, 2290026, 1, 2290027, 1, 2290028, 1, 2290029, 1, 2290030, 1, 2290031, 1, 2290036, 1, 2290037, 1, 2290038, 1, 2290039, 1, 2290040, 1, 2290041, 1);
                            break;
                        case Jobs.IceLightningArchmage:
                            user.exchange(0, 2290096, 1, 2290024, 1, 2290025, 1, 2290026, 1, 2290027, 1, 2290028, 1, 2290029, 1, 2290032, 1, 2290033, 1, 2290042, 1, 2290043, 1, 2290044, 1, 2290045, 1, 2290046, 1, 2290047, 1);
                            break;
                        case Jobs.Bishop:
                            user.exchange(0, 2290096, 1, 2290024, 1, 2290025, 1, 2290026, 1, 2290027, 1, 2290028, 1, 2290029, 1, 2290034, 1, 2290035, 1, 2290048, 1, 2290049, 1, 2280009, 1, 2290050, 1, 2290051, 1);
                            break;
                        case Jobs.Bowmaster:
                            user.exchange(0, 2290096, 1, 2290052, 1, 2290053, 1, 2290054, 1, 2290055, 1, 2290056, 1, 2290057, 1, 2290058, 1, 2290059, 1, 2290060, 1, 2290061, 1, 2290062, 1, 2290063, 1, 2290064, 1, 2290065, 1);
                            break;
                        case Jobs.Marksman:
                            user.exchange(0, 2290096, 1, 2290052, 1, 2290053, 1, 2290054, 1, 2290055, 1, 2290066, 1, 2290067, 1, 2290068, 1, 2290069, 1, 2290070, 1, 2290071, 1, 2290072, 1, 2290073, 1, 2290074, 1, 2290075, 1);
                            break;
                        case Jobs.NightLord:
                            user.exchange(0, 2290096, 1, 2290076, 1, 2290077, 1, 2290078, 1, 2290079, 1, 2290080, 1, 2290081, 1, 2290082, 1, 2290083, 1, 2280010, 1, 2290084, 1, 2290085, 1, 2290086, 1, 2290087, 1, 2290088, 1, 2290089, 1);
                            break;
                        case Jobs.Shadower:
                            user.exchange(0, 2290096, 1, 2290076, 1, 2290077, 1, 2290078, 1, 2290079, 1, 2290080, 1, 2290081, 1, 2290082, 1, 2290083, 1, 2290090, 1, 2290091, 1, 2290092, 1, 2290093, 1, 2290094, 1, 2290095, 1);
                            break;
                        case Jobs.Buccaneer:
                        case Jobs.Corsair:
                        default:
                            say("Hmm.. I don't know what to do about your job("+Jobs.getName(user.getJobId())+")...");
                    }
                }),
                MenuItem.of("Remove remaining ap & sp", () -> {
                    askMenu("What do you want?",
                            MenuItem.of("Eliminate the reamining AP",
                                    () -> user.increaseAbilityPoints(-user.getAbilityPoints())),
                            MenuItem.of("Eliminate the remaining SP",
                                    () -> user.increaseSkillPoints(-user.getSkillPoints())));
                })
        );
    }

    @Override
    protected void work() {
        if (field.getId() != 180000000 || !user.isGameMaster()) {
            say("Hey, how's it going? I've been doing well here.");
        } else {
            var ing = user.getScriptVariable("levelUP-ing");
            if (ing != null && !ing.isEmpty()) levelup();
            else menu();
        }
    }
}
