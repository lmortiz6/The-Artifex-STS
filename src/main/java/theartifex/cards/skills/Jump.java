package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Jump extends BaseCard {

    public static final String ID = makeID(Jump.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1
    );
    private static final int LIMIT = 2;
    private static final int UPG_LIMIT = 1;
    private static final int DRAW = 4;

    public Jump() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(LIMIT, UPG_LIMIT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c != this)
                count++;
        }
        if (count <= this.magicNumber) {
            addToBot(new DrawCardAction(p, DRAW));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c != this)
                count++;
        }
        if (count <= this.magicNumber) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Jump();
    }
}
