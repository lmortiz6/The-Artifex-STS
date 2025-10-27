package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractCyberneticCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.InflatableAxonsPower;
import theartifex.relics.InflatableAxonsRelic;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class InflatableAxons extends AbstractCyberneticCard {

    public static final String ID = makeID(InflatableAxons.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    public static final int creditCost = info.baseCost;
    private static final int BUFF = 1;
    private static final String cyberneticRelic = makeID(InflatableAxonsRelic.class.getSimpleName());

    public InflatableAxons() {
        super(ID, info, cyberneticRelic);
        this.tags.add(CustomCardTags.THEARTIFEXCYBERNETIC);
    }

    @Override
    public boolean canUpgrade() { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new InflatableAxonsPower(p, p, BUFF)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new InflatableAxons();
    }
}
