package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.TimeDilationGrenadePower;
import theartifex.util.CardStats;

public class TimeDilationGrenade extends BaseCard {

    public static final String ID = makeID(TimeDilationGrenade.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;
    private static final int TURNS = 1;

    public TimeDilationGrenade() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DRAW, UPG_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TimeDilationGrenadePower(p, p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new TimeDilationGrenade();
    }
}
