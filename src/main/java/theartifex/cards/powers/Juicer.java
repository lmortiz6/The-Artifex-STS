package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.JuicerPower;
import theartifex.util.CardStats;

public class Juicer extends BaseCard {

    public static final String ID = makeID(Juicer.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int BUFF = 1;
    private static final int UPG_BUFF = 1;

    public Juicer() {
        super(ID, info);

        this.setMagic(BUFF, UPG_BUFF);
        this.keywords.add("theartifex:adversereaction");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new JuicerPower(p, p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Juicer();
    }
}
