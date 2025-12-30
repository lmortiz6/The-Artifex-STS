package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.cards.attacks.ForceKnife;

import static theartifex.TheArtifexMod.makeID;

public class PrecisionForceLathePower extends BasePower{

    public static final String POWER_ID = makeID(PrecisionForceLathePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PrecisionForceLathePower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            AbstractCard card = new ForceKnife();
            card.upgrade();
            addToBot(new MakeTempCardInHandAction(card, this.amount, false));
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
