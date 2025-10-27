package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static theartifex.TheArtifexMod.makeID;

public class BiodynamicPowerPlantPower extends BasePower{

    public static final String POWER_ID = makeID(BiodynamicPowerPlantPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public BiodynamicPowerPlantPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
    }

    @Override
    public int onHeal(int healAmount) {
        flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EnergizedPower(AbstractDungeon.player, amount)));
        return super.onHeal(healAmount);
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        for (int i = 0; i < amount; i++)
            sb.append("[E] ");
        sb.append(DESCRIPTIONS[1]);
        this.description = sb.toString();
    }
}
