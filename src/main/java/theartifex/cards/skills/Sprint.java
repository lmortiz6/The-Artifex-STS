package theartifex.cards.skills;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.TheArtifexMod;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.relics.HyperElasticAnkleTendonsRelic;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class Sprint extends BaseCard {

    public static final String ID = makeID(Sprint.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.NONE,
            0
    );
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;
    private static final int DISCARD = 1;

    public Sprint() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setMagic(DRAW, UPG_DRAW);
        this.setExhaust(true);
        this.tags.add(CustomCardTags.THEARTIFEXSPRINT);
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.playAV(makeID("SPRINT"), 0f, 1.0f); // Sound Effect
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new DiscardAction(p, p, DISCARD, false));
    }

    @Override
    public void applyPowers() {

        super.applyPowers();

        this.magicNumber = this.baseMagicNumber;

        if (AbstractDungeon.player != null) {
            if (AbstractDungeon.player.hasPower("theartifex:HyperElasticAnkleTendonsPower")) {
                this.magicNumber += AbstractDungeon.player.getPower("theartifex:HyperElasticAnkleTendonsPower").amount;
                this.isMagicNumberModified = true;
            }
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof HyperElasticAnkleTendonsRelic) {
                    this.magicNumber += 1;
                    this.isMagicNumberModified = true;
                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Sprint();
    }
}
