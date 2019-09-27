package moe.maple.scripts.npc.victoria.sleepywood;

import moe.maple.api.script.model.Script;

/**
 * @author umbreon22
 * Created on 9/27/2019.
 */
@Script(name="viola_blue", description = "The flower at the end of some sleepywood jump quest I can't recall the name of.")
public class ViolaBlue extends Viola {
    @Override
    protected void work() {
        work(
                2053,
                4031026, 20,
                4020000, 4020001, 4020002, 4020003, 4020004, 4020005, 4020006
        );
    }
}
