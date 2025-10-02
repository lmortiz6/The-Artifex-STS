package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.util.CardStats;
import theartifex.util.CustomAttackEffect;

public class ForceKnife extends BaseCard {

    public static final String ID = makeID(ForceKnife.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.COLORLESS, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 5;
    private static final int UPG_COST = 0;

    public ForceKnife() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE);
        setCostUpgrade(UPG_COST);
        setEthereal(true);
        setExhaust(true);
        cardsToPreview = new ForceKnife(false);
    }

    public ForceKnife(boolean preview) {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE);
        setCostUpgrade(UPG_COST);
        setEthereal(true);
        setExhaust(true);
        if (preview) {
            cardsToPreview = new ForceKnife(false);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), CustomAttackEffect.FORCE_KNIFE));
        addToBot(new MakeTempCardInHandAction(new ForceKnife(), 1, false));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ForceKnife();
    }
}
