package moe.maple.scripts.quest;

import moe.maple.api.script.helper.MoeNotFound;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.FieldObject;
import moe.maple.api.script.model.object.QuestObject;
import moe.maple.api.script.util.ScriptStringBuilder;

public class QuestNotFound extends MoeNotFound {

    @Override
    @Script(name = "moe_script_missing")
    public void work() {
        var sb = new ScriptStringBuilder();

        sb.append("Missing Script: ").append(expected);
        sb.append(", Quest: ").append(getQuestObject().map(QuestObject::getId).orElse(0));

        message(sb.toString());
    }
}
