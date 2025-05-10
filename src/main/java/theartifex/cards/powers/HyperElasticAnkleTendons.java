package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.cards.skills.Sprint;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.HyperElasticAnkleTendonsPower;
import theartifex.util.CardStats;

public class HyperElasticAnkleTendons extends BaseCard {

    public static final String ID = makeID(HyperElasticAnkleTendons.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int BUFF = 1;

    public HyperElasticAnkleTendons() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.cardsToPreview = new Sprint();
    }

    @Override
    public boolean canUpgrade() { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HyperElasticAnkleTendonsPower(p, p, BUFF)));
    }



    @Override
    public AbstractCard makeCopy() { //Optional
        return new HyperElasticAnkleTendons();
    }
}
