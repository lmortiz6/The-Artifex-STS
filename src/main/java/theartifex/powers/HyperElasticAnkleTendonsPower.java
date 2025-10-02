package theartifex.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class HyperElasticAnkleTendonsPower extends BasePower {

    public static final String POWER_ID = makeID(HyperElasticAnkleTendonsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private ArrayList<Boolean> used;

    public HyperElasticAnkleTendonsPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateExistingSprints();
        updateDescription();
    }

    private void updateExistingSprints() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(CustomCardTags.THEARTIFEXSPRINT) && !c.isMagicNumberModified) {
                c.exhaust = false;
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(CustomCardTags.THEARTIFEXSPRINT) && !c.isMagicNumberModified) {
                c.exhaust = false;
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(CustomCardTags.THEARTIFEXSPRINT) && !c.isMagicNumberModified) {
                c.exhaust = false;
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.hasTag(CustomCardTags.THEARTIFEXSPRINT) && !c.isMagicNumberModified) {
                c.exhaust = false;
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        updateExistingSprints();
    }

    @Override
    public void atStartOfTurn() {
        updateExistingSprints();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[]{this.amount});
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount});
        }
    }
}
