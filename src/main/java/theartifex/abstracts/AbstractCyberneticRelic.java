package theartifex.abstracts;

import theartifex.relics.BaseRelic;

public class AbstractCyberneticRelic extends BaseRelic {
    private final int cost;
    public AbstractCyberneticRelic(String id, String imageName, RelicTier tier, LandingSound sfx, int cost) {
        super(id, imageName, tier, sfx);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
