package theartifex.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theartifex.util.CustomCardTags;

import static theartifex.TheArtifexMod.makeID;

public class MedassistModulePower extends BasePower{

    public static final String POWER_ID = makeID(MedassistModulePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static int totalAmount;

    public MedassistModulePower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        totalAmount += amount;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        amount = totalAmount;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (amount > 0 && card.hasTag(CustomCardTags.THEARTIFEXINJECTOR)) {
            amount--;
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
