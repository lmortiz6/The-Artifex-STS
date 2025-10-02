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

public class SkulkInjectorAction extends AbstractGameAction {

    private AbstractPlayer p;

    private ArrayList<AbstractCard> cannotTinker = new ArrayList<>();

    private AbstractCard sourceCard;

    public SkulkInjectorAction(AbstractCreature source, int amount, AbstractCard sourceCard) {
        setValues(AbstractDungeon.player, source, amount);
        this.actionType = AbstractGameAction.ActionType.DRAW;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
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
                        modCard(c);
                        CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                        this.isDone = true;
                        return;
                    }
                }
            ArrayList<AbstractCard> group = new ArrayList<>(this.p.hand.group);
            group.removeAll(this.cannotTinker);
            if (group.size() > 1) {
                for (int i = 0; i < amount; i++) {
                    if (!group.isEmpty()) {
                        int j = AbstractDungeon.cardRandomRng.random(0, group.size() - 1);
                        AbstractCard c = group.get(j);
                        modCard(c);
                        this.cannotTinker.add(c);
                        group.remove(c);
                    }
                }
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                this.isDone = true;
                return;
            }
            if (group.size() == 1) {
                modCard(group.get(0));
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f); // Sound Effect
                this.isDone = true;
                return;
            }
        }
        tickDuration();
    }

    private void modCard(AbstractCard c) {
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXFLEXIWEAVED, false);
    }

    private boolean isTinkerable(AbstractCard card) {
        return (card != sourceCard && CustomCardTags.getMods(card).size() < 2 && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTFLEXIWEAVED) && !card.tags.contains(CustomCardTags.THEARTIFEXFLEXIWEAVED));
    }
}
