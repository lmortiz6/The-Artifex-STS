package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theartifex.util.CustomCardTags;

import static theartifex.TheArtifexMod.makeID;

public class AkimboPower extends BasePower{

    public static final String POWER_ID = makeID(AkimboPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static boolean used;

    public AkimboPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        used = false;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        used = false;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!used && card.hasTag(CustomCardTags.THEARTIFEXGUN)) {
            addToBot(new DrawCardAction(this.amount));
            flash();
            used = true;
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = String.format(DESCRIPTIONS[0], (this.amount));
        }
    }
}
