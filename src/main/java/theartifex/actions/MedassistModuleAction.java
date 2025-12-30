package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.abstracts.AbstractInjector;
import theartifex.util.CustomCardTags;

import static theartifex.TheArtifexMod.makeID;

public class MedassistModuleAction extends AbstractGameAction {

    private final AbstractPlayer p;
    private final boolean isRelic;

    public static boolean modded = false;

    public MedassistModuleAction(int amount, boolean isRelic) {
        this.p = AbstractDungeon.player;
        this.isRelic = isRelic;
        this.startDuration = 0.05F;
        setValues(p, p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = startDuration;
    }

    public void update() {
        if (this.duration == startDuration) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.drawPile.group) {
                if (c instanceof AbstractInjector)
                    tmp.addToTop(c);
            }
            if (tmp.isEmpty()) {
                this.isDone = true;
                return;
            }
            int roll;
            AbstractCard card;
            if (isRelic)
                card = tmp.getRandomCard(AbstractDungeon.relicRng);
            else
                card = tmp.getRandomCard(AbstractDungeon.cardRandomRng);
            if (this.p.hand.size() < 10) {
                if (isTinkerable(card)) {
                    modCard(card);
                }
                card.unhover();
                card.lighten(true);
                card.setAngle(0.0F);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                card.current_x = CardGroup.DRAW_PILE_X;
                card.current_y = CardGroup.DRAW_PILE_Y;
                this.p.drawPile.removeCard(card);
                AbstractDungeon.player.hand.addToTop(card);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
            }
            this.isDone = true;
            return;
        }
        tickDuration();
    }

    private void modCard(AbstractCard c) {
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXSPRINGLOADED, false);

        if (!modded) {
            CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f);
            modded = true;
        }
    }

    private boolean isTinkerable(AbstractCard card) {
        return (CustomCardTags.getMods(card).size() < 2 && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTSPRINGLOADED) && !card.tags.contains(CustomCardTags.THEARTIFEXSPRINGLOADED));
    }
}
