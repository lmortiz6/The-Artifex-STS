package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.cards.attacks.FlumeDash;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.FlumeFlierPower;
import theartifex.util.CardStats;

public class FlumeFlier extends BaseCard {

    public static final String ID = makeID(FlumeFlier.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    private static final int UPG_COST = 2;
    private static final int AMOUNT = 1;

    public FlumeFlier() {
        super(ID, info);

        this.setCostUpgrade(UPG_COST);
        this.cardsToPreview = new FlumeDash();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FlumeFlierPower(p, p, AMOUNT)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlumeFlier();
    }
}
