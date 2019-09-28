package moe.maple.scripts.npc.ossyria.elnath;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;

/**
 * @author umbreon22
 * Created on 9/22/2019.
 */
@Script(name="pirate3", description = "Pirate 3rd job advancement + Some Zakum quest")
public class PedroPirate3 extends ExplorerThirdJobInstructor {

    public PedroPirate3() {
        super(List.of(10, 20), List.of(11, 12, 21, 22));
    }

    @Override
    JobText getThirdJobIntroText(int jobId) {
        switch (jobId) {
            default:
                return dummyJobText;
            case Jobs.Marauder:
                return new JobText("Great! You are now a", "Marauder", "", "As a Marauder, you will learn some of the most sophisticated skills related to melee-based attacks. #bEnergy Charge#k is a skill that allows you to store your power and the damage you receive into a special form of energy. Once this ball of energy is charged, you may use #bEnergy Blast#k to apply maximum damage against your enemies, and also use #bEnergy Drain#k to steal your enemy's HP to recover your own. #bTransformation#k will allow you to transform into a superhuman begin with devastating melee attacks, and while transformed, you can use #bShockwave#k to cause a mini-earth strike and inflict massive damage to your enemies");
            case Jobs.Outlaw:
                return new JobText("Great! You are now a", "Outlaw", "", "As an Outlaw, you will become a true pistolero, a master of every known Gun attack, as well as a few other skills to help you vanquish evil. #bBurst Fire#k is a more powerful version of Double Shot, shooting more bullets and ausing more damage at the same time. You also now have the ability to summon a loyal #bOctopus#k and the swooping #bGaviota#k as your trusty allies, while attacking your enemies using #bBullseye#k. You can also use element-based attacks by using #bFlamethrower#k and #bIce Splitter#k");
        }
    }

    @Override
    String getBasicJobName() {
        return "pirate";
    }

    @Override
    String getBasicJobNamePlural() {
        return "pirates";
    }

    @Override
    int getJobHome() {
        return Victoria.Nautilus;
    }

    @Override
    int getFirstJobInstructor() {
        return 1072008;
    }

    @Override
    int getJobCategory() {
        return 5;
    }
}