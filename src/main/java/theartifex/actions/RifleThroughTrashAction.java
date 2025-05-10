package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RifleThroughTrashAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private boolean gotCard;

    public RifleThroughTrashAction(int amount) {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.gotCard = false;
        CardCrawlGame.sound.playA("CARD_OBTAIN", 0F);
    }

    public void update() {
        if (this.amount > 0) {
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.createHandIsFullDialog();
                this.isDone = true;
                return;
            }
            if (this.p.exhaustPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (!gotCard)
                CardCrawlGame.sound.playA("CARD_OBTAIN", 0F);
            this.gotCard = true;
            AbstractCard card = this.p.exhaustPile.getRandomCard(AbstractDungeon.cardRandomRng);
            card.unfadeOut();
            this.p.hand.addToHand(card);
            if (AbstractDungeon.player.hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL)
                card.setCostForTurn(-9);
            this.p.exhaustPile.removeCard(card);
            card.unhover();
            card.fadingOut = false;
            card.lighten(true);
            card.setAngle(0.0F);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.current_x = Settings.WIDTH * 0.95F;
            card.current_y = Settings.HEIGHT * 0.20F;
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
            tickDuration();
            this.amount--;
        }else {
            this.isDone = true;
        }
        addToBot(new WaitAction(0.4F));
    }
}
