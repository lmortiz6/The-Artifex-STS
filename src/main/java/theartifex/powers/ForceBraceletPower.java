package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static theartifex.TheArtifexMod.makeID;

public class ForceBraceletPower extends BasePower{

    public static final String POWER_ID = makeID(ForceBraceletPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ForceBraceletPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if (EnergyPanel.getCurrentEnergy() <= 0) {
            flash();
            if (owner.hasPower("Metallicize")) {
                flash();
                owner.getPower("Metallicize").reducePower(amount);
                if (owner.getPower("Metallicize").amount <= 0) {
                    addToTop(new RemoveSpecificPowerAction(owner, owner, owner.getPower("Metallicize")));
                    addToTop(new RemoveSpecificPowerAction(owner, owner, this));
                }
                owner.getPower("Metallicize").updateDescription();
            }
        }
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        sb.append("[E] ");
        sb.append(DESCRIPTIONS[1]);
        this.description = String.format(sb.toString(), this.amount);
    }
}
