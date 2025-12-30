package theartifex.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theartifex.character.TheArtifexCharacter;

import static com.megacrit.cardcrawl.ui.panels.EnergyPanel.energyVfxTimer;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "renderVfx"
)
public class OrbVfxPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(EnergyPanel __instance, @ByRef SpriteBatch[] sb, Color ___energyVfxColor, Texture ___gainEnergyImg, float ___energyVfxScale) {
        if (AbstractDungeon.player instanceof TheArtifexCharacter) {
            if (energyVfxTimer != 0.0F) {
                sb[0].setBlendFunction(770, 1);
                sb[0].setColor(___energyVfxColor);
                sb[0].draw(___gainEnergyImg, __instance.current_x - 128.0F, __instance.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0, 0, 0, 256, 256, false, false);
                sb[0].draw(___gainEnergyImg, __instance.current_x - 128.0F, __instance.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, Settings.scale, Settings.scale, 0, 0, 0, 256, 256, false, false);
                sb[0].setBlendFunction(770, 771);
            }
            return SpireReturn.Return();
        } else
            return SpireReturn.Continue();
    }
}
