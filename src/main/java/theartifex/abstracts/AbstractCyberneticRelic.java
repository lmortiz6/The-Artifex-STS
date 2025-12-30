package theartifex.abstracts;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import theartifex.relics.BaseRelic;

import static theartifex.TheArtifexMod.makeID;

public class AbstractCyberneticRelic extends BaseRelic {
    private final int cost;
    private final String cardID;
    public AbstractCyberneticRelic(String id, String imageName, RelicTier tier, LandingSound sfx, String cardID, int cost) {
        super(id, imageName, tier, sfx);
        this.cardID = cardID;
        this.cost = cost;
        String header = CardCrawlGame.languagePack.getRelicStrings(makeID("AbstractCyberRelic")).NAME;
        String body = CardCrawlGame.languagePack.getRelicStrings(makeID("AbstractCyberRelic")).DESCRIPTIONS[0];
        this.tips.add(new PowerTip(header, body));
    }

    public int getCost() {
        return cost;
    }

    public String getCard() {
        return cardID;
    }
}
