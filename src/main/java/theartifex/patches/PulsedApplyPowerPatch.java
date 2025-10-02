package theartifex.patches;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(
        clz = ApplyPowerAction.class,
        method = "update"
)
public class PulsedApplyPowerPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix (ApplyPowerAction __instance, AbstractPower ___powerToApply, @ByRef float[] ___duration) {
        if (__instance.target != null && __instance.target.hasPower("theartifex:PulsedPower") &&  (___powerToApply.type == AbstractPower.PowerType.BUFF || ___powerToApply.ID.equals("Shackled"))) {
            AbstractDungeon.actionManager.addToTop(new TextAboveCreatureAction(__instance.target, "Negated"));
            ___duration[0] -= Gdx.graphics.getDeltaTime();
            CardCrawlGame.sound.play("NULLIFY_SFX");
            __instance.target.getPower("theartifex:PulsedPower").flashWithoutSound();
            __instance.target.getPower("theartifex:PulsedPower").onSpecificTrigger();
            __instance.isDone = true;
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}
