package theartifex.abstracts;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theartifex.TheArtifexMod;
import theartifex.character.TheArtifexCharacter;
import theartifex.relics.BaseRelic;

import static theartifex.TheArtifexMod.makeID;

public class AbstractCreditRelic extends BaseRelic {

    private final int amount;

    public AbstractCreditRelic(String id, String imageName, RelicTier tier, LandingSound sfx, int amount) {
        super(id, imageName, TheArtifexCharacter.Meta.CARD_COLOR, tier, sfx);
        this.amount = amount;
        String header = CardCrawlGame.languagePack.getRelicStrings(makeID("AbstractCredRelic")).NAME;
        String body = CardCrawlGame.languagePack.getRelicStrings(makeID("AbstractCredRelic")).DESCRIPTIONS[0];
        this.tips.add(new PowerTip(header, body));
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
