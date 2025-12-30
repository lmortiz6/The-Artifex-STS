package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theartifex.TheArtifexMod;

import java.util.ArrayList;

public class StartOfTurnDrawPatch {
    @SpirePatch(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class GameActionManagerPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(GameActionManager __instance) {
            int maxDraw = Math.min(AbstractDungeon.player.gameHandSize, AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size());
            TheArtifexMod.cardsDrawnAtTurnStart = Math.min(maxDraw, 10 - AbstractDungeon.player.hand.size());
            TheArtifexMod.logger.info("cardsDrawnAtTurnStart: " + TheArtifexMod.cardsDrawnAtTurnStart);
        }
    }

    @SpirePatch(
            clz = AbstractRoom.class,
            method = "update"
    )
    public static class AbstractRoomPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractRoom __instance) {
            int maxDraw = Math.min(AbstractDungeon.player.gameHandSize, AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size());
            TheArtifexMod.cardsDrawnAtTurnStart = Math.min(maxDraw, 10 - AbstractDungeon.player.hand.size());
            TheArtifexMod.logger.info("cardsDrawnAtTurnStart: " + TheArtifexMod.cardsDrawnAtTurnStart);
        }
    }

    public static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnCards");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }

    @SpirePatch(
            clz = AbstractRoom.class,
            method = "endBattle"
    )
    public static class EndBattlePatch {
        @SpirePrefixPatch
        public static void Prefix(AbstractRoom __instance) {
            TheArtifexMod.cardsDrawnAtTurnStart = 0;
        }
    }
}
