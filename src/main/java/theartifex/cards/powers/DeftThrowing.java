package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.DeftThrowingPower;
import theartifex.util.CardStats;

public class DeftThrowing extends BaseCard {

    public static final String ID = makeID(DeftThrowing.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int DEX = 1;
    private static final int UPG_DEX = 1;

    public DeftThrowing() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DEX, UPG_DEX);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DeftThrowingPower(p, p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DeftThrowing();
    }
}
