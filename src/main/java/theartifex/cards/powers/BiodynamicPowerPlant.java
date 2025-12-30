package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractCyberneticCard;
import theartifex.cards.attacks.Strike_Artifex;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.BiodynamicPowerPlantPower;
import theartifex.relics.BiodynamicPowerPlantRelic;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class BiodynamicPowerPlant extends AbstractCyberneticCard {

    public static final String ID = makeID(BiodynamicPowerPlant.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    public static final int creditCost = info.baseCost;
    private static final int BUFF = 1;
    private static final String cyberneticRelic = makeID(BiodynamicPowerPlantRelic.class.getSimpleName());

    public BiodynamicPowerPlant() {
        super(ID, info, cyberneticRelic);

        this.cardsToPreview = new Strike_Artifex();
        this.tags.add(CustomCardTags.THEARTIFEXCYBERNETIC);
    }

    @Override
    public boolean canUpgrade() { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BiodynamicPowerPlantPower(p, p, BUFF)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BiodynamicPowerPlant();
    }
}
