package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractGun;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomAttackEffect;
import theartifex.util.CustomCardTags;

public class ChromeRevolver extends AbstractGun {

    public static final String ID = makeID(ChromeRevolver.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private static final int DRAW = 1;

    public ChromeRevolver() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DRAW);
        tags.add(CustomCardTags.THEARTIFEXGUN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isMultiDamage)
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, CustomAttackEffect.CHROME_REVOLVER, true));
        else
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), CustomAttackEffect.CHROME_REVOLVER));
        addToBot(new DrawCardAction(p, magicNumber, false));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChromeRevolver();
    }
}
