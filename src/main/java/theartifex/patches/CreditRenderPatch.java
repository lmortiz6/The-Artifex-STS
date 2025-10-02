package theartifex.patches;

import basemod.BaseMod;
import basemod.patches.com.megacrit.cardcrawl.relics.AbstractRelic.InstantObtainRelicGetHook;
import basemod.patches.com.megacrit.cardcrawl.relics.AbstractRelic.InstantObtainRelicGetHook2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;
import theartifex.TheArtifexMod;

public class CreditRenderPatch {

    @SpirePatch(
            clz = TopPanel.class,
            method = "render",
            paramtypez = SpriteBatch.class
    )
    public static class RenderPatch {
        @SpirePostfixPatch
        public static void Postfix(TopPanel __instance, @ByRef SpriteBatch[] sb, float ___INFO_TEXT_Y) {
            String text = (TheArtifexMod.maxCreditsFast - TheArtifexMod.availableCreditsFast) + "¢ / " + TheArtifexMod.maxCreditsFast + "¢";
            FontHelper.renderFontLeftTopAligned(sb[0], FontHelper.topPanelInfoFont, text, TopPanel.potionX + 160.0F * Settings.scale, ___INFO_TEXT_Y, Settings.GOLD_COLOR);
        }
    }
}
