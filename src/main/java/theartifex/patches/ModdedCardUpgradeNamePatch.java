package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ModdedCardUpgradeNamePatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "upgradeName"
    )
    public static class CardRenderTitlePatch {
        @SpirePostfixPatch
        public static void Postfix(@ByRef AbstractCard[] __instance) {
            /*String name = __instance[0].name;
            int i;
            for(i = name.length() - 1; i > 0 && name.charAt(i) == '+'; i--) {
            }
            if (name.charAt(i) == '^') {
                int addlMods = 0;
                for (int j = i - 1; j > 0 && name.charAt(j) == '^'; j--) {
                    addlMods++;
                }

                String s1 = name.substring(0, i - addlMods);
                String s2 = name.substring(i + 1);
                String s3 = name.substring(i - addlMods, i + 1);
                __instance[0].name = s1 + s2 + s3;
            }*/
        }
    }
}
