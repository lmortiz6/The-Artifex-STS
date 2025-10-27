package theartifex.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theartifex.actions.ChangeNonManualDiscardAction;

import static theartifex.TheArtifexMod.makeID;

public class SwiftReflexesPower extends BasePower{

    public static final String POWER_ID = makeID(SwiftReflexesPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public int used = 0;

    public SwiftReflexesPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        addToBot(new ChangeNonManualDiscardAction(false));
    }

    @Override
    public void atStartOfTurn() {
        this.used = 0;
        addToBot(new ChangeNonManualDiscardAction(false));
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[] { Integer.valueOf(this.amount) });
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount)});
        }
    }
}
