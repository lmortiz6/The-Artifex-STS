package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theartifex.TheArtifexMod.makeID;

public class MagneticCorePower extends BasePower {

    public static final String POWER_ID = makeID(MagneticCorePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public static boolean sounded = false;

    public MagneticCorePower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        sounded = false;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.POWER && this.amount > 0) {
            flash();
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (!m.isDead && !m.isDying)
                    addToBot(new ApplyPowerAction(m, owner, new PulsedPower(m, owner, this.amount)));
            if (!sounded) {
                CardCrawlGame.sound.playV(makeID("EMP"), 1.4f);
                sounded = true;
            }
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        sounded = false;
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
