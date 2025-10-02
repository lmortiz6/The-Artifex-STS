package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "hasRelic"
)
public class AmnesiaPatch {
    @SpirePrefixPatch
    public static SpireReturn<Boolean> Prefix(AbstractPlayer __instance, String targetID) {
        if (targetID.equals("Runic Dome") && __instance.hasPower("theartifex:AmnesiaPower")) {
            return SpireReturn.Return(true);
        } else
            return SpireReturn.Continue();
    }
}
