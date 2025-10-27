package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.JackInAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class JackIn extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(JackIn.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            3
    );
    private static final int BUFF = 2;
    private static final int UPG_BUFF = 0;

    public JackIn() {
        super(ID, info);

        this.setMagic(BUFF, UPG_BUFF);
        this.setEthereal(true);
        this.setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JackInAction(p, this.magicNumber, this));
    }

    @Override
    public AbstractCard makeCopy() {
        return new JackIn();
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playV(makeID("LEARN_SCHEMATIC"), 1.6f);
    }
}
