package theartifex.abstracts;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.cards.BaseCard;
import theartifex.util.CardStats;

public class AbstractCyberneticCard extends BaseCard {

    private final AbstractRelic relic;

    public AbstractCyberneticCard(String ID, CardStats info, AbstractRelic relic) {
        super(ID, info);
        this.relic = relic;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    public AbstractRelic getRelic() {
        return relic;
    }
}
