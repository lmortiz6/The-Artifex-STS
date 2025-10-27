package theartifex.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.util.CustomAttackEffect;

import static theartifex.TheArtifexMod.makeID;

public class HighExplosiveGrenadePower extends BasePower {

    public static final String POWER_ID = makeID(HighExplosiveGrenadePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private final int damage;
    private static int bombIdOffset;

    public HighExplosiveGrenadePower(AbstractCreature owner, AbstractCreature source, int amount, int magicNumber){
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
            if (this.amount == 1)
                addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, CustomAttackEffect.EXPLOSIVE));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], (this.damage));
        } else {
            this.description = String.format(DESCRIPTIONS[0], (this.amount), (this.damage));
        }
    }
}
