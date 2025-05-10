package theartifex.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static theartifex.TheArtifexMod.makeID;

public class ForceModulatorRelic extends BaseRelic{
    private static final String NAME = ForceModulatorRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;

    private int used = 1;

    public ForceModulatorRelic() {
        super(ID, NAME, RARITY, SOUND);
        this.tips.add(new PowerTip(TipHelper.capitalize("cybernetic"), "Cybernetic relics can be unimplanted at #yRest #ySites to gain their respective card."));
    }

    @Override
    public void atTurnStart() {
        used = 1;
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if ( used > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            info.type = DamageInfo.DamageType.HP_LOSS;
            used--;
            return info.base;
        }
        return damageAmount;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
