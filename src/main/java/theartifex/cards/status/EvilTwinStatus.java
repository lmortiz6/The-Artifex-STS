package theartifex.cards.status;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.util.CardStats;

public class EvilTwinStatus extends BaseCard {

    public static final String ID = makeID(EvilTwinStatus.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.STATUS,
            CardRarity.COMMON,
            CardTarget.NONE,
            -2
    );
    private static final int DAMAGE = 3;

    public EvilTwinStatus() {
        super(ID, info);
        setMagic(DAMAGE);
        //setEthereal(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.type == CardType.ATTACK) {
            addToTop(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }

    @Override
    public void upgrade() {}

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new EvilTwinStatus();
    }
}
