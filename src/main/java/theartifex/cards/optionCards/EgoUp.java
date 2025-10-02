package theartifex.cards.optionCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityParticleEffect;
import theartifex.cards.BaseCard;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class EgoUp extends BaseCard {

    public static final String ID = makeID(EgoUp.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.COLORLESS, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    private static final int BUFF = 15;
    private static final int UPG_BUFF = 7;

    public EgoUp() {
        super(ID, info);
        this.setMagic(BUFF, UPG_BUFF);
        tags.add(CustomCardTags.THEARTIFEXNECTAR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber * 2, true));
        AbstractPlayer p = AbstractDungeon.player;
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderLongFlashEffect(Color.valueOf("905ab7A3"), true)));
        addToBot((AbstractGameAction)new GainGoldAction(this.magicNumber));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new DivinityParticleEffect(), 3.0F));
    }

    public AbstractCard makeCopy() {
        return new EgoUp();
    }
}
