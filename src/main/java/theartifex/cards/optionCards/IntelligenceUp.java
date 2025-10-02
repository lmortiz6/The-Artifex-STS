package theartifex.cards.optionCards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import theartifex.cards.BaseCard;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class IntelligenceUp extends BaseCard {

    public static final String ID = makeID(IntelligenceUp.class.getSimpleName());

    private static final CardStats info = new CardStats(
            CardColor.COLORLESS, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2
    );
    private static final int BUFF = 2;
    private static final int UPG_BUFF = 0;

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
        addToBot((AbstractGameAction)new VFXAction(flash));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new VerticalAuraEffect(Color.valueOf("c2d3d37D"), p.hb.cX, p.hb.cY), 1.0F));
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard c = AbstractDungeon.returnTrulyRandomColorlessCardInCombat().makeCopy();
            if (this.upgraded) {
                c.upgrade();
            }
            c.setCostForTurn(0);
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
        }
    }

    public AbstractCard makeCopy() {
        return new IntelligenceUp();
    }
}
