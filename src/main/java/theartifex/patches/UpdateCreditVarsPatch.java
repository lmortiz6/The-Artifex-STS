package theartifex.patches;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCreditRelic;
import theartifex.abstracts.AbstractCyberneticRelic;

public class UpdateCreditVarsPatch {

    @SpirePatch(
            clz = AbstractRelic.class,
            method = "obtain"
    )
    public static class ObtainRelicPatch {

        @SpirePostfixPatch
        public static void Postfix(AbstractRelic __instance) {
            if (__instance instanceof AbstractCyberneticRelic || __instance instanceof AbstractCreditRelic) {
                TheArtifexMod.availableCreditsFast = TheArtifexMod.getAvailableCredits();
                TheArtifexMod.maxCreditsFast = TheArtifexMod.getMaxCredits();
            }
        }
    }

    @SpirePatch(
            clz = AbstractRelic.class,
            method = "instantObtain",
            paramtypez = {AbstractPlayer.class, int.class, boolean.class}
    )
    public static class InstantObtainRelicPatch {

        @SpirePostfixPatch
        public static void Postfix(AbstractRelic __instance, AbstractPlayer p, int slot, boolean callOnEquip) {
            if (AbstractDungeon.player == p) {
                if (__instance instanceof AbstractCyberneticRelic || __instance instanceof AbstractCreditRelic) {
                    TheArtifexMod.availableCreditsFast = TheArtifexMod.getAvailableCredits();
                    TheArtifexMod.maxCreditsFast = TheArtifexMod.getMaxCredits();
                }
            }

        }
    }

    @SpirePatch(
            clz = AbstractRelic.class,
            method = "instantObtain",
            paramtypez = {}
    )
    public static class InstantObtainRelicPatch2 {

        @SpirePostfixPatch
        public static void Postfix(AbstractRelic __instance) {
            if (__instance instanceof AbstractCyberneticRelic || __instance instanceof AbstractCreditRelic) {
                TheArtifexMod.availableCreditsFast = TheArtifexMod.getAvailableCredits();
                TheArtifexMod.maxCreditsFast = TheArtifexMod.getMaxCredits();
            }
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "loseRelic"
    )
    public static class LoseRelicPatch {

        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer __instance, String targetID) {
            TheArtifexMod.availableCreditsFast = TheArtifexMod.getAvailableCredits();
            TheArtifexMod.maxCreditsFast = TheArtifexMod.getMaxCredits();
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "loseRandomRelics"
    )
    public static class LoseRandomRelicsPatch {
        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer __instance, int amount) {
            TheArtifexMod.availableCreditsFast = TheArtifexMod.getAvailableCredits();
            TheArtifexMod.maxCreditsFast = TheArtifexMod.getMaxCredits();
        }
    }
}
