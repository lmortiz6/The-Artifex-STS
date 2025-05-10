package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractGun;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class ChainPistol extends AbstractGun {

    public static final String ID = makeID(ChainPistol.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            1
    );
    private static final int DAMAGE = 3;
    private static final int TIMES = 3;
    private static final int UPG_TIMES = 1;

    public ChainPistol() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setDamage(DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(TIMES, UPG_TIMES);
        tags.add(CustomCardTags.GUN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
                m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChainPistol();
    }
}
