package moe.maple.scripts.npc.ossyria.elnath;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;

/**
 * @author umbreon22
 * Created on 9/21/2019.
 */
@Script(name = "bowman3", description = "Archer 3rd job advancement + Some Zakum quest")
public class ReneBowman3 extends ExplorerThirdJobInstructor {

    public ReneBowman3() {
        super(List.of(10, 20), List.of(11, 12, 21, 22));
    }

    @Override
    String getBasicJobName() {
        return "bowman";
    }

    @Override
    String getBasicJobNamePlural() {
        return "bowmen";
    }

    @Override
    int getJobHome() {
        return Victoria.Henesys;
    }

    @Override
    int getFirstJobInstructor() {
        return 1012100;
    }

    @Override
    int getJobCategory() {
        return 3;
    }

    @Override
    JobText getThirdJobIntroText(int jobId) {
        switch(jobId) {
            case Jobs.Ranger: return new JobText("You have officially become a", "Ranger", "",
            "One of the skills that you'll truly embrace is a skill called #bMortal Blow#k that allows Rangers to fire arrows from close-range. #bInferno#k allows Rangers to temporarily perform fire-based attacks on monsters, while skills like #bPuppet#k (summons a scarecrow which attracts the monsters' attention) and #bSilver Hawk#k (summons a Silver Hawk that attacks monsters) solidify the Bowman's status as a long-range attack extraordinaire"
            );
            case Jobs.Sniper: return new JobText("You have officially become a", "Sniper", "",
                    "One of the skills that you'll truly embrace is a skill called #bMortal Blow#k that allows Snipers to fire arrows from close-range. #bBlizzard#k allows the Snipers to temporarily perform ice-based attacks on monsters, while skills like #bPuppet#k (summons a scarecrow which attracts the monsters' attention) and #bGolden Eagle#k (summons a Golden Eagle that attacks monsters) solidify the Bowman's status as a long-range attack extraordinaire"
            );
            default: return dummyJobText;
        }
    }
}
