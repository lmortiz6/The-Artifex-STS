package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theartifex.abstracts.AbstractGun;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class GrapplingGun extends AbstractGun {

    public static final String ID = makeID(GrapplingGun.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );
    private static final int DAMAGE = 1;
    private static final int UPG_DAMAGE = 1;
    private static final int VULN = 1;
    private static final int UPG_VULN = 1;
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 0;

    public GrapplingGun() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(VULN, UPG_VULN);
        setCustomVar("draw", DRAW, UPG_DRAW);
        tags.add(CustomCardTags.GUN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
        addToBot(new DrawCardAction(p, customVar("draw")));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GrapplingGun();
    }
}
