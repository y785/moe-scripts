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

package moe.maple.scripts.field.rien;

import moe.maple.api.script.model.FieldScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.util.builder.ScriptFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Script(name = "aranDirection")
public class AranDirection extends FieldScript {
    private static final Logger log = LoggerFactory.getLogger( AranDirection.class );

    @Override
    public void work() {
        var startId = 914090010;
        var fieldId = user.getFieldId();
        if (fieldId == startId || fieldId == 914090100) {
            user.setStandAloneMode(true);
            user.setDirectionMode(true);
        }

        switch (fieldId) {
            case 914090100:
                reservedEffect("Effect/Direction1.img/aranTutorial/HandedPoleArm"+user.getGender());
                break;
            case 914090010:
                reservedEffect("Effect/Direction1.img/aranTutorial/Scene0");
                break;
            case 914090011:
                reservedEffect("Effect/Direction1.img/aranTutorial/Scene1"+user.getGender());
                break;
            case 914090012:
                reservedEffect("Effect/Direction1.img/aranTutorial/Scene2"+user.getGender());
                break;
            case 914090013:
                reservedEffect("Effect/Direction1.img/aranTutorial/Scene3"+user.getGender());
                break;
            default:
                log.error("Unhandled map: {}", fieldId);
                break;
        }
    }
}
