package theartifex.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCreditRelic;

import static theartifex.TheArtifexMod.makeID;

public class CommonCreditRelic extends AbstractCreditRelic {
    private static final String NAME = CommonCreditRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.COMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final int AMOUNT = 2;

    public CommonCreditRelic() {
        super(ID, NAME, RARITY, SOUND, AMOUNT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
