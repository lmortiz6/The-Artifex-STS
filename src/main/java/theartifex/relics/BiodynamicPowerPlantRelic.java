package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.BiodynamicPowerPlant;

import static theartifex.TheArtifexMod.makeID;

public class BiodynamicPowerPlantRelic extends AbstractCyberneticRelic {
    private static final String NAME = BiodynamicPowerPlantRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;
    private static final String card = makeID(BiodynamicPowerPlant.class.getSimpleName());
    private static final int cost = BiodynamicPowerPlant.creditCost;

    public BiodynamicPowerPlantRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EnergizedPower(AbstractDungeon.player, 1)));
        return super.onPlayerHeal(healAmount);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
