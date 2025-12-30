package theartifex.cards.skills;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theartifex.actions.BecomeNullAction;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class BecomeNull extends BaseCard implements OnObtainCard {

    public static final String ID = makeID(BecomeNull.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            -1
    );

    public BecomeNull() {
        super(ID, info);
        this.exhaust = true;
        this.cardsToPreview = new VoidCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        if (effect > 0) {
            addToBot(new BecomeNullAction(this, effect));
            addToBot(new WaitAction(0.2F));
            if (!this.freeToPlayOnce)
                p.energy.use(EnergyPanel.totalCount);
        }
        if (!upgraded)
            addToBot(new MakeTempCardInDrawPileAction(new VoidCard(), 1, true, true));

    }

    @Override
    public void upgrade() {
        super.upgrade();
        this.cardsToPreview = null;
    }

    @Override
    public AbstractCard makeCopy() {
        return new BecomeNull();
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playV(makeID("LEARN_SCHEMATIC"), 1.6f);
    }
}
