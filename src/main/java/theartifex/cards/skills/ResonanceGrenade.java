package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.ResonanceGrenadePower;
import theartifex.util.CardStats;

public class ResonanceGrenade extends BaseCard {

    public static final String ID = makeID(ResonanceGrenade.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 3;
    private static final int VULN = 3;
    private static final int UPG_VULN = 1;
    private static final int TURNS = 2;

    public ResonanceGrenade() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DAMAGE, UPG_DAMAGE);
        this.setCustomVar("vuln", VULN, UPG_VULN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ResonanceGrenadePower(p, p, TURNS, this.magicNumber, this.customVar("vuln"))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ResonanceGrenade();
    }
}
