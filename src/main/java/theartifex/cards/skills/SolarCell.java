package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class SolarCell extends BaseCard {

    public static final String ID = makeID(SolarCell.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            -2
    );
    private static final int ENERGY = 2;
    private static final int UPG_ENERGY = 1;

    public SolarCell() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(ENERGY, UPG_ENERGY);
    }

    @Override
    public void triggerWhenDrawn() {
        if (this.baseMagicNumber > 0) {
            addToTop(new GainEnergyAction(magicNumber));
            this.baseMagicNumber--;
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {}

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SolarCell();
    }
}
