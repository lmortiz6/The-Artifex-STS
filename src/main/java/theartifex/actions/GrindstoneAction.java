package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.abstracts.AbstractGun;
import theartifex.util.CustomCardTags;

import static theartifex.TheArtifexMod.makeID;

public class GrindstoneAction extends AbstractGameAction {
    public static final String[] TEXT = {
            "Choose a Card to Modify with Sharp.",
            "Choose Cards to Modify with Sharp."
    };

    private AbstractPlayer p;

    public GrindstoneAction(int amount) {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.drawPile.group) {
                if (isTinkerable(c))
                    tmp.addToRandomSpot(c);
            }
            if (tmp.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (tmp.size() <= this.amount) {

                for (AbstractCard card : tmp.group) {
                    modCard(card);
                }
                CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f);
                this.isDone = true;
                return;
            }
            if (amount > 1)
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[1], false);
            else
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                modCard(c);
            }
            CardCrawlGame.sound.playV(makeID("TINKER_MOD"), 1.3f);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private AbstractCard getModdedCard (AbstractCard c) {
        AbstractCard card = c.makeStatEquivalentCopy();
        modCard(card);
        return card;
    }

    private void modCard(AbstractCard c) {
        CustomCardTags.loadMod(c, CustomCardTags.THEARTIFEXSHARP, false);
    }

    private boolean isTinkerable(AbstractCard card) {
        return (CustomCardTags.getMods(card).size() < 2 && card.type == AbstractCard.CardType.ATTACK && !(card instanceof AbstractGun) && card.cost != -2 && card.type != AbstractCard.CardType.CURSE && !card.tags.contains(CustomCardTags.THEARTIFEXPERMANENTSHARP) && !card.tags.contains(CustomCardTags.THEARTIFEXSHARP));
    }
}
