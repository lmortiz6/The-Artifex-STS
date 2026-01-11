package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomAttackEffect;

public class Shank extends BaseCard {

    public static final String ID = makeID(Shank.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 1;
    private static final int SCALING = 2;
    private static final int UPG_SCALING = 2;

    public Shank() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(SCALING, UPG_SCALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), CustomAttackEffect.SHANK));
    }

    public void calculateCardDamage(AbstractMonster m) {
        int debuffs = 0;
        for (AbstractPower power : m.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                debuffs += 1;
            }
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage += magicNumber * debuffs;
        super.calculateCardDamage(m);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Shank();
    }
}
