package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.RubbergumPower;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class RubbergumInjector extends AbstractInjector {

    public static final String ID = makeID(RubbergumInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int BUFF = 5;
    private static final int UPG_BUFF = 3;

    public RubbergumInjector() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(BUFF, UPG_BUFF);
        this.setExhaust(true);
        tags.add(CustomCardTags.INJECTOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RubbergumPower(p, p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RubbergumInjector();
    }
}
