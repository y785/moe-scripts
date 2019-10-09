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

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.helper.Exchange;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.With;
import moe.maple.api.script.util.builder.ScriptStringBuilder;
import moe.maple.api.script.util.tuple.Tuple;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Script(name = "whitto")
public class Whitto extends NpcScript {

    public Whitto() { }

    private void shouwaChange(int idx, int needItem) {
        final var rand = ThreadLocalRandom.current().nextInt(1, 10000);
        var itemId = 0;
        var count = 0;
        var str = 0;

        if(idx == 0){
            if(rand<=2) { itemId=1102040;str = 0;count=1;}
            else if(rand<=4){ itemId=1002393;str = 0;count=1;}
            else if(rand<=6){ itemId=1082149;str = 0;count=1;}
            else if(rand<=250){itemId=2022025;str = 1;count=15;}
            else if(rand<=500){itemId=2022027;str = 1;count=15;}
            else if(rand<=3200){itemId=2022000;str = 2;count=20;}
            else if(rand<=5900){itemId=2022018;str = 2;count=20;}
            else if(rand<=6700){itemId=2022000;str = 2;count=10;}
            else if(rand<=7500){itemId=2022018;str = 2;count=10;}
            else if(rand<=7700){itemId=4020008;str = 2;count=2;}
            else if(rand<=7950){itemId=4020001;str = 2;count=2;}
            else if(rand<=8950){itemId=2022000;str = 3;count=5;}
            else if(rand<=9950){itemId=2022018; str = 3;count=5;}
            else{ itemId=2000006; str = 4;count=1;}
        } else if(idx ==1) {
            if(rand<=2) { itemId=1102040;str = 0;count=1;}
            else if(rand<=6){ itemId=1102043;str = 0;count=1;}
            else if(rand<=250){ itemId=2001001;str = 1;count=15;}
            else if(rand<=500){itemId=2001002;str = 1;count=15;}
            else if(rand<=2100){itemId=2022019;str = 2;count=15;}
            else if(rand<=3700){itemId=2022022;str = 2;count=15;}
            else if(rand<=4600){itemId=2022019;str = 2;count=10;}
            else if(rand<=5500){itemId=2022022;str = 2;count=10;}
            else if(rand<=5700){itemId=4010006;str = 2;count=2;}
            else if(rand<=5900){itemId=4010004;str = 2;count=2;}
            else if(rand<=7900){itemId=2022019;str = 3;count=5;}
            else if(rand<=9900){itemId=2022022;str = 3;count=5;}
            else{ itemId=2000006; str = 4;count=1;}
        } else if(idx ==2){
            if(rand<=2) { itemId=1082149;str = 0;count=1;}
            else if(rand<=6){ itemId=1082150;str = 0;count=1;}
            else if(rand<=150){ itemId=2060003;str = 1;count=1000;}
            else if(rand<=300){itemId=2061003;str = 1;count=1000;}
            else if(rand<=2100){itemId=2000006;str = 2;count=15;}
            else if(rand<=3900){itemId=2022019;str = 2;count=15;}
            else if(rand<=4800){itemId=2000006;str = 2;count=10;}
            else if(rand<=5700){itemId=2022019;str = 2;count=10;}
            else if(rand<=5950){itemId=4010003;str = 2;count=2;}
            else if(rand<=6200){itemId=4010002;str = 2;count=2;}
            else if(rand<=8000){itemId=2000006;str = 3;count=5;}
            else if(rand<=9800){itemId=2022019;str = 3;count=5;}
            else{ itemId=2000003; str = 4;count=1;}
        } else {
            if(rand<=1) { itemId=1002393;str = 0;count=1;}
            else if(rand<=4){ itemId=1002395;str = 0;count=1;}
            else if(rand<=154){itemId=2022024;str = 1;count=15;}
            else if(rand<=300){itemId=2022026;str = 1;count=15;}
            else if(rand<=2300){itemId=2000002;str = 2;count=20;}
            else if(rand<=4300){itemId=2000003;str = 2;count=20;}
            else if(rand<=5100){itemId=2000002;str = 2;count=10;}
            else if(rand<=5900){itemId=2000003;str = 2;count=10;}
            else if(rand<=6100){itemId=4020000;str = 2;count=2;}
            else if(rand<=6300){itemId=4020006;str = 2;count=2;}
            else if(rand<=7900){itemId=2000002;str = 3;count=5;}
            else if(rand<=9500){itemId=2000003;str = 3;count=5;}
            else if(rand<=9900){itemId=2000006;str = 4;count=1;}
            else{ itemId=2000000; str = 4;count=1;}
        }
        
        final String message;
        if (str == 0)
            message = "Hmmm ... the beautiful sparkle, the smooth surface, and this energy that seems to radiate throughout the room...yes, I've been looking for an item like this! And you brought 100! I must give you a reward befitting of your hard work! Let me see...here, take this, #t"+ itemId +"#.";
        else if (str == 1)
            message = "Ohh... I like this. Yes, yesh! This is definitely something that cannot be easily obtained. No doubt this is going to be part of my collection. I can't believe you found something like this, and gathered up mass quantities of it! Something as awesome as this deserves a reward like this, #t"+itemId+"#. It's okay, go ahead and take it~! ";
        else if (str == 2)
            message = "Hmmm ... if not for this minor scratch ... sigh. I'm afraid I can only deem this a standard-quality item. Well, here's #t"+ itemId +"# for you. ";
        else if (str == 3)
            message = "Hmmm ... I see some dents here and there, and what's this scratch? Did you run into an angry cat? Or perhaps a wild Bain? Honestly, this really isn't much. This is below the standard level I've come to expect from warriors such as yourself, so as always, I will reward with an item that matches the quality of what you wish to redeem. Here, I'll give you #t"+itemId+"#. ";
        else
            message = "What's this? What the...! This...this is DEFINITELY something you don't see everyday--it's complete rubbish! Only a simpleton would take an item of such obvious low quality! I'm sure you didn't do this on purpose, but...for you to give this to me, it doesn't make me the most pleasant person in the world. I can only give you #t"+itemId+"# for now, but if you get another chance, then please return! ";

        if (user.hasItem(needItem, 100)) {
            var exc = new Exchange(0, Tuple.of(needItem, -100), Tuple.of(itemId, count));
            exchange(message,
                    "Hmmm... please check and see if you've gathered the items, or if your inventory is full. ", exc);
        } else {
            say("Hey, what do you think you're doing? Go lie to someone that DOESN'T know what he's talking about. Not me!");
        }
    }

    @Override
    protected void work() {
        askYesNo("If you're looking for someone that can pinpoint the characteristics of various items, you're looking at one right now. I'm currently looking for something. Would you like to hear my story? ", () -> {
            @SuppressWarnings("unchecked")
            final var choices = (Tuple<Integer, Integer>[]) new Tuple[]{
                    Tuple.of(3, 4000064), Tuple.of(3, 4000065), Tuple.of(3, 4000066),
                    Tuple.of(1, 4000075), Tuple.of(2, 4000077), Tuple.of(3, 4000089),
                    Tuple.of(2, 4000090), Tuple.of(7, 4000091), Tuple.of(1, 4000092),
                    Tuple.of(1, 4000093), Tuple.of(1, 4000094)};

            final var ssb = new ScriptStringBuilder();
            ssb.append("The items I'm looking for are 1,2,3 ... phew, too many to mention. Anyhow, if you gather up 100 of the same items, then I may trade it for something similar. I can understand being a little wary, but don't worry--I'll keep my end of the deal. Now, shall we trade?").newLine().blue();
            for (int i = 0; i < choices.length; i++) {
                var itemId = choices[i].right();
                ssb.appendMenuItemLine(i, String.format("#v%d# #t%d#", itemId, itemId));
            }
            askMenu(ssb.toString()).andThen(sel -> {
                var tup = choices[sel];
                shouwaChange(tup.left(), tup.right());
            });
        }, () -> say("Really? Let me know if you ever change your mind. I'll be waiting!"));
    }
}
