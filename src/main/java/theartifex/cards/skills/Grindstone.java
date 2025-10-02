package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.GrindstoneAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class Grindstone extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(Grindstone.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static int BUFF = 1;
    private static int UPG_BUFF = 1;

    public Grindstone() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(BUFF, UPG_BUFF);
        this.setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GrindstoneAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Grindstone();
    }

    @Override
    public void onObtainCard() {
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if ((c.cardID.equals(this.cardID) && c.uuid != this.uuid) || c.cardID.equals(Juke.ID)) {
                return;
            }
        }
        CardCrawlGame.sound.playV(makeID("LEARN_SCHEMATIC"), 1.6f); // Sound Effect
    }
}
