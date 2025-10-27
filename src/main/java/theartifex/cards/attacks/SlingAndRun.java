package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractGun;
import theartifex.cards.skills.Sprint;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomAttackEffect;
import theartifex.util.CustomCardTags;

public class SlingAndRun extends AbstractGun {

    public static final String ID = makeID(SlingAndRun.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;
    private static final int BUFF = 1;
    //private static final int UPG_BUFF = 1;
    private final AbstractCard cardToMake;

    public SlingAndRun() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(BUFF);
        tags.add(CustomCardTags.THEARTIFEXGUN);
        this.cardsToPreview = new Sprint();
        this.cardToMake = new Sprint();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isMultiDamage)
            addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, CustomAttackEffect.ISSACHAR_RIFLE));
        else
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), CustomAttackEffect.ISSACHAR_RIFLE));
        //addToBot(new SlingAndRunAction(this.magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(cardToMake, 1, true, true, false));
    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.cardToMake.upgrade();
        this.cardsToPreview = this.cardToMake;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SlingAndRun();
    }
}
