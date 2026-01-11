package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import theartifex.TheArtifexMod;
import theartifex.relics.CommonCreditRelic;
import theartifex.relics.RareCreditRelic;
import theartifex.relics.StartingCredits;

import static theartifex.TheArtifexMod.makeID;

public class CreditRewardPatch {
    @SpirePatch(
            clz = MonsterRoom.class,
            method = "dropReward"
    )
    public static class BossRewardPatch {
        @SpirePrefixPatch
        public static void Prefix(@ByRef MonsterRoom[] __instance) {
            if (__instance[0] instanceof MonsterRoomBoss && AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(makeID(StartingCredits.class.getSimpleName()))) {
                TheArtifexMod.logger.info(AbstractDungeon.actNum);
                if (AbstractDungeon.actNum == 1)
                    __instance[0].addRelicToRewards(new RareCreditRelic());
                else
                    __instance[0].addRelicToRewards(new CommonCreditRelic());
            }
        }
    }

    @SpirePatch(
            clz = MonsterRoomElite.class,
            method = "dropReward"
    )
    public static class EliteRewardPatch {
        @SpirePrefixPatch
        public static void Prefix(@ByRef MonsterRoom[] __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(makeID(StartingCredits.class.getSimpleName()))) {
                if (AbstractDungeon.relicRng.random(0, 5) == 0)
                    __instance[0].addRelicToRewards(new RareCreditRelic());
            }
        }
    }
}
