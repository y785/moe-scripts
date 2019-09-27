package moe.maple.scripts.npc.victoria.sleepywood;

import moe.maple.api.script.model.Script;

/**
 * @author umbreon22
 * Created on 9/27/2019.
 */
@Script(name="viola_white", description = "The flower at the end of some sleepywood jump quest I can't recall the name of.")
public class ViolaWhite extends Viola {
    @Override
    protected void work() {
        work(
                2054,
                4031028, 20,
                4010006, 4020007, 4020008//ores
        );
    }
}
