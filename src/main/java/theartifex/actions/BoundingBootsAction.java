package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.util.CustomCardTags;

import static theartifex.TheArtifexMod.makeID;

public class BoundingBootsAction extends AbstractGameAction {

    private boolean modded = false;

    public BoundingBootsAction(int amount) {
        setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.25F;
    }

    @Override
    public void update() {
        for (int i = DrawCardAction.drawnCards.size() - 1; i > DrawCardAction.drawnCards.size() - 1 - amount && i >= 0; i--) {
            if (isTinkerable(DrawCardAction.drawnCards.get(i))) {
                modCard(DrawCardAction.drawnCards.get(i));
            }
        }
        if (modded)
            CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f);
        this.isDone = true;
        tickDuration();
    }

    private void modCard(AbstractCard c) {
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXSPRINGLOADED, false);
        modded = true;
    }

    private boolean isTinkerable(AbstractCard card) {
        return (CustomCardTags.getMods(card).size() < 2 && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTSPRINGLOADED) && !card.tags.contains(CustomCardTags.THEARTIFEXSPRINGLOADED));
    }
}
