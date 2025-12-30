package theartifex.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theartifex.character.TheArtifexCharacter;

import static theartifex.TheArtifexMod.makeID;

public class AmaranthinePrismRelic extends BaseRelic {

    private static final String NAME = AmaranthinePrismRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private static final int CYCLE = 10;

    public AmaranthinePrismRelic() {
        super(ID, NAME, TheArtifexCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        counter = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        counter++;
        if (counter == CYCLE) {
            counter = 0;
            flash();
            if (AbstractDungeon.player.energy.energyMaster > 0)
                AbstractDungeon.player.energy.energyMaster--;
        }
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster += 2;
    }
}
