package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Objects;

import static theartifex.TheArtifexMod.makeID;

public class NextTurnDrawReductionPower extends BasePower {
    public static final String POWER_ID = makeID(NextTurnDrawReductionPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public NextTurnDrawReductionPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.isTurnBased = true;
        updateDescription();
    }

    public void onInitialApplication() {
        AbstractDungeon.player.gameHandSize -= amount;
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target.equals(owner) && Objects.equals(power.ID, POWER_ID)) {
            AbstractDungeon.player.gameHandSize -= power.amount;
        }
    }

    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    public void onRemove() {
        AbstractDungeon.player.gameHandSize += this.amount;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = String.format(DESCRIPTIONS[0], (this.amount));
        }
    }
}
