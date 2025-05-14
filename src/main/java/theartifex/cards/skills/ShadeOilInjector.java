package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theartifex.abstracts.AbstractInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class ShadeOilInjector extends AbstractInjector {

    public static final String ID = makeID(ShadeOilInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BUFF = 1;
    private static final int DEBUFF = 2;
    private static final int UPG_DEBUFF = -1;

    public ShadeOilInjector() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DEBUFF, UPG_DEBUFF);
        this.setExhaust(true);
        tags.add(CustomCardTags.INJECTOR);
        this.reaction = new Burn();
        this.reaction.upgrade();
        this.cardsToPreview = this.reaction;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, BUFF)));
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, this.magicNumber, true)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ShadeOilInjector();
    }
}
