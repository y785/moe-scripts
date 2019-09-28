package moe.maple.scripts.npc.ossyria.ludi;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

/**
 * @author umbreon22
 */
@Script(name = "Populatus01", field = 220080001, description = "Machine Apparatus (2041025) | Takes you outside Origin of Clocktower, the Pap boss map")
public class MachineApparatus extends NpcScript {

    @Override
    protected void work() {
        if(user.getFieldId()==220080001) {//Origin of ClockTower
            askYesNo("Beep... beep... you can make your escape to a safer place through me. Beep ... beep ... would you like to leave this place?",
                () -> user.transferField(220080000, "in00")//Deep Inside Clocktower
            );
        } else say("Beep... beep..");
    }
}
