package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class ChemCell extends BaseCard {

    public static final String ID = makeID(ChemCell.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            -2
    );
    private static final int ENERGY = 2;
    private static final int DRAW = 1;

    public ChemCell() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.setSelfRetain(false, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void triggerOnExhaust() {
        addToTop(new GainEnergyAction(ENERGY));
        addToTop(new DrawCardAction(DRAW));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChemCell();
    }
}
