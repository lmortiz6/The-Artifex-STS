package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theartifex.abstracts.AbstractInjector;
import theartifex.actions.SkulkInjectorAction;
import theartifex.cards.attacks.ShieldBash;
import theartifex.cards.powers.SwiftReflexes;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.AmnesiaPower;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class SkulkInjector extends AbstractInjector implements OnObtainCard {

    public static final String ID = makeID(SkulkInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BUFF = 2;
    private static final int UPG_BUFF = 1;

    public SkulkInjector() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(BUFF, UPG_BUFF);
        this.setExhaust(true);
        tags.add(CustomCardTags.THEARTIFEXINJECTOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new SkulkInjectorAction(p, magicNumber, this));
    }

    @Override
    public void adverseReaction() {
        super.adverseReaction();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AmnesiaPower(AbstractDungeon.player, AbstractDungeon.player, 2)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SkulkInjector();
    }

    @Override
    public void onObtainCard() {
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if ((c.cardID.equals(this.cardID) && c.uuid != this.uuid) || c.cardID.equals(SwiftReflexes.ID)) {
                return;
            }
        }
        CardCrawlGame.sound.playV(makeID("LEARN_SCHEMATIC"), 1.6f); // Sound Effect
    }
}
