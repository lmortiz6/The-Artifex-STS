package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.HexPower;
import theartifex.powers.LovesickPower;

public class LovesickPatch {
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update"
    )
    public static class DebuffPatch {
        @SpirePrefixPatch
        public static void Prefix (@ByRef ApplyPowerAction[] __instance, float ___duration, float ___startingDuration, AbstractPower ___powerToApply) {
            if (___duration == ___startingDuration) {
                if (__instance[0].source != null && __instance[0].source.hasPower("theartifex:LovesickPower") && ___powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                    __instance[0].target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.aiRng);
                    __instance[0].source.getPower("theartifex:LovesickPower").flash();
                }
            }
        }
    }

    @SpirePatch(
            clz = DamageAction.class,
            method = "update"
    )
    public static class DamagePatch {
        @SpirePrefixPatch
        public static void Prefix (@ByRef DamageAction[] __instance, float ___duration) {
            if (___duration == 0.1F) {
                if (__instance[0].source != null && __instance[0].source.hasPower("theartifex:LovesickPower")) {
                    __instance[0].target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.aiRng);
                    __instance[0].source.getPower("theartifex:LovesickPower").flash();
                }
            }
        }
    }

    @SpirePatch(
            clz = HexPower.class,
            method = "onUseCard"
    )
    public static class HexPowerPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix (HexPower __instance, AbstractCard card, UseCardAction action) {
            if (__instance.owner instanceof AbstractPlayer) {
                return SpireReturn.Continue();
            } else
                return SpireReturn.Return();
        }
    }

    @SpirePatch(
            clz = DrawReductionPower.class,
            method = "onInitialApplication"
    )
    public static class DrawReductionApplyPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix (DrawReductionPower __instance) {
            if (__instance.owner instanceof AbstractPlayer) {
                return SpireReturn.Continue();
            } else
                return SpireReturn.Return();
        }
    }

    @SpirePatch(
            clz = DrawReductionPower.class,
            method = "onRemove"
    )
    public static class DrawReductionRemovePatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix (DrawReductionPower __instance) {
            if (__instance.owner instanceof AbstractPlayer) {
                return SpireReturn.Continue();
            } else
                return SpireReturn.Return();
        }
    }
}
