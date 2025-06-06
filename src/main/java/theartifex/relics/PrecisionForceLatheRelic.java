package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.attacks.ForceKnife;
import theartifex.cards.powers.PrecisionForceLathe;

import static theartifex.TheArtifexMod.makeID;

public class PrecisionForceLatheRelic extends AbstractCyberneticRelic {
    private static final String NAME = PrecisionForceLatheRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private static final String card = makeID(PrecisionForceLathe.class.getSimpleName());
    private static final int cost = PrecisionForceLathe.creditCost;

    public PrecisionForceLatheRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public void atTurnStart() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            AbstractCard card = new ForceKnife();
            card.upgrade();
            addToBot(new MakeTempCardInHandAction(card, 1, false));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
