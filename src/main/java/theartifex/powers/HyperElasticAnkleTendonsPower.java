package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.cards.attacks.PyrokinesisField;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static theartifex.TheArtifexMod.makeID;

public class HyperElasticAnkleTendonsPower extends BasePower {

    public static final String POWER_ID = makeID(HyperElasticAnkleTendonsPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //private ArrayList<UUID> used = new ArrayList<>();

    public HyperElasticAnkleTendonsPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateDescription();
    }

    /*@Override
    public void onInitialApplication() {
        used = new ArrayList<>();
        updateExistingSprints();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateExistingSprints();
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        updateExistingSprints();
    }

    @Override
    public void atStartOfTurn() {
        updateExistingSprints();
    }

    private void updateExistingSprints() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(CustomCardTags.THEARTIFEXSPRINT) && Collections.frequency(used, c.uuid) < amount) {
                used.add(c.uuid);
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(CustomCardTags.THEARTIFEXSPRINT) && Collections.frequency(used, c.uuid) < amount) {
                used.add(c.uuid);
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(CustomCardTags.THEARTIFEXSPRINT) && Collections.frequency(used, c.uuid) < amount) {
                used.add(c.uuid);
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.hasTag(CustomCardTags.THEARTIFEXSPRINT) && Collections.frequency(used, c.uuid) < amount) {
                used.add(c.uuid);
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
    }*/

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[]{this.amount});
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount});
        }
    }
}
