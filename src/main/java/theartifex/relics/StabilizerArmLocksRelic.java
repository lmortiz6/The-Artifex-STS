package theartifex.relics;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.powers.NextTurnDrawPower;
import theartifex.powers.NextTurnDrawReductionPower;

import static theartifex.TheArtifexMod.makeID;

public class StabilizerArmLocksRelic extends BaseRelic{
    private static final String NAME = StabilizerArmLocksRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private boolean justApplied = true;

    public StabilizerArmLocksRelic() {
        super(ID, NAME, RARITY, SOUND);
        //this.tips.add(new PowerTip(TipHelper.capitalize("gun"), "The damage of Gun cards is affected by Dexterity rather than Strength."));
        this.tips.add(new PowerTip(TipHelper.capitalize("cybernetic"), "Cybernetic relics can be unimplanted at #yRest #ySites to gain their respective card."));
    }

    @Override
    public String getUpdatedDescription() {

        return DESCRIPTIONS[0];
    }
}
