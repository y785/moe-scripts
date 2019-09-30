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

package moe.maple.scripts.npc.misc;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;
import moe.maple.api.script.model.helper.SlideItem;

import java.util.ArrayList;

/**
 * Icons can be found in UI/UIWindow.img/SlideMenu/{0,1}/BtMain
 * where {0, 1} = Dimensional Mirror, Time Gate
 * v92 has:
 * 0: AriantPQ, 1: Dojo, 2: CPQ, 3: CPQ2, 4: DaviJones, 5: Nett's Pyramid,
 * 6: Dual Raid, 7: Happyville, 99: Dragon's Nest
 */
@Script(name = "unityPortal", description = "Dimensional Mirror")
public class UnityPortal extends NpcScript {

    private final boolean HAPPYVILLE_EVENT = true;
    private final boolean DRAGONNEST_EVENT = true;

    private void saveAndTransfer(int targetId, String portalName) {
        user.setScriptVariable("unityPortal", field.getId());
        user.transferField(targetId, portalName);
    }

    @Override
    protected void work() {
        var level = user.getLevel();
        var slides = new ArrayList<SlideItem>();
        if (level >= 20 && level <= 30)
            slides.add(new SlideItem(0, "Ariant Coliseum", () -> saveAndTransfer(980010000, "out00")));
        if (level >= 25)
            slides.add(new SlideItem(1, "Mu Lung Dojo", () -> saveAndTransfer(925020000, "out00")));
        if (level >= 30 && level <= 50)
            slides.add(new SlideItem(2, "Monster Carnival", () -> saveAndTransfer(980000000, "out00")));
        if (level >= 51 && level <= 70)
            slides.add(new SlideItem(3, "Monster Carnival", () -> saveAndTransfer(980030000, "out00")));
        if (level >= 1)
            slides.add(new SlideItem(4, "Dual Raid", () -> saveAndTransfer(923020000, "sp")));
        if (level >= 40)
            slides.add(new SlideItem(5, "Nett's Pyramid", () -> saveAndTransfer(926010000, "out00")));
        if (level >= 25 && level <= 30)
            slides.add(new SlideItem(6, "Abandoned Subway", () -> saveAndTransfer(910320000, "out00")));
        if (HAPPYVILLE_EVENT && level >= 10)
            slides.add(new SlideItem(7, "Happyville", () -> saveAndTransfer(209000000, "out00")));
        if (DRAGONNEST_EVENT && level >= 15)
            slides.add(new SlideItem(99, "Dragon's Nest", () -> saveAndTransfer(683010000, "sp")));

        if (slides.size() == 0)
            slides.add(SlideItem.of("There is nothing here UwU"));
        askSlideMenu(slides);
    }
}
