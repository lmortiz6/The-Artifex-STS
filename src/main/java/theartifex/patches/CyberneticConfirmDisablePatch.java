package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import com.megacrit.cardcrawl.ui.buttons.GridSelectConfirmButton;
import theartifex.TheArtifexMod;

@SpirePatch(
        clz = GridCardSelectScreen.class,
        method = "update"
)
public class CyberneticConfirmDisablePatch {
    @SpirePostfixPatch
    public static void Postfix(GridCardSelectScreen __instance, @ByRef GridSelectConfirmButton[] ___confirmButton) {
        if (TheArtifexMod.gridScreenForCyberRelics) {
            if (!TheArtifexMod.hasCyberneticCard() && AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                ___confirmButton[0].isDisabled = true;
            } else {
                ___confirmButton[0].isDisabled = false;
            }
        }
        if (TheArtifexMod.gridScreenForCyberCards) {
            int cost = 0;
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                cost += c.cost;
            }
            if (!TheArtifexMod.currBecomingNookEffect.uninstalled && AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                ___confirmButton[0].isDisabled = true;
            } else
            if (cost > TheArtifexMod.currBecomingNookEffect.credits) {
                ___confirmButton[0].isDisabled = true;
            }  else {
                ___confirmButton[0].isDisabled = false;
            }
        }
    }
}
