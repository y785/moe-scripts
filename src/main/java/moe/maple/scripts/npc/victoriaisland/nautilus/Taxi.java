package moe.maple.scripts.npc.victoriaisland.nautilus;

import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.tuple.Tuple;
import moe.maple.scripts.npc.victoriaisland.VictoriaBasicTaxi;

public class Taxi extends VictoriaBasicTaxi {

    @Override
    @Script(name = "taxi5")
    public void work() {
        super.work(Tuple.of(104000000, 900),
                Tuple.of(102000000, 800),
                Tuple.of(101000000, 1000),
                Tuple.of(100000000, 900),
                Tuple.of(103000000, 1000));
    }
}