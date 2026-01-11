package theartifex.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.cards.status.Shocked;

public class TransformHandAction extends AbstractGameAction {
    private final AbstractCard replacement;

    public TransformHandAction(AbstractCard replacement) {
        AbstractPlayer p = AbstractDungeon.player;
        this.replacement = replacement;
        this.startDuration = 0.05F;
        setValues(p, p, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration;

        if (this.replacement.type != AbstractCard.CardType.CURSE && this.replacement.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower"))
            this.replacement.upgrade();
    }

    public void update() {
        if (duration == startDuration) {
            boolean transformed = false;
            for (int i = 0; i < AbstractDungeon.player.hand.size(); i++) {
                AbstractCard target = AbstractDungeon.player.hand.group.get(i);
                if (isTransformable(target)) {
                    transformed = true;
                    AbstractCard replacementCopy = replacement.makeCopy();
                    replacementCopy.current_x = target.current_x;
                    replacementCopy.current_y = target.current_y;
                    replacementCopy.target_x = target.target_x;
                    replacementCopy.target_y = target.target_y;
                    replacementCopy.drawScale = 1.0F;
                    replacementCopy.targetDrawScale = target.targetDrawScale;
                    replacementCopy.angle = target.angle;
                    replacementCopy.targetAngle = target.targetAngle;
                    replacementCopy.superFlash(Color.WHITE.cpy());
                    AbstractDungeon.player.hand.group.set(i, replacementCopy);
                }
            }
            if (transformed) {
                if (replacement instanceof Burn) {
                    addToTop(new SFXAction("CARD_BURN"));
                } else
                    if (replacement instanceof Shocked)
                        addToTop(new SFXAction("ORB_PLASMA_EVOKE"));
            }
            AbstractDungeon.player.hand.glowCheck();
        }
        tickDuration();
    }

    private boolean isTransformable(AbstractCard card) {
        return (!(card instanceof Necronomicurse));
    }
}
