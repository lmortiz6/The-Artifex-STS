package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.StunGasGrenadePower;
import theartifex.util.CardStats;

public class StunGasGrenade extends BaseCard {

    public static final String ID = makeID(StunGasGrenade.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2
    );
    private static final int TURNS = 2;

    public StunGasGrenade() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setCostUpgrade(1);
        this.setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StunGasGrenadePower(p, p, TURNS)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StunGasGrenade();
    }
}
