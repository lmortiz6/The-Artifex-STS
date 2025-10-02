package theartifex.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCreditRelic;
import theartifex.abstracts.AbstractCyberneticRelic;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class EatersCrestRelic extends AbstractCreditRelic {

    private static final String NAME = EatersCrestRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private static final int AMOUNT = 3;

    private boolean active = false;
    private final ArrayList<AbstractRelic> possibleRelics = new ArrayList<>();

    public EatersCrestRelic() {
        super(ID, NAME, RARITY, SOUND, AMOUNT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        active = true;

        for (AbstractRelic r : RelicLibrary.specialList) {
            if (r instanceof AbstractCyberneticRelic && ((AbstractCyberneticRelic)r).getCost() >= 3 && ((AbstractCyberneticRelic)r).getCost() <= TheArtifexMod.getAvailableCredits()) {
                possibleRelics.add(r);
            }
        }
    }

    @Override
    public void update() {
        super.update();
        if (active && !AbstractDungeon.isScreenUp) {
            if (!possibleRelics.isEmpty()) {
                TheArtifexMod.logger.info(possibleRelics.toString());
                int i = AbstractDungeon.relicRng.random(possibleRelics.size() - 1);
                AbstractDungeon.combatRewardScreen.open();
                AbstractDungeon.combatRewardScreen.rewards.clear();
                AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(RelicLibrary.getRelic(possibleRelics.get(i).relicId).makeCopy()));
                AbstractDungeon.combatRewardScreen.positionRewards();
                AbstractDungeon.overlayMenu.proceedButton.setLabel("Skip Reward");
                active = false;
                (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
            }
            active = false;
        }
    }
}
