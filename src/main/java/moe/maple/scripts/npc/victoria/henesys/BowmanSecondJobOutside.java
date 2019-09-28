package moe.maple.scripts.npc.victoria.henesys;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.npc.misc.job.SecondJobInstructorOutside;
import moe.maple.scripts.util.Jobs;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name="change_archer", field = 106010000, description = "Bowman Job Instructor | 2nd job advancement test entrance")
public class BowmanSecondJobOutside extends SecondJobInstructorOutside {

    @Override
    public int allowedJob() {
        return Jobs.Archer;
    }

    @Override
    protected String jobCategory() {
        return "bowman";
    }

    @Override
    protected String townName() {
        return "Henehoeland";
    }

    @Override
    protected int letterItemId() {
        return 4031010;
    }

    @Override
    protected int instructorNpcId() {
        return 1012100;
    }

    @Override
    protected int firstFieldId() {
        return 108000100;
    }
}
