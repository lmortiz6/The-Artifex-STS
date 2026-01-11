package theartifex.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.actions.OtherpearlAction;
import theartifex.character.TheArtifexCharacter;

import static theartifex.TheArtifexMod.makeID;

public class OtherpearlRelic extends BaseRelic{
    private static final String NAME = OtherpearlRelic.class.getSimpleName();
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;

    public boolean active;

    public OtherpearlRelic() {
        super(ID, NAME, TheArtifexCharacter.Meta.CARD_COLOR, RARITY, SOUND);
        active = false;
        this.tips.add(new PowerTip(CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[4], CardCrawlGame.languagePack.getUIString(makeID("UI")).EXTRA_TEXT[5]));
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        active = true;
    }

    public void onCardMod() {
        addToBot(new OtherpearlAction());
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new OtherpearlRelic();
    }
}
