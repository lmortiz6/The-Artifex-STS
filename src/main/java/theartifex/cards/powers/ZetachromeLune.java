package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.LoseMetallicizePower;
import theartifex.util.CardStats;

public class ZetachromeLune extends BaseCard {

    public static final String ID = makeID(ZetachromeLune.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    private static final int BUFF = 10;
    private static final int UPG_BUFF = 2;
    private static final int DEBUFF = 2;

    public ZetachromeLune() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(BUFF, UPG_BUFF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseMetallicizePower(p, p, DEBUFF)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ZetachromeLune();
    }
}
