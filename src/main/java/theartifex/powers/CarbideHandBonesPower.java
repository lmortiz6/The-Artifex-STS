package theartifex.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class CarbideHandBonesPower extends BasePower{

    public static final String POWER_ID = makeID(CarbideHandBonesPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private ArrayList<Boolean> used;

    public CarbideHandBonesPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, source, amount);
        updateExistingStrikes();
        updateDescription();
    }

    private void updateExistingStrikes() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
                c.baseDamage *= 1.5F;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
                c.baseDamage *= 1.5F;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
                c.baseDamage *= 1.5F;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
                c.baseDamage *= 1.5F;
            }
        }
    }

    public void updateDescription() {
        /*System.out.println("Carbide Update Description");
        if (used == null) {
            used = new ArrayList<Boolean>();
        }
        System.out.println(used.toString());

         */
        if (this.amount == 1) {
            this.description = String.format(DESCRIPTIONS[1], new Object[]{this.amount});
        } else {
            this.description = String.format(DESCRIPTIONS[0], new Object[]{Math.round(Math.pow(1.5, amount) * 100.0) / 100.0});
        }

        /*System.out.println("used.size() : " + used.size());
        System.out.println("amount : " + amount);

        if (used.size() < amount) {
            for (int i = 0; i < amount - used.size(); i++)
                used.add(false);
        }

        int total = 0;
        for (int i = 0; i < used.size(); i++) {
            boolean spent = used.get(i);
            if (!spent) {
                total += 1;
                used.set(i, true);
            }
        }
        System.out.println("total : " + total);
        for (int i = 0; i < total; i++) {
            updateExistingStrikes();
        }
        System.out.println(used.toString());

         */
    }
}
