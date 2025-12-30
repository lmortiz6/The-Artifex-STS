package theartifex.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.actions.MedassistModuleAction;
import theartifex.cards.powers.MedassistModule;

import static theartifex.TheArtifexMod.makeID;

public class MedassistModuleRelic extends AbstractCyberneticRelic {
    private static final String NAME = MedassistModuleRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final String card = makeID(MedassistModule.class.getSimpleName());
    private static final int cost = MedassistModule.creditCost;
    public int amount;

    public MedassistModuleRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
        amount = 1;
    }

    @Override
    public void atTurnStart() {
        addToTop(new MedassistModuleAction(1, true));
    }

    @Override
    public void onPlayerEndTurn() {
        MedassistModuleAction.modded = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
