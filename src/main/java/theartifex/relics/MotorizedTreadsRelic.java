package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.MotorizedTreads;

import static theartifex.TheArtifexMod.makeID;

public class MotorizedTreadsRelic extends AbstractCyberneticRelic {
    private static final String NAME = MotorizedTreadsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY;
    private static final String card = makeID(MotorizedTreads.class.getSimpleName());
    private static final int cost = MotorizedTreads.creditCost;
    private final int DRAW;
    //private int drawnCards;

    public MotorizedTreadsRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
        DRAW = MotorizedTreads.BUFF;
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.player.gameHandSize += DRAW;

        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            int maxDraw = Math.min(AbstractDungeon.player.gameHandSize, AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size());
            TheArtifexMod.cardsDrawnAtTurnStart = Math.min(maxDraw, 10 - AbstractDungeon.player.hand.size());
        }
    }

    @Override
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        AbstractCreature owner = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(owner, owner, new NoDrawPower(owner)));
    }

    @Override
    public void onVictory() {
        AbstractDungeon.player.gameHandSize -= DRAW;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
