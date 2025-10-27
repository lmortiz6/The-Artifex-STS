package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theartifex.TheArtifexMod;
import theartifex.cards.attacks.BloodGradientHandVacuum;
import theartifex.util.CustomAttackEffect;

import static java.lang.Math.min;

public class BloodGradientHandVacuumAction extends AbstractGameAction {
    private final boolean isMultiDamage;
    public int[] damage;

    public BloodGradientHandVacuumAction(AbstractCreature target, AbstractCreature source, int amount, DamageInfo.DamageType type) {
        setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
        this.isMultiDamage = false;
    }

    public BloodGradientHandVacuumAction(AbstractCreature target, AbstractCreature source, int[] amount, DamageInfo.DamageType type) {
        setValues(target, source, amount[0]);
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
        this.isMultiDamage = true;
    }

    public void update() {
        if (shouldCancelAction()) {
            this.isDone = true;
            return;
        }
        tickDuration();
        if (this.isDone) {
            int healAmount = 0;
            if (isMultiDamage) {
                for (int i = 0; i < (AbstractDungeon.getCurrRoom()).monsters.monsters.size(); i++) {
                    this.target = (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i);
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, CustomAttackEffect.BLOOD_GRADIENT, i != 0));
                    this.target.damage(new DamageInfo(source, damage[i], damageType));
                    if (this.target.lastDamageTaken > 0) {
                        if (this.target.hb != null && this.source.hb != null)
                            addToTop(new VFXAction(new BloodShotEffect(this.target.hb.cX, this.target.hb.cY, this.source.hb.cX, this.source.hb.cY, min(this.target.lastDamageTaken, 15))));
                        healAmount += this.target.lastDamageTaken;
                    }
                }
                if (healAmount > 0)
                    addToBot(new HealAction(this.source, this.source, healAmount));
                if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
                addToTop(new WaitAction(0.1F));
            } else {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, CustomAttackEffect.BLOOD_GRADIENT, false));
                this.target.damage(new DamageInfo(source, amount, damageType));
                if (this.target.lastDamageTaken > 0) {
                    if (this.target.hb != null && this.source.hb != null)
                        addToTop( new VFXAction(new BloodShotEffect(this.target.hb.cX, this.target.hb.cY, this.source.hb.cX, this.source.hb.cY, min(this.target.lastDamageTaken, 15))));
                    healAmount += this.target.lastDamageTaken;
                }
                if (healAmount > 0)
                    addToBot(new HealAction(this.source, this.source, healAmount));
                if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
                addToTop(new WaitAction(0.1F));
            }
        }
    }
}
