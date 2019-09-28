package moe.maple.scripts.npc.ossyria.elnath;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;

/**
 * @author umbreon22
 * Created on 9/22/2019.
 */
@Script(name="warrior3", description = "Warrior 3rd job advancement + Some Zakum quest")
public class TylusWarrior3 extends ExplorerThirdJobInstructor {

    public TylusWarrior3() {
        super(List.of(10, 20, 30), List.of(11, 21, 31, 12, 22, 32));
    }

    @Override
    JobText getThirdJobIntroText(int jobId) {
        switch (jobId) {
            case Jobs.Crusader: return new JobText("You have become the", "Crusader", "", "You have a number of new devastating offensive skills such as #bShout#k, #bCombo Attack#k and #bArmor Crush#k");
            case Jobs.WhiteKnight: return new JobText("You have become the", "White Knight", "", "You'll be introduced to a new skill book featuring various new attacking skills as well as element-based attacks. I suggest that you continue to use a weapon complementary to the Page, whether it be a sword or a blunt weapon. There's a skill called #bCharge#k, which adds an element of fire, ice, or lightning to the weapon, making the White Knight the only warrior that can perform element-based attacks. Charge your weapon with an element that weakens the monster, and then apply massive damage with the #bCharged Blow#k. You would definitely be a devastating force");
            case Jobs.DragonKnight: return new JobText("You have become the", "Dragon Knight", "", "You'll be introduced to a row of new offensive skills for spears and polearms. Skills such as #bDragon Buster#k (heavy damage to one monster) and #bDragon Fury#k (damage to multiple monsters) are recommended as main attacking skills, while a skill called #bDragon Roar#k will damage everything on screen with devastating force. The downside is that the skill consumes a considerate amount of HP");
            default: return dummyJobText;
        }
    }

    @Override
    String getBasicJobName() {
        return "warrior";
    }

    @Override
    String getBasicJobNamePlural() {
        return "warriors";
    }

    @Override
    int getJobHome() {
        return Victoria.Perion;
    }

    @Override
    int getFirstJobInstructor() {
        return 1022000;
    }

    @Override
    int getJobCategory() {
        return 1;
    }
}
