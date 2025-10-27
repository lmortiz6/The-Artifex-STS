package theartifex.relics;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.InflatableAxons;
import theartifex.powers.NextTurnDrawPower;
import theartifex.powers.NextTurnDrawReductionPower;

import static theartifex.TheArtifexMod.makeID;

public class InflatableAxonsRelic extends AbstractCyberneticRelic {
    private static final String NAME = InflatableAxonsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private boolean justApplied = true;
    private static final String card = makeID(InflatableAxons.class.getSimpleName());
    private static final int cost = InflatableAxons.creditCost;

    public InflatableAxonsRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.player.gameHandSize += 1;

        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            int maxDraw = Math.min(AbstractDungeon.player.gameHandSize, AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size());
            TheArtifexMod.cardsDrawnAtTurnStart = Math.min(maxDraw, 10 - AbstractDungeon.player.hand.size());
        }
    }

    @Override
    public void atBattleStart() {
        justApplied = true;
        AbstractCreature owner = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawReductionPower(owner, owner, 1)));
    }

    @Override
    public void onPlayerEndTurn() {
        if (justApplied)
            AbstractDungeon.player.gameHandSize -= 1;
        justApplied = false;
    }

    @Override
    public void atTurnStart() {
        AbstractCreature owner = AbstractDungeon.player;
        if ((GameActionManager.turn + 1) % 2 == 1) {
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawReductionPower(owner, owner, 1)));
        } else if (!justApplied){
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, 1)));
        }
    }

    @Override
    public String getUpdatedDescription() {

        return DESCRIPTIONS[0];
    }
}
