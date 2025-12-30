package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import theartifex.actions.MedassistModuleAction;

@SpirePatch(
        clz = MonsterRoom.class,
        method = "onPlayerEntry"
)
public class MedassistModuleBattleStartPatch {
    @SpirePrefixPatch
    public static void Prefix(MonsterRoom __instance) {
        MedassistModuleAction.modded = false;
    }
}
