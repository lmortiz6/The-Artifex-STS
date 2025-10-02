package theartifex.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCreditRelic;

import static theartifex.TheArtifexMod.makeID;

public class StartingCredits extends AbstractCreditRelic {
    private static final String NAME = StartingCredits.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.STARTER;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final int AMOUNT = 4;

    public StartingCredits() {
        super(ID, NAME, RARITY, SOUND, AMOUNT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
