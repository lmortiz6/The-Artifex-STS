package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.FlurryAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Flurry extends BaseCard {

    public static final String ID = makeID(Flurry.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            -1
    );
    private static final int DAMAGE = 5;

    public Flurry() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE); //Sets the card's damage and how much it changes when upgraded.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new FlurryAction(p, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, this.upgraded));
        if (this.upgraded) {
            addToBot((AbstractGameAction)new ExhaustAction(1, false));
        } else {
            addToBot((AbstractGameAction)new ExhaustAction(1, true, false, false));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Flurry();
    }
}
