package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.TransformHandAction;
import theartifex.cards.BaseCard;
import theartifex.cards.status.Shocked;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.TinkerPower;
import theartifex.util.CardStats;

public class ReverseEngineer extends BaseCard {

    public static final String ID = makeID(ReverseEngineer.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BUFF = 2;
    private static final int UPG_BUFF = 1;

    public ReverseEngineer() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(BUFF, UPG_BUFF);
        setExhaust(true);
        this.cardsToPreview = new Shocked();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TinkerPower(p, p, magicNumber)));
        addToBot(new TransformHandAction(this.cardsToPreview.makeCopy()));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ReverseEngineer();
    }
}
