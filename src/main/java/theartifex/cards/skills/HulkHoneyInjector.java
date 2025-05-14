package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theartifex.abstracts.AbstractInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class HulkHoneyInjector extends AbstractInjector {

    public static final String ID = makeID(HulkHoneyInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BUFF = 2;
    private static final int UPG_BUFF = 1;
    private static final int DEBUFF = 2;

    public HulkHoneyInjector() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(BUFF, UPG_BUFF);
        this.setExhaust(true);
        tags.add(CustomCardTags.INJECTOR);
        this.reaction = new Burn();
        this.cardsToPreview = this.reaction;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, DEBUFF, true)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HulkHoneyInjector();
    }
}
