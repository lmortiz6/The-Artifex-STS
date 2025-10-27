package theartifex.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.DisassembleAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

import java.util.ArrayList;

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
        setMagic(BUFF, UPG_BUFF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DisassembleAction(p, p, this.magicNumber, GOLD));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Disassemble();
    }
}
