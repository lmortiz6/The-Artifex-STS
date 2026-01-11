package theartifex.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.character.TheArtifexCharacter;

import static theartifex.TheArtifexMod.makeID;

public class ScrapCapeRelic extends BaseRelic{
    private static final String NAME = ScrapCapeRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;
    private static final int ARMOR = 1;

    public ScrapCapeRelic() {
        super(ID, NAME, TheArtifexCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onSpecificTrigger() {
        flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, ARMOR)));
    }

    public AbstractRelic makeCopy() {
        return new ScrapCapeRelic();
    }
}
