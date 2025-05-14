package theartifex.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.RifleThroughTrashAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class RifleThroughTrash extends BaseCard {

    public static final String ID = makeID(RifleThroughTrash.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1
    );
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public RifleThroughTrash() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DRAW, UPG_DRAW);
        this.setEthereal(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RifleThroughTrashAction(magicNumber));
        this.modifyCostForCombat(1);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RifleThroughTrash();
    }
}
