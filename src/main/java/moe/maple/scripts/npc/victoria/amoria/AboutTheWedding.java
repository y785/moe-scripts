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

package moe.maple.scripts.npc.victoria.amoria;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.tuple.Tuple;

@Script(name = "abouttheWedding", description = "Amoria | Ames the Wise")
public class AboutTheWedding extends NpcScript {

    private void loop() {
        askMenu("Greetings Traveler! I trust your adventures have proven lively thus far. My name is Ames the Wise, and I'm the oldest citizen in Amoria. I can offer a lifetime's worth of advice if you want to get married. Would you like to know more?",
                Tuple.of("Yes, I'm interested in marriage.", () -> {
                    say("Ready to get married, eh? First things first-congratulations! I'm happy for you. Second, you'll need to get engaged. After that, figure out where you want to get married-at the Cathedral or the White Wedding Chapel. Both are great places as far as I'm concerned.",
                            "You'll also need a Normal or Premium Wedding ticket from the Cash Shop for either location-just one per couple. After that, you'll be happily married and enjoy the benefits of marriage. I can explain a little more if you want...").andThen(this::loop);
                }),
                Tuple.of("No, no... I'm definitely not ready for that. But I'd still like to see what Amoria's about. Do I have to be married?", () -> {
                    say("Amoria welcomes everyone. You do not have to married to help some of the good townspeople around here-or hunt. I invite you to speak with a few of them and see if you can assist them. ").andThen(this::loop);
                }),
                Tuple.of("Ok, I think I'll talk to some people around here.", () -> {
                    say("Great! Stay as long as you like. See me if you have some questions.");
                }),
                Tuple.of("How do I get engaged?", () -> {
                    say("Good thinking. You'll have to be male to obtain an Engagement Ring. After you have one, select the propose option, and wait for a response from your sweetheart. I recommend both of you being online so you can celebrate. As for getting an engagement ring, I'd speak with Moony, our ring-maker.",
                            "He crafts the best rings I've ever seen-4 different kinds in fact. When you do select your engagement ring, please pick carefully-wouldn't want to make a mistake! What else is on your mind?").andThen(this::loop);
                }),
                Tuple.of("How do I get married?", () -> {
                    say("Straight to the point, eh? I like that. Well, after you're engaged, you'll need to speak with Wedding Coordinator Victoria or Wayne and make your reservation. You will need a normal or Premium Wedding Ticket from the Cash Shop, an Engagement Ring and some time. If you've recently performed an annulment, then you'll need to wait 7 days before you can marry again and Moony will make you a new ring.",
                    "There's a bit more documentation for the Cathedral, they have a more elaborate system over there. Also, you'll need to tell the Wedding Coordinator your wish lists.",
                    "Pila Present will hold them for you when your friends turn them in, and you can pick them up afterwards. You'll also get wedding invitations to send them. Do you know where you want to get married?").andThen(this::loop);
                }),
                Tuple.of("I want to be married in the White Wedding Chapel!", () -> {
                    say("Bit of an adventurous streak, I see. Pelvis Bebop and his crew know how to put on a nice show. Just speak with Wedding Assistant Bonnie, and be sure you have a Wedding Receipt, an Engagement Ring and about 5-10 minutes.",
                    "The White Wedding Chapel is faster, and a little more care-free. Anything else you're interested in?").andThen(this::loop);
                }),
                Tuple.of("I want the wedding of my dreams in the Cathedral!", () -> {
                    say("Ah, the Cathedral. If there's a place you want to really want to get married in style, that's it. As I mentioned before, they have a more elaborate system, so you'll need to speak with High Priest John for his permission.",
                    "The bride-to-be will also have to call on Mom and Dad to vouch for you both. To get married in the Cathedral, Just speak with Wedding Assistant Nicole and be sure you have a Wedding Receipt, the Officiator's Permission, and an Engagement Ring and about 10-20 minutes.",
                    "Also, the Cathedral allows your guests to give the married couple an experience blessing during the wedding-1 exp per click, in fact. What else would you like to know?").andThen(this::loop);
                }),
                Tuple.of("How do I invite my friends?", () -> {
                    say("You will receive Wedding Invitations along with your Wedding ticket. If you need more, you can talk with one of the Wedding Assistants. The invitations are pretty simple, you just type in your friend's name, hit \"Send\" and off they go.",
                    "They'll land in the Etc slot-make sure they have a few spaces free. What else would you like to know?").andThen(this::loop);
                }),
                Tuple.of("What happens after the Wedding?", () -> {
                    say("For the White Wedding Chapel and the cathedral, after the Wedding ends, you and your guests are sent to the photo area, Cherished Visage. You can snap away for 60 seconds, and then relax with the new couple for 5 minutes.",
                    "After that, you're whisked back to Amoria. Unless you have a premium ticket, in which case you get to visit the famous Robin the Huntress. What else are you curious about?").andThen(this::loop);
                }),
                Tuple.of("What's the difference between normal and Premium Weddings?", () -> {
                    say("Well, the normal Wedding ticket is nice enough; however, the Premium Wedding ticket gives the newly married couple and their guests a Wedding Party afterwards at the Untamed Hearts Hunting Ground.",
                    "It's hosted by the legendary lady archer herself, Robin the Huntress. There's quite a few valuables there I'm told, though you'll see for yourself.").andThen(this::loop);
                }),
                Tuple.of("What about Marriage benefits?", () -> {
                    say("Now you're talking. As a way to remember Elias the Hunter, Married Couples receive the rings, which produce an effect whenever you're near each other-it's different depending on the ring you choose. You'll also get to do some activities that are for Couples only.",
                    "For instance, some of the townsfolk here will give you tasks that they wouldn't give others, they'll trust you a little more. It's a different lifestyle, and you'll always be with your sweetheart. What else can I assist with?").andThen(this::loop);
                }),
                Tuple.of("How do I get my marriage annulled?", () -> {
                    say("Some marriages don't work out for the best. It's unfortunate, but you can visit Moony to end your marriage. Make sure you have a good amount of money as well. Anything else?").andThen(this::loop);
                }),
                Tuple.of("Ok, I understand the system. Thank you!", () -> {
                    say("My pleasure, friend! Visit me anytime if you need to know more.");
                }));
    }

    @Override
    public void work() {
        loop();
    }
}
