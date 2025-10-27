package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.attacks.PyrokinesisField;
import theartifex.cards.powers.CathedraWithRubyTracery;

import static theartifex.TheArtifexMod.makeID;

public class CathedraWithRubyTraceryRelic extends AbstractCyberneticRelic {
    private static final String NAME = CathedraWithRubyTraceryRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY;
    private static final String card = makeID(CathedraWithRubyTracery.class.getSimpleName());
    private static final int cost = CathedraWithRubyTracery.creditCost;

    public CathedraWithRubyTraceryRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public void atBattleStartPreDraw() {
        addToBot(new MakeTempCardInDrawPileAction(new PyrokinesisField(), 1, true, true));
    }

    @Override
    public void atTurnStart() {
        addToBot(new GainEnergyAction(1));
    }

    @Override
    public String getUpdatedDescription() {
        String sb = DESCRIPTIONS[0] +
                "[E] " +
                DESCRIPTIONS[1];
        this.description = sb;
        return description;
    }
}
