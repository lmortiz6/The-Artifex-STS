package theartifex.cards.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ConkAction extends AbstractGameAction {

    private static final float DURATION = 0.1F;

    private final AbstractMonster m;
    private final AbstractCreature p;
    private final int magicNumber;

    public ConkAction(AbstractMonster target, AbstractCreature source, int weakAmount) {
        this.m = target;
        this.p = source;
        this.magicNumber = weakAmount;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.duration = DURATION;
    }

    @Override
    public void update() {
        if (this.m == null) {
            this.isDone = true;
            return;
        }
        if (this.m.hasPower("Weakened") && this.m.getPower("Weakened").amount > 1) {
            addToBot(new StunMonsterAction(m, p));
            addToBot(new RemoveSpecificPowerAction(m, p, "Weakened"));
        } else {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
        }
        this.isDone = true;
    }
}
