package theartifex.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.IronBucklerAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class IronBuckler extends BaseCard {

    public static final String ID = makeID(IronBuckler.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 1;
    private static final int COUNT = 2;
    private static final int UPG_COUNT = 1;

    public IronBuckler() {
        super(ID, info);

        setBlock(BLOCK);
        setMagic(COUNT, UPG_COUNT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IronBucklerAction(p, p, this.magicNumber, false, this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new IronBuckler();
    }
}
