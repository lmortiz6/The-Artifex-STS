package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Calloused extends BaseCard {

    public static final String ID = makeID(Calloused.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;

    public Calloused() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        this.setSelfRetain(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public void onRetained() {
        this.baseBlock -= 1;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Calloused();
    }
}
