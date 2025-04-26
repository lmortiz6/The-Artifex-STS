package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Sprint extends BaseCard {

    public static final String ID = makeID(Sprint.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            0
    );
    private static final int DRAW = 2;
    private static final int DISCARD = 1;

    public Sprint() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.baseMagicNumber = DRAW;
        this.magicNumber = baseMagicNumber;
        this.setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, DRAW));
        addToBot(new DiscardAction(p, p, DISCARD, false));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.setExhaust(false);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Sprint();
    }
}
