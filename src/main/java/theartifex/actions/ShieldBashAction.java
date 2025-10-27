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

public class ShieldBashAction extends AbstractGameAction {

    private final AbstractPlayer p;

    private final ArrayList<AbstractCard> cannotTinker = new ArrayList<>();

    private final AbstractCard sourceCard;

    public ShieldBashAction(AbstractCreature source, int amount, AbstractCard sourceCard) {
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
            for (AbstractCard c : this.p.hand.group) {
                if (isTinkerable(c)) {
                    modCard(c);
                }
            }
            CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f);
            this.isDone = true;
        }
        tickDuration();
    }

    private void modCard(AbstractCard c) {
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXREINFORCED, false);
    }

    private boolean isTinkerable(AbstractCard card) {
        return (card != sourceCard &&
                CustomCardTags.getMods(card).size() < 2 &&
                card.type == AbstractCard.CardType.SKILL
                && card.cost != -2
                && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTREINFORCED)
                && !card.tags.contains(CustomCardTags.THEARTIFEXREINFORCED));
    }
}
