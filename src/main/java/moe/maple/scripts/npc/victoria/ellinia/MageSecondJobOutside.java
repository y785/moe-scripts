package moe.maple.scripts.npc.victoria.ellinia;

import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;
import moe.maple.scripts.npc.misc.job.SecondJobInstructorOutside;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name = "change_magician", field = 102020300, description = "Warrior Job Instructor | 2nd job advancement test entrance")
public class MageSecondJobOutside extends SecondJobInstructorOutside {
    @Override
    protected int allowedJob() {
        return Jobs.Magician;
    }

    @Override
    protected String jobCategory() {
        return "magician";
    }

    @Override
    protected String townName() {
        return Moematter.map(Victoria.Ellinia);
    }

    @Override
    protected int letterItemId() {
        return 4031009;
    }

    @Override
    protected int instructorNpcId() {
        return 1032001;
    }

    @Override
    protected int firstFieldId() {
        return 108000200;
    }

}
