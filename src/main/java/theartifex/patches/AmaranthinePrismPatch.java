package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.SlaversCollar;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theartifex.relics.AmaranthinePrismRelic;

import java.util.ArrayList;

public class AmaranthinePrismPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "preBattlePrep"
    )
    public static class beforeEnergyPrepPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractPlayer __instance) {
            if (AbstractDungeon.player != null) {
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    if (r instanceof AmaranthinePrismRelic) {
                        ((AmaranthinePrismRelic)r).beforeEnergyPrep();
                    }
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SlaversCollar.class, "beforeEnergyPrep");
                int[] lineToReturn = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
                for (int i = 0; i < lineToReturn.length; i++) {
                    lineToReturn[i] += 1;
                }
                return lineToReturn;
            }
        }
    }
}