package theartifex.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class InflatableAxonsPower extends BasePower implements InvisiblePower {

    public static final String POWER_ID = makeID(InflatableAxonsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private ArrayList<Boolean> used;

    public InflatableAxonsPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
        if ((GameActionManager.turn) % 2 == 1) {
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawReductionPower(owner, owner, amount)));
        } else {
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, 2*amount)));
        }
    }

    public void atStartOfTurn() {
        if ((GameActionManager.turn + 1) % 2 == 1) {
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawReductionPower(owner, owner, amount)));
        } else {
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, 2*amount)));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[]{this.amount, this.amount});
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount, this.amount});
        }
    }
}
