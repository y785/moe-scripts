package moe.maple.scripts.npc.ossyria.elnath;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.ScriptStringBuilder;
import moe.maple.api.script.util.tuple.Tuple;

import java.util.Map;

/**
 * @author umbreon22
 * Created on 9/27/2019.
 */
@Script(name="oldBook1", description = "A nice little shop after completing a few quests")
public class AlcasterOldBook1 extends NpcScript {
    @Override
    protected void work() {
        var qr = user.getQuestHolder();
        var questState = qr.getState(3035);
        if(questState == 2) {//Has completed Alcaster's questline
            promptForSecretShop();
        } else if(user.getLevel() > 54) {
            if(user.getLevel() < 60) {
                say(questState == 0 ? "Come back when you're a bit stronger and we can figure out this whole sealing #bThe Book of Ancient#k thing." : "hank you for all of your help.");
            } else say("Well hello there! Thank you for saying hi.\r\nCould you please consider helping me locate #bThe Book of Ancient#k? I'd be very appreciative.");
        } else {
            sayf("I am #b#p{}# the Sorcerer.#k I've been working on various spells and magic here for over 300 years.", self.getTemplateId());
        }
    }

    private void promptForSecretShop() {
        var items = Map.of(
            2050003, Tuple.of(300, "cures the state of darkness or seal"),
            2050004, Tuple.of(400, "cures any abnormal state"),
            4006000, Tuple.of(5000, "is used in powerful magic"),//I just made these up
            4006001, Tuple.of(5000, "holds immense power inside")
        );
        var thankAsura = new ScriptStringBuilder()
                .append("Thanks to you, #bThe Book of Ancient#k is safely sealed. As a result, I used up about half of the power I have accumulated over the last 800 years...but can now die in peace.")
                .newLine(2)
                .append("Would you happen to be looking for rare items by any chance? As a sign of appreciation for your hard work.")
                .newLine(2)
                .append("I'll sell some items in my possession to you and ONLY you. Pick out the one you want!").newLine();
        for(var entry : items.entrySet()) {
            thankAsura.appendMenuItemLine(entry.getKey(), Moematter.format("#z{}# ({})", entry.getKey(), entry.getValue().right()));
        }
        askMenu(thankAsura.build()).andThen(itemId->{//hold a sec
            var item = items.get(itemId);
            sellItem(itemId, item.left(), item.right());
        });
    }

    private void sellItem(int itemId, int unitPrice, String desc) {
        String ask = Moematter.format("You want some #b#v{}# #t{}##k?\r\nThis item {}.\r\n\r\nSince you helped me, I'll sell it to you for cheap. It'll cost you #r{} mesos#k each.\r\n\r\nHow many would you like?", itemId, itemId, desc, Moematter
                .formatWithLocale(unitPrice));
        askNumber(ask, 1, 1, 100).andThen(howMany->{
            int price = unitPrice * howMany;
            var priceFormatted = Moematter.formatWithLocale(price);
            askYesNo(Moematter.format("Do you really want to buy #b{} #t{}#(s)#k? It'll cost you #r{} mesos#k total.", howMany, itemId, priceFormatted), ()->{
                if(user.exchange(-price, itemId, howMany)) {
                    say("Thank you. If some other day you need additional items, come by.\r\nI may have grown old over time, but I can still make magic items easily!");
                } else {
                    say("Are you sure you have enough mesos? Please check if your inventories are full or if you have at least #r"+priceFormatted+"#k mesos..");
                }
            },"I understand. I have many other items available - take a look.\r\nI'm only selling these because you've helped me a great deal.");//these are lies
        });
    }
}
