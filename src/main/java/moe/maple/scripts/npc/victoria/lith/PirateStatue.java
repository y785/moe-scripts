package moe.maple.scripts.npc.victoria.lith;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.fields.Victoria;

/**
 * @author umbreon22
 */
@Script(name="goNautilus", field=104000000, description = "Pirate Teleport Statue")
public class PirateStatue extends BeginnerJobStatue {
    @Override
    protected void work() {
        work("Pirate", "Pirates use their quickness and strength to shoot with bull's-eye accuracy, while emplyoying their physical attack skills to overpower enemies in an instant. Quickness is important for Gunslingers who use guns to attack from far distances, while Brawlers who use powerful physical attacks from short range would benefit from increases to their strength.",
                "Guns", "Knuckles", 10, 120000101, Victoria.Nautilus, 1090000,
        0);//2081//The Path of a Pirate
    }
}
