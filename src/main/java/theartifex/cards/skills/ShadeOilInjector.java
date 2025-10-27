package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theartifex.abstracts.AbstractInjector;
import theartifex.cards.status.EvilTwinStatus;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class ShadeOilInjector extends AbstractInjector {

    public static final String ID = makeID(ShadeOilInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    private static final int BUFF = 1;
    private static final int UPG_BUFF = 1;
    private static final int DEBUFF = 2;

    public ShadeOilInjector() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(BUFF, UPG_BUFF);
        this.setExhaust(true);
        tags.add(CustomCardTags.THEARTIFEXINJECTOR);
        this.reaction = new EvilTwinStatus();
        this.cardsToPreview = this.reaction;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, DEBUFF, true)));
    }

    @Override
    public void adverseReaction() {
        super.adverseReaction();
        addToBot(new MakeTempCardInDrawPileAction(reaction, 1, true, true));
    }

    @Override
    public void upgrade() {
        super.upgrade();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ShadeOilInjector();
    }
}
