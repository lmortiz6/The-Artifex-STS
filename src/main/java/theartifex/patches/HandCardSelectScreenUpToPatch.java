package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;

@SpirePatch(
        clz = HandCardSelectScreen.class,
        method = "updateMessage"
)
public class HandCardSelectScreenUpToPatch {
    @SpirePostfixPatch
    public static void Postfix(HandCardSelectScreen __instance, boolean ___anyNumber, @ByRef String[] ___message) {
        if (__instance.numCardsToSelect < 10 && ___anyNumber) {
            if (__instance.numCardsToSelect > 1)
                ___message[0] = "Select up to " + __instance.numCardsToSelect + " cards to " + __instance.selectionReason;
            else
                ___message[0] = "Select up to " + __instance.numCardsToSelect + " card to " + __instance.selectionReason;
        }
    }
}
