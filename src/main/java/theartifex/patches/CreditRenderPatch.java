package theartifex.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
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
