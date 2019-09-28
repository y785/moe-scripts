package moe.maple.scripts.npc.victoria.sleepywood;

import moe.maple.api.script.model.Script;

/**
 * @author umbreon22
 * Created on 9/27/2019.
 */
@Script(name="viola_pink", description = "The flower at the end of some sleepywood jump quest I can't recall the name of.")
public class ViolaPink extends Viola {
    @Override
    protected void work() {
        work(
                2052,
                4031025, 10,
                4010000, 4010001, 4010002, 4010003, 4010004, 4010005
        );
    }
}
