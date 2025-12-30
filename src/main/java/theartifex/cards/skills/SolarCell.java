package theartifex.cards.skills;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.SanctityEffect;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class SolarCell extends BaseCard {

    public static final String ID = makeID(SolarCell.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.NONE,
            -2
    );
    private static final int ENERGY = 1;
    private static final int UPG_ENERGY = 1;

    public SolarCell() {
        super(ID, info);
        setMagic(ENERGY, UPG_ENERGY);
    }

    @Override
    public void triggerWhenDrawn() {
        this.initializeDescription();
        if (this.baseMagicNumber > 0) {
            addToTop(new GainEnergyAction(magicNumber));
            addToTop(new VFXAction(new SanctityEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
            addToTop(new SFXAction("HEAL_1"));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.dontTriggerOnUseCard && this.baseMagicNumber > 0) {
            addToTop(new WaitAction(0.1F));
            addToTop(new SFXAction("ORB_PLASMA_EVOKE"));
            addToTop(new WaitAction(0.1F));
            addToTop(new WaitAction(0.1F));
            this.superFlash(Color.WHITE.cpy());
            this.baseMagicNumber--;
            this.resetAttributes();
            this.initializeDescription();
        }
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        if (this.baseMagicNumber > 0) {
            this.dontTriggerOnUseCard = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
        }
    }

    @Override
    public void initializeDescription() {
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null &&
                (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
            if (cardStrings != null) {
                if (this.magicNumber > 0) {
                    StringBuilder sb = new StringBuilder();
                    int index1 = 43;
                    if (Settings.language == Settings.GameLanguage.ZHS) {
                        index1 = 22;
                    }
                    sb.append(cardStrings.DESCRIPTION, 0, index1);
                    for (int i = 0; i < this.magicNumber; i++)
                        sb.append(" [E]");
                    sb.append(cardStrings.DESCRIPTION, index1 + 4, cardStrings.DESCRIPTION.length());
                    this.rawDescription = sb.toString();
                } else {
                    if (Settings.language == Settings.GameLanguage.ZHS)
                        this.rawDescription = " 不能被打出 。";
                    else
                        this.rawDescription = "Unplayable.";
                }
            }
        }
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new SolarCell();
    }
}
