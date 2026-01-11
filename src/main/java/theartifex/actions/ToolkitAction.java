package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.abstracts.AbstractGun;
import theartifex.relics.BasicToolkitRelic;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class ToolkitAction extends AbstractGameAction {

    private final AbstractPlayer p;

    private final ArrayList<AbstractCard> cannotTinker = new ArrayList<>();

    public ToolkitAction(AbstractCreature source, int amount) {
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
                if (AbstractDungeon.player.hasRelic(makeID(BasicToolkitRelic.class.getSimpleName())))
                    AbstractDungeon.player.getRelic(makeID(BasicToolkitRelic.class.getSimpleName())).flash();
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
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
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                for (int i = 0; i < amount; i++) {
                    if (!handGroup.isEmpty()) {
                        int j = AbstractDungeon.relicRng.random(0, (int) (handGroup.size() - 1));
                        AbstractCard c = handGroup.get(j);
                        if (isTinkerable(c))
                            modCard(c);
                        handGroup.remove(c);
                    }
                }
                if (AbstractDungeon.player.hasRelic(makeID(BasicToolkitRelic.class.getSimpleName())))
                    AbstractDungeon.player.getRelic(makeID(BasicToolkitRelic.class.getSimpleName())).flash();
                this.isDone = true;
                return;
            }
        }
        tickDuration();
    }

    private void modCard(AbstractCard c) {
        if (!isTinkerable(c))
            return;
        if (c.type == AbstractCard.CardType.ATTACK && !(c instanceof AbstractGun) && (AbstractDungeon.relicRng.random(0, 2) < 2 || c.hasTag(CustomCardTags.THEARTIFEXREINFORCED) || c.hasTag(CustomCardTags.THEARTIFEXPERMANENTREINFORCED))) {
            CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXSHARP, false);
        } else {
            CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXREINFORCED, false);
        }
    }

    private boolean isTinkerable(AbstractCard card) {
        return (CustomCardTags.getMods(card).size() < 2 && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && ((!card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTREINFORCED) && !card.tags.contains(CustomCardTags.THEARTIFEXREINFORCED)) || (card.type == AbstractCard.CardType.ATTACK && !(card instanceof AbstractGun) )));
    }
}
