package moe.maple.scripts.field;

import moe.maple.api.script.helper.MoeNotFound;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.FieldObject;
import moe.maple.api.script.model.object.PortalObject;
import moe.maple.api.script.util.ScriptStringBuilder;

public class FieldNotFound extends MoeNotFound {
    @Override
    @Script(name = "moe_script_missing")
    public void work() {
        var sb = new ScriptStringBuilder();

        sb.append("Missing Script: ").append(expected);
        sb.append(", Field: ").append(getFieldObect().map(FieldObject::getId).orElse(0));

        message(sb.toString());
    }
}
