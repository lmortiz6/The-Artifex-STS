package theartifex.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

public class MetamorphicPolygelAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DualWieldAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private static final float DURATION_PER_CARD = 0.25F;

    private final AbstractPlayer p;

    private final int dupeAmount;

    private final ArrayList<AbstractCard> cannotDuplicate = new ArrayList<>();

    private final AbstractCard original;

    public MetamorphicPolygelAction(AbstractCreature source, AbstractCard original) {
        setValues(AbstractDungeon.player, source, amount);
        this.actionType = AbstractGameAction.ActionType.DRAW;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
        this.amount = 1;
        this.dupeAmount = 1;
        this.original = original;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : this.p.hand.group) {
                if (!isDualWieldable(c))
                    this.cannotDuplicate.add(c);
            }
            if (this.cannotDuplicate.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() - this.cannotDuplicate.size() == 1)
                for (AbstractCard c : this.p.hand.group) {
                    if (isDualWieldable(c)) {
                        for (int i = 0; i < this.dupeAmount; i++)
                            transformCard(original, c);
                        this.isDone = true;
                        return;
                    }
                }
            this.p.hand.group.removeAll(this.cannotDuplicate);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                tickDuration();
                return;
            }
            if (this.p.hand.group.size() == 1) {
                transformCard(original, this.p.hand.getTopCard());
                returnCards();
                this.isDone = true;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard c = AbstractDungeon.handCardSelectScreen.selectedCards.getTopCard();
            addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
            transformCard(original, c);
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private void transformCard(AbstractCard target, AbstractCard replacement) {
        AbstractCard replacementCopy = replacement.makeStatEquivalentCopy();
        replacementCopy.current_x = target.current_x;
        replacementCopy.current_y = target.current_y;
        replacementCopy.target_x = target.target_x;
        replacementCopy.target_y = target.target_y;
        replacementCopy.drawScale = 1.0F;
        replacementCopy.targetDrawScale = target.targetDrawScale;
        replacementCopy.angle = target.angle;
        replacementCopy.targetAngle = target.targetAngle;
        replacementCopy.superFlash(Color.WHITE.cpy());

        this.p.hand.addToHand(target);
        this.p.hand.group.set(this.p.hand.group.size() - 1, replacementCopy);
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotDuplicate)
            this.p.hand.addToTop(c);
        this.p.hand.refreshHandLayout();
    }

    private boolean isDualWieldable(AbstractCard card) {
        return (!card.hasTag(CustomCardTags.THEARTIFEXCANNOTPOLYGEL));
    }
}
