package moe.maple.scripts.npc.ossyria.elnath;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;

/**
 * @author umbreon22
 * Created on 9/22/2019.
 */
@Script(name = "wizard3", description = "Mage 3rd job advancement + Some Zakum quest")
public class RobeiraWizard3 extends ExplorerThirdJobInstructor {

    public RobeiraWizard3() {
        super(List.of(10, 20, 30), List.of(11, 12, 21, 22, 31, 32));
    }

    @Override
    JobText getThirdJobIntroText(int jobId) {
        switch (jobId) {
            default: return dummyJobText;
            case Jobs.FirePoisonMage:
                return new JobText("You're a", "Mage of Fire and Poison", " from this point forward", "The new skill book features new and improved fire and poison based skills, and skills such as #bElement Amplification#k (improves element-based spells) and #bSpell Booster#k (improves the overall speed of your attacking spells) will enable you to attack the monsters quickly and effectively. Defensive spells such as #bPartial Resistance#k (allows you to become stronger against certain element-based attacks) and #bSeal#k (seals up the monster) will help negate the one weakness of Mages: lack of HP");
            case Jobs.IceLightningMage:
                return new JobText("You're a", "Mage of Ice and Lightning", " from this point forward", "The new skill book features new and improved ice and lightning based skills, and skills such as #bElement Amplification#k (improves element-based spells) and #bSpell Booster#k (improves the overall speed of your attacking spells) will enable you to attack the monsters quickly and effectively. Defensive spells such as #bPartial Resistance#k (allows you to become stronger against certain element-based attacks) and #bSeal#k (seals up the monster) will help negate the one weakness of Mages: lack of HP");
            case Jobs.Priest:
                return new JobText("You're a", "Priest", " from here on out", "The new skill book features new and improved holy spells such as #bShining Ray#k and #bSummon Dragon#k, and skills such as #bMystic Door#k (creates a door to the nearest town) and #bHoly Symbol#k (improves the EXP gained) can be vital to the party play. Off-beat spells such as #bDoom#k (turns monsters into snails) separates Priests from the rest");
        }
    }

    @Override
    String getBasicJobName() {
        return "magician";
    }

    @Override
    String getBasicJobNamePlural() {
        return "magicians";
    }

    @Override
    int getJobHome() {
        return Victoria.Ellinia;
    }

    @Override
    int getFirstJobInstructor() {
        return 1032001;
    }

    @Override
    int getJobCategory() {
        return 2;
    }

}
