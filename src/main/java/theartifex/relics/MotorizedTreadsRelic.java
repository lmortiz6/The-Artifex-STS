package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
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


    public MotorizedTreadsRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
        DRAW = MotorizedTreads.BUFF;
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.player.gameHandSize += DRAW;
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
