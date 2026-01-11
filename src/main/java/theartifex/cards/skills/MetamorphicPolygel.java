package theartifex.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.MetamorphicPolygelAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

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
        this.tags.add(CustomCardTags.THEARTIFEXCANNOTPOLYGEL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.tags.add(CustomCardTags.THEARTIFEXTRANSFORMONPLAY);
        addToBot(new MetamorphicPolygelAction(p, this));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MetamorphicPolygel();
    }
}
