package theartifex.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.StabilizerArmLocks;

import static theartifex.TheArtifexMod.makeID;

public class StabilizerArmLocksRelic extends AbstractCyberneticRelic {
    private static final String NAME = StabilizerArmLocksRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;
    private static final String card = makeID(StabilizerArmLocks.class.getSimpleName());
    private static final int cost = StabilizerArmLocks.creditCost;
    public int amount;

    public StabilizerArmLocksRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
        amount = 0;
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        amount = 0;
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r.relicId.equals(ID))
                amount++;
        }
    }

    @Override
    public String getUpdatedDescription() {

        return DESCRIPTIONS[0];
    }
}
