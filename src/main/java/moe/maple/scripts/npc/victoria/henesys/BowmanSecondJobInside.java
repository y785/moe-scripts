package moe.maple.scripts.npc.victoria.henesys;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.npc.misc.job.SecondJobInstructorInside;
import moe.maple.scripts.util.Jobs;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name="inside_archer", description = "Archer Job Instructor | 2nd job advancement test exit")
public class BowmanSecondJobInside extends SecondJobInstructorInside {

    @Override
    public int allowedJob() {
        return Jobs.Archer;
    }

    @Override
    protected int returnMap() {
        return 106010000;
    }

    @Override
    protected int letter() {
        return 4031010;
    }

}
