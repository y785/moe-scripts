package moe.maple.scripts.npc.victoria.lith;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.fields.Victoria;

/**
 * @author umbreon22
 */
@Script(name="goPerion", field = 104000000, description = "Warrior Teleport Statue")
public class WarriorStatue extends BeginnerJobStatue {
    @Override
    protected void work() {
        work("Warrior", "Warriors have powerful short-ranged attacks and high Strength, thereby making them a class that is always at the very forefront of battles. It is a job class that begins with very effective basic attack skills, and leads to a path of even greater strength once more advanced skills are acquired.",
                "one-handed swords, two-handed swords, spears", "polearms", 10, 102000003, Victoria.Perion, 1022000,
        0);//2077//The Path of a Warrior
    }
}
