package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class SalveInjector extends AbstractInjector {

    public static final String ID = makeID(SalveInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BUFF = 6;
    private static final int UPG_BUFF = 2;

    public SalveInjector() {
        super(ID, info);

        this.setMagic(BUFF, UPG_BUFF);
        this.setExhaust(true);
        tags.add(CustomCardTags.THEARTIFEXINJECTOR);
        tags.add(CardTags.HEALING);
        this.reaction = new Dazed();
        this.cardsToPreview = this.reaction;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new HealAction(p, p, magicNumber));
    }

    @Override
    public void adverseReaction() {
        super.adverseReaction();
        addToBot(new MakeTempCardInDrawPileAction(reaction, 1, true, true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SalveInjector();
    }
}
