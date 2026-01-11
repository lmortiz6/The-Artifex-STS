package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import theartifex.util.CustomCardTags;

@SpirePatch(
        clz = CardGroup.class,
        method = "moveToDiscardPile"
)
public class TransformOnPlayPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(@ByRef CardGroup[] __instance, AbstractCard c) {
        if (c.hasTag(CustomCardTags.THEARTIFEXTRANSFORMONPLAY)) {
            //__instance[0].moveToHand(c);
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}
