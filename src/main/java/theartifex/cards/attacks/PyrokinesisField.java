package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import theartifex.abstracts.AbstractGun;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class PyrokinesisField extends AbstractGun {

    public static final String ID = makeID(PyrokinesisField.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.COLORLESS, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ALL_ENEMY,
            2
    );
    private static final int DAMAGE = 21;
    private static final int UPG_DAMAGE = 7;

    public PyrokinesisField() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        this.isMultiDamage = true;
        this.cardsToPreview = new VoidCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        addToBot(new MakeTempCardInDiscardAction(new VoidCard(), 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PyrokinesisField();
    }
}
