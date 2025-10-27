package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.SpreadShotAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class SpreadShot extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(SpreadShot.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1
    );
    private final static int MOD = 1;
    private final static int UPG_MOD = 1;

    public SpreadShot() {
        super(ID, info);
        this.exhaust = true;
        this.setSelfRetain(true);
        setMagic(MOD, UPG_MOD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SpreadShotAction(p, magicNumber, this));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpreadShot();
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playV(makeID("LEARN_SCHEMATIC"), 1.6f);
    }
}
