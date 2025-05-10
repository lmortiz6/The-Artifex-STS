package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.cards.attacks.PyrokinesisField;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.CathedraWithRubyTraceryPower;
import theartifex.util.CardStats;

public class CathedraWithRubyTracery extends BaseCard {

    public static final String ID = makeID(CathedraWithRubyTracery.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            5
    );
    private static final int BUFF = 1;

    public CathedraWithRubyTracery() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.cardsToPreview = new PyrokinesisField();
    }

    @Override
    public boolean canUpgrade() { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CathedraWithRubyTraceryPower(p, p, BUFF)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CathedraWithRubyTracery();
    }
}
