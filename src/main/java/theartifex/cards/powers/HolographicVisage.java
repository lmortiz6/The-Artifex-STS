package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractCyberneticCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.HolographicVisagePower;
import theartifex.relics.HolographicVisageRelic;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class HolographicVisage extends AbstractCyberneticCard {

    public static final String ID = makeID(HolographicVisage.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );
    public static final int creditCost = info.baseCost;
    private static final int BUFF = 2;
    private static final String cyberneticRelic = makeID(HolographicVisageRelic.class.getSimpleName());

    public HolographicVisage() {
        super(ID, info, cyberneticRelic);
        this.tags.add(CustomCardTags.THEARTIFEXCYBERNETIC);
    }

    @Override
    public boolean canUpgrade() { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HolographicVisagePower(p, p, BUFF)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HolographicVisage();
    }
}
