package theartifex.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCreditRelic;

import static theartifex.TheArtifexMod.makeID;

public class StartingCredits extends AbstractCreditRelic {
    private static final String NAME = StartingCredits.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.STARTER;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final int AMOUNT = 3;

    public StartingCredits() {
        super(ID, NAME, RARITY, SOUND, AMOUNT);
    }

    public void onChestOpen(boolean bossChest) {
        if (!bossChest && AbstractDungeon.relicRng.random(0, 3) == 0) {
            AbstractDungeon.getCurrRoom().addRelicToRewards(new RareCreditRelic());
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
