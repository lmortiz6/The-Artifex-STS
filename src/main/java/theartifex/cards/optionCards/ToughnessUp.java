package theartifex.cards.optionCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.AdrenalineEffect;
import com.megacrit.cardcrawl.vfx.combat.HealVerticalLineEffect;
import theartifex.cards.BaseCard;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class ToughnessUp extends BaseCard {

    public static final String ID = makeID(ToughnessUp.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.COLORLESS, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    private static final int BUFF = 4;
    private static final int UPG_BUFF = 1;

    public ToughnessUp() {
        super(ID, info);
        this.setMagic(BUFF, UPG_BUFF);
        tags.add(CustomCardTags.NECTAR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderLongFlashEffect(Color.valueOf("7ea49580"), false)));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new AdrenalineEffect(), 0.9F));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, new HealVerticalLineEffect(p.hb.cX, p.hb.cY), 0.7F));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new RegenPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ToughnessUp();
    }
}
