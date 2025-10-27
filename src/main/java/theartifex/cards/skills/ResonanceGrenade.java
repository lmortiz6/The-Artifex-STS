package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.ResonanceGrenadePower;
import theartifex.util.CardStats;

public class ResonanceGrenade extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(ResonanceGrenade.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 4;
    private static final int VULN = 2;
    private static final int UPG_VULN = 0;
    private static final int TURNS = 2;

    public ResonanceGrenade() {
        super(ID, info);

        this.setMagic(DAMAGE, UPG_DAMAGE);
        this.setCustomVar("vuln", VULN, UPG_VULN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.playV(makeID("GRENADE_THROW"), 1.4f);
        addToBot(new ApplyPowerAction(p, p, new ResonanceGrenadePower(p, p, TURNS, this.magicNumber, this.customVar("vuln"))));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ResonanceGrenade();
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playV(makeID("TINKER_BUILD"), 1.3f);
    }
}
