package theartifex.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theartifex.util.CustomAttackEffect;

import static theartifex.TheArtifexMod.makeID;

public class StunGasGrenadePower extends BasePower{

    public static final String POWER_ID = makeID(StunGasGrenadePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private static int bombIdOffset;
    private int weak;

    public StunGasGrenadePower(AbstractCreature owner, AbstractCreature source, int amount, int damage, int weak){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.ID = POWER_ID + bombIdOffset;
        bombIdOffset++;
        this.amount = amount;
        this.amount2 = damage;
        this.weak = weak;
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1){
                addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount2, true), DamageInfo.DamageType.THORNS, CustomAttackEffect.GAS_EXPLOSIVE));
                for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        addToBot(new ApplyPowerAction(monster, source, new WeakPower(monster, this.weak, false), this.weak));
                    }
                }
            }
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[] { Integer.valueOf(this.amount2), Integer.valueOf(this.weak) });
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount), Integer.valueOf(this.amount2), Integer.valueOf(this.weak) });
        }
    }
}
