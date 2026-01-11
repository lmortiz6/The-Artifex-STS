package theartifex.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class TransformCardInHandAction extends AbstractGameAction {

    private final AbstractPlayer p;

    private final ArrayList<AbstractCard> cannotTransform = new ArrayList<>();
    private final ArrayList<AbstractCard> toRemove = new ArrayList<>();

    private final AbstractCard replacement;

    public TransformCardInHandAction(int amount, AbstractCard replacement) {
        this.p = AbstractDungeon.player;
        this.startDuration = 0.05F;
        setValues(p, p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = startDuration;

        this.replacement = replacement;
    }

    public void update() {
        if (this.duration == startDuration) {
            for (AbstractCard c : this.p.hand.group) {
                if (!isTransformable(c))
                    this.cannotTransform.add(c);
            }
            if (this.cannotTransform.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() - this.cannotTransform.size() <= amount) {
                for (AbstractCard c : this.p.hand.group) {
                    if (isTransformable(c)) {
                        toRemove.add(c);
                    }
                }
                this.p.hand.group.removeAll(toRemove);
                for (AbstractCard c : toRemove) {
                    transformCard(c);
                }
                if (replacement instanceof Burn) {
                    addToTop(new SFXAction("CARD_BURN"));
                }
                this.p.hand.glowCheck();
                this.isDone = true;
                return;
            }
            this.p.hand.group.removeAll(this.cannotTransform);
            if (this.p.hand.group.size() > amount) {
                AbstractDungeon.handCardSelectScreen.open(CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[3], this.amount, false);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                transformCard(c);
            }
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            if (replacement instanceof Burn) {
                addToTop(new SFXAction("CARD_BURN"));
            }
            this.p.hand.glowCheck();
            this.isDone = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotTransform)
            this.p.hand.addToTop(c);
        this.p.hand.refreshHandLayout();
    }

    private void transformCard(AbstractCard target) {
        AbstractCard replacementCopy = replacement.makeCopy();
        replacementCopy.current_x = target.current_x;
        replacementCopy.current_y = target.current_y;
        replacementCopy.target_x = target.target_x;
        replacementCopy.target_y = target.target_y;
        replacementCopy.drawScale = 1.0F;
        replacementCopy.targetDrawScale = target.targetDrawScale;
        replacementCopy.angle = target.angle;
        replacementCopy.targetAngle = target.targetAngle;
        replacementCopy.superFlash(Color.WHITE.cpy());
        if (target instanceof Burn && replacement instanceof Burn)
            replacementCopy.upgrade();
        this.p.hand.addToHand(replacementCopy);
    }

    private boolean isTransformable(AbstractCard card) {
        return (!(card instanceof Necronomicurse));
    }
}
