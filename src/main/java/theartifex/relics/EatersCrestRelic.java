package theartifex.relics;

import basemod.devcommands.deck.DeckAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCreditRelic;
import theartifex.abstracts.AbstractCyberneticCard;

import java.util.ArrayList;

import static theartifex.TheArtifexMod.makeID;

public class EatersCrestRelic extends AbstractCreditRelic {

    private static final String NAME = EatersCrestRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.SHOP;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;
    private static final int AMOUNT = 3;

    private final ArrayList<AbstractCard> possibleCards = new ArrayList<>();

    public EatersCrestRelic() {
        super(ID, NAME, RARITY, SOUND, AMOUNT);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        for (AbstractCard c : CardLibrary.getAllCards()) {
            if (c instanceof AbstractCyberneticCard && c.rarity == AbstractCard.CardRarity.RARE && c.cost <= TheArtifexMod.getAvailableCredits()) {
                possibleCards.add(c);
            }
        }
        int i = AbstractDungeon.relicRng.random(possibleCards.size() - 1);
        DeckAdd.execute(new String[] {"deck", "add", possibleCards.get(i).cardID});
    }
}
