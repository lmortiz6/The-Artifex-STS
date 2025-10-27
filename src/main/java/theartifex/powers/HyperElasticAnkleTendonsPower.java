package theartifex.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theartifex.TheArtifexMod.makeID;

public class HyperElasticAnkleTendonsPower extends BasePower {

    public static final String POWER_ID = makeID(HyperElasticAnkleTendonsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HyperElasticAnkleTendonsPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[]{this.amount});
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount});
        }
    }
}
