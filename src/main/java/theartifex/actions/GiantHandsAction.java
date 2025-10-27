package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class GiantHandsAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private boolean used;
    private AbstractPower power;
    private AbstractRelic relic;

    public GiantHandsAction(int amount) {
        this(amount, null, null);
    }

    public GiantHandsAction(int amount, AbstractPower power) {
        this(amount, power, null);
    }

    public GiantHandsAction(int amount, AbstractRelic relic) {
        this(amount, null, relic);
    }

    private GiantHandsAction(int amount, AbstractPower power, AbstractRelic relic) {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player, amount);
        this.duration = Settings.ACTION_DUR_FAST;
        this.used = false;
        this.power = power;
        this.relic = relic;
    }

    public void update() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0; i < amount; i++) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK && c.costForTurn > 0) {
                    group.addToBottom(c);
                }
            }
            if (!group.isEmpty()) {
                this.used = true;
                AbstractCard c = group.getRandomCard(AbstractDungeon.cardRandomRng);
                c.costForTurn -= 1;
                c.isCostModifiedForTurn = true;
            }
            group.clear();
        }

        if (this.used) {
            if (this.power != null) {
                power.flash();
            }
            if (this.relic != null) {
                relic.flash();
            }
        }

        this.isDone = true;
    }
}
