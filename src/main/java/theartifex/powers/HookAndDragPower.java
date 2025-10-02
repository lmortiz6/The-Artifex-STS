package theartifex.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.util.CustomAttackEffect;

import static theartifex.TheArtifexMod.makeID;

public class HookAndDragPower extends BasePower implements InvisiblePower{

    public static final String POWER_ID = makeID(HookAndDragPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private boolean isActive;

    public HookAndDragPower(AbstractCreature owner, AbstractCreature source, int amount){
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        this.amount = -1;
        isActive = false;
    }

    @Override
    public void atStartOfTurn() {
        isActive = true;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
            if (m.hasPower(makeID(HookedPower.class.getSimpleName()))) {
                addToBot(new RemoveSpecificPowerAction(m, owner, makeID(HookedPower.class.getSimpleName())));
            }
        addToBot(new RemoveSpecificPowerAction(this.source, this.source, POWER_ID));
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (isActive) {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters)
                if (m.hasPower(makeID(HookedPower.class.getSimpleName()))) {
                    //CardCrawlGame.sound.playV(makeID("DRAG"), 1.8f); // Sound Effect
                    //addToTop(new LoseHPAction(m, owner, m.getPower(makeID(HookedPower.class.getSimpleName())).amount));
                    addToTop(new DamageAction(m, new DamageInfo(AbstractDungeon.player, m.getPower(makeID(HookedPower.class.getSimpleName())).amount, DamageInfo.DamageType.HP_LOSS), CustomAttackEffect.CARBIDE_AXE));
                }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
