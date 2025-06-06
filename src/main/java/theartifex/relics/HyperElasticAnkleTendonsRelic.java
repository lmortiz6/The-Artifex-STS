package theartifex.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.powers.HyperElasticAnkleTendons;
import theartifex.util.CustomCardTags;

import static theartifex.TheArtifexMod.makeID;

public class HyperElasticAnkleTendonsRelic extends AbstractCyberneticRelic {
    private static final String NAME = HyperElasticAnkleTendonsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SPECIAL;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final String card = makeID(HyperElasticAnkleTendons.class.getSimpleName());
    private static final int cost = HyperElasticAnkleTendons.creditCost;

    public HyperElasticAnkleTendonsRelic() {
        super(ID, NAME, RARITY, SOUND, card, cost);
    }

    @Override
    public void atBattleStart() {
        updateExistingSprints();
    }

    private void updateExistingSprints() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(CustomCardTags.SPRINT)) {
                c.exhaust = false;
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(CustomCardTags.SPRINT)) {
                c.exhaust = false;
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(CustomCardTags.SPRINT)) {
                c.exhaust = false;
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.hasTag(CustomCardTags.SPRINT)) {
                c.exhaust = false;
                c.magicNumber += 1;
                c.isMagicNumberModified = true;
                c.initializeDescription();
            }
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        // if a card spawns a sprint
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
