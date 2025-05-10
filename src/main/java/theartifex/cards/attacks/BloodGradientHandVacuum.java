package theartifex.cards.attacks;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;

    public BloodGradientHandVacuum() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setExhaust(true);

        tags.add(CardTags.HEALING);
        tags.add(CustomCardTags.GUN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL);
        addToBot(new BloodGradientHandVacuumAction(m, info));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BloodGradientHandVacuum();
    }
}
