package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

import static theartifex.TheArtifexMod.makeID;

public class HolographicVisagePower extends BasePower {

    public static final String POWER_ID = makeID(HolographicVisagePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static boolean haveAttacked;

    public HolographicVisagePower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
        haveAttacked = false;
    }

    public void atStartOfTurn() {
        haveAttacked = false;
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            haveAttacked = true;
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!haveAttacked) {
            addToTop(new ApplyPowerAction(owner, owner, new BufferPower(owner, 1)));
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[]{this.amount, this.amount});
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount, this.amount});
        }
    }
}
