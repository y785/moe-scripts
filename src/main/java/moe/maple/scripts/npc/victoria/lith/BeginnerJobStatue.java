package moe.maple.scripts.npc.victoria.lith;

import moe.maple.api.script.model.NpcScript;
import moe.maple.api.script.util.Moematter;
import moe.maple.api.script.util.builder.SayBuilder;

/**
 * @author umbreon22
 * Created on 9/9/2019.
 */
public abstract class BeginnerJobStatue extends NpcScript {
    protected void work(String jobName, String jobText, String weaponOne, String weaponTwo, int reqLevel, int room, int town, int instructor, int requiredQuest) {
        if(user.getJobId() != 0) {
            say("It looks like you've already selected your job!\\r\\nTransportation can only be used by beginners.");
        } else {
            var qr = user.getQuestHolder();
            if(requiredQuest > 0 && !qr.isComplete(requiredQuest)) {
                say(Moematter.format("Explorers who wish to take the Path of the {} must seek Olaf.", jobName));
            } else {
                var saying = new SayBuilder(this).next(
                    "Welcome, Beginning Explorer! In MapleStory, you can choose a #rjob#k when you reach #rLv 10#k (or 8, if you're a wizard, Harry)\r\n\r\nIn other words, you'll be choosing your own future path! When you get a job, you get to use various skills and magic which make your experience in MapleStory more enjoyable. So, work hard to carve your own destiny!",
                    Moematter.format("My role is to help you become a #r{}#k.\r\n\r\n{}", jobName, jobText),
                    Moematter.format(
                        "Weapons used include #b{}#k and #b{}#k.\r\n\r\nRequired Level: #rOver Lv {}#k\r\nLocation: #r#m{}##k in #b#m{}##k\r\nJob Instructor: #r#p{}##k",
                        weaponOne, weaponTwo, reqLevel, room, town, instructor
                    ));
                say(saying.build()).andThen(()->askForTransport(reqLevel, jobName, instructor, room, town));
            }
        }
    }

    private void askForTransport(int reqLevel, String jobName, int instructor, int room, int town) {
        if(user.getLevel() >= reqLevel) {
           askYesNo(Moematter.format("Do you want to become a #r{}?", jobName), ()->
               askYesNo(Moematter.format("In order to make the job advancement, you must visit #r#p{}##k at #r#m{}##k in #b#m{}##k.\r\n\r\nWould you like to be transported there now? #e- The transportation service cannot be used once you make the job advancement.#n", instructor, room, town),
               ()->say(Moematter.format("Alright, I will now take you to #r#m{}##k in #b#m{}##k.", room, town)).andThen(()->user.transferField(room, 0)), this::talkToMeAgain),
            this::talkToMeAgain);
        } else talkToMeAgain();
    }

    private void talkToMeAgain() {
        say("Please talk to me again if you have any questions.");
    }
}
