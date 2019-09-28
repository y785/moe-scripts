package moe.maple.scripts.npc.victoria.lith;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.fields.Victoria;

/**
 * @author umbreon22
 */
@Script(name="goHenesys", description = "Bowman Teleport Statue")
public class BowmanStatue extends BeginnerJobStatue {
    @Override
    protected void work() {
        work("Bowman", "Bowmen specialize in Long-Ranged Attacks from the back of the battle lines, since theyt are deft but have limited Strength. Bowmen can become stronger as they level up, employing various attack skills that make them particularly effective with long-ranged Attacks. They are also very capable hunters who can take advantage of the topography.",
                "Bows", "Crossbows", 10, 100000201, Victoria.Henesys, 2131000,
        0);//2078//The Path of a Bowman
    }
}
