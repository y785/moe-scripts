package moe.maple.scripts.npc.victoria.lith;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.fields.Victoria;

/**
 * @author umbreon22
 */
@Script(name="goKerningCity", field = 104000000, description = "Thief Teleport Statue")
public class ThiefStatue extends BeginnerJobStatue {
    @Override
    protected void work() {
        work("Thief", "In a class that requires a lot of luck and a slight amount of quickness and strength, thieves employ special skills to attack or hide. With incomparable mobility and avoidability, thieves offer the enjoyment of easy movement control. They also level fast using various skills that compliment their weaker strength.",
                "Claws", "Daggers", 10, 103000003, Victoria.Kerning, 1052001,
        0);//2079//The Path of a Thief
    }
}
