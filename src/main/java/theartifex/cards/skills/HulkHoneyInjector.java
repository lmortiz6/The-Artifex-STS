package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theartifex.abstracts.AbstractInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class HulkHoneyInjector extends AbstractInjector {

    public static final String ID = makeID(HulkHoneyInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int BUFF = 2;
    private static final int UPG_BUFF = 1;
    private static final int DEBUFF = 1;

    public HulkHoneyInjector() {
        super(ID, info);

        this.setMagic(BUFF, UPG_BUFF);
        this.setExhaust(true);
        tags.add(CustomCardTags.THEARTIFEXINJECTOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));

    }

    @Override
    public void adverseReaction() {
        super.adverseReaction();
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NoBlockPower(AbstractDungeon.player, 1, true)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HulkHoneyInjector();
    }
}
