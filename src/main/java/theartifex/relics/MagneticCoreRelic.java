package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.MagneticCore;
import theartifex.powers.MagneticCorePower;
import theartifex.powers.PulsedPower;

import static theartifex.TheArtifexMod.makeID;

public class MagneticCoreRelic extends AbstractCyberneticRelic {
    private static final String NAME = MagneticCoreRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY;
    private static final String card = makeID(MagneticCore.class.getSimpleName());
    private static final int cost = MagneticCore.creditCost;

    public MagneticCoreRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public void atTurnStart() {
        MagneticCorePower.sounded = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.type == AbstractCard.CardType.POWER) {
            flash();
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (!m.isDead && !m.isDying)
                    addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new PulsedPower(m, AbstractDungeon.player, 1)));
            if (!MagneticCorePower.sounded) {
                CardCrawlGame.sound.playV(makeID("EMP"), 1.4f);
                MagneticCorePower.sounded = true;
            }
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        MagneticCorePower.sounded = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
