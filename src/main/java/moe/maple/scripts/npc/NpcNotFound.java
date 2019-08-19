/*
 * Copyright (C) 2019, y785, http://github.com/y785
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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