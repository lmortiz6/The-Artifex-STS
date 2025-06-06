package theartifex.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.actions.GiantHandsAction;
import theartifex.cards.powers.GiantHands;

import static theartifex.TheArtifexMod.makeID;

public class GiantHandsRelic extends AbstractCyberneticRelic {
    private static final String NAME = GiantHandsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final String card = makeID(GiantHands.class.getSimpleName());
    private static final int cost = GiantHands.creditCost;
    private static int drawnCards;

    public GiantHandsRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    /*@Override
    public void atTurnStart() {
        drawnCards = Math.min(AbstractDungeon.player.gameHandSize, 10 - AbstractDungeon.player.hand.size());
    }

    @Override
    public void onDrawOrDiscard() {
        drawnCards--;
        if (drawnCards == 0) {
            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK && c.costForTurn > 0) {
                    group.addToBottom(c);
                }
            }
            if (!group.isEmpty()) {
                flash();
                group.getRandomCard(AbstractDungeon.cardRandomRng).costForTurn -= 1;
            }
            group.clear();
        }
    }*/

    @Override
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        addToBot(new GiantHandsAction(1, this));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
