package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.HolographicVisage;

import static theartifex.TheArtifexMod.makeID;

public class HolographicVisageRelic extends AbstractCyberneticRelic {
    private static final String NAME = HolographicVisageRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private static final String card = makeID(HolographicVisage.class.getSimpleName());
    private static final int cost = HolographicVisage.creditCost;
    private static boolean haveAttacked;
    private static final int BASE_AMOUNT = 2;
    private int amount;

    public HolographicVisageRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
        haveAttacked = false;
        amount = BASE_AMOUNT;
    }

    @Override
    public void atBattleStartPreDraw() {
        amount = BASE_AMOUNT;
    }

    @Override
    public void atTurnStart() {
        haveAttacked = false;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            haveAttacked = true;
        }
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractCreature owner = AbstractDungeon.player;
        if (!haveAttacked && this.amount > 0) {
            flash();
            addToTop(new ApplyPowerAction(owner, owner, new BufferPower(owner, 1)));
            this.amount--;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
