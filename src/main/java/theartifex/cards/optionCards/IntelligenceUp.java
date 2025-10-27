package theartifex.cards.optionCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import theartifex.cards.BaseCard;
import theartifex.powers.TinkerPower;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class IntelligenceUp extends BaseCard {

    public static final String ID = makeID(IntelligenceUp.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    private static final int BUFF = 1;
    private static final int UPG_BUFF = 1;

    public IntelligenceUp() {
        super(ID, info);
        this.setMagic(BUFF, UPG_BUFF);
        tags.add(CustomCardTags.THEARTIFEXNECTAR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        BorderFlashEffect flash = new BorderFlashEffect(Color.valueOf("c2d3d32D"), false);
        flash.duration = 0.82F;
        addToBot(new VFXAction(flash));
        addToBot(new VFXAction(p, (AbstractGameEffect)new VerticalAuraEffect(Color.valueOf("c2d3d37D"), p.hb.cX, p.hb.cY), 1.0F));
        addToBot(new ApplyPowerAction(p, p, new TinkerPower(p, p, magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new IntelligenceUp();
    }
}
