package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.TheArtifexMod;
import theartifex.powers.SwiftReflexesPower;
import theartifex.util.CustomCardTags;

public class SwiftReflexesOnDiscardPatch {

    @SpirePatch(
            clz = CardGroup.class,
            method = "moveToDiscardPile"
    )
    public static class CardGroupPatch {
        @SpirePostfixPatch
        public static void Postfix(CardGroup __instance, @ByRef AbstractCard[] c) {
            if (AbstractDungeon.player.hasPower("theartifex:SwiftReflexesPower") && ((SwiftReflexesPower)AbstractDungeon.player.getPower("theartifex:SwiftReflexesPower")).used < AbstractDungeon.player.getPower("theartifex:SwiftReflexesPower").amount && !TheArtifexMod.nonManualDiscard && ((CustomCardTags.getMods(c[0]).size() < 2 && c[0].cost != -2 && c[0].type != AbstractCard.CardType.CURSE && !c[0].tags.contains(CustomCardTags.THEARTIFEXPERMANENTFLEXIWEAVED) && !c[0].tags.contains(CustomCardTags.THEARTIFEXFLEXIWEAVED)))) {
                if (AbstractDungeon.player.hand.size() < 10) {
                    AbstractDungeon.player.hand.addToHand(c[0]);
                    AbstractDungeon.player.discardPile.removeCard(c[0]);
                }
                c[0].lighten(false);
                c[0].applyPowers();
                CustomCardTags.loadMod(c[0], CustomCardTags.THEARTIFEXFLEXIWEAVED, false);
                ((SwiftReflexesPower)AbstractDungeon.player.getPower("theartifex:SwiftReflexesPower")).used++;
            }
        }
    }

    @SpirePatch(
            clz = DiscardAtEndOfTurnAction.class,
            method = "update"
    )
    public static class DiscardAtEndOfTurnActionPatch {
        @SpirePrefixPatch
        public static void Prefix(DiscardAtEndOfTurnAction __instance) {
            TheArtifexMod.nonManualDiscard = true;
        }
    }

    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class UseCardActionPatch {
        @SpirePrefixPatch
        public static void Prefix(UseCardAction __instance) {
            TheArtifexMod.nonManualDiscard = true;
        }
    }
}
