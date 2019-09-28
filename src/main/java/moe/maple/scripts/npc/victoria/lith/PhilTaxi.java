package moe.maple.scripts.npc.victoria.lith;

import moe.maple.api.script.logic.action.BasicScriptAction;
import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.ScriptMenuBuilder;
import moe.maple.api.script.util.builder.ScriptStringBuilder;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;

/**
 * @author umbreon22
 * Created on 9/7/2019.
 */
@Script(name = "rithTeleport", description = "A cheap 'taxi' for beginners who also informs you about Victoria Island")
public class PhilTaxi extends NpcScript {

    private final int discount = 90;

    private int applyDiscount(int cost) {
        return cost/(100-discount);
    }

    @Override
    protected void work() {
        ScriptStringBuilder sb = new ScriptStringBuilder().append("Do you want to go somewhere else? I can take you there for a small fee.");
        if(user.isBeginner()) sb.appendf(" Or an even smaller fee, if you're a beginner... say, {}% off?", discount);
        say(sb.toString()).andThen(this::askFirstMenu);
    }

    private void askFirstMenu() {
        ScriptMenuBuilder menu = new ScriptStringBuilder()
            .append("It's understandable that you can be a little confused about this world, if it's your first time here. If you have any questions, fire away.")
            .newLine()
            .blue().appendMenu("Tell me about Victoria Island", "Take me somewhere else.");
        askMenu(menu.toString()).andThen(choice->{switch(choice) {
            case 0: firstCityIntro();break;
            case 1: promptForTravel();break;
            default: say("This should be impossible.");
        }});
    }

    private void promptForTravel() {
        var townsAndCost = List.of(
            Tuple.of(Victoria.Perion, applyDiscount(1200)),
            Tuple.of(Victoria.Ellinia, applyDiscount(1200)),
            Tuple.of(Victoria.Kerning, applyDiscount(800)),
            Tuple.of(Victoria.Henesys, applyDiscount(1000)),
            Tuple.of(Victoria.Nautilus, applyDiscount(1050))
        );
        var menu = new ScriptMenuBuilder<>();
        menu.append("Where do you want to go?");
        if(user.isBeginner()) menu.appendf("Don't forget, there's a special {}% discount for all beginners.", discount);
        menu.blue().newLine();
        townsAndCost.forEach(townAndCost->menu.appendMenu(Moematter.format("#m{}# ({} mesos)", townAndCost.left(), townAndCost.right())));
        askMenu(menu.toString()).andThen(townIndex->{
            var townAndCost = townsAndCost.get(townIndex);
            goToTown(townAndCost.left(), townAndCost.right());
        });
    }

    private void goToTown(int town, int cost) {
        askYesNo(Moematter.format("I guess you don't need to stay here any longer. So you want to go to #b#m{}##k, huh? That'll cost you a fare of #r{} mesos.#k Still interested?",
            town, Moematter.formatWithLocale(cost)),()->{
            if(user.increaseMoney(-cost)) {
                user.transferField(town, 0);
            } else say("I'm not that friendly, go farm some mesos in the next map over.");//You do not have enough money. With your skills, you should have more than that!
        }, ()->say("There's a lot to see in this city too. Let me know if you change your mind."));
    }

    private void firstCityIntro() {
        var cityDescriptions = Tuple.listOf(
            Moematter.map(Victoria.LithHarbor), buildIntro(
                    "The town you are at is Lith Harbor! Alright I'll explain to you more about #b#m104000000##k. It's the place you landed on Victoria Island by riding The Victoria. That's #m104000000#. A lot of beginners who just got here from Maple Island start their journey here.",
                    "It's a quiet town with the wide body of water on the back of it, thanks to the fact that the harbor is located at the west end of the island. Most of the people here are, or used to be fishermen, so they may look intimidating, but if you strike up a conversation with them, they'll be friendly to you.",
                    "Around town lies a beautiful prairie. Most of the monsters there are small and gentle, perfect for beginners. If you haven't chosen your job yet, this is a good place to boost up your level."
            ),
            Moematter.map(Victoria.Ellinia), buildIntro(
                    "Alright I'll explain to you more about #b#m101000000##k. It's a magician-town located at the fart east of Victoria Island, and covered in tall, mystic trees. You'll find some fairies there, too; They don't like humans in general so it'll be best for you to be on their good side and stay quiet.",
                    "Near the forest you'll find green slimes, walking mushrooms, monkeys and zombie monkeys all residing there. Walk deeper into the forest and you'll find witches with the flying broomstick navigating the skies. A word of warning: unless you are really strong, I recommend you don't go near them.",
                    "If you want to be the #bMagician#k, search for #r#p1032001##k, the head wizard of #m101000000#. He may make you a wizard if you're at or above level 8 with a decent amount of INT. If that's not the case, you may have to hunt more and train yourself to get there."
            ),
            Moematter.map(Victoria.Perion), buildIntro(
                    "Alright I'll explain to you more about #b#m102000000##k. It's a warrior-town located at the northern-most part of Victoria Island, surrounded by rocky mountains. With an unfriendly atmosphere, only the strong survives there.",
                    "Around the highland you'll find a really skinny tree, a wild hog running around the place, and monkeys that live all over the island. There's also a deep valley, and when you go deep into it, you'll find a humongous dragon with the power to match his size. Better go in there very carefully, or don't go at all.",
                    "If you want to be a the #bWarrior#k then find #r#p1022000##k, the chief of #m102000000#. If you're level 10 or higher, along with a good STR level, he may make you a warrior afterall. If not, better keep training yourself until you reach that level."
            ),
            Moematter.map(Victoria.Henesys), buildIntro(
                    "Alright I'll explain to you more about #b#m100000000##k. It's a bowman-town located at the southernmost part of the island, made on a flatland in the midst of a deep forest and prairies. The weather's just right, and everything is plentiful around that town, perfect for living. Go check it out.",
                    "Around the prairie you'll find weak monsters such as snails, mushrooms, and pigs. According to what I hear, though, in the deepest part of the Pig Park, which is connected to the town somewhere, you'll find a humongous, powerful mushroom called Mushmom every now and then.",
                    "If you want to be the #bBowman#k, you need to go see #r#p1012100##k at #m100000000#. With a level at or above 10 and a decent amount of DEX, she may make you be one afterall. If not, go train yourself, make yourself stronger, then try again."
            ),
            Moematter.map(Victoria.Kerning), buildIntro(
                    "Alright I'll explain to you more about #b#m103000000##k. It's a thief-town located at the northwest part of Victoria Island, and there are buildings up there that have just this strange feeling around them. It's mostly covered in black clouds, but if you can go up to a really high place, you'll be able to see a very beautiful sunset there.",
                    "From #m103000000#, you can go into several dungeons. You can go to a swamp where alligators and snakes are abound, or hit the subway full of ghosts and bats. At the deepest part of the underground, you'll find Lace, who is just as big and dangerous as a dragon.",
                    "If you want to be the #bThief#k, seek #r#p1052001##k, the heart of darkness of #m103000000#. He may well make you the thief if you're at or above level 10 with a good amount of DEX. If not, go hunt and train yourself to reach there."
            ),
            Moematter.map(Victoria.Nautilus), buildIntro(
                    "Here's a little information on #b#m120000000##k. It's a submarine that's currently parked in between Ellinia and Henesys in Victoria Island. That submarine serves as home to numerous pirates. You can have just as beautiful a view of the ocean there as you do here in Lith Harbor.",
                    "#m120000000# is parked in between Henesys and Ellinia, so if you step out just a bit, you'll be able to enjoy the view of both towns. All the pirates you'll meet in town are very gregarious and friendly as well.",
                    "If you are serious about becoming a #bPirate#k, then you better meet the captain of #m120000000#, #r#p1090000##k. If you are over Level 10 with 20 DEX, then she may let you become one. If you aren't up to that level, then you'll need to train harder to get there!"
            ),
            Moematter.map(Victoria.Sleepywood), buildIntro(
                    "Alright I'll explain to you more about #b#m105040300##k. It's a forest town located at the southeast side of Victoria Island. It's pretty much in between #m100000000# and the ant-tunnel dungeon. There's a hotel there, so you can rest up after a long day at the dungeon ... it's a quiet town in general.",
                    "In front of the hotel there's an old buddhist monk by the name of #r#p1061000##k. Nobody knows a thing about that monk. Apparently he collects materials from the travellers and create something, but I am not too sure about the details. If you have any business going around that area, please check that out for me.",
                    "From #m105040300#, head east and you'll find the ant tunnel connected to the deepest part of the Victoria Island. Lots of nasty, powerful monsters abound so if you walk in thinking it's a walk in the park, you'll be coming out as a corpse. You need to fully prepare yourself for a rough ride before going in.",
                    "And this is what I hear ... apparently, at #m105040300# there's a secret entrance leading you to an unknown place. Apparently, once you move in deep, you'll find a stack of black rocks that actually move around. I want to see that for myself in the near future ..."
            )
        );
        askMenu("There are 6 major cities here on Victoria Island. Which would you like to get to know better?", cityDescriptions);
    }

    private BasicScriptAction buildIntro(String... text) {
        return ()->say(text).andThen(this::firstCityIntro);
    }
}
