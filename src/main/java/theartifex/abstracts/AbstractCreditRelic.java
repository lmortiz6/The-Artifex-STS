package theartifex.abstracts;

import theartifex.relics.BaseRelic;

public class AbstractCreditRelic extends BaseRelic {

    private final int amount;

    public AbstractCreditRelic(String id, String imageName, RelicTier tier, LandingSound sfx, int amount) {
        super(id, imageName, tier, sfx);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
