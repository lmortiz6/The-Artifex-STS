package theartifex.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static theartifex.TheArtifexMod.makeID;

public class CarbideHandBonesRelic extends BaseRelic{
    private static final String NAME = CarbideHandBonesRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.SOLID;

    public CarbideHandBonesRelic() {
        super(ID, NAME, RARITY, SOUND);
        this.tips.add(new PowerTip(TipHelper.capitalize("cybernetic"), "Cybernetic relics can be unimplanted at #yRest #ySites to gain their respective card."));
    }

    @Override
    public void atBattleStart() {
        updateExistingStrikes();
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

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
