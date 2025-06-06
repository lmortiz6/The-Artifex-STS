package theartifex.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.MedassistModule;
import theartifex.util.CustomCardTags;

import static theartifex.TheArtifexMod.makeID;

public class MedassistModuleRelic extends AbstractCyberneticRelic {
    private static final String NAME = MedassistModuleRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final String card = makeID(MedassistModule.class.getSimpleName());
    private static final int cost = MedassistModule.creditCost;
    public int amount;
    private int amountOwned;

    public MedassistModuleRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
        amount = 1;
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        amountOwned = 0;
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r.relicId.equals(ID))
                amountOwned++;
        }
    }

    @Override
    public void atTurnStart() {
        amount = amountOwned;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (amount > 0 && c.hasTag(CustomCardTags.INJECTOR)) {
            amount--;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
