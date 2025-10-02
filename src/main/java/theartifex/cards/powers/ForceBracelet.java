package theartifex.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.ForceBraceletPower;
import theartifex.util.CardStats;

public class ForceBracelet extends BaseCard {

    public static final String ID = makeID(ForceBracelet.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            -1
    );
    private static final int BUFF = 3;
    private static final int DEBUFF = 3;

    public ForceBracelet() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        setMagic(BUFF); //Sets the card's damage and how much it changes when upgraded.
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
        if (upgraded) {
            effect++;
        }
        if (effect > 0) {
            int metallicizeAmount = magicNumber * effect;
            addToBot(new ApplyPowerAction(p, m, new MetallicizePower(p, metallicizeAmount), metallicizeAmount, true));
            addToBot(new ApplyPowerAction(p, m, new ForceBraceletPower(p, p, DEBUFF)));
            if (!this.freeToPlayOnce)
                p.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ForceBracelet();
    }
}
