package theartifex.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.DisassembleAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Disassemble extends BaseCard {

    public static final String ID = makeID(Disassemble.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0
    );
    private static final int BUFF = 1;
    private static final int UPG_BUFF = 1;
    private static final int GOLD = 6;

    public Disassemble() {
        super(ID, info);
        setMagic(GOLD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int buff = this.upgraded ? BUFF + UPG_BUFF : BUFF;
        addToBot(new DisassembleAction(p, p, buff, magicNumber));
        if (this.baseMagicNumber > 0)
            this.baseMagicNumber -= 2;
        if (this.baseMagicNumber < 0)
            this.baseMagicNumber = 0;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Disassemble();
    }
}
