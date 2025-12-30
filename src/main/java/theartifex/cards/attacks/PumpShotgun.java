package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractGun;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomAttackEffect;
import theartifex.util.CustomCardTags;

public class PumpShotgun extends AbstractGun {

    public static final String ID = makeID(PumpShotgun.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;

    public PumpShotgun() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CustomCardTags.THEARTIFEXGUN);
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.hasTag(CustomCardTags.THEARTIFEXBEAMSPLITTER)) {
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, CustomAttackEffect.SHOTGUN));
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, CustomAttackEffect.SHOTGUN));
        }
        else {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), CustomAttackEffect.SHOTGUN));
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, CustomAttackEffect.SHOTGUN));
        }
        addToBot(new DiscardAction(p, p, 1, true));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PumpShotgun();
    }
}
