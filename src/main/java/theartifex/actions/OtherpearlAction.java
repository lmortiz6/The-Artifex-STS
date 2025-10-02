package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

public class OtherpearlAction extends AbstractGameAction {

    public OtherpearlAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        AbstractCard disCard = generateCard().makeStatEquivalentCopy();
        if (disCard != null) {
            //disCard.setCostForTurn(0);
            disCard.current_x = -1000.0F * Settings.xScale;
            if (AbstractDungeon.player.hand.size() < 10) {
                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            } else {
                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            }
        }
        this.isDone = true;
    }

    private AbstractCard generateCard() {
        AbstractCard.CardRarity cardRarity;
        int roll = AbstractDungeon.cardRandomRng.random(99);
        if (roll < 45) {
            cardRarity = AbstractCard.CardRarity.COMMON;
        } else if (roll < 80) {
            cardRarity = AbstractCard.CardRarity.UNCOMMON;
        } else {
            cardRarity = AbstractCard.CardRarity.RARE;
        }
        AbstractCard.CardType cardType;
        roll = AbstractDungeon.cardRandomRng.random(2);
        if (roll == 0) {
            cardType = AbstractCard.CardType.ATTACK;
        } else if (roll == 1) {
            cardType = AbstractCard.CardType.SKILL;
        } else if (roll == 2) {
            cardType = AbstractCard.CardType.POWER;
        }
        return CardLibrary.getAnyColorCard(AbstractCard.CardType.ATTACK, cardRarity);
    }
}
