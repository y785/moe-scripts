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

package moe.maple.scripts.quest.victoria.lith;

import moe.maple.api.script.model.QuestScript;
import moe.maple.api.script.model.Script;

@Script(name = "q2197e", description = "Tienk, the Monster Book Salesman")
public class End2197 extends QuestScript {
    @Override
    protected void work() {
        say("Hello there! Welcome, welcome. I'm Tienk, the salesman who'll give you the best deal of your life. The product I want to introduce to you today is the #bMonster Book#k! Have you heard of the Monster Book before? It's a product that will change your life. Let me tell you why this opportunity is too good to pass up.",
        "A Monster Book is a book used to collect rare #bMonster Cards#k that you can acquire from the monsters. I know, you're probably wondering what good collecting Monster Cards would do you.",
        "Well, first off, the Monster Book will grant you a special buff every time you collect a Monster Card. This includes increased drop rates or speed/jump boosts, as well as enhanced Accuracy and Avoidability. But I'm afraid you can only receive a special buff from monsters of at least Lv. 30.",
        "Secondly, you can also receive the #bDesignated Monster Hunt#k magic effect casted on the Monster Book. You can save up to 5 of the same cards in the Monster book, and when you collect all 5, the magic casted on the Monster Book will become activated to give you bonus EXP for an hour when you hunt the designated monster. Isn't that awesome?",
        "Lastly, collecting Monster Cards in the Monster Book also allows you easy access to basic information about the monsters as well as other useful information, such as the monster's drop item and spawn location. Pretty impressive, right?",
        "All this can be yours for only 100,000 mesos and I will throw in a... wait a minute. You've already purchased a Monster Book. Why didn't you say so? Haven't you opened the book yet? You can easily open the book using the #bHotkey B#k. I feel like I've wasted time explaining the obvious. I'm sure you could have figured it out on your own.",
        "Make the best use of the Monster Book and feel free to come and see me whenever you have questions about it. Alright then, have yourself a great day and remember Tienk is always here for you! Oh, oh, oh! And this is a free gift. You'll need it when and if you come and see me.").andThen(this::completeQuest);
    }
}
