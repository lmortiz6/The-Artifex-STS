package theartifex.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.actions.ToolkitAction;
import theartifex.character.TheArtifexCharacter;

import static theartifex.TheArtifexMod.makeID;

public class BasicToolkitRelic extends BaseRelic{
    private static final String NAME = BasicToolkitRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.STARTER;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY;
    private static final int AMOUNT_MODDED = 2;
    /*private static final int CHANCE = 16;

    private final ArrayList<AbstractCard> doNotMod = new ArrayList<>();
    private final ArrayList<AbstractCard> isModded = new ArrayList<>();
    */

    public BasicToolkitRelic() {
        super(ID, NAME, TheArtifexCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        this.tips.add(new PowerTip(CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[4], CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[5]));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    /*public void onPreviewObtainCard(AbstractCard c) {
        int roll = AbstractDungeon.relicRng.random(0, CHANCE - 1);
        if (roll == 0) {
            modCard(c, true);
            TheArtifexMod.logger.info("Toolbox Hit : " + roll);
        } else {
            doNotMod.add(c);
            TheArtifexMod.logger.info("Toolbox Miss : " + roll);
        }
    }

    public void onObtainCard(AbstractCard c) {
        if (doNotMod.contains(c) || isModded.contains(c))
            return;
        int roll = AbstractDungeon.relicRng.random(0, CHANCE - 1);
        if (roll == 0) {
            modCard(c, true);
            TheArtifexMod.logger.info("Toolbox Hit : " + roll);
        } else {
            TheArtifexMod.logger.info("Toolbox Miss : " + roll);
        }
    }*/

    @Override
    public void atBattleStart() {
        //doNotMod.clear();
        //isModded.clear();
        addToBot(new ToolkitAction(AbstractDungeon.player, 3));
    }
    public AbstractRelic makeCopy() {
        return new BasicToolkitRelic();
    }
}
