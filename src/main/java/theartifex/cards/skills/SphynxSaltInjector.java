package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EntanglePower;
import theartifex.abstracts.AbstractInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class SphynxSaltInjector extends AbstractInjector {

    public static final String ID = makeID(SphynxSaltInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            1
    );
    private static final int SCRY = 4;
    private static final int UPG_SCRY = 2;
    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public SphynxSaltInjector() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(SCRY, UPG_SCRY);
        this.setCustomVar("draw", DRAW, UPG_DRAW);
        this.setExhaust(true);
        tags.add(CustomCardTags.THEARTIFEXINJECTOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new ScryAction(this.magicNumber));
        addToBot(new DrawCardAction(p, this.customVar("draw")));
    }

    @Override
    public void adverseReaction() {
        super.adverseReaction();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EntanglePower(AbstractDungeon.player)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SphynxSaltInjector();
    }
}
