package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Meditate extends BaseCard {

    public static final String ID = makeID(Meditate.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;
    private static final int RETAIN = 1;
    private static final int UPG_RETAIN = 1;

    public Meditate() {
        super(ID, info);
        setMagic(DRAW, UPG_DRAW);
        setCustomVar("retain", RETAIN, UPG_RETAIN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.playV(makeID("MEDITATE"), 1.3f);
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new RetainCardsAction(p, customVar("retain")));
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new Meditate();
    }
}
