package moe.maple.scripts.npc.victoria.lith;

import moe.maple.api.script.model.Script;
import moe.maple.scripts.util.fields.Victoria;

/**
 * @author umbreon22
 */
@Script(name="goElinia", description = "Mage Teleport Statue")
public class MagicianStatue extends BeginnerJobStatue {
    @Override
    protected void work() {
        work("Magician", "Pursuing ancient knowledge is their lifelong task, therefore high intelligence is required to become a Magician. While their strength and  defense is low compared to other classes, Magicians use elemental magic skills that create wondrous displays and secodnary magic skills that can be uesful while hunting in a party. Elemental magic skills can be learned with the 2nd job advancement, which can cause great damage to enemies with opposing elemental natures.",
                "Wands", "Staffs", 8, 101000003, Victoria.Ellinia, 1032001,//Grendel
        0);//2080
    }
}
