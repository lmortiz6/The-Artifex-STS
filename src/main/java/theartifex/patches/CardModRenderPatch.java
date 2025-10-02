package theartifex.patches;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theartifex.TheArtifexMod;
import theartifex.util.CustomCardTags;

@SpirePatch(
        clz = AbstractCard.class,
        method =  "render",
        paramtypez = {
                SpriteBatch.class
        }
)
public class CardModRenderPatch {

    @SpirePostfixPatch
    public static void Postfix(AbstractCard __instance, @ByRef SpriteBatch[] sb) {
        if (!Settings.hideCards && !__instance.isFlipped) {
            int i = 0;
            for (AbstractCard.CardTags tag : CustomCardTags.getMods(__instance)) {
                Texture modTexture = null;
                if (tag == CustomCardTags.THEARTIFEXJACKED || tag == CustomCardTags.THEARTIFEXPERMANENTJACKED)
                    modTexture = TheArtifexMod.cardModTextures.get(0 + i);
                if (tag == CustomCardTags.THEARTIFEXREINFORCED || tag == CustomCardTags.THEARTIFEXPERMANENTREINFORCED)
                    modTexture = TheArtifexMod.cardModTextures.get(2 + i);
                if (tag == CustomCardTags.THEARTIFEXNULLING || tag == CustomCardTags.THEARTIFEXPERMANENTNULLING)
                    modTexture = TheArtifexMod.cardModTextures.get(4 + i);
                if (tag == CustomCardTags.THEARTIFEXBEAMSPLITTER || tag == CustomCardTags.THEARTIFEXPERMANENTBEAMSPLITTER)
                    modTexture = TheArtifexMod.cardModTextures.get(6 + i);
                if (tag == CustomCardTags.THEARTIFEXFLEXIWEAVED || tag == CustomCardTags.THEARTIFEXPERMANENTFLEXIWEAVED)
                    modTexture = TheArtifexMod.cardModTextures.get(8 + i);
                if (tag == CustomCardTags.THEARTIFEXSHARP || tag == CustomCardTags.THEARTIFEXPERMANENTSHARP)
                    modTexture = TheArtifexMod.cardModTextures.get(10 + i);
                if (tag == CustomCardTags.THEARTIFEXSPRINGLOADED || tag == CustomCardTags.THEARTIFEXPERMANENTSPRINGLOADED)
                    modTexture = TheArtifexMod.cardModTextures.get(12 + i);
                if (modTexture != null) {
                    float scale = __instance.drawScale * Settings.scale;
                    float drawX = __instance.current_x - 256.0F;
                    float drawY = __instance.current_y - 256.0F;
                    sb[0].draw(modTexture, drawX, drawY, 256.0F, 256.0F, 512.0F, 512.0F, scale, scale, __instance.angle, 0, 0, 512, 512, false, false);
                    i++;
                }
            }
        }
    }
}
