package theartifex.cards.status;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.powers.NextTurnDrawReductionPower;
import theartifex.util.CardStats;

public class Shocked extends BaseCard {

    public static final String ID = makeID(Shocked.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.COLORLESS, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.STATUS,
            CardRarity.COMMON,
            CardTarget.NONE,
            1
    );
    private static final int DEBUFF = 1;

    public Shocked() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setMagic(DEBUFF);
        setExhaust(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void triggerWhenDrawn() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new NextTurnDrawReductionPower(p, p, magicNumber)));
    }

    @Override
    public void upgrade() {}

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Shocked();
    }
}
