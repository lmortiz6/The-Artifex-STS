package theartifex.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.MetamorphicPolygelAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class MetamorphicPolygel extends BaseCard {

    public static final String ID = makeID(MetamorphicPolygel.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0
    );

    public MetamorphicPolygel() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setSelfRetain(false, true);
        this.setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MetamorphicPolygelAction(p, 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MetamorphicPolygel();
    }
}
