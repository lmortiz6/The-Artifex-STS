package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Dismember extends BaseCard {

    public static final String ID = makeID(Dismember.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 10;
    private static final int DEBUFF = 2;
    private static final int UPG_DEBUFF = 0;

    public Dismember() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(DEBUFF, UPG_DEBUFF);
        this.setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Dismember();
    }
}
