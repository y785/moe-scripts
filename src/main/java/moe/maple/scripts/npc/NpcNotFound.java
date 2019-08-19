package moe.maple.scripts.npc;

import moe.maple.api.script.helper.MoeNotFound;
import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.FieldObject;
import moe.maple.api.script.model.object.NpcObject;
import moe.maple.api.script.util.ScriptStringBuilder;

public class NpcNotFound extends MoeNotFound {

    @Override
    @Script(name = "moe_script_missing")
    public void work() {
        var sb = new ScriptStringBuilder();
        var id = getSpeakerTemplateId();

        sb.append("Hello, my script is missing ^-^.\r\n");
        sb.append("Script: ").red().append(expected).black().append("\r\n");
        sb.append("Field: ").purple().append(getFieldObect().map(FieldObject::getId).orElse(0)).black().append("\r\n");
        sb.append("Npc: ").blue().append(id).black().append("\r\n");
        sb.append("If you know how this NPC works, feel free to contact staff. The goal is to have all NPCs GMS-like. I'm working as fast as I can, but there are hundreds of scripts to write. Sorry for the inconvenience. ^-^");

        say(sb.toString());
    }
}