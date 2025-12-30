package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.HookAndDragPower;
import theartifex.powers.HookedPower;
import theartifex.util.CardStats;
import theartifex.util.CustomAttackEffect;

public class HookAndDrag extends BaseCard {

    public static final String ID = makeID(HookAndDrag.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 0;
    private static final int DEBUFF = 2;
    private static final int UPG_DEBUFF = 1;

    public HookAndDrag() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DEBUFF, UPG_DEBUFF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), CustomAttackEffect.CLEAVE));
        addToBot(new ApplyPowerAction(m, p, new HookedPower(m, p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new HookAndDragPower(p, p, -1), 0));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HookAndDrag();
    }
}
