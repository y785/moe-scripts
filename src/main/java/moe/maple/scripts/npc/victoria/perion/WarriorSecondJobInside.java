package moe.maple.scripts.npc.victoria.perion;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.npc.misc.job.SecondJobInstructorInside;
import moe.maple.scripts.util.Jobs;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name = "inside_swordman", description = "Warrior Job Instructor | 2nd job advancement test exit")
public class WarriorSecondJobInside extends SecondJobInstructorInside {
    @Override
    protected int allowedJob() {
        return Jobs.Swordman;
    }

    @Override
    protected int returnMap() {
        return 102020300;
    }

    @Override
    protected int letter() {
        return 4031008;
    }
}
