package moe.maple.scripts.npc.ereve;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.model.Script;

/**
 * @author umbreon22
 */
@Script(name="cygnusTutor", description = "Helpful Summoned Monster (Mimo) [1101008] | In response to a TalkToTutor packet, we send this script")
public class CygnusTutor extends NpcScript {
    @Override
    protected void work() {
        String[] mostlyImages = {
            "About You",//Not really an image, but image[0] is in Korean anyways.
            "Mini Map", "Quest Window", "Inventory", "Regular Attack Hunting", "How to Pick Up Items", "How to Equip Items", "Skill Window",
            "How to Use Quick Slots", "How to Break Boxes", "How to Sit in a Chair", "World Map", "Quest Notifications", "Enhancing Stats",
            "Who are the Cygnus Knights?"//Not an image, but w/e
        };
        //todo: Needs the rest of the sentence after 'prep'...
        askMenu("Wait! You'll figure this stuff out by the time you reach Lv. 10 anyway, but if you absolutely want to prep#b... Hey, if you're reading this, find out what I'm supposed to say.", mostlyImages).andThen(idx->{
            if(idx <= 0) {
                say( "I serve under Shinsoo, the guardian of Empress Cygnus. My master, #bShinsoo#k, has ordered me to guide everyone who comes to the Maple World to join the Cygnus Knights. I will be assisting and following you around until you become a Knight or reach Lv. 11. Please let me know if you have any questions.",
                    "There is no need for you to check this info now. These are basics that you'll pick up as you play. You can always ask me questions that come up after you've reached Lv. 10, so just relax."
                );//Wtf Mimo is kind of a dick...
            } else if(idx < mostlyImages.length - 1) {//The last index is NOT a Cygnus image. So - 1.
                user.tutorMessage(idx);
            } else {
                say("The #eBlack Mage#n is trying to revive and conquer our peaceful Maple World. As a response to this threat, #bEmpress Cygnus#k has formed a knighthood, now known as Cygnus Knights. You can become a Knight when you reach Lv. 10.");
            }
        });
    }

}
