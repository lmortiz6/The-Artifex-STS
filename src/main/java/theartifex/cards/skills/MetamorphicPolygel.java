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
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            0
    );

    public MetamorphicPolygel() {
        super(ID, info);
        setSelfRetain(false, true);
        this.setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MetamorphicPolygelAction(p, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MetamorphicPolygel();
    }
}
