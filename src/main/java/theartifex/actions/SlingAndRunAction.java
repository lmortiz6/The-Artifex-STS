package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theartifex.cards.skills.Sprint;

public class SlingAndRunAction extends AbstractGameAction {

    public SlingAndRunAction(int amount) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
    }

    public void update() {
        boolean debuffed = false;
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF)
                debuffed = true;
        }
        if (!debuffed) {
            addToBot(new MakeTempCardInDrawPileAction(new Sprint(), this.amount, true, true, false));
        }
        this.isDone = true;
    }
}
