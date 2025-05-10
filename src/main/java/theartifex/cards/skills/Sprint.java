package theartifex.cards.skills;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class Sprint extends BaseCard {

    public static final String ID = makeID(Sprint.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            1
    );
    private static final int DRAW = 2;
    private static final int DISCARD = 1;

    public Sprint() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DRAW);
        this.setExhaust(true);
        this.setCostUpgrade(0);
        this.tags.add(CustomCardTags.SPRINT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new DiscardAction(p, p, DISCARD, false));
    }

    public void initializeDescription() {
        super.initializeDescription();
        String exhaustString = "*Exhaust.";
        GlyphLayout gl = new GlyphLayout();
        gl.setText(FontHelper.cardDescFont_N, exhaustString);
        if (this.exhaust) {
            description.add(new DescriptionLine(exhaustString, gl.width));
            if (!this.keywords.contains("exhaust"))
                this.keywords.add("exhaust");
        } else {
            this.keywords.remove("exhaust");
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Sprint();
    }
}
