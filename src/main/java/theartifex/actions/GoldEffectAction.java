package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class GoldEffectAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public GoldEffectAction(int amount) {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        for (int i = 0; i < this.amount; i++) {
            AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, true));
        }
        this.isDone = true;
    }
}
