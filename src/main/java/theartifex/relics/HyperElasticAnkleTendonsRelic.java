package theartifex.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.HyperElasticAnkleTendons;

import static theartifex.TheArtifexMod.makeID;

public class HyperElasticAnkleTendonsRelic extends AbstractCyberneticRelic {
    private static final String NAME = HyperElasticAnkleTendonsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final String card = makeID(HyperElasticAnkleTendons.class.getSimpleName());
    private static final int cost = HyperElasticAnkleTendons.creditCost;

    public HyperElasticAnkleTendonsRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
