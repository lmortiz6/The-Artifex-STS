package theartifex.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theartifex.actions.MedassistModuleAction;

import static theartifex.TheArtifexMod.makeID;

public class MedassistModulePower extends BasePower{

    public static final String POWER_ID = makeID(MedassistModulePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MedassistModulePower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < amount; i++)
            addToTop(new MedassistModuleAction(1, false));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        MedassistModuleAction.modded = false;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], (this.amount));
        } else {
            this.description = String.format(DESCRIPTIONS[0], (this.amount));
        }
    }
}
