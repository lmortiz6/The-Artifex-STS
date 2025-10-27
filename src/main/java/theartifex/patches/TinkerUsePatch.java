package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

public class TinkerUsePatch {
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "useCard"
    )
    public static class PlayerUseCardPatch {

        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance, @ByRef AbstractCard[] c, AbstractMonster monster, int energyOnUse) {

            int tinker = 0;
            if (AbstractDungeon.player.hasPower("theartifex:TinkerPower"))
                tinker += AbstractDungeon.player.getPower("theartifex:TinkerPower").amount;

            if (c[0].tags.contains(CustomCardTags.THEARTIFEXJACKED) || c[0].tags.contains(CustomCardTags.THEARTIFEXPERMANENTJACKED)) {
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1 + tinker));
            }
            if (c[0].tags.contains(CustomCardTags.THEARTIFEXREINFORCED) || c[0].tags.contains(CustomCardTags.THEARTIFEXPERMANENTREINFORCED)) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 4 + tinker));
            }
            if (c[0].tags.contains(CustomCardTags.THEARTIFEXNULLING) || c[0].tags.contains(CustomCardTags.THEARTIFEXPERMANENTNULLING)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ArtifactPower(AbstractDungeon.player, 1 + tinker)));
                c[0].exhaust = true;
            }
            if (c[0].tags.contains(CustomCardTags.THEARTIFEXFLEXIWEAVED) || c[0].tags.contains(CustomCardTags.THEARTIFEXPERMANENTFLEXIWEAVED)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1 + tinker)));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseDexterityPower(AbstractDungeon.player, 1 + tinker)));
            }
            if (c[0].tags.contains(CustomCardTags.THEARTIFEXSPRINGLOADED) || c[0].tags.contains(CustomCardTags.THEARTIFEXPERMANENTSPRINGLOADED)) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1 + tinker));
                AbstractDungeon.actionManager.addToBottom(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player,1 + tinker, false));
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "applyPowersToBlock"
    )
    public static class CardApplyPowersToBlockPatch {

        private static final int DEX = 1;

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"tmp"}
        )
        public static void Insert(AbstractCard __instance, @ByRef float[] tmp) {

            if (__instance.tags.contains(CustomCardTags.THEARTIFEXFLEXIWEAVED) || __instance.tags.contains(CustomCardTags.THEARTIFEXPERMANENTFLEXIWEAVED)) {
                int tinker = 0;
                if (AbstractDungeon.player.hasPower("theartifex:TinkerPower"))
                    tinker += AbstractDungeon.player.getPower("theartifex:TinkerPower").amount;
                tmp[0] += DEX + tinker;
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "baseBlock");
                int[] lineToReturn = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
                for (int i = 0; i < lineToReturn.length; i++) {
                    lineToReturn[i] += 1;
                }
                return lineToReturn;
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "applyPowers"
    )
    public static class CardApplyPowersPatch {

        private static final int DAMAGE = 4;

        @SpireInsertPatch(
                locator = Locator1.class,
                localvars = {"tmp"}
        )
        public static void Insert1(AbstractCard __instance, @ByRef float[] tmp) {

            if (__instance.tags.contains(CustomCardTags.THEARTIFEXSHARP) || __instance.tags.contains(CustomCardTags.THEARTIFEXPERMANENTSHARP)) {
                int tinker = 0;
                if (AbstractDungeon.player.hasPower("theartifex:TinkerPower"))
                    tinker += AbstractDungeon.player.getPower("theartifex:TinkerPower").amount;
                tmp[0] += DAMAGE + tinker;
            }
        }

        @SpireInsertPatch(
                locator = Locator2.class,
                localvars = {"tmp"}
        )
        public static void Insert2(AbstractCard __instance, @ByRef float[][] tmp) {

            if (__instance.tags.contains(CustomCardTags.THEARTIFEXSHARP) || __instance.tags.contains(CustomCardTags.THEARTIFEXPERMANENTSHARP)) {
                int tinker = 0;
                if (AbstractDungeon.player.hasPower("theartifex:TinkerPower"))
                    tinker += AbstractDungeon.player.getPower("theartifex:TinkerPower").amount;
                for (int i = 0; i < tmp[0].length; i++) {
                    tmp[0][i] += DAMAGE + tinker;
                }
            }
        }

        private static class Locator1 extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "baseDamage");
                int[] lineToReturn = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
                for (int i = 0; i < lineToReturn.length; i++) {
                    lineToReturn[i] += 1;
                }
                return lineToReturn;
            }
        }

        private static class Locator2 extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "baseDamage");
                ArrayList<Matcher> preReqs = new ArrayList<Matcher>();
                preReqs.add(new Matcher.MethodCallMatcher(AbstractDungeon.class, "getCurrRoom"));
                int[] lineToReturn = LineFinder.findInOrder(ctMethodToPatch, preReqs, finalMatcher);
                for (int i = 0; i < lineToReturn.length; i++) {
                    lineToReturn[i] += 1;
                }
                return lineToReturn;
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "calculateCardDamage"
    )
    public static class CardCalculateDamagePatch {

        private static final int DAMAGE = 4;

        @SpireInsertPatch(
                locator = CardApplyPowersPatch.Locator1.class,
                localvars = {"tmp"}
        )
        public static void Insert1(AbstractCard __instance, AbstractMonster mo, @ByRef float[] tmp) {


            if (__instance.tags.contains(CustomCardTags.THEARTIFEXSHARP) || __instance.tags.contains(CustomCardTags.THEARTIFEXPERMANENTSHARP)) {
                int tinker = 0;
                if (AbstractDungeon.player.hasPower("theartifex:TinkerPower"))
                    tinker += AbstractDungeon.player.getPower("theartifex:TinkerPower").amount;
                tmp[0] += DAMAGE + tinker;
            }
        }

        @SpireInsertPatch(
                locator = CardApplyPowersPatch.Locator2.class,
                localvars = {"tmp"}
        )
        public static void Insert2(AbstractCard __instance, AbstractMonster mo, @ByRef float[][] tmp) {

            if (__instance.tags.contains(CustomCardTags.THEARTIFEXSHARP) || __instance.tags.contains(CustomCardTags.THEARTIFEXPERMANENTSHARP)) {
                int tinker = 0;
                if (AbstractDungeon.player.hasPower("theartifex:TinkerPower"))
                    tinker += AbstractDungeon.player.getPower("theartifex:TinkerPower").amount;
                for (int i = 0; i < tmp[0].length; i++) {
                    tmp[0][i] += DAMAGE + tinker;
                }
            }
        }
    }
}
