package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static theartifex.TheArtifexMod.makeID;

public class LovesickPower extends BasePower{

    public static final String POWER_ID = makeID(LovesickPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public LovesickPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
    }

    @Override
    public void atEndOfRound() {
        if (this.amount <= 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            addToTop(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
        updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], (this.amount));
        } else {
            this.description = String.format(DESCRIPTIONS[0], (this.amount));
        }
    }
}
