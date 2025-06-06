package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.AddToHandAction;
import theartifex.actions.GoldEffectAction;
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
    private static final int GOLD = 10;
    private static final int UPG_GOLD = 6;
    private ArrayList<AbstractCard> nonSkills;

    public Disassemble() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        nonSkills = new ArrayList<AbstractCard>();
        setMagic(GOLD, UPG_GOLD); //Sets the card's damage and how much it changes when upgraded.
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        nonSkills.clear();
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card.type != CardType.SKILL) {
                nonSkills.add(card);
            }
        }
        for (AbstractCard card : nonSkills) {
            AbstractDungeon.player.hand.removeCard(card);
        }
        addToTop(new ExhaustAction(1, false, false, false));
        addToBot(new GoldEffectAction(magicNumber));
        addToBot(new WaitAction(2F));
        addToBot(new GainGoldAction(this.magicNumber));
        addToBot(new AddToHandAction(nonSkills));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        super.canUse(p, m);
        this.cantUseMessage = "I don't have a skill to exhaust!";
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (!card.equals(this) && card.type == CardType.SKILL) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Disassemble();
    }
}
