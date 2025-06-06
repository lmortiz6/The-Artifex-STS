package theartifex.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import theartifex.TheArtifexMod;
import theartifex.actions.DebugLogAction;

import static theartifex.TheArtifexMod.makeID;

public class MotorizedTreadsPower extends BasePower implements InvisiblePower {

    public static final String POWER_ID = makeID(MotorizedTreadsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final int DRAW = 2;
    //private static int drawnCards;

    public MotorizedTreadsPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
        addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, DRAW*amount)));
    }

    /*public void atStartOfTurn() {
        drawnCards = Math.min(AbstractDungeon.player.gameHandSize, 10 - AbstractDungeon.player.hand.size());
    }

    public void onCardDraw(AbstractCard card) {
        drawnCards--;
        if (drawnCards == 0) {
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, DRAW*amount)));
            addToBot(new ApplyPowerAction(owner, owner, new NoDrawPower(owner)));
        }
    }*/

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        //addToBot(new DebugLogAction(POWER_ID));
        addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, DRAW*amount)));
        addToBot(new ApplyPowerAction(owner, owner, new NoDrawPower(owner)));
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount*DRAW});
    }
}
