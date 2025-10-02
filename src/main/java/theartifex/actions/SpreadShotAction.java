package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.abstracts.AbstractGun;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class SpreadShotAction extends AbstractGameAction {

    private AbstractPlayer p;

    private ArrayList<AbstractCard> cannotTinker = new ArrayList<>();

    private AbstractCard sourceCard;

    public SpreadShotAction(AbstractCreature source, int amount, AbstractCard sourceCard) {
        setValues((AbstractCreature)AbstractDungeon.player, source, amount);
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
            if (this.p.hand.group.size() - this.cannotTinker.size() <= amount) {
                for (AbstractCard c : this.p.hand.group) {
                    if (isTinkerable(c)) {
                        modCard(c);
                    }
                }
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                this.isDone = true;
                return;
            }
            this.p.hand.group.removeAll(this.cannotTinker);
            if (this.p.hand.group.size() > amount) {
                AbstractDungeon.handCardSelectScreen.open("Modify", this.amount, false);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                addToTop((AbstractGameAction)new MakeTempCardInHandAction(getModdedCard(c)));
            }
            CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
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
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXBEAMSPLITTER, false);
    }

    private boolean isTinkerable(AbstractCard card) {
        return (card != sourceCard && CustomCardTags.getMods(card).size() < 2 && (card instanceof AbstractGun) && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTBEAMSPLITTER) && !card.tags.contains(CustomCardTags.THEARTIFEXBEAMSPLITTER));
    }
}
