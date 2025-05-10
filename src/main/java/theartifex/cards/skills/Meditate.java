package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Meditate extends BaseCard {

    public static final String ID = makeID(Meditate.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 3;
    private static final int DRAW = 1;
    private static final int RETAIN = 1;

    public Meditate() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setBlock(BLOCK, UPG_BLOCK); //Sets the card's damage and how much it changes when upgraded.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new DrawCardAction(p, DRAW));
        addToBot(new RetainCardsAction(p, RETAIN));
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Meditate();
    }
}
