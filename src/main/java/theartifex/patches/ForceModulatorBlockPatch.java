package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "decrementBlock"
)
public class ForceModulatorBlockPatch {
    @SpirePrefixPatch
    public static SpireReturn<Integer> Prefix(AbstractCreature __instance, DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && AbstractDungeon.player != null && info.owner == AbstractDungeon.player && (AbstractDungeon.player.hasRelic("theartifex:ForceModulatorRelic") || AbstractDungeon.player.hasPower("theartifex:ForceModulatorPower")) && __instance instanceof AbstractMonster) {
            return SpireReturn.Return(damageAmount);
        } else
            return SpireReturn.Continue();
    }
}
