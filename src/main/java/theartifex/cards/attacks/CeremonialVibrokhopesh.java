package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class CeremonialVibrokhopesh extends BaseCard {

    public static final String ID = makeID(CeremonialVibrokhopesh.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 11;
    private static final int UPG_DAMAGE = 6;
    private static final int COST_REDUCE = 1;

    public CeremonialVibrokhopesh() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(COST_REDUCE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveAllBlockAction(m, p));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ReduceCostAction(this.uuid, this.magicNumber));
    }

    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null) {
            int originalStrength = strength.amount;
            strength.amount -= strength.amount;
            super.applyPowers();
            strength.amount += originalStrength;
        } else
            super.applyPowers();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null) {
            int originalStrength = strength.amount;
            strength.amount -= strength.amount;
            super.calculateCardDamage(mo);
            strength.amount += originalStrength;
        } else
            super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CeremonialVibrokhopesh();
    }
}
