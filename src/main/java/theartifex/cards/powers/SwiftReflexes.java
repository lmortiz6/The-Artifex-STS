package theartifex.cards.powers;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.cards.skills.SkulkInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.SwiftReflexesPower;
import theartifex.util.CardStats;

public class SwiftReflexes extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(SwiftReflexes.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            AbstractCard.CardType.POWER,
            AbstractCard.CardRarity.RARE,
            AbstractCard.CardTarget.SELF,
            1
    );

    public SwiftReflexes() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setInnate(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SwiftReflexesPower(p, p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SwiftReflexes();
    }

    @Override
    public void onObtainCard() {
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if ((c.cardID.equals(this.cardID) && c.uuid != this.uuid) || c.cardID.equals(SkulkInjector.ID)) {
                return;
            }
        }
        CardCrawlGame.sound.playV(makeID("LEARN_SCHEMATIC"), 1.6f); // Sound Effect
    }
}
