package theartifex.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.DeathScreen;
import theartifex.character.TheArtifexCharacter;

import static theartifex.TheArtifexMod.makeID;

public class AmaranthinePrismRelic extends BaseRelic {

    private static final String NAME = AmaranthinePrismRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private static final int BONUS_AMOUNT = 2;
    private static final int CYCLE = 6;

    private int bonus;
    private int bonusThisCombat;

    public AmaranthinePrismRelic() {
        super(ID, NAME, TheArtifexCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        bonus = BONUS_AMOUNT;
        bonusThisCombat = bonus;
        counter = 0;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void beforeEnergyPrep() {
        counter++;
        if (counter == CYCLE) {
            counter = 0;
            flash();
            bonus -= 1;
        }

        bonusThisCombat = bonus;
        AbstractDungeon.player.energy.energyMaster += bonusThisCombat;

        if (AbstractDungeon.player.energy.energyMaster == 0) {
            AbstractDungeon.closeCurrentScreen();
            AbstractDungeon.player.isDead = true;
            AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
        }
    }

    public void onVictory() {
        AbstractDungeon.player.energy.energyMaster -= bonusThisCombat;
    }
}
