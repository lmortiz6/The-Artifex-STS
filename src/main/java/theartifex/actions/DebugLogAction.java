package theartifex.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.TheArtifexMod;

public class DebugLogAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private String log;

    public DebugLogAction(String log) {
        this.p = AbstractDungeon.player;
        this.amount = 0;
        setValues(this.p, this.p, this.amount);
        this.duration = Settings.ACTION_DUR_FAST;
        this.log = log;
    }

    public void update() {
        TheArtifexMod.logger.info(this.log);
        this.isDone = true;
    }
}
