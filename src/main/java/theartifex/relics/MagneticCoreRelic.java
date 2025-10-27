package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.actions.MagneticCoreAction;
import theartifex.cards.powers.MagneticCore;
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
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.type == AbstractCard.CardType.POWER) {
            flash();
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new PulsedPower(m, AbstractDungeon.player, 1)));
            CardCrawlGame.sound.playV(makeID("EMP"), 1.4f); // Sound Effect
        }
    }

    /*@Override
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        addToBot(new MagneticCoreAction(1));
    }*/

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
