package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.TheArtifexMod;

public class ChangeNonManualDiscardAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final boolean isNonManualDiscard;

    public ChangeNonManualDiscardAction(boolean isNonManualDiscard) {
        this.p = AbstractDungeon.player;
        setValues(this.p, this.p, 1);
        this.duration = Settings.ACTION_DUR_FAST;
        this.isNonManualDiscard = isNonManualDiscard;
    }

    public void update() {
        TheArtifexMod.nonManualDiscard = isNonManualDiscard;
        this.isDone = true;
    }
}
