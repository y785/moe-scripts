/*
 * Copyright (C) 2019, http://github.com/y785/moe-scripts
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

package moe.maple.scripts.field;

import moe.maple.api.script.helper.MoeNotFound;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.object.FieldObject;
import moe.maple.api.script.util.builder.ScriptStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Script(name = "moe_script_missing")
public class FieldNotFound extends MoeNotFound {

    private static final Logger log = LoggerFactory.getLogger( FieldNotFound.class );

    @Override
    protected void work() {
        var sb = new ScriptStringBuilder();

        sb.append("Field is missing Script: ").append(expected);
        sb.append(", Field: ").append(getFieldObject().map(FieldObject::getId).orElse(0));
        message(sb.toString());
    }
}
