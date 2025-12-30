package theartifex.util;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theartifex.abstracts.AbstractGun;
import theartifex.relics.OtherpearlRelic;

import java.util.ArrayList;

public class CustomCardTags {


    public static final float IMG_WIDTH = 300.0F * Settings.scale, IMG_HEIGHT = 420.0F * Settings.scale;

    private static final float TITLE_BOX_WIDTH = IMG_WIDTH * 0.6F;

    private static final float TITLE_BOX_WIDTH_NO_COST = IMG_WIDTH * 0.7F;

    @SpireEnum public static AbstractCard.CardTags THEARTIFEXNECTAR;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXINJECTOR;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXGUN;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXCANNOTPOLYGEL;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXSPRINT;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXCYBERNETIC;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXJACKED;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXREINFORCED;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXNULLING;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXBEAMSPLITTER;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXFLEXIWEAVED;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXSHARP;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXSPRINGLOADED;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXPERMANENTJACKED;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXPERMANENTREINFORCED;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXPERMANENTNULLING;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXPERMANENTBEAMSPLITTER;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXPERMANENTFLEXIWEAVED;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXPERMANENTSHARP;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXPERMANENTSPRINGLOADED;
    @SpireEnum public static AbstractCard.CardTags THEARTIFEXBROKEN;

    public static ArrayList<AbstractCard.CardTags> getMods (AbstractCard card) {
        ArrayList<AbstractCard.CardTags> ModTags = new ArrayList<>();
        ModTags.add(THEARTIFEXJACKED);
        ModTags.add(THEARTIFEXREINFORCED);
        ModTags.add(THEARTIFEXNULLING);
        ModTags.add(THEARTIFEXBEAMSPLITTER);
        ModTags.add(THEARTIFEXFLEXIWEAVED);
        ModTags.add(THEARTIFEXSHARP);
        ModTags.add(THEARTIFEXSPRINGLOADED);
        ModTags.add(THEARTIFEXPERMANENTJACKED);
        ModTags.add(THEARTIFEXPERMANENTREINFORCED);
        ModTags.add(THEARTIFEXPERMANENTNULLING);
        ModTags.add(THEARTIFEXPERMANENTBEAMSPLITTER);
        ModTags.add(THEARTIFEXPERMANENTFLEXIWEAVED);
        ModTags.add(THEARTIFEXPERMANENTSHARP);
        ModTags.add(THEARTIFEXPERMANENTSPRINGLOADED);
        ModTags.add(THEARTIFEXBROKEN);
        ArrayList<AbstractCard.CardTags> cardMods = new ArrayList<>();
        for (AbstractCard.CardTags tag : card.tags) {
            if (ModTags.contains(tag)) {
                cardMods.add(tag);
            }
        }
        return cardMods;
    }

    public static AbstractCard.CardTags getTempTag(AbstractCard.CardTags tag) {
        if (tag == THEARTIFEXPERMANENTJACKED)
            return THEARTIFEXJACKED;
        if (tag == THEARTIFEXPERMANENTREINFORCED)
            return THEARTIFEXREINFORCED;
        if (tag == THEARTIFEXPERMANENTNULLING)
            return THEARTIFEXNULLING;
        if (tag == THEARTIFEXPERMANENTBEAMSPLITTER)
            return  THEARTIFEXBEAMSPLITTER;
        if (tag == THEARTIFEXPERMANENTFLEXIWEAVED)
            return THEARTIFEXFLEXIWEAVED;
        if (tag == THEARTIFEXPERMANENTSHARP)
            return THEARTIFEXSHARP;
        if (tag == THEARTIFEXPERMANENTSPRINGLOADED)
            return  THEARTIFEXSPRINGLOADED;
        return THEARTIFEXREINFORCED;
    }

    public static void loadSavedPermanentMod(AbstractCard card) {
        switch (card.misc) {
            case 0:
                break;
            case 1:
                loadMod(card, CustomCardTags.THEARTIFEXPERMANENTJACKED, true);
                break;
            case 2:
                loadMod(card, CustomCardTags.THEARTIFEXPERMANENTREINFORCED, true);
                break;
            case 3:
                loadMod(card, CustomCardTags.THEARTIFEXPERMANENTNULLING, true);
                break;
            case 4:
                loadMod(card, CustomCardTags.THEARTIFEXPERMANENTBEAMSPLITTER, true);
                break;
            case 5:
                loadMod(card, CustomCardTags.THEARTIFEXPERMANENTFLEXIWEAVED, true);
                break;
            case 6:
                loadMod(card, CustomCardTags.THEARTIFEXPERMANENTSHARP, true);
                break;
            case 7:
                loadMod(card, CustomCardTags.THEARTIFEXPERMANENTSPRINGLOADED, true);
                break;
            default:
                break;
        }
    }

    public static void loadMod(AbstractCard c, AbstractCard.CardTags tagToLoad, boolean permanent) {
        loadMod(c, tagToLoad, permanent, false);
    }

    public static void loadRandomMod(AbstractCard c, boolean permanent) {
        loadMod(c, CustomCardTags.THEARTIFEXREINFORCED, permanent, true);
    }

    private static void loadMod(AbstractCard c, AbstractCard.CardTags tagToLoad, boolean permanent, boolean random) {
        if (AbstractDungeon.player != null) {
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r instanceof OtherpearlRelic) {
                    if(((OtherpearlRelic)r).active) {
                        ((OtherpearlRelic)r).onCardMod();
                        ((OtherpearlRelic) r).active = false;
                    }
                }
            }
        }
        ArrayList<AbstractCard.CardTags> tags = new ArrayList<>();
        if (permanent) {
            tags.add(CustomCardTags.THEARTIFEXPERMANENTJACKED);
            tags.add(CustomCardTags.THEARTIFEXPERMANENTREINFORCED);
            tags.add(CustomCardTags.THEARTIFEXPERMANENTNULLING);
            tags.add(CustomCardTags.THEARTIFEXPERMANENTBEAMSPLITTER);
            tags.add(CustomCardTags.THEARTIFEXPERMANENTFLEXIWEAVED);
            tags.add(CustomCardTags.THEARTIFEXPERMANENTSHARP);
            tags.add(CustomCardTags.THEARTIFEXPERMANENTSPRINGLOADED);
        } else {
            tags.add(CustomCardTags.THEARTIFEXJACKED);
            tags.add(CustomCardTags.THEARTIFEXREINFORCED);
            tags.add(CustomCardTags.THEARTIFEXNULLING);
            tags.add(CustomCardTags.THEARTIFEXBEAMSPLITTER);
            tags.add(CustomCardTags.THEARTIFEXFLEXIWEAVED);
            tags.add(CustomCardTags.THEARTIFEXSHARP);
            tags.add(CustomCardTags.THEARTIFEXSPRINGLOADED);
        }
        ArrayList<AbstractCard.CardTags> tagsOriginal = new ArrayList<>(tags);

        if (random) {
            for (AbstractCard.CardTags tagToRemove : CustomCardTags.getMods(c)) {
                if (!permanent && tagToRemove.toString().contains("THEARTIFEXPERMANENT"))
                    tags.remove(CustomCardTags.getTempTag(tagToRemove));
                else
                    tags.remove(tagToRemove);
            }
            if (!(c instanceof AbstractGun)) {
                if (permanent)
                    tags.remove(CustomCardTags.THEARTIFEXPERMANENTBEAMSPLITTER);
                else
                    tags.remove(CustomCardTags.THEARTIFEXBEAMSPLITTER);
            }
            if (c.type != AbstractCard.CardType.ATTACK || c instanceof AbstractGun) {
                if (permanent)
                    tags.remove(CustomCardTags.THEARTIFEXPERMANENTSHARP);
                else
                    tags.remove(CustomCardTags.THEARTIFEXSHARP);
            }
        }

        int i;
        if (random) {
            if (permanent)
                i = AbstractDungeon.relicRng.random(0, tags.size() - 1);
            else
                i = AbstractDungeon.cardRng.random(0, tags.size() - 1);
        }
        else
            i = tagsOriginal.indexOf(tagToLoad);

        AbstractCard.CardTags tag = tags.get(i);
        String tagString;
        if (permanent)
            tagString = tag.toString().substring(19).toLowerCase();
        else
            tagString = tag.toString().substring(10).toLowerCase();

        if (Settings.language == Settings.GameLanguage.ZHS)
            tagString = getZHS(tagString);
        if (!c.keywords.contains("theartifex:" + tagString)) {
            c.keywords.add(getMods(c).size(), "theartifex:" + tagString);
        }

        c.tags.add(tag);

        if (permanent)
            c.misc = tagsOriginal.indexOf(tags.get(i)) + 1;
    }

    public static ArrayList<AbstractCard.CardTags> getTempModsList() {
        ArrayList<AbstractCard.CardTags> tags = new ArrayList<>();
        tags.add(CustomCardTags.THEARTIFEXJACKED);
        tags.add(CustomCardTags.THEARTIFEXREINFORCED);
        tags.add(CustomCardTags.THEARTIFEXNULLING);
        tags.add(CustomCardTags.THEARTIFEXBEAMSPLITTER);
        tags.add(CustomCardTags.THEARTIFEXFLEXIWEAVED);
        tags.add(CustomCardTags.THEARTIFEXSHARP);
        tags.add(CustomCardTags.THEARTIFEXSPRINGLOADED);
        tags.add(CustomCardTags.THEARTIFEXBROKEN);
        return tags;
    }

    public static String getZHS (String ENG) {
        switch (ENG) {
            case "jacked":
                return "直连";
            case "reinforced":
                return "加固";
            case "nulling":
                return "归常";
            case "beamsplitter":
                return "分束";
            case "flexiweaved":
                return "弹织";
            case "sharp":
                return "锋锐";
            case "springloaded":
                return "簧载";
            case "broken":
                return "损坏";
        }
        return "损坏";
    }
}
