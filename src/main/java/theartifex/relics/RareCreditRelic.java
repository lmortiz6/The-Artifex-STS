package theartifex.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCreditRelic;

import static theartifex.TheArtifexMod.makeID;

public class RareCreditRelic extends AbstractCreditRelic {
    private static final String NAME = RareCreditRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final int AMOUNT = 3;

    public RareCreditRelic() {
        super(ID, NAME, RARITY, SOUND, AMOUNT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
