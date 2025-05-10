package theartifex.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static theartifex.TheArtifexMod.makeID;

public class TimeDilationGrenadePower extends BasePower{

    public static final String POWER_ID = makeID(TimeDilationGrenadePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private final int magicNumber;
    private static int bombIdOffset;

    public TimeDilationGrenadePower(AbstractCreature owner, AbstractCreature source, int amount, int magicNumber){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.ID = POWER_ID + bombIdOffset;
        bombIdOffset++;
        this.magicNumber = magicNumber;
        this.amount = amount;
        this.updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1)
                addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawCardNextTurnPower(AbstractDungeon.player, magicNumber)));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[] { Integer.valueOf(this.magicNumber) });
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount), Integer.valueOf(this.magicNumber) });
        }
    }
}
