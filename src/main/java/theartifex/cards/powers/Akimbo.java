package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.AkimboPower;
import theartifex.util.CardStats;

public class Akimbo extends BaseCard {

    public static final String ID = makeID(Akimbo.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 0;

    public Akimbo() {
        super(ID, info);

        this.setMagic(DRAW, UPG_DRAW);
        this.setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AkimboPower(p, p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Akimbo();
    }
}
