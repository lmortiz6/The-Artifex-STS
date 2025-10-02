package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.TheArtifexMod;

import java.util.HashMap;

import static theartifex.TheArtifexMod.makeID;

public class ChiralRingsRelic extends BaseRelic {

    private static final String NAME = ChiralRingsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;

    private HashMap<AbstractCreature, Integer> creaturesToDebuff;

    public ChiralRingsRelic() {
        super(ID, NAME, RARITY, SOUND);
        creaturesToDebuff = new HashMap<>();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        TheArtifexMod.logger.info("Chiral rings onAttacked: " + damageAmount);
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount > 0) {
            if (creaturesToDebuff.containsKey(info.owner)) {
                creaturesToDebuff.put(info.owner, creaturesToDebuff.get(info.owner) + 1);
            } else {
                creaturesToDebuff.put(info.owner, 1);
            }
            TheArtifexMod.logger.info(creaturesToDebuff.toString());
        }
        return damageAmount;
    }

    @Override
    public void atTurnStart() {
        for (AbstractCreature m : creaturesToDebuff.keySet()) {
            for (int i = 0; i < creaturesToDebuff.get(m); i++) {
                addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, -2), -2));
                if (m != null && (!m.hasPower("Artifact") || m.getPower("Artifact").amount <= i))
                    addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new GainStrengthPower(m, 2), 2));
            }
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
