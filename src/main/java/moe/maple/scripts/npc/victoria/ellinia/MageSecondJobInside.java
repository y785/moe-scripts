package moe.maple.scripts.npc.victoria.ellinia;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.npc.misc.job.SecondJobInstructorInside;
import moe.maple.scripts.util.Jobs;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name = "inside_magician", description = "Magician Job Instructor | 2nd job advancement test exit")
public class MageSecondJobInside extends SecondJobInstructorInside {
    @Override
    protected int allowedJob() {
        return Jobs.Magician;
    }

    @Override
    protected int returnMap() {
        return 101020000;
    }

    @Override
    protected int letter() {
        return 4031009;
    }
}
