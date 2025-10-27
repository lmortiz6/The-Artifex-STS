package theartifex.cards.skills;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SanctityEffect;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class SolarCell extends BaseCard {

    public static final String ID = makeID(SolarCell.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            -2
    );
    private static final int ENERGY = 2;
    private static final int UPG_ENERGY = 1;

    public SolarCell() {
        super(ID, info);
        setMagic(ENERGY, UPG_ENERGY);
    }

    @Override
    public void triggerWhenDrawn() {
        if (this.baseMagicNumber > 0) {
            addToTop(new GainEnergyAction(magicNumber));
            addToTop(new VFXAction(new SanctityEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
            addToTop(new SFXAction("HEAL_1"));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard && this.baseMagicNumber > 0) {
            addToTop(new WaitAction(0.1F));
            addToTop(new SFXAction("ORB_PLASMA_EVOKE"));
            addToTop(new WaitAction(0.1F));
            addToTop(new WaitAction(0.1F));
            this.superFlash(Color.WHITE.cpy());
            this.baseMagicNumber--;
        }
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }
    @Override
    public AbstractCard makeCopy() {
        return new SolarCell();
    }
}
