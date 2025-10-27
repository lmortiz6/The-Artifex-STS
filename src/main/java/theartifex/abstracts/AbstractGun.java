package theartifex.abstracts;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theartifex.cards.BaseCard;
import theartifex.powers.StabilizerArmLocksPower;
import theartifex.relics.StabilizerArmLocksRelic;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class AbstractGun extends BaseCard {

    public AbstractGun(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void applyPowers() {

        if (!this.isMultiDamage && (this.tags.contains(CustomCardTags.THEARTIFEXBEAMSPLITTER) || this.tags.contains(CustomCardTags.THEARTIFEXPERMANENTBEAMSPLITTER)))
            this.isMultiDamage = true;

        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        AbstractPower dex = AbstractDungeon.player.getPower("Dexterity");
        AbstractPower armLocksPower = AbstractDungeon.player.getPower(makeID(StabilizerArmLocksPower.class.getSimpleName()));
        StabilizerArmLocksRelic armLocksRelic = (StabilizerArmLocksRelic) AbstractDungeon.player.getRelic(makeID(StabilizerArmLocksRelic.class.getSimpleName()));
        int tinker = 0;
        if (AbstractDungeon.player.hasPower("theartifex:TinkerPower"))
            tinker += AbstractDungeon.player.getPower("theartifex:TinkerPower").amount;

        int originalStrength = 0;
        if (strength != null) {
            originalStrength = strength.amount;
            strength.amount -= strength.amount;
        }
        if (dex != null)
            this.baseDamage += dex.amount;
        if (armLocksPower != null)
            this.baseDamage += armLocksPower.amount;
        if (armLocksRelic != null)
            this.baseDamage += 2 * armLocksRelic.amount;
        if (this.tags.contains(CustomCardTags.THEARTIFEXFLEXIWEAVED))
            this.baseDamage += 1 + tinker;

        super.applyPowers();

        if (strength != null)
            strength.amount += originalStrength;
        if (dex != null) {
            this.isDamageModified = true;
            this.baseDamage -= dex.amount;
        }
        if (armLocksPower != null) {
            this.isDamageModified = true;
            this.baseDamage -= armLocksPower.amount;
        }
        if (armLocksRelic != null) {
            this.isDamageModified = true;
            this.baseDamage -= 2 * armLocksRelic.amount;
        }
        if (this.tags.contains(CustomCardTags.THEARTIFEXFLEXIWEAVED)) {
            this.isDamageModified = true;
            this.baseDamage -= (1 + tinker);
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {

        if (!this.isMultiDamage && (this.tags.contains(CustomCardTags.THEARTIFEXBEAMSPLITTER) || this.tags.contains(CustomCardTags.THEARTIFEXPERMANENTBEAMSPLITTER)))
            this.isMultiDamage = true;

        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        AbstractPower dex = AbstractDungeon.player.getPower("Dexterity");
        AbstractPower armLocksPower = AbstractDungeon.player.getPower(makeID(StabilizerArmLocksPower.class.getSimpleName()));
        StabilizerArmLocksRelic armLocksRelic = (StabilizerArmLocksRelic) AbstractDungeon.player.getRelic(makeID(StabilizerArmLocksRelic.class.getSimpleName()));
        int tinker = 0;
        if (AbstractDungeon.player.hasPower("theartifex:TinkerPower"))
            tinker += AbstractDungeon.player.getPower("theartifex:TinkerPower").amount;

        int originalStrength = 0;
        if (strength != null) {
            originalStrength = strength.amount;
            strength.amount -= strength.amount;
        }
        if (dex != null)
            this.baseDamage += dex.amount;
        if (armLocksPower != null)
            this.baseDamage += armLocksPower.amount;
        if (armLocksRelic != null)
            this.baseDamage += 2 * armLocksRelic.amount;
        if (this.tags.contains(CustomCardTags.THEARTIFEXFLEXIWEAVED))
            this.baseDamage += 1 + tinker;

        super.calculateCardDamage(mo);

        if (strength != null)
            strength.amount += originalStrength;
        if (dex != null) {
            this.isDamageModified = true;
            this.baseDamage -= dex.amount;
        }
        if (armLocksPower != null) {
            this.isDamageModified = true;
            this.baseDamage -= armLocksPower.amount;
        }
        if (armLocksRelic != null) {
            this.isDamageModified = true;
            this.baseDamage -= 2 * armLocksRelic.amount;
        }
        if (this.tags.contains(CustomCardTags.THEARTIFEXFLEXIWEAVED)) {
            this.isDamageModified = true;
            this.baseDamage -= (1 + tinker);
        }
    }
}
