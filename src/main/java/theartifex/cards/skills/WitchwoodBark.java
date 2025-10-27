package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class WitchwoodBark extends BaseCard {

    public static final String ID = makeID(WitchwoodBark.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            0
    );
    private static final int REGEN = 3;
    private static final int UPG_REGEN = 1;

    public WitchwoodBark() {
        super(ID, info);

        this.setMagic(REGEN, UPG_REGEN);
        this.setExhaust(true);

        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.playV(makeID("WITCHWOOD"), 1.2f);
        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new WitchwoodBark();
    }
}
