package moe.maple.scripts.npc.ossyria.orbis;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.Moematter;

/**
 * @author umbreon22
 * Created on 9/27/2019.
 */
@Script(name="oldBook5", description = "Just a draft for the script. Text could be improved.")
public class SpirunaOldBook5 extends NpcScript {
    @Override
    protected void work() {//This script is in DESPERATE need of translation fixes. todo
        if(user.getQuestHolder().isComplete(3014)) {
            askMenu("Hella is a good little girl who does what she's told. She doesn't complain about anything! One day, she's going to become a much greater witch than I am.",
                    "I want that sweet Dark Crystal",  "I don't want your help lady").andThen(answer->{
                if(answer == 0) {
                    int darkCrystal = 4005004;//gets 1
                    int darkCrystalOre = 4004004;//needs 10
                    int alcaster = 2020005;
                    int mesoCost = 50000;
                    String ask = Moematter.format("#b#t{}##k?? How did you... did #b#p{}##k tell you? Yes, I know how to refine, but... this ore is very difficult to refine.\r\nTo create #b1 #t{}##k, I need #b10 #t{}#s#k and {} mesos. Do you want one?", darkCrystal, alcaster, darkCrystal, darkCrystal, Moematter
                            .formatWithLocale(mesoCost));
                    askYesNo(ask, ()->{//#b#t4005004##k?? Como voc�... voc� ficou sabendo por #b#p2020005##k? Sim, eu sei como refinar, mas... este min�rio � muito dif�cil de se conseguir. Para fazer #b1 #t4005004##k, eu preciso de #b10 #t4004004#s#k e 50000 mesos. Voc� quer um?
                        if(user.exchange(-mesoCost, darkCrystalOre, -10, darkCrystal, 1)) {
                            sayf("Here, take #b1#t{}##k .. It's been so long since I last did one, I hope it worked out... Aliens, how did you get the crystal mints? Be very special, anyway, it's an incredible item, please make good use of it.", darkCrystal);
                        } else say("Do you not have enough mesos? Or the crystals?\r\nPerhaps you lack the inventory space?");
                    },"#b#t4005004##k. It's been a long time since I've seen... it's been hundreds of years since I last refined, so I can barely remember how I did it... of course you will not have it now...");//#b#t4005004##k. Faz muito tempo que n�o vejo... faz centenas de anos desde a �ltima vez que refinei, por isto, mal consigo lembrar-me de como fiz... claro que voc� n�o vai t�-lo agora...
                } else goAway();
            });
        } else goAway();
    }

    private void goAway() {
        say("I'm working on an important spell, so don't disturb me. I can't concentrate when an outsider prowls around my house. Please, go away...");
    }
}
