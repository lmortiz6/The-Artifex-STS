package theartifex.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theartifex.TheArtifexMod.makeID;

public class TinkerPower extends BasePower{

    public static final String POWER_ID = makeID(TinkerPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public TinkerPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.updateDescription();
    }

    @Override
    public void onSpecificTrigger() {
        flash();
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    public void updateDescription() {
        this.name = getPowerStrings(this.ID).NAME + " " + intToRoman(this.amount);
        if (amount > 1) {
            this.description = String.format(DESCRIPTIONS[0], (this.amount));
        } else {
            this.description = String.format(DESCRIPTIONS[1], (this.amount));
        }
    }

    @Override
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        if (this.amount > 0) {
            if (!this.isTurnBased) {
                this.greenColor2.a = c.a;
                c = this.greenColor2;
            }
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, intToRoman(this.amount), x, y, this.fontScale, c);
        } else if (this.amount < 0 && this.canGoNegative) {
            this.redColor2.a = c.a;
            c = this.redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, intToRoman(this.amount), x, y, this.fontScale, c);
        }
    }

    private String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder romanNumeral = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                romanNumeral.append(symbols[i]);
                num -= values[i];
            }
        }
        return romanNumeral.toString();
    }
}
