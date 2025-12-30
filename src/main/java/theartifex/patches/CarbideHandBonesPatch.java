package theartifex.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.relics.CarbideHandBonesRelic;

public class CarbideHandBonesPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "applyPowers"
    )
    public static class ApplyPowersPatch {
        @SpirePostfixPatch
        public static void Postfix(@ByRef AbstractCard[] __instance) {
            if (__instance[0].hasTag(AbstractCard.CardTags.STARTER_STRIKE) && AbstractDungeon.player != null) {
                if (AbstractDungeon.player.hasPower("theartifex:CarbideHandBonesPower")) {
                    float tmp = __instance[0].damage;
                    for (int i = 0; i < AbstractDungeon.player.getPower("theartifex:CarbideHandBonesPower").amount; i++) {
                        tmp *= 1.3f;
                    }
                    if (__instance[0].baseDamage != MathUtils.round(tmp))
                        __instance[0].isDamageModified = true;
                    __instance[0].damage = MathUtils.round(tmp);
                }
                if (AbstractDungeon.player.hasRelic("theartifex:CarbideHandBonesRelic")) {
                    float tmp = __instance[0].damage;
                    for (AbstractRelic r : AbstractDungeon.player.relics) {
                        if (r instanceof CarbideHandBonesRelic) {
                            tmp *= 1.3f;
                        }
                    }
                    if (__instance[0].baseDamage != MathUtils.round(tmp))
                        __instance[0].isDamageModified = true;
                    __instance[0].damage = MathUtils.round(tmp);
                }

            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "calculateCardDamage"
    )
    public static class CalculateCardDamagePatch {
        @SpirePostfixPatch
        public static void Postfix(@ByRef AbstractCard[] __instance) {
            if (__instance[0].hasTag(AbstractCard.CardTags.STARTER_STRIKE) && AbstractDungeon.player != null) {
                if (AbstractDungeon.player.hasPower("theartifex:CarbideHandBonesPower")) {
                    float tmp = __instance[0].damage;
                    for (int i = 0; i < AbstractDungeon.player.getPower("theartifex:CarbideHandBonesPower").amount; i++) {
                        tmp *= 1.3f;
                    }
                    if (__instance[0].baseDamage != MathUtils.round(tmp))
                        __instance[0].isDamageModified = true;
                    __instance[0].damage = MathUtils.round(tmp);
                }
                if (AbstractDungeon.player.hasRelic("theartifex:CarbideHandBonesRelic")) {
                    float tmp = __instance[0].damage;
                    for (AbstractRelic r : AbstractDungeon.player.relics) {
                        if (r instanceof CarbideHandBonesRelic) {
                            tmp *= 1.3f;
                        }
                    }
                    if (__instance[0].baseDamage != MathUtils.round(tmp))
                        __instance[0].isDamageModified = true;
                    __instance[0].damage = MathUtils.round(tmp);
                }

            }
        }
    }
}
