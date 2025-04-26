package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class PenetratingStrike extends BaseCard {

    public static final String ID = makeID(PenetratingStrike.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 6;
    private static final int SCALING = 2;
    private static final int UPG_SCALING = 2;

    public PenetratingStrike() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(SCALING, UPG_SCALING);

        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null)
            strength.amount *= this.magicNumber;
        super.applyPowers();
        if (strength != null)
            strength.amount /= this.magicNumber;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        if (strength != null)
            strength.amount *= this.magicNumber;
        super.calculateCardDamage(mo);
        if (strength != null)
            strength.amount /= this.magicNumber;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PenetratingStrike();
    }
}
