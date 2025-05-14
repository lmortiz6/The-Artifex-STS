package theartifex.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.CarbideHandBones;
import theartifex.cards.powers.StabilizerArmLocks;

import static theartifex.TheArtifexMod.makeID;

public class StabilizerArmLocksRelic extends AbstractCyberneticRelic {
    private static final String NAME = StabilizerArmLocksRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private boolean justApplied = true;
    private static final AbstractCard card = new StabilizerArmLocks();
    private static final int creditCost = card.cost;

    public StabilizerArmLocksRelic() {
        super(ID, NAME, RARITY, SOUND, creditCost);
        //this.tips.add(new PowerTip(TipHelper.capitalize("gun"), "The damage of Gun cards is affected by Dexterity rather than Strength."));
        this.tips.add(new PowerTip(TipHelper.capitalize("cybernetic"), "Cybernetic relics can be unimplanted at #yRest #ySites to gain their respective card."));
    }

    @Override
    public String getUpdatedDescription() {

        return DESCRIPTIONS[0];
    }
}
