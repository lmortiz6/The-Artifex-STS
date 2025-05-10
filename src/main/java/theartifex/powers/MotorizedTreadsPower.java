package theartifex.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import static theartifex.TheArtifexMod.makeID;

public class MotorizedTreadsPower extends BasePower implements InvisiblePower {

    public static final String POWER_ID = makeID(MotorizedTreadsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static int drawnCards;

    public MotorizedTreadsPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
        addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, 3*amount)));
    }

    public void atStartOfTurn() {
        drawnCards = AbstractDungeon.player.gameHandSize;
    }

    public void onCardDraw(AbstractCard card) {
        drawnCards--;
        if (drawnCards == 0) {
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, 3*amount)));
            addToBot(new ApplyPowerAction(owner, owner, new NoDrawPower(owner)));
        }
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount*3});
    }
}
