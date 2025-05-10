package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import static theartifex.TheArtifexMod.makeID;

public class RubbergumPower extends BasePower{

    public static final String POWER_ID = makeID(RubbergumPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public RubbergumPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            flash();
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NextTurnBlockPower(AbstractDungeon.player, this.amount), this.amount));
        }
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount) });
    }
}
