package theartifex.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.actions.AdvancedToolkitAction;
import theartifex.character.TheArtifexCharacter;

import static theartifex.TheArtifexMod.makeID;

public class AdvancedToolkitRelic extends BaseRelic{
    private static final String NAME = AdvancedToolkitRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY;
    private static final int AMOUNT_MODDED = 2;

    public AdvancedToolkitRelic() {
        super(ID, NAME, TheArtifexCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        this.tips.add(new PowerTip(CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[4], CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[5]));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStartPostDraw() {
        addToBot(new AdvancedToolkitAction(AbstractDungeon.player, 2));
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(makeID(BasicToolkitRelic.class.getSimpleName()));
    }

    public AbstractRelic makeCopy() {
        return new AdvancedToolkitRelic();
    }
}
