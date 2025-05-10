package theartifex.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;

import static theartifex.TheArtifexMod.makeID;

public class WillpowerUpPower extends BasePower{

    public static final String POWER_ID = makeID(WillpowerUpPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public WillpowerUpPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this, 1));
            if (this.amount > 0)
                addToBot(new GainEnergyAction(1));
        }
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        if (this.amount == 1) {
            sb.append(DESCRIPTIONS[1]);
        } else {
            sb.append(String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount) }));
        }
        sb.append("[E] ");
        sb.append(LocalizedStrings.PERIOD);
        this.description = sb.toString();
    }
}
