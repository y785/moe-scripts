package moe.maple.scripts.npc.ossyria.elnath;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.Jobs;
import moe.maple.scripts.util.fields.Victoria;

import java.util.List;

/**
 * @author umbreon22
 * Created on 9/22/2019.
 */
@Script(name = "thief3", description = "Thief 3rd job advancement + Some Zakum quest")
public class ArecThief3 extends ExplorerThirdJobInstructor {

    public ArecThief3() {
        super(List.of(110, 20, 32), List.of(11, 12, 21, 22, 32, 33, 34));
    }

    @Override
    JobText getThirdJobIntroText(int jobId) {
        switch (jobId) {
            default:
                return dummyJobText;
            case Jobs.Hermit:
                return new JobText("You have officially been anointed", "Hermit", " from this point forward", "The skill book introduces a slew of new offensive skills for Hermits, using shadows as a way of duplication and replacement. You'll learn skills like #bShadow Meso#k (replace MP with mesos and attack monsters with the damage based on the amount of mesos thrown) and #bShadow Partner#k (create a shadow that mimics your every move, enabling you to attack twice). Use those skills to take on monsters that may have been difficult to conquer before");
            case Jobs.ChiefBandit:
                return new JobText("You have officially been anointed", "Chief Bandit", " from this point forward", "One of the new additions to the skill book is a skill called #bBand of Thieves#k, which lets you summon fellow Bandits to attack multiple monsters at once. Chief Bandits can also utilize mesos in numerous ways, from attacking monsters (#bMeso Explosion#k, which explodes mesos on the ground) to defending yourself (#bMeso Guard#k, which decreases damage done to you)");
            case Jobs.BladeLord:
                return new JobText("You have officially been anointed", "Blade Lord", " from this point forward", "One of the new additions to the skill book is a skill called #bOwl Spirit#k, which can one-shot kill enemies and #bBloody Storm#k, which can attack multiple enemies quickly");
        }
    }

    @Override
    String getBasicJobName() {
        return "thief";
    }

    @Override
    String getBasicJobNamePlural() {
        return "thieves";
    }

    @Override
    int getJobHome() {
        return Victoria.Kerning;
    }

    @Override
    int getFirstJobInstructor() {
        return 1052001;
    }

    @Override
    int getJobCategory() {
        return 4;
    }
}
