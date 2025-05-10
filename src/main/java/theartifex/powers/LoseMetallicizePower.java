package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static theartifex.TheArtifexMod.makeID;

public class LoseMetallicizePower extends BasePower{

    public static final String POWER_ID = makeID(LoseMetallicizePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public LoseMetallicizePower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (owner.hasPower("Metallicize")) {
            flash();
            owner.getPower("Metallicize").reducePower(amount);
            if (owner.getPower("Metallicize").amount <= 0) {
                addToTop(new RemoveSpecificPowerAction(owner, owner, owner.getPower("Metallicize")));
            }
            owner.getPower("Metallicize").updateDescription();
        }
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount) });
    }
}
