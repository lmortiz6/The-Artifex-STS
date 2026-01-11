package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.actions.TransformCardInHandAction;
import theartifex.cards.attacks.PyrokinesisField;
import theartifex.relics.CathedraWithRubyTraceryRelic;

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
        AbstractDungeon.player.gameHandSize += amount;
        addToBot(new MakeTempCardInDrawPileAction(new PyrokinesisField(), amount, true, true));
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        AbstractDungeon.player.gameHandSize += stackAmount;
        addToBot(new MakeTempCardInDrawPileAction(new PyrokinesisField(), stackAmount, true, true));
    }

    public void atStartOfTurn() {
        CathedraWithRubyTraceryRelic.transformed = false;
    addToBot(new GainEnergyAction(amount));
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!CathedraWithRubyTraceryRelic.transformed) {
            int installed = 0;
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof CathedraWithRubyTraceryRelic) {
                    installed++;
                }
            }
            addToBot(new TransformCardInHandAction(amount + installed, new Burn()));
            CathedraWithRubyTraceryRelic.transformed = true;
        }
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DESCRIPTIONS[0], (this.amount)));
        for (int i = 0; i < amount; i++)
            sb.append("[E] ");
        sb.append(String.format(DESCRIPTIONS[1], (this.amount)));
        this.description = sb.toString();
    }
}
