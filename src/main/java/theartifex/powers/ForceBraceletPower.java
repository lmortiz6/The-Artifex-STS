package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static theartifex.TheArtifexMod.makeID;

public class ForceBraceletPower extends BasePower{

    public static final String POWER_ID = makeID(ForceBraceletPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public ForceBraceletPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
        if (AbstractDungeon.player.energy.energy <= 0) {
            flash();
            addToBot(new LoseBlockAction(owner, owner, amount));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        System.out.println(EnergyPanel.getCurrentEnergy());
        if (EnergyPanel.getCurrentEnergy() <= 0) {
            flash();
            addToBot(new LoseBlockAction(owner, owner, amount));
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount) });
    }
}
