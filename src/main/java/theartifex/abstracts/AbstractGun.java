package theartifex.abstracts;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.cards.BaseCard;
import theartifex.powers.StabilizerArmLocksPower;
import theartifex.relics.StabilizerArmLocksRelic;
import theartifex.util.CardStats;

public class AbstractGun extends BaseCard {

    public AbstractGun(String ID, CardStats info) {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        AbstractPower dex = AbstractDungeon.player.getPower("Dexterity");
        AbstractPower armLocksPower = AbstractDungeon.player.getPower(makeID(StabilizerArmLocksPower.class.getSimpleName()));
        AbstractRelic armLocksRelic = AbstractDungeon.player.getRelic(makeID(StabilizerArmLocksRelic.class.getSimpleName()));
        int originalStrength = 0;
        if (strength != null) {
            originalStrength = strength.amount;
            strength.amount -= strength.amount;
        }
        if (dex != null)
            this.baseDamage += dex.amount;
        if (armLocksPower != null)
            this.baseDamage += 2 * armLocksPower.amount;
        if (armLocksRelic != null)
            this.baseDamage += 2;
        super.applyPowers();
        if (strength != null)
            strength.amount += originalStrength;
        if (dex != null) {
            this.isDamageModified = true;
            this.baseDamage -= dex.amount;
        }
        if (armLocksPower != null) {
            this.isDamageModified = true;
            this.baseDamage -= 2 * armLocksPower.amount;
        }
        if (armLocksRelic != null) {
            this.isDamageModified = true;
            this.baseDamage -= 2;
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        AbstractPower dex = AbstractDungeon.player.getPower("Dexterity");
        AbstractPower armLocksPower = AbstractDungeon.player.getPower(makeID(StabilizerArmLocksPower.class.getSimpleName()));
        AbstractRelic armLocksRelic = AbstractDungeon.player.getRelic(makeID(StabilizerArmLocksRelic.class.getSimpleName()));
        int originalStrength = 0;
        if (strength != null) {
            originalStrength = strength.amount;
            strength.amount -= strength.amount;
        }
        if (dex != null)
            this.baseDamage += dex.amount;
        if (armLocksPower != null)
            this.baseDamage += 2 * armLocksPower.amount;
        if (armLocksRelic != null)
            this.baseDamage += 2;
        super.calculateCardDamage(mo);
        if (strength != null)
            strength.amount += originalStrength;
        if (dex != null) {
            this.isDamageModified = true;
            this.baseDamage -= dex.amount;
        }
        if (armLocksPower != null) {
            this.isDamageModified = true;
            this.baseDamage -= 2 * armLocksPower.amount;
        }
        if (armLocksRelic != null) {
            this.isDamageModified = true;
            this.baseDamage -= 2;
        }
    }
}
