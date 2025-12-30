package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.TheArtifexMod;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class ChiralRingsRelic extends BaseRelic {

    private static final String NAME = ChiralRingsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;

    private final ArrayList<AbstractCreature> creaturesToDebuff;

    public ChiralRingsRelic() {
        super(ID, NAME, RARITY, SOUND);
        creaturesToDebuff = new ArrayList<>();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        TheArtifexMod.logger.info("Chiral rings onAttacked: " + damageAmount);
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            if (!creaturesToDebuff.contains(info.owner)) {
                creaturesToDebuff.add(info.owner);
            }
        }
        return damageAmount;
    }

    @Override
    public void atTurnStart() {
        for (AbstractCreature m : creaturesToDebuff) {
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, -2), -2));
            if (!m.hasPower("Artifact"))
                    addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new GainStrengthPower(m, 2), 2));
        }
        creaturesToDebuff.clear();
    }

    @Override
    public void atBattleStart() {
        creaturesToDebuff.clear();
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
