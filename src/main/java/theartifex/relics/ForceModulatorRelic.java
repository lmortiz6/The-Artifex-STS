package theartifex.relics;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.ForceModulator;

import static theartifex.TheArtifexMod.makeID;

public class ForceModulatorRelic extends AbstractCyberneticRelic {
    private static final String NAME = ForceModulatorRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;
    private static final String card = makeID(ForceModulator.class.getSimpleName());
    private static final int cost = ForceModulator.creditCost;

    private int used = 1;

    public ForceModulatorRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public void atTurnStart() {
        used = 1;
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if ( used > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            info.type = DamageInfo.DamageType.HP_LOSS;
            used--;
            return info.base;
        }
        return damageAmount;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
