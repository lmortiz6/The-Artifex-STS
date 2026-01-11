package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.abstracts.AbstractGun;
import theartifex.relics.AdvancedToolkitRelic;
import theartifex.relics.BasicToolkitRelic;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class AdvancedToolkitAction extends AbstractGameAction {

    private final AbstractPlayer p;

    private final ArrayList<AbstractCard> cannotTinker = new ArrayList<>();

    public AdvancedToolkitAction(AbstractCreature source, int amount) {
        setValues(AbstractDungeon.player, source, amount);
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.25F;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : this.p.hand.group) {
                if (!isTinkerable(c))
                    this.cannotTinker.add(c);
            }
            if (this.cannotTinker.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() - this.cannotTinker.size() <= amount) {
                for (AbstractCard c : this.p.hand.group) {
                    if (isTinkerable(c)) {
                        modCard(c);
                    }
                }
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                if (AbstractDungeon.player.hasRelic(makeID(AdvancedToolkitRelic.class.getSimpleName())))
                    AbstractDungeon.player.getRelic(makeID(AdvancedToolkitRelic.class.getSimpleName())).flash();
                this.isDone = true;
                return;
            }
            ArrayList<AbstractCard> handGroup = new ArrayList<>(this.p.hand.group);
            handGroup.removeAll(cannotTinker);
            if (handGroup.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (handGroup.size() > 1) {
                for (int i = 0; i < amount; i++) {
                    if (!handGroup.isEmpty()) {
                        int j = AbstractDungeon.relicRng.random(0, (int) (handGroup.size() - 1));
                        AbstractCard c = handGroup.get(j);
                        if (isTinkerable(c))
                            modCard(c);
                        handGroup.remove(c);
                    }
                }
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                if (AbstractDungeon.player.hasRelic(makeID(AdvancedToolkitRelic.class.getSimpleName())))
                    AbstractDungeon.player.getRelic(makeID(AdvancedToolkitRelic.class.getSimpleName())).flash();
                this.isDone = true;
                return;
            }
        }
        tickDuration();
    }

    private void modCard(AbstractCard c) {
        if (!isTinkerable(c))
            return;
        CustomCardTags.loadRandomMod(c, false);
    }

    private boolean isTinkerable(AbstractCard card) {
        return (CustomCardTags.getMods(card).size() < 2 && card.cost != -2 && card.type != AbstractCard.CardType.CURSE);
    }
}
