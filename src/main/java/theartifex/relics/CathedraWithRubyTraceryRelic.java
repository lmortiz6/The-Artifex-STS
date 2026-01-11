package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.actions.TransformCardInHandAction;
import theartifex.cards.attacks.PyrokinesisField;
import theartifex.cards.powers.CathedraWithRubyTracery;
import theartifex.powers.CathedraWithRubyTraceryPower;

import static theartifex.TheArtifexMod.makeID;

public class CathedraWithRubyTraceryRelic extends AbstractCyberneticRelic {
    private static final String NAME = CathedraWithRubyTraceryRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY;
    private static final String card = makeID(CathedraWithRubyTracery.class.getSimpleName());
    private static final int cost = CathedraWithRubyTracery.creditCost;
    public static boolean transformed = false;

    private final int DRAW = 1;

    public CathedraWithRubyTraceryRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.player.gameHandSize += DRAW;
        addToBot(new MakeTempCardInDrawPileAction(new PyrokinesisField(), 1, true, true));
    }

    @Override
    public void atTurnStart() {
        transformed = false;
        addToBot(new GainEnergyAction(1));
    }

    @Override
    public void atTurnStartPostDraw() {
        if (!transformed) {
            int powerAmount = 0;
            if (AbstractDungeon.player.hasPower(makeID(CathedraWithRubyTraceryPower.class.getSimpleName())))
                powerAmount = AbstractDungeon.player.getPower(makeID(CathedraWithRubyTraceryPower.class.getSimpleName())).amount;
            int installed = 0;
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof CathedraWithRubyTraceryRelic) {
                    installed++;
                }
            }
            addToBot(new TransformCardInHandAction(installed + powerAmount, new Burn()));
            transformed = true;
        }
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
