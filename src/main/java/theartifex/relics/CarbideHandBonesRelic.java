package theartifex.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.CarbideHandBones;

import static theartifex.TheArtifexMod.makeID;

public class CarbideHandBonesRelic extends AbstractCyberneticRelic {
    private static final String NAME = CarbideHandBonesRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;
    private static final String card = makeID(CarbideHandBones.class.getSimpleName());
    private static final int cost = CarbideHandBones.creditCost;

    public CarbideHandBonesRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
