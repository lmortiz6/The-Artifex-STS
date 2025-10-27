package theartifex.abstracts;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.util.CardStats;

public class AbstractCyberneticCard extends BaseCard {

    private final String relicID;

    public AbstractCyberneticCard(String ID, CardStats info, String relicID) {
        super(ID, info);
        this.relicID = relicID;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public void upgrade() {}

    public String getRelic() {
        return relicID;
    }
}
