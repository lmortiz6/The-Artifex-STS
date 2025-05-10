package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class FulleriteAegis extends BaseCard {

    public static final String ID = makeID(FulleriteAegis.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int BLOCK = 15;
    private static final int UPG_BLOCK = 5;

    public FulleriteAegis() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExhaustAction(1, false, false, false));
        addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        super.canUse(p, m);
        this.cantUseMessage = "I don't have a card to exhaust!";
        return (p.hand.size() - 1 >= 1);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FulleriteAegis();
    }
}
