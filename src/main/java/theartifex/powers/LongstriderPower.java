package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import theartifex.cards.skills.Sprint;

import static theartifex.TheArtifexMod.makeID;

public class LongstriderPower extends BasePower{

    public static final String POWER_ID = makeID(LongstriderPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private final boolean upgraded;

    public LongstriderPower(AbstractCreature owner, AbstractCreature source, int amount, boolean upgraded){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.ID = upgraded ? POWER_ID : POWER_ID + "UP";
        this.amount = amount;
        this.updateDescription();
        this.upgraded = upgraded;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractCard c = new Sprint();
        if (upgraded)
            c.upgrade();
        addToBot(new MakeTempCardInDrawPileAction(c, this.amount, true, true));
    }

    public void updateDescription() {
        if (upgraded) {
            if (this.amount == 1) {
                this.description = String.format(DESCRIPTIONS[3], new Object[]{this.amount, this.amount});
            } else {
                this.description = String.format(DESCRIPTIONS[2], new Object[]{this.amount, this.amount});
            }
        } else {
            if (this.amount == 1) {
                this.description = String.format(DESCRIPTIONS[1], new Object[]{this.amount, this.amount});
            } else {
                this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount, this.amount});
            }
        }
    }
}
