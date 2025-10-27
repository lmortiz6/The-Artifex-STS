package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractCyberneticCard;
import theartifex.cards.attacks.PyrokinesisField;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.CathedraWithRubyTraceryPower;
import theartifex.relics.CathedraWithRubyTraceryRelic;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class CathedraWithRubyTracery extends AbstractCyberneticCard {

    public static final String ID = makeID(CathedraWithRubyTracery.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            4
    );
    public static final int creditCost = info.baseCost;
    private static final int BUFF = 1;
    private static final String cyberneticRelic = makeID(CathedraWithRubyTraceryRelic.class.getSimpleName());

    public CathedraWithRubyTracery() {
        super(ID, info, cyberneticRelic);

        this.cardsToPreview = new PyrokinesisField();
        this.tags.add(CustomCardTags.THEARTIFEXCYBERNETIC);
    }

    @Override
    public boolean canUpgrade() { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CathedraWithRubyTraceryPower(p, p, BUFF)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CathedraWithRubyTracery();
    }
}
