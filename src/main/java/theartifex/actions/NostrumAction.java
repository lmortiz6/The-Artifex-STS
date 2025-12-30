package theartifex.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.cards.attacks.LoveInjector;
import theartifex.cards.skills.*;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

public class NostrumAction extends AbstractGameAction {
    private final boolean isBroken;

    public NostrumAction(boolean isBroken) {
        AbstractPlayer p = AbstractDungeon.player;
        this.isBroken = isBroken;
        this.startDuration = 0.05F;
        setValues(p, p, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration;
    }

    public void update() {
        if (duration == startDuration && !AbstractDungeon.player.hand.isEmpty()) {

            ArrayList<AbstractCard> injectors = new ArrayList<>();
            injectors.add(new EatersNectarInjector());
            injectors.add(new HulkHoneyInjector());
            injectors.add(new RubbergumInjector());
            injectors.add(new SalveInjector());
            injectors.add(new ShadeOilInjector());
            injectors.add(new SkulkInjector());
            injectors.add(new SphynxSaltInjector());
            injectors.add(new LoveInjector());

            if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                for (AbstractCard c : injectors) {
                    c.upgrade();
                }
            }

            if (isBroken) {
                for (AbstractCard c : injectors) {
                    c.keywords.add(0, "theartifex:broken");
                    c.tags.add(CustomCardTags.THEARTIFEXBROKEN);
                }
            }

            for (int i = 0; i < AbstractDungeon.player.hand.size(); i++) {
                AbstractCard target = AbstractDungeon.player.hand.group.get(i);
                AbstractCard replacementCopy = injectors.get(AbstractDungeon.cardRandomRng.random(0, injectors.size() - 1)).makeStatEquivalentCopy();
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
            AbstractDungeon.player.hand.glowCheck();
        }
        tickDuration();
    }
}
