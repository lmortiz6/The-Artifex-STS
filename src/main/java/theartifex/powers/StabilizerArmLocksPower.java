package theartifex.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class StabilizerArmLocksPower extends BasePower{

    public static final String POWER_ID = makeID(StabilizerArmLocksPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private ArrayList<Boolean> used;

    public StabilizerArmLocksPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[]{amount});
    }
}
