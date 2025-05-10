package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class FlurryAction extends AbstractGameAction {

    private static final int WEAK = 1;

    private boolean freeToPlayOnce;

    private int damage;

    private AbstractPlayer p;

    private DamageInfo.DamageType damageTypeForTurn;

    private int energyOnUse;

    private boolean upgraded;

    public FlurryAction(AbstractPlayer source, int damage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse, boolean upgraded) {
        this.p = source;
        this.damage = damage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (upgraded) {
            effect++;
        }
        if (effect > 0) {
            for (int i = 0; i < effect; i++){
                AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                addToBot(new DamageAction(m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                addToBot(new ApplyPowerAction(m, p, new WeakPower(m, WEAK, false)));
            }
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
