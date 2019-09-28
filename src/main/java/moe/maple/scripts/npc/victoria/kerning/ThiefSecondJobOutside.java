package moe.maple.scripts.npc.victoria.kerning;

import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;
import moe.maple.scripts.npc.misc.job.SecondJobInstructorOutside;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

/**
 * @author umbreon22
 * Created on 9/23/2019.
 */
@Script(name = "change_rogue", field = 102040000, description = "Thief Job Instructor | 2nd job advancement test entrance")
public class ThiefSecondJobOutside extends SecondJobInstructorOutside {
    @Override
    protected int allowedJob() {
        return Jobs.Rogue;
    }

    @Override
    protected String jobCategory() {
        return "thief";
    }

    @Override
    protected String townName() {
        return Moematter.map(Victoria.Kerning);
    }

    @Override
    protected int letterItemId() {
        return 4031011;
    }

    @Override
    protected int instructorNpcId() {
        return 1052001;
    }

    @Override
    protected int firstFieldId() {
        return 108000400;
    }

}
