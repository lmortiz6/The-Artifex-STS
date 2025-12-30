package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.BufferPower;
import theartifex.TheArtifexMod;

import static theartifex.TheArtifexMod.makeID;

public class HolographicVisagePower extends BasePower {

    public static final String POWER_ID = makeID(HolographicVisagePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HolographicVisagePower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!TheArtifexMod.haveAttacked) {
            addToTop(new ApplyPowerAction(owner, owner, new BufferPower(owner, 1)));
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], this.amount, this.amount);
        } else {
            this.description = String.format(DESCRIPTIONS[0], this.amount, this.amount);
        }
    }
}
