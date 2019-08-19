package moe.maple.scripts.portal;

import moe.maple.api.script.helper.MoeNotFound;
import moe.maple.api.script.model.PortalScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.FieldObject;
import moe.maple.api.script.model.object.NpcObject;
import moe.maple.api.script.model.object.PortalObject;
import moe.maple.api.script.util.ScriptStringBuilder;

public class PortalNotFound extends MoeNotFound {

    @Override
    @Script(name = "moe_script_missing")
    public void work() {
        var sb = new ScriptStringBuilder();

        sb.append("Missing Script: ").append(expected);
        sb.append(", Field: ").append(getFieldObect().map(FieldObject::getId).orElse(0));
        sb.append(", Portal Id: ").append(getPortalObject().map(PortalObject::getId).orElse(0));
        sb.append(", Name: ").append(getPortalObject().map(PortalObject::getPortalName).orElse(""));

        message(sb.toString());
    }
}
