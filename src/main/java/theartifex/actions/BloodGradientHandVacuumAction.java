package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theartifex.util.CustomAttackEffect;

import static java.lang.Math.min;

public class BloodGradientHandVacuumAction extends AbstractGameAction {
    private DamageInfo info;
    private boolean isMultiDamage;

    public BloodGradientHandVacuumAction(AbstractCreature target, DamageInfo info, boolean isMultiDamage) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
        this.isMultiDamage = isMultiDamage;
    }

    public void update() {
        if (shouldCancelAction()) {
            this.isDone = true;
            return;
        }
        tickDuration();
        if (this.isDone) {
            if (isMultiDamage) {
                for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                    this.target = m;
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, CustomAttackEffect.BLOOD_GRADIENT, false));
                    this.target.damage(this.info);
                    if (this.target.lastDamageTaken > 0) {
                        if (this.target.hb != null && this.source.hb != null)
                            addToTop((AbstractGameAction) new VFXAction((AbstractGameEffect) new BloodShotEffect(this.target.hb.cX, this.target.hb.cY, this.source.hb.cX, this.source.hb.cY, min(this.target.lastDamageTaken, 15))));
                        addToTop(new HealAction(this.source, this.source, this.target.lastDamageTaken));
                    }
                    if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                        AbstractDungeon.actionManager.clearPostCombatActions();
                        break;
                    }
                }
            } else {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, CustomAttackEffect.BLOOD_GRADIENT, false));
                this.target.damage(this.info);
                if (this.target.lastDamageTaken > 0) {
                    if (this.target.hb != null && this.source.hb != null)
                        addToTop((AbstractGameAction) new VFXAction((AbstractGameEffect) new BloodShotEffect(this.target.hb.cX, this.target.hb.cY, this.source.hb.cX, this.source.hb.cY, min(this.target.lastDamageTaken, 15))));
                    addToTop(new HealAction(this.source, this.source, this.target.lastDamageTaken));
                }
                if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }
            }
        }
    }
}
