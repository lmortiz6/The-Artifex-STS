package theartifex.patches;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theartifex.util.CustomAttackEffect;

import static theartifex.TheArtifexMod.makeID;

public class AttackEffectPatch {

    @SpirePatch(
            clz = FlashAtkImgEffect.class,
            method = "loadImage"
    )
    public static class VfxPatch
    {
        @SpirePrefixPatch
        public static SpireReturn<TextureAtlas.AtlasRegion> Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect ___effect) {

            if (___effect == CustomAttackEffect.CHAIN_PISTOL) {
                return SpireReturn.Return(ImageMaster.ATK_BLUNT_LIGHT);
            }
            else if (___effect == CustomAttackEffect.BLOOD_GRADIENT) {
                return SpireReturn.Return(ImageMaster.ATK_SLASH_RED);
            }
            else if (___effect == CustomAttackEffect.VIBROKHOPESH) {
                return SpireReturn.Return(ImageMaster.ATK_SLASH_V);
            }
            else if (___effect == CustomAttackEffect.CHROME_REVOLVER) {
                return SpireReturn.Return(ImageMaster.ATK_BLUNT_LIGHT);
            }
            else if (___effect == CustomAttackEffect.ISSACHAR_RIFLE) {
                return SpireReturn.Return(ImageMaster.ATK_BLUNT_HEAVY);
            }
            else if (___effect == CustomAttackEffect.CONK) {
                return SpireReturn.Return(ImageMaster.ATK_BLUNT_HEAVY);
            }
            else if (___effect == CustomAttackEffect.GRAPPLING_GUN) {
                return SpireReturn.Return(ImageMaster.ATK_BLUNT_LIGHT);
            }
            else if (___effect == CustomAttackEffect.SHANK) {
                return SpireReturn.Return(ImageMaster.ATK_SLASH_H);
            }
            else if (___effect == CustomAttackEffect.EXPLOSIVE) {
                return SpireReturn.Return(ImageMaster.ATK_FIRE);
            }
            else if (___effect == CustomAttackEffect.GAS_EXPLOSIVE) {
                return SpireReturn.Return(ImageMaster.ATK_BLUNT_LIGHT);
            }
            else if (___effect == CustomAttackEffect.HAND_E_NUKE) {
                return SpireReturn.Return(ImageMaster.ATK_FIRE);
            }
            else if (___effect == CustomAttackEffect.DISMEMBER) {
                return SpireReturn.Return(ImageMaster.ATK_SLASH_HEAVY);
            }
            else if (___effect == CustomAttackEffect.CLEAVE) {
                return SpireReturn.Return(ImageMaster.ATK_SLASH_HEAVY);
            }
            else if (___effect == CustomAttackEffect.CARBIDE_AXE) {
                return SpireReturn.Return(ImageMaster.ATK_SLASH_H);
            }
            else if (___effect == CustomAttackEffect.FORCE_KNIFE) {
                return SpireReturn.Return(ImageMaster.ATK_SLASH_H);
            }

            return SpireReturn.Continue();
        }

    }

    @SpirePatch(
            clz = FlashAtkImgEffect.class,
            method = "playSound"
    )
    public static class SfxPatch
    {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(FlashAtkImgEffect e, AbstractGameAction.AttackEffect effect) {

            if (effect == CustomAttackEffect.CHAIN_PISTOL) {
                CardCrawlGame.sound.playV(makeID("CHAIN_PISTOL"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.BLOOD_GRADIENT) {
                CardCrawlGame.sound.playV(makeID("BLOOD_GRADIENT"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.VIBROKHOPESH) {
                CardCrawlGame.sound.playV(makeID("VIBROKHOPESH"), 1.5f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.CHROME_REVOLVER) {
                CardCrawlGame.sound.playV(makeID("CHROME_REVOLVER"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.ISSACHAR_RIFLE) {
                CardCrawlGame.sound.playV(makeID("ISSACHAR_RIFLE"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.CONK) {
                CardCrawlGame.sound.playV(makeID("CONK"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.GRAPPLING_GUN) {
                CardCrawlGame.sound.playV(makeID("GRAPPLING_GUN"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.SHANK) {
                CardCrawlGame.sound.playV(makeID("SHANK"), 1.5f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.EXPLOSIVE) {
                CardCrawlGame.sound.playV(makeID("EXPLOSIVE"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.GAS_EXPLOSIVE) {
                CardCrawlGame.sound.playV(makeID("GAS_EXPLOSIVE"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.HAND_E_NUKE) {
                CardCrawlGame.sound.playV(makeID("HAND_E_NUKE"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.DISMEMBER) {
                CardCrawlGame.sound.playV(makeID("DISMEMBER"), 1.5f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.CLEAVE) {
                CardCrawlGame.sound.playV(makeID("CLEAVE"), 1.4f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.CARBIDE_AXE) {
                CardCrawlGame.sound.playV(makeID("CARBIDE_AXE"), 1.3f); // Sound Effect
            }
            else if (effect == CustomAttackEffect.FORCE_KNIFE) {
                CardCrawlGame.sound.playV(makeID("FORCE_KNIFE"), 1.5f); // Sound Effect
            }
            else {
                return SpireReturn.Continue();
            }

            return SpireReturn.Return(null);
        }
    }
}