package theartifex.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import static theartifex.TheArtifexMod.makeID;

public class MotorizedTreadsPower extends BasePower implements InvisiblePower {

    public static final String POWER_ID = makeID(MotorizedTreadsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final int DRAW = 2;

    public MotorizedTreadsPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, amount)));
    }

    @Override
    public void stackPower(int stackAmount) {
        addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, stackAmount)));
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();

        addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, amount)));
        addToBot(new ApplyPowerAction(owner, owner, new NoDrawPower(owner)));
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount});
    }
}
