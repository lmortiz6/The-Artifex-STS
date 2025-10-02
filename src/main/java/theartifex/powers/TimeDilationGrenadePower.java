package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Objects;

import static theartifex.TheArtifexMod.makeID;

public class TimeDilationGrenadePower extends BasePower{

    public static final String POWER_ID = makeID(TimeDilationGrenadePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    //private final int magicNumber;
    private static int bombIdOffset;
    private static int originalHandSize;

    public TimeDilationGrenadePower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.ID = POWER_ID + bombIdOffset;
        bombIdOffset++;
        //this.magicNumber = magicNumber;
        this.amount = amount;
        this.updateDescription();
    }

    public void onInitialApplication() {
        originalHandSize = AbstractDungeon.player.gameHandSize;
        AbstractDungeon.player.gameHandSize += amount;
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target.equals(owner) && Objects.equals(power.ID, POWER_ID)) {
            AbstractDungeon.player.gameHandSize += power.amount;
        }
    }

    /*public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount == 1)
                addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawCardNextTurnPower(AbstractDungeon.player, magicNumber)));
        }
    }*/

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        //addToBot(new DrawCardAction(this.amount));
    }

    public void onRemove() {
        AbstractDungeon.player.gameHandSize -= amount;
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount)});
    }
}
