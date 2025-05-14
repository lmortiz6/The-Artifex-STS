package theartifex.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import theartifex.TheArtifexMod;

@SpirePatch(
        clz = CampfireUI.class,
        method = "initializeButtons"
)
public class DebugPatch {
    public DebugPatch() {
    }

    public static void Prefix(CampfireUI __instance) {
        TheArtifexMod.logger.info("getMaxCredits: " + TheArtifexMod.getMaxCredits());
        TheArtifexMod.logger.info("getAvailableCredits: " + TheArtifexMod.getAvailableCredits());
        TheArtifexMod.logger.info("hasCyberneticRelic: " + TheArtifexMod.hasCyberneticRelic());
        TheArtifexMod.logger.info("hasCyberneticCard: " + TheArtifexMod.hasCyberneticCard());
    }
}
