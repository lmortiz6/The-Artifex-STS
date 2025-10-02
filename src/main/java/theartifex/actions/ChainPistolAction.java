package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.util.CustomAttackEffect;

public class ChainPistolAction extends AbstractGameAction {

    private static final int WEAK = 1;

    private boolean freeToPlayOnce;

    private int damage;

    private int[] multidamage;

    private AbstractPlayer p;

    private DamageInfo.DamageType damageTypeForTurn;

    private int energyOnUse;

    private boolean upgraded;

    private boolean isMultiDamage;

    public ChainPistolAction(AbstractPlayer source, int damage, int[] multidamage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse, boolean upgraded, boolean isMultiDamage) {
        this.p = source;
        this.damage = damage;
        this.multidamage = multidamage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
        this.isMultiDamage = isMultiDamage;
    }

    public void update() {
        DamageAllEnemiesAction action;
        if (isMultiDamage) {
            action = new DamageAllEnemiesAction(p, this.multidamage, this.damageTypeForTurn, CustomAttackEffect.CHAIN_PISTOL, true);
            addToTop(action);
        }
        else {
            AbstractCreature m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            addToTop(new DamageAction(m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), CustomAttackEffect.CHAIN_PISTOL));
        }
        this.isDone = true;
    }
}
