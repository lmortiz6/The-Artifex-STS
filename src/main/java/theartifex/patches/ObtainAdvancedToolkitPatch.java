package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.select.BossRelicSelectScreen;
import com.megacrit.cardcrawl.vfx.FloatyEffect;
import theartifex.relics.AdvancedToolkitRelic;

import static theartifex.TheArtifexMod.makeID;

public class ObtainAdvancedToolkitPatch {

    @SpirePatch(
            clz = AbstractRelic.class,
            method = "bossObtainLogic"
    )
    public static class BossObtainLogicPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(@ByRef AbstractRelic[] __instance, @ByRef FloatyEffect[] ___f_effect) {
            if (__instance[0].relicId.equals(makeID(AdvancedToolkitRelic.class.getSimpleName()))) {
                __instance[0].isObtained = true;
                ___f_effect[0].x = 0.0F;
                ___f_effect[0].y = 0.0F;
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = BossRelicSelectScreen.class,
            method = "relicObtainLogic"
    )
    public static class SelectScreenPatch{
        @SpirePostfixPatch
        public static void Postfix(BossRelicSelectScreen __instance, @ByRef AbstractRelic[] r) {
            if (r[0] instanceof AdvancedToolkitRelic) {
                r[0].instantObtain(AbstractDungeon.player, 0, true);
                (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
            }
        }
    }
}
