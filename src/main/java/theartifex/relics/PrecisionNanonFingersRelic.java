package theartifex.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static theartifex.TheArtifexMod.makeID;

public class PrecisionNanonFingersRelic extends BaseRelic implements OnApplyPowerRelic {

    private static final String NAME = PrecisionNanonFingersRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;

    public PrecisionNanonFingersRelic() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public boolean onApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        return true;
    }

    @Override
    public int onApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (target instanceof AbstractMonster && source instanceof AbstractPlayer && power.type == AbstractPower.PowerType.DEBUFF) {
            flash();
            if (power.amount > 0) {
                power.amount += 1;
                power.updateDescription();
                return stackAmount + 1;
            } else {
                power.amount -= 1;
                power.updateDescription();
                return stackAmount - 1;
            }
        } else {
            return stackAmount;
        }
    }
}
