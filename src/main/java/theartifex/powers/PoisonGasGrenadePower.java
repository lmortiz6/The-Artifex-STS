package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static theartifex.TheArtifexMod.makeID;

public class PoisonGasGrenadePower extends BasePower {

    public static final String POWER_ID = makeID(PoisonGasGrenadePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private final int damage;
    private static int bombIdOffset;

    public PoisonGasGrenadePower(AbstractCreature owner, AbstractCreature source, int amount, int magicNumber){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.ID = POWER_ID + bombIdOffset;
        bombIdOffset++;
        damage = magicNumber;
        this.amount2 = magicNumber;
        this.amount = amount;
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1){
                for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        addToBot(new ApplyPowerAction(monster, source, new PoisonPower(monster, source, this.damage), this.damage));
                    }
                }
            }
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[] { Integer.valueOf(this.damage) });
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount), Integer.valueOf(this.damage) });
        }
    }
}
