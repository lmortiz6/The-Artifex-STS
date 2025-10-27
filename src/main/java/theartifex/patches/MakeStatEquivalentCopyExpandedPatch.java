package theartifex.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theartifex.util.CustomCardTags;

@SpirePatch(
        clz = AbstractCard.class,
        method = "makeStatEquivalentCopy"
)
public class MakeStatEquivalentCopyExpandedPatch {
    @SpirePostfixPatch
    public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance) {
        // Copy permanent mods
        for (AbstractCard.CardTags tag : __instance.tags) {
            if (tag.toString().contains("THEARTIFEXPERMANENT")) {
                __result.tags.add(tag);
                String tagString = tag.toString().substring(19).toLowerCase();
                __result.keywords.add(0, "theartifex:" + tagString);
            }
        }
        // Copy mods in combat
        try {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractCard.CardTags tag : __instance.tags) {
                    if (CustomCardTags.getTempModsList().contains(tag)) {
                        __result.tags.add(tag);
                        String tagString = tag.toString().substring(10).toLowerCase();
                        __result.keywords.add(0, "theartifex:" + tagString);
                    }
                }
            }
        } catch (NullPointerException ignored) { }
        return __result;
    }
}
