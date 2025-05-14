package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticCard;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.ForceModulatorPower;
import theartifex.relics.CarbideHandBonesRelic;
import theartifex.relics.ForceModulatorRelic;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class ForceModulator extends AbstractCyberneticCard {

    public static final String ID = makeID(ForceModulator.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            3
    );
    private static final int BUFF = 1;
    private static final AbstractRelic cyberneticRelic = new ForceModulatorRelic();

    public ForceModulator() {
        super(ID, info, cyberneticRelic); //Pass the required information to the BaseCard constructor.
        this.tags.add(CustomCardTags.CYBERNETIC);
    }

    @Override
    public boolean canUpgrade() { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ForceModulatorPower(p, p, BUFF)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ForceModulator();
    }
}
