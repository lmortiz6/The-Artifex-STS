package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.NextTurnDrawReductionPower;
import theartifex.util.CardStats;

public class MagnetizedChassisPlate extends BaseCard {

    public static final String ID = makeID(MagnetizedChassisPlate.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    private static final int BLOCK = 20;
    private static final int UPG_BLOCK = 5;
    private static final int DEBUFF = 1;

    public MagnetizedChassisPlate() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new NextTurnDrawReductionPower(p, p, DEBUFF), DEBUFF));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagnetizedChassisPlate();
    }
}
