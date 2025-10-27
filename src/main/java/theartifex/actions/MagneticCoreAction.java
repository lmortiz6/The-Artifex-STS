package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.util.CustomCardTags;

public class MagneticCoreAction extends AbstractGameAction {

    public MagneticCoreAction(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        setValues(p, p, amount);
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.POWER && c.costForTurn > 0) {
                c.costForTurn -= Math.min(amount, c.costForTurn);
                c.isCostModifiedForTurn = true;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.type == AbstractCard.CardType.POWER && c.costForTurn > 0) {
                c.costForTurn -= Math.min(amount, c.costForTurn);
                c.isCostModifiedForTurn = true;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.type == AbstractCard.CardType.POWER && c.costForTurn > 0) {
                c.costForTurn -= Math.min(amount, c.costForTurn);
                c.isCostModifiedForTurn = true;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.type == AbstractCard.CardType.POWER && c.costForTurn > 0) {
                c.costForTurn -= Math.min(amount, c.costForTurn);
                c.isCostModifiedForTurn = true;
            }
        }

        this.isDone = true;
    }
}
