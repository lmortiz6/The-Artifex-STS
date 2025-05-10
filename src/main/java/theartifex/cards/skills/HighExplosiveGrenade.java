package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.HighExplosiveGrenadePower;
import theartifex.util.CardStats;

public class HighExplosiveGrenade extends BaseCard {

    public static final String ID = makeID(HighExplosiveGrenade.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;
    private static final int TURNS = 2;

    public HighExplosiveGrenade() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HighExplosiveGrenadePower(p, p, TURNS, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HighExplosiveGrenade();
    }
}
