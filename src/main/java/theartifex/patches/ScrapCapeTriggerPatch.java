package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theartifex.relics.ScrapCapeRelic;

import java.util.ArrayList;

@SpirePatch(
        clz = UseCardAction.class,
        method = "update"
)
public class ScrapCapeTriggerPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert (UseCardAction __instance) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof ScrapCapeRelic) {
                ((ScrapCapeRelic)r).onSpecificTrigger();
            }
        }
    }

    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "moveToExhaustPile");
            int[] lineToReturn = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
            for (int i = 0; i < lineToReturn.length; i++) {
                lineToReturn[i] += 1;
            }
            return lineToReturn;
        }
    }
}
