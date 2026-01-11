package theartifex.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import theartifex.TheArtifexMod;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class CreditRenderPatch {

    @SpirePatch(
            clz = TopPanel.class,
            method = "render",
            paramtypez = SpriteBatch.class
    )
    public static class RenderPatch {
        @SpirePostfixPatch
        public static void Postfix(TopPanel __instance, @ByRef SpriteBatch[] sb, float ___INFO_TEXT_Y, float ___TIP_OFF_X, float ___TIP_Y) {
            if (AbstractDungeon.player != null && TheArtifexMod.maxCreditsFast > 0) {
                String text = (TheArtifexMod.maxCreditsFast - TheArtifexMod.availableCreditsFast) + "/" + TheArtifexMod.maxCreditsFast + "¢";
                FontHelper.renderFontLeftTopAligned(sb[0], FontHelper.topPanelInfoFont, text, TopPanel.potionX + 51.0F * Settings.scale * AbstractDungeon.player.potionSlots, ___INFO_TEXT_Y, Settings.GOLD_COLOR);
                Hitbox hb = new Hitbox(TopPanel.potionX + 51.0F * Settings.scale * AbstractDungeon.player.potionSlots, ___INFO_TEXT_Y - 30 * Settings.scale, 60.0F * Settings.scale, 60.0F * Settings.scale);
                hb.update(TopPanel.potionX + 51.0F * Settings.scale * AbstractDungeon.player.potionSlots, ___INFO_TEXT_Y - 30 * Settings.scale);
                if (hb.hovered) {
                    ArrayList<PowerTip> tip = new ArrayList<>();
                    PowerTip creditTip = new PowerTip(CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[0], CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[1]);
                    tip.add(creditTip);
                    TipHelper.queuePowerTips(InputHelper.mX - ___TIP_OFF_X, ___TIP_Y,  tip);
                }
            }
        }
    }
}
