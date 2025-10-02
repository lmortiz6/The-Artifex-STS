package theartifex.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theartifex.util.CustomCardTags;

import static theartifex.TheArtifexMod.makeID;

public class JuicerPower extends BasePower{

    public static final String POWER_ID = makeID(JuicerPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public JuicerPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = amount;
        this.updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        AbstractPlayer p = AbstractDungeon.player;
        if (card.hasTag(CustomCardTags.THEARTIFEXINJECTOR)) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(owner, amount)));
            addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, amount)));
        }
    }

    public void updateDescription() {
            this.description = String.format(DESCRIPTIONS[0], new Object[]{this.amount, this.amount});
    }
}
