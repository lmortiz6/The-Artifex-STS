package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class AddToHandAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final ArrayList<AbstractCard> cards;

    public AddToHandAction(ArrayList<AbstractCard> cards) {
        this.p = AbstractDungeon.player;
        this.cards = cards;
        setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        for (AbstractCard card : cards) {
            AbstractDungeon.player.hand.addToHand(card);
        }
        this.isDone = true;
    }
}
