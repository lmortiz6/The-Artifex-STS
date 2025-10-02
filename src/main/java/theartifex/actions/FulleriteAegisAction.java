package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class FulleriteAegisAction extends AbstractGameAction {

    private final AbstractPlayer p;

    private final ArrayList<AbstractCard> cannotTinker = new ArrayList<>();

    private final AbstractCard sourceCard;

    public FulleriteAegisAction(AbstractCreature source, int amount, AbstractCard sourceCard) {
        setValues(AbstractDungeon.player, source, amount);
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.25F;
        this.sourceCard = sourceCard;
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
            if (this.p.hand.group.size() - this.cannotTinker.size() == 1)
                for (AbstractCard c : this.p.hand.group) {
                    if (isTinkerable(c)) {
                        CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                        modCard(c);
                        this.isDone = true;
                        return;
                    }
                }
            ArrayList<AbstractCard> handGroup = new ArrayList<>(this.p.hand.group);
            handGroup.removeAll(cannotTinker);
            if (handGroup.size() > 1) {
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                for (int i = 0; i < amount; i++) {
                    int j = AbstractDungeon.cardRandomRng.random(0, handGroup.size() - 1);
                    AbstractCard c = handGroup.get(j);
                    modCard(c);
                    handGroup.remove(c);
                }
                this.isDone = true;
                return;
            }
            if (handGroup.size() == 1) {
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                modCard(handGroup.get(0));
                this.isDone = true;
                return;
            }
        }
        tickDuration();
    }

    private void modCard(AbstractCard c) {
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXREINFORCED, false);
    }

    private boolean isTinkerable(AbstractCard card) {
        return (card != sourceCard && CustomCardTags.getMods(card).size() < 2 && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTREINFORCED) && !card.tags.contains(CustomCardTags.THEARTIFEXREINFORCED));
    }
}
