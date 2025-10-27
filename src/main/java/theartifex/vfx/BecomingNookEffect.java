package theartifex.vfx;

import basemod.devcommands.relic.RelicAdd;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCyberneticCard;

import java.util.ArrayList;
import java.util.Iterator;

import static theartifex.TheArtifexMod.makeID;

public class BecomingNookEffect extends AbstractGameEffect {

    public boolean openedScreen = false;
    public boolean relicSelect = false;
    public boolean cardSelect = false;
    public boolean confirmSelect = false;
    public boolean uninstalled = false;
    private Color screenColor;

    private CardGroup cards;
    private CardGroup relics;
    public ArrayList<AbstractCard> cardsToAdd;
    public ArrayList<AbstractCard> cardsToRemove;
    public ArrayList<String> relicsToAdd;
    public ArrayList<String> relicsToRemove;
    public int credits;
    private boolean hasCyberneticRelic;
    private boolean hasCyberneticCard;

    public BecomingNookEffect() {
        cards = TheArtifexMod.getCyberneticCards();
        relics = TheArtifexMod.getCyberneticRelicsAsCards();
        cardsToAdd = new ArrayList<>();
        cardsToRemove = new ArrayList<>();
        relicsToAdd = new ArrayList<>();
        relicsToRemove = new ArrayList<>();
        credits = TheArtifexMod.getAvailableCredits();
        hasCyberneticRelic = TheArtifexMod.hasCyberneticRelic();
        hasCyberneticCard = TheArtifexMod.hasCyberneticCard();

        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.duration = 1.5F;
        this.screenColor.a = 0.0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        CardCrawlGame.sound.playV(makeID("BECOMING_NOOK"), 1.3f);
    }

    public void update() {
        if (!AbstractDungeon.isScreenUp) {
            this.duration -= Gdx.graphics.getDeltaTime();
            this.updateBlackScreenColor();
        }

        Iterator var1;
        if (!AbstractDungeon.isScreenUp && relicSelect && !this.isDone) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                uninstalled = true;
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
                while (var1.hasNext()) {
                    AbstractCyberneticCard c = (AbstractCyberneticCard) var1.next();
                    relicsToRemove.add(c.getRelic());
                    cardsToAdd.add(c);
                    credits += c.cost;
                }
            }
            if (!relicsToRemove.isEmpty())
                CardCrawlGame.sound.playV(makeID("UNIMPLANT"), 1.3f);
            relicSelect = false;
            TheArtifexMod.gridScreenForCyberRelics = false;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            if (hasCyberneticCard) {
                cardSelect = true;
                TheArtifexMod.gridScreenForCyberCards = true;
                AbstractDungeon.gridSelectScreen.open(cards, 10, true, "Select Cybernetics to install.\n(Credit Cost = Energy Cost)");
            } else {
                confirmSelect = true;
            }
        }

        if (!AbstractDungeon.isScreenUp && cardSelect && !this.isDone) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
                while (var1.hasNext()) {
                    AbstractCyberneticCard c = (AbstractCyberneticCard) var1.next();
                    cardsToRemove.add(c);
                    relicsToAdd.add(c.getRelic());
                }
            }
            if (!cardsToRemove.isEmpty())
                CardCrawlGame.sound.playV(makeID("IMPLANT"), 1.3f);
            cardSelect = false;
            TheArtifexMod.gridScreenForCyberCards = false;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            confirmSelect = true;
        }

        if (!AbstractDungeon.isScreenUp && confirmSelect) {
            var1 = relicsToRemove.iterator();
            while (var1.hasNext()) {
                String r = (String) var1.next();
                AbstractDungeon.player.loseRelic(r);
            }
            var1 = relicsToAdd.iterator();
            while(var1.hasNext()) {
                String r = (String) var1.next();
                RelicAdd.execute(new String[]{"relic", "add", r});
            }
            var1 = cardsToRemove.iterator();
            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
                    if (card.cardID.equals(c.cardID)) {
                        //AbstractDungeon.effectList.add(new PurgeCardEffect(card, Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        AbstractDungeon.player.masterDeck.removeCard(card);
                        break;
                    }
                }
            }
            var1 = cardsToAdd.iterator();
            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                //AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c.makeCopy(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                AbstractDungeon.player.masterDeck.addToTop(c.makeCopy());

            }
            confirmSelect = false;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        if (this.duration < 1.0F && !this.openedScreen) {
            this.openedScreen = true;
            if (hasCyberneticRelic) {
                this.relicSelect = true;
                TheArtifexMod.gridScreenForCyberRelics = true;
                AbstractDungeon.gridSelectScreen.open(relics, 10, true, "Select Cybernetics to uninstall.");
            } else if (hasCyberneticCard) {
                this.cardSelect = true;
                TheArtifexMod.gridScreenForCyberCards = true;
                AbstractDungeon.gridSelectScreen.open(cards, 10, true, "Select Cybernetics to install.\n(Credit Cost = Energy Cost)");
            }
        }

        if (this.duration < 0.0F) {
            AbstractDungeon.overlayMenu.cancelButton.hideInstantly();
            this.isDone = true;
            if (CampfireUI.hidden) {
                AbstractRoom.waitTimer = 0.0F;
                (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
                ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
            }

        }
    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float) Settings.WIDTH, (float) Settings.HEIGHT);
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
            AbstractDungeon.gridSelectScreen.render(sb);
        }

    }

    @Override
    public void dispose() {
    }
}
