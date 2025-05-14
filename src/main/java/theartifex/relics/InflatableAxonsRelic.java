package theartifex.relics;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.CarbideHandBones;
import theartifex.cards.powers.InflatableAxons;
import theartifex.powers.NextTurnDrawPower;
import theartifex.powers.NextTurnDrawReductionPower;

import static theartifex.TheArtifexMod.makeID;

public class InflatableAxonsRelic extends AbstractCyberneticRelic {
    private static final String NAME = InflatableAxonsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private boolean justApplied = true;
    private static final AbstractCard card = new InflatableAxons();
    private static final int creditCost = card.cost;

    public InflatableAxonsRelic() {
        super(ID, NAME, RARITY, SOUND, creditCost);
        this.tips.add(new PowerTip(TipHelper.capitalize("cybernetic"), "Cybernetic relics can be unimplanted at #yRest #ySites to gain their respective card."));
    }

    @Override
    public void atBattleStart() {
        justApplied = true;
        AbstractCreature owner = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawReductionPower(owner, owner, 1)));
        addToBot(new DrawCardAction(1));
    }

    @Override
    public void atTurnStart() {
        AbstractCreature owner = AbstractDungeon.player;
        if ((GameActionManager.turn + 1) % 2 == 1) {
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawReductionPower(owner, owner, 1)));
        } else if (!justApplied){
            addToBot(new ApplyPowerAction(owner, owner, new NextTurnDrawPower(owner, owner, 2)));
        }
        justApplied = false;
    }

    @Override
    public String getUpdatedDescription() {

        return DESCRIPTIONS[0];
    }
}
