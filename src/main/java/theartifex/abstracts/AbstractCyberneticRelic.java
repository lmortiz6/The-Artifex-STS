package theartifex.abstracts;

import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import theartifex.character.TheArtifexCharacter;
import theartifex.relics.BaseRelic;

public class AbstractCyberneticRelic extends BaseRelic {
    private final int cost;
    private final String cardID;
    public AbstractCyberneticRelic(String id, String imageName, RelicTier tier, LandingSound sfx, String cardID, int cost) {
        super(id, imageName, TheArtifexCharacter.Meta.CARD_COLOR, tier, sfx);
        this.cardID = cardID;
        this.cost = cost;
        this.tips.add(new PowerTip(TipHelper.capitalize("cybernetic"), "Cybernetic relics can be unimplanted at #yRest #ySites to gain their respective card."));
    }

    public int getCost() {
        return cost;
    }

    public String getCard() {
        return cardID;
    }
}
