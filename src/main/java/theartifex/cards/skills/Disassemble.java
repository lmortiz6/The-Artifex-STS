package theartifex.cards.skills;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.DisassembleAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

import java.util.ArrayList;

public class Disassemble extends BaseCard {

    public static final String ID = makeID(Disassemble.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            0
    );
    private static final int BUFF = 1;
    private static final int UPG_BUFF = 1;
    private static final int GOLD = 6;
    private ArrayList<AbstractCard> nonSkills;

    public Disassemble() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        nonSkills = new ArrayList<AbstractCard>();
        setMagic(BUFF, UPG_BUFF); //Sets the card's damage and how much it changes when upgraded.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        /*nonSkills.clear();
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.type != CardType.SKILL) {
                nonSkills.add(card);
            }
        }
        for (AbstractCard card : nonSkills) {
            AbstractDungeon.player.hand.removeCard(card);
        }*/
        addToBot(new DisassembleAction(p, p, this.magicNumber, GOLD));
        //addToBot(new AddToHandAction(nonSkills));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Disassemble();
    }
}
