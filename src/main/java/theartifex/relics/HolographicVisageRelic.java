package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.HolographicVisage;

import static theartifex.TheArtifexMod.makeID;

public class HolographicVisageRelic extends AbstractCyberneticRelic {
    private static final String NAME = HolographicVisageRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private static final String card = makeID(HolographicVisage.class.getSimpleName());
    private static final int cost = HolographicVisage.creditCost;
    private static final int BASE_AMOUNT = 3;

    public HolographicVisageRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
        counter = BASE_AMOUNT;
    }

    @Override
    public void atBattleStartPreDraw() {
        counter = BASE_AMOUNT;
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractCreature owner = AbstractDungeon.player;
        if (!TheArtifexMod.haveAttacked && this.counter > 0) {
            flash();
            addToTop(new ApplyPowerAction(owner, owner, new BufferPower(owner, 1)));
            this.counter--;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
