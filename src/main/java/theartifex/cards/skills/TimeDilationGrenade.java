package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.TimeDilationGrenadePower;
import theartifex.util.CardStats;

public class TimeDilationGrenade extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(TimeDilationGrenade.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;
    private static final int TURNS = 1;

    public TimeDilationGrenade() {
        super(ID, info);

        this.setMagic(DRAW, UPG_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.playV(makeID("GRENADE_THROW"), 1.4f);
        addToBot(new ApplyPowerAction(p, p, new TimeDilationGrenadePower(p, p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TimeDilationGrenade();
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playV(makeID("TINKER_BUILD"), 1.3f);
    }
}
