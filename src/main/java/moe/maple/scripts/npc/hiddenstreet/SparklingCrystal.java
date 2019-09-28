package moe.maple.scripts.npc.hiddenstreet;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.field.NpcObject;
import moe.maple.api.script.util.Moematter;

import java.util.Map;

/**
 * @author umbreon22
 * Created on 9/7/2019.
 */
@Script(name="3jobExit", description = "Shadow Zone | Sparking Crystal | Takes you home if you're too chicken to finish the 3rd job advancement fight")
public class SparklingCrystal extends NpcScript {
    @Override
    protected void work() {
        var enterToExit = Map.of(
            108010301, 102000000,//Warrior
            108010201, 101000000,//Mage
            108010101, 100000000,//Hene Hoe
            108010401, 103000000,//Thief
            108010501, 120000000//Booty chaser
        );
        int currentField = user.getFieldId();
        int returnField = enterToExit.getOrDefault(user.getFieldId(), user.getFieldId());
        if(currentField != returnField) {
            String ask = Moematter.format("If you place your hand on the #b#p{}##k, you will return to the real world. Do you wish to escape?", getNpcObject().map(NpcObject::getTemplateId).orElse(2000));
            askYesNo(ask, ()->user.transferField(returnField, ""));
        } else say("You shouldn't be in here. Please contact a staff member for #eass#nistance.");
    }
}
