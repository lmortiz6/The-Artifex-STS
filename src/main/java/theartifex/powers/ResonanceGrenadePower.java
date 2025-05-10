package theartifex.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static theartifex.TheArtifexMod.makeID;

public class ResonanceGrenadePower extends BasePower{

    public static final String POWER_ID = makeID(ResonanceGrenadePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private final int damage;
    private final int vuln;
    private static int bombIdOffset;

    public ResonanceGrenadePower(AbstractCreature owner, AbstractCreature source, int amount, int magicNumber, int magicNumber2){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.ID = POWER_ID + bombIdOffset;
        bombIdOffset++;
        damage = magicNumber;
        vuln = magicNumber2;
        this.amount = amount;
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1) {
                addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
                for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        addToBot(new ApplyPowerAction(monster, source, new VulnerablePower(monster, this.vuln, false), this.vuln));
                    }
                }
            }
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[] { Integer.valueOf(this.damage), Integer.valueOf(this.vuln) });
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount), Integer.valueOf(this.damage), Integer.valueOf(this.vuln) });
        }
    }
}
