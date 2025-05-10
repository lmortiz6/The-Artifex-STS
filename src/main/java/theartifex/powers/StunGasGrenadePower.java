package theartifex.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theartifex.TheArtifexMod.makeID;

public class StunGasGrenadePower extends BasePower{

    public static final String POWER_ID = makeID(StunGasGrenadePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private static int bombIdOffset;

    public StunGasGrenadePower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.ID = POWER_ID + bombIdOffset;
        bombIdOffset++;
        this.amount = amount;
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1){
                for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        addToBot(new StunMonsterAction(monster, this.source));
                    }
                }
            }
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount) });
        }
    }
}
