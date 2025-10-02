package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CustomCardTags;

@SpirePatch(
        clz = CardCrawlGame.class,
        method = "loadPlayerSave"
)
public class LoadCardModsPatch {

    @SpirePostfixPatch
    public static void Postfix(CardCrawlGame __instance, AbstractPlayer p) {
        for (AbstractCard c : p.masterDeck.group) {
            if (c.color == TheArtifexCharacter.Meta.CARD_COLOR) {
                CustomCardTags.loadSavedPermanentMod(c);
            }
        }
    }
}
