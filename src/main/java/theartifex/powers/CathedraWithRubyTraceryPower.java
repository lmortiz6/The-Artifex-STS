package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import theartifex.actions.TransformCardInHandAction;
import theartifex.cards.attacks.PyrokinesisField;

import static theartifex.TheArtifexMod.makeID;

public class CathedraWithRubyTraceryPower extends BasePower{

    public static final String POWER_ID = makeID(CathedraWithRubyTraceryPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CathedraWithRubyTraceryPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        addToBot(new MakeTempCardInDrawPileAction(new PyrokinesisField(), amount, true, true));
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        addToBot(new MakeTempCardInDrawPileAction(new PyrokinesisField(), stackAmount, true, true));
    }

    public void atStartOfTurn() {
        addToBot(new GainEnergyAction(amount));
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new TransformCardInHandAction(amount, new Burn()));
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DESCRIPTIONS[0], (this.amount)));
        for (int i = 0; i < amount; i++)
            sb.append("[E] ");
        sb.append(LocalizedStrings.PERIOD);
        this.description = sb.toString();
    }
}
