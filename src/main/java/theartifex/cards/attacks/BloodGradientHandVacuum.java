package theartifex.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractGun;
import theartifex.actions.BloodGradientHandVacuumAction;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class BloodGradientHandVacuum extends AbstractGun {

    public static final String ID = makeID(BloodGradientHandVacuum.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;

    public BloodGradientHandVacuum() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setExhaust(true);

        tags.add(CardTags.HEALING);
        tags.add(CustomCardTags.THEARTIFEXGUN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isMultiDamage) {
            addToBot(new BloodGradientHandVacuumAction(m, p, this.multiDamage, this.damageTypeForTurn));
        } else {
            addToBot(new BloodGradientHandVacuumAction(m, p, damage, this.damageTypeForTurn));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BloodGradientHandVacuum();
    }
}
