package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.EMPGrenadePower;
import theartifex.util.CardStats;

public class EMPGrenade extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(EMPGrenade.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int DEBUFF = 1;
    private static final int UPG_DEBUFF = 1;
    private static final int TURNS = 2;

    public EMPGrenade() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DEBUFF, UPG_DEBUFF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.playV(makeID("GRENADE_THROW"), 1.4f); // Sound Effect
        addToBot(new ApplyPowerAction(p, p, new EMPGrenadePower(p, p, TURNS, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EMPGrenade();
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playV(makeID("TINKER_BUILD"), 1.3f); // Sound Effect
    }
}
