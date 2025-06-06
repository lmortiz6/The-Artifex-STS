package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

public class ModifyCostForCombatAction extends AbstractGameAction {
    private final AbstractCard c;

    public ModifyCostForCombatAction(AbstractCard c, int amount) {
        this.c = c;
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        c.modifyCostForCombat(amount);
        this.isDone = true;
    }
}
