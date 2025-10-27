package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

public class RigRushAction extends AbstractGameAction {

    private final AbstractPlayer p;

    private final ArrayList<AbstractCard> cannotTinker = new ArrayList<>();

    private final AbstractCard sourceCard;

    public RigRushAction(AbstractCreature source, int amount, AbstractCard sourceCard) {
        setValues(AbstractDungeon.player, source, amount);
        this.actionType = AbstractGameAction.ActionType.DRAW;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
        this.sourceCard = sourceCard;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : this.p.hand.group) {
                if (!isTinkerable(c))
                    this.cannotTinker.add(c);
            }
            if (this.cannotTinker.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() - this.cannotTinker.size() == 1)
                for (AbstractCard c : this.p.hand.group) {
                    if (isTinkerable(c)) {
                        modCard(c);
                        this.isDone = true;
                        return;
                    }
                }
            this.p.hand.group.removeAll(this.cannotTinker);
            if (this.p.hand.group.size() > 1) {
                for (int i = 0; i < amount; i++) {
                    int j = AbstractDungeon.cardRandomRng.random(0, this.p.hand.group.size() - 1);
                    AbstractCard c = this.p.hand.group.get(j);
                    modCard(c);
                    this.cannotTinker.add(c);
                    p.hand.group.remove(c);
                }
                returnCards();
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() == 1) {
                modCard(this.p.hand.getTopCard());
                returnCards();
                this.isDone = true;
                return;
            }
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotTinker)
            this.p.hand.addToTop(c);
        this.p.hand.refreshHandLayout();
    }

    private AbstractCard getModdedCard (AbstractCard c) {
        AbstractCard card = c.makeStatEquivalentCopy();
        modCard(card);
        return card;
    }

    private void modCard(AbstractCard c) {
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXSPRINGLOADED, false);
    }

    private boolean isTinkerable(AbstractCard card) {
        return (card != sourceCard && CustomCardTags.getMods(card).size() < 2 && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTSPRINGLOADED) && !card.tags.contains(CustomCardTags.THEARTIFEXSPRINGLOADED));
    }
}
