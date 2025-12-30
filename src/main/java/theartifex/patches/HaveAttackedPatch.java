package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.TheArtifexMod;


public class HaveAttackedPatch {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "useCard"
    )
    public static class UseAttackPatch {
        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                TheArtifexMod.haveAttacked = true;
            }
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnRelics"
    )
    public static class EndTurnResetPatch {
        @SpirePrefixPatch
        public static void Prefix() {
            TheArtifexMod.haveAttacked = false;
        }
    }
}
