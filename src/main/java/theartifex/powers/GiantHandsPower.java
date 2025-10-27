package theartifex.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theartifex.actions.GiantHandsAction;

import static theartifex.TheArtifexMod.makeID;

public class GiantHandsPower extends BasePower{

    public static final String POWER_ID = makeID(GiantHandsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static int drawnCards;

    public GiantHandsPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        addToBot(new GiantHandsAction(amount, this));
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount) });
        }
    }
}
