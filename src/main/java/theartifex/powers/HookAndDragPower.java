package theartifex.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static theartifex.TheArtifexMod.makeID;

public class HookAndDragPower extends BasePower implements InvisiblePower{

    public static final String POWER_ID = makeID(HookAndDragPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HookAndDragPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        addToBot(new ApplyPowerAction(owner, source, new HookedPower(owner, source, amount)));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        addToBot(new RemoveSpecificPowerAction(this.source, this.source, POWER_ID));
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        flash();
        addToBot(new LoseHPAction(this.owner, this.source, this.amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
