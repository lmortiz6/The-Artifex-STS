package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class IronBuckler extends BaseCard {

    public static final String ID = makeID(IronBuckler.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 1;
    private static final int UPG_COST = 1;
    private static int count = 0;

    public IronBuckler() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyPowers();
        for (int i = 0; i < count; i++) {
            addToBot(new GainBlockAction(p, p, this.block));
        }
    }

    public void applyPowers() {
        count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c != this)
                count++;
        }
        count /= 2;
        super.applyPowers();
        this.setMagic(block*count);
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new IronBuckler();
    }
}
