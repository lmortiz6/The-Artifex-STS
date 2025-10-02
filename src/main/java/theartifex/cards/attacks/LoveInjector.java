package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.LovesickPower;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class LoveInjector extends AbstractInjector {

    public static final String ID = makeID(LoveInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 3;
    private static final int DEBUFF = 1;
    private static final int UPG_DEBUFF = 1;

    public LoveInjector() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(DEBUFF, UPG_DEBUFF);
        this.setExhaust(true);
        tags.add(CustomCardTags.THEARTIFEXINJECTOR);
        this.cardsToPreview = new Burn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m, p, new LovesickPower(m, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void adverseReaction() {
        addToBot(new MakeTempCardInHandAction(new Burn(), 2));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new LoveInjector();
    }
}
