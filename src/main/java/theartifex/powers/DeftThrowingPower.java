package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import static theartifex.TheArtifexMod.makeID;

public class DeftThrowingPower extends BasePower{

    public static final String POWER_ID = makeID(DeftThrowingPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public DeftThrowingPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
    }

    public void atStartOfTurn() {
        int count = 0;
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p.ID.contains("Grenade") || p.ID.contains("HandENuke")) {
                count++;
            }
        }
        if (count > 0) {
            flash();
            AbstractCreature p = AbstractDungeon.player;
            for (int i = 0; i < count; i++) {
                addToBot(new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new DexterityPower((AbstractCreature) p, amount), amount));
                addToBot(new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new LoseDexterityPower((AbstractCreature) p, amount), amount));
            }
        }
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[] { Integer.valueOf(this.amount) });
    }
}
