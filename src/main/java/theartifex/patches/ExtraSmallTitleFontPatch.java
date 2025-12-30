package theartifex.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theartifex.TheArtifexMod;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractCard.class,
        method = "renderTitle"
)
public class ExtraSmallTitleFontPatch {

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(AbstractCard __instance, SpriteBatch sb) {
        if (TheArtifexMod.extraSmallTitleFont.contains(__instance.cardID)) {
            FontHelper.cardTitleFont.getData().setScale(__instance.drawScale * 0.66F);
        } else
        if (TheArtifexMod.smallTitleFont.contains(__instance.cardID)) {
            FontHelper.cardTitleFont.getData().setScale(__instance.drawScale * 0.73F);
        }
    }

    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "upgraded");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }
}
