package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractGun;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomAttackEffect;
import theartifex.util.CustomCardTags;

public class IssacharRifle extends AbstractGun {

    public static final String ID = makeID(IssacharRifle.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;

    public IssacharRifle() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CustomCardTags.THEARTIFEXGUN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isMultiDamage)
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, CustomAttackEffect.ISSACHAR_RIFLE));
        else
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), CustomAttackEffect.ISSACHAR_RIFLE));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new IssacharRifle();
    }
}
