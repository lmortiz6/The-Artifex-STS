package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.ui.buttons.CancelButton;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractCyberneticRelic;

@SpirePatch(
        clz = CancelButton.class,
        method = "update"
)
public class CyberneticGridCancelActionPatch {

    @SpirePrefixPatch
    public static void Prefix(CancelButton obj) {
        if (!obj.isHidden) {
            obj.hb.update();

            if (obj.hb.clicked || (InputHelper.pressedEscape || CInputActionSet.cancel.isJustPressed()) && obj.current_x != CancelButton.HIDE_X) {

                if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID && TheArtifexMod.gridScreenForCyberCards) {
                    if (!AbstractDungeon.gridSelectScreen.confirmScreenUp) {
                        CardGroup relics = TheArtifexMod.getCyberneticRelicsAsCards();
                        if (TheArtifexMod.currBecomingNookEffect != null) {
                            TheArtifexMod.currBecomingNookEffect.relicSelect = false;
                            TheArtifexMod.currBecomingNookEffect.cardSelect = false;
                            TheArtifexMod.currBecomingNookEffect.openedScreen = false;
                            for (String r : TheArtifexMod.currBecomingNookEffect.relicsToRemove) {
                                AbstractCyberneticRelic cr = (AbstractCyberneticRelic) RelicLibrary.getRelic(r);
                                TheArtifexMod.currBecomingNookEffect.credits -= cr.getCost();
                            }
                            TheArtifexMod.currBecomingNookEffect.relicsToRemove.clear();
                            TheArtifexMod.currBecomingNookEffect.cardsToAdd.clear();
                        }
                        TheArtifexMod.gridScreenForCyberCards = false;
                        if (TheArtifexMod.hasCyberneticRelic()) {
                            //TheArtifexMod.gridScreenForCyberRelics = true;
                            AbstractDungeon.gridSelectScreen.selectedCards.clear();
                            //AbstractDungeon.gridSelectScreen.open(relics, 10, true, "Select any number of Cybernetics to uninstall.");
                        } else {
                            TheArtifexMod.gridScreenForCyberRelics = false;
                            AbstractDungeon.gridSelectScreen.selectedCards.clear();
                            TheArtifexMod.currBecomingNookEffect.isDone = true;
                            AbstractDungeon.closeCurrentScreen();
                            if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
                                RestRoom r = (RestRoom) AbstractDungeon.getCurrRoom();
                                r.campfireUI.reopen();
                            }
                        }
                    }
                }

                if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID && TheArtifexMod.gridScreenForCyberRelics) {
                    if (!AbstractDungeon.gridSelectScreen.confirmScreenUp) {
                        TheArtifexMod.gridScreenForCyberRelics = false;
                        TheArtifexMod.gridScreenForCyberCards = false;
                        TheArtifexMod.currBecomingNookEffect.isDone = true;
                        AbstractDungeon.closeCurrentScreen();
                        if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
                            RestRoom r = (RestRoom) AbstractDungeon.getCurrRoom();
                            r.campfireUI.reopen();
                        }
                    }
                }
            }
        }
    }
}