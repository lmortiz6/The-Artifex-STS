package theartifex.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.TheArtifexMod;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class NachamsRibbonRelic extends BaseRelic{
    private static final String NAME = NachamsRibbonRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;
    private static final int CHANCE = 15;

    private final ArrayList<AbstractCard> doNotMod = new ArrayList<>();
    private final ArrayList<AbstractCard> isModded = new ArrayList<>();

    public NachamsRibbonRelic() {
        super(ID, NAME, TheArtifexCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        this.tips.add(new PowerTip(TipHelper.capitalize("Card Mod"), "Card mods grant additional effects when played. Cards can have a maximum of 2 mods."));
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onPreviewObtainCard(AbstractCard c) {
        int roll = AbstractDungeon.relicRng.random(0, CHANCE - 1);
        if (roll == 0) {
            modCard(c);
            TheArtifexMod.logger.info("Nachams Hit : " + roll);
        } else {
            doNotMod.add(c);
            TheArtifexMod.logger.info("Nachams Miss : " + roll);
        }
    }

    public void onObtainCard(AbstractCard c) {
        if (doNotMod.contains(c) || isModded.contains(c))
            return;
        int roll = AbstractDungeon.relicRng.random(0, CHANCE - 1);
        if (roll == 0) {
            modCard(c);
            TheArtifexMod.logger.info("Nachams Hit : " + roll);
        } else {
            TheArtifexMod.logger.info("Nachams Miss : " + roll);
        }
    }

    @Override
    public void atBattleStart() {
        doNotMod.clear();
        isModded.clear();
    }

    private void modCard(AbstractCard c) {
        if (!CustomCardTags.getMods(c).isEmpty() || c.cost == -2  || c.color != TheArtifexCharacter.Meta.CARD_COLOR)
            return;
        CustomCardTags.loadRandomMod(c, true);
        isModded.add(c);
    }

    public AbstractRelic makeCopy() {
        return new NachamsRibbonRelic();
    }
}
