package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class JackInAction extends AbstractGameAction {

    private final AbstractPlayer p;

    private final ArrayList<AbstractCard> cannotTinker = new ArrayList<>();

    private final AbstractCard sourceCard;

    public JackInAction(AbstractCreature source, int amount, AbstractCard sourceCard) {
        setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
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
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f);
                this.isDone = true;
                return;
            }
            this.p.hand.group.removeAll(this.cannotTinker);
            if (this.p.hand.group.size() > amount) {
                AbstractDungeon.handCardSelectScreen.open(CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[2], this.amount, false);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                AbstractDungeon.player.hand.addToHand(getModdedCard(c));
            }
            CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f);
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
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXJACKED, false);
    }

    private boolean isTinkerable(AbstractCard card) {
        return (card != sourceCard && CustomCardTags.getMods(card).size() < 2 && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTJACKED) && !card.tags.contains(CustomCardTags.THEARTIFEXJACKED));
    }
}
