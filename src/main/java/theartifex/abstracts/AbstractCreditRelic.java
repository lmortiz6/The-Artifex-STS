package theartifex.abstracts;

import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import theartifex.TheArtifexMod;
import theartifex.character.TheArtifexCharacter;
import theartifex.relics.BaseRelic;

public class AbstractCreditRelic extends BaseRelic {

    private final int amount;

    public AbstractCreditRelic(String id, String imageName, RelicTier tier, LandingSound sfx, int amount) {
        super(id, imageName, TheArtifexCharacter.Meta.CARD_COLOR, tier, sfx);
        this.amount = amount;
        this.tips.add(new PowerTip(TipHelper.capitalize("cybernetic"), "Cybernetic cards can be implanted as permanent relics at #yRest #ySites. They cannot be upgraded."));
    }

    @Override
    public void onEquip() {
        super.onEquip();
        TheArtifexMod.maxCreditsFast = TheArtifexMod.getMaxCredits();
        TheArtifexMod.availableCreditsFast = TheArtifexMod.getAvailableCredits();
    }

    public int getAmount() {
        return amount;
    }
}
