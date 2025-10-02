package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.TheArtifexMod;
import theartifex.actions.BoundingBootsAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class BoundingBoots extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(BoundingBoots.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            1
    );

    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;

    public BoundingBoots() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DRAW, UPG_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new SpringBootsAction(magicNumber));
        TheArtifexMod.logger.info(DrawCardAction.drawnCards.size());
        CardCrawlGame.sound.playAV(makeID("SPRINT"), 0f, 1.0f); // Sound Effect
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new BoundingBootsAction(magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BoundingBoots();
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playV(makeID("LEARN_SCHEMATIC"), 1.6f); // Sound Effect
    }
}
