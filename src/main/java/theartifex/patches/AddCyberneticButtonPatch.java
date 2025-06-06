package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import javassist.CtBehavior;
import theartifex.TheArtifexMod;
import theartifex.ui.CyberneticCampfireOption;

import java.util.ArrayList;

public class AddCyberneticButtonPatch {
    @SpirePatch(clz = CampfireUI.class, method = "initializeButtons")
    public static class AddKeys {
        @SpireInsertPatch(locator = Locator.class)
        public static void patch(CampfireUI __instance, ArrayList<AbstractCampfireOption> ___buttons) {

            boolean active = TheArtifexMod.hasCyberneticRelic() || TheArtifexMod.hasCyberneticCard();

            if (TheArtifexMod.getMaxCredits() > 0) {
                TheArtifexMod.cyberneticCampfireOption = new CyberneticCampfireOption(active);
                ___buttons.add(TheArtifexMod.cyberneticCampfireOption);
            }
        }
    }

    public static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
