package theartifex.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.ModifyCostForCombatAction;
import theartifex.actions.RifleThroughTrashAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class RifleThroughTrash extends BaseCard {

    public static final String ID = makeID(RifleThroughTrash.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0
    );
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public RifleThroughTrash() {
        super(ID, info);

        this.setMagic(DRAW, UPG_DRAW);
        this.setEthereal(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RifleThroughTrashAction(magicNumber));
        addToBot(new ModifyCostForCombatAction(this, 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RifleThroughTrash();
    }
}
