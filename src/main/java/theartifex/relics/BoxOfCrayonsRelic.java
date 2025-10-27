package theartifex.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import theartifex.TheArtifexMod;

import static theartifex.TheArtifexMod.makeID;

public class BoxOfCrayonsRelic extends BaseRelic {

    private static final String NAME = BoxOfCrayonsRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;

    private boolean cardSelected = true;

    public BoxOfCrayonsRelic() {
        super(ID, NAME, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        cardSelected = false;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0; i < 20; i++) {
            AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
            boolean containsDupe = true;
            while (containsDupe) {
                containsDupe = false;
                for (AbstractCard c : group.group) {
                    if (c.cardID.equals(card.cardID)) {
                        containsDupe = true;
                        card = AbstractDungeon.getCard(AbstractDungeon.rollRarity()).makeCopy();
                    }
                }
            }
            if (!group.contains(card)) {
                for (AbstractRelic r : AbstractDungeon.player.relics)
                    r.onPreviewObtainCard(card);
                group.addToBottom(card);
            } else {
                i--;
            }
        }
        for (AbstractCard c : group.group)
            UnlockTracker.markCardAsSeen(c.cardID);
        AbstractDungeon.gridSelectScreen.open(group, 1, "Choose a Card", false);
    }

    @Override
    public void update() {
        super.update();
        if (!cardSelected && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = (AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeCopy();
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            cardSelected = true;
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
