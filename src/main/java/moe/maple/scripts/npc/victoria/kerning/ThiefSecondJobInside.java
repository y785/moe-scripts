package moe.maple.scripts.npc.victoria.kerning;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.npc.misc.job.SecondJobInstructorInside;
import moe.maple.scripts.util.Jobs;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name = "inside_rogue", description = "Thief Job Instructor | 2nd job advancement test exit")
public class ThiefSecondJobInside extends SecondJobInstructorInside {
    @Override
    protected int allowedJob() {
        return Jobs.Rogue;
    }

    @Override
    protected int returnMap() {
        return 102040000;
    }

    @Override
    protected int letter() {
        return 4031011;
    }
}
