package theartifex.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static theartifex.TheArtifexMod.makeID;

public class ForceModulatorPower extends BasePower{

    public static final String POWER_ID = makeID(ForceModulatorPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //private static int used;

    public ForceModulatorPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        //used = amount;
        updateDescription();
    }

    /*
    public void atStartOfTurn() {
        used = amount;
    }

    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        //if ( used > 0 && info.type == DamageInfo.DamageType.NORMAL) {
        if (info.type == DamageInfo.DamageType.NORMAL) {
            info.type = DamageInfo.DamageType.HP_LOSS;
            used--;
            return info.base;
        }
        return damageAmount;
    }*/

    public void updateDescription() {
            this.description = DESCRIPTIONS[0];
    }
}
