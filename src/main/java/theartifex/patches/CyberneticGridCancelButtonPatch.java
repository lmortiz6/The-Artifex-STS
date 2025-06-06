package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import theartifex.TheArtifexMod;

@SpirePatch(
        clz = GridCardSelectScreen.class,
        method = "open",
        paramtypez = {CardGroup.class, int.class, String.class, boolean.class, boolean.class, boolean.class, boolean.class}
)
public class CyberneticGridCancelButtonPatch {

    @SpirePrefixPatch
    public static void Prefix(GridCardSelectScreen obj, CardGroup group, int numCards, String tipMsg, boolean forUpgrade, boolean forTransform, boolean canCancel, boolean forPurge) {
        if ((TheArtifexMod.gridScreenForCyberRelics || TheArtifexMod.gridScreenForCyberCards) && canCancel) {
            AbstractDungeon.overlayMenu.cancelButton.show(GridCardSelectScreen.TEXT[1]);
        }
    }
}