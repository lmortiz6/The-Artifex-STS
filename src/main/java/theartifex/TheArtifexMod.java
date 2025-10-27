package theartifex;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFileHandle;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;
import theartifex.abstracts.AbstractCreditRelic;
import theartifex.abstracts.AbstractCyberneticCard;
import theartifex.abstracts.AbstractCyberneticRelic;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.relics.BaseRelic;
import theartifex.ui.CyberneticCampfireOption;
import theartifex.util.CustomCardTags;
import theartifex.util.GeneralUtils;
import theartifex.util.KeywordInfo;
import theartifex.util.TextureLoader;
import theartifex.vfx.BecomingNookEffect;

import java.nio.charset.StandardCharsets;
import java.util.*;

@SpireInitializer
public class TheArtifexMod implements
        AddAudioSubscriber,
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditKeywordsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        PostInitializeSubscriber {
    public static ModInfo info;
    public static String modID; //Edit your pom.xml to change this
    static { loadModInfo(); }
    private static final String resourcesFolder = checkResourcesPath();
    public static final Logger logger = LogManager.getLogger(modID); //Used to output to the console.
    public static CyberneticCampfireOption cyberneticCampfireOption;
    public static BecomingNookEffect currBecomingNookEffect = null;
    public static boolean gridScreenForCyberRelics = false;
    public static boolean gridScreenForCyberCards = false;
    public static boolean nonManualDiscard = false;
    public static int cardsDrawnAtTurnStart = 5;
    public static ArrayList<Texture> cardModTextures = new ArrayList<>();

    public static int availableCreditsFast = 0;
    public static int maxCreditsFast = 0;

    //This is used to prefix the IDs of various objects like cards and relics,
    //to avoid conflicts between different mods using the same name for things.
    public static String makeID(String id) {
        return modID + ":" + id;
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(makeID("SPRINT"), "theartifex/audio/Sprint.ogg");
        BaseMod.addAudio(makeID("CHAIN_PISTOL"), "theartifex/audio/ChainPistol.ogg");
        BaseMod.addAudio(makeID("BLOOD_GRADIENT"), "theartifex/audio/BloodGradient.ogg");
        BaseMod.addAudio(makeID("VIBROKHOPESH"), "theartifex/audio/Vibrokhopesh.ogg");
        BaseMod.addAudio(makeID("CHROME_REVOLVER"), "theartifex/audio/ChromeRevolver.ogg");
        BaseMod.addAudio(makeID("ISSACHAR_RIFLE"), "theartifex/audio/IssacharRifle.ogg");
        BaseMod.addAudio(makeID("CARBIDE_AXE"), "theartifex/audio/CarbideAxe.ogg");
        BaseMod.addAudio(makeID("CONK"), "theartifex/audio/Conk.ogg");
        BaseMod.addAudio(makeID("FORCE_KNIFE"), "theartifex/audio/ForceKnife.ogg");
        BaseMod.addAudio(makeID("GRAPPLING_GUN"), "theartifex/audio/GrapplingGun.ogg");
        BaseMod.addAudio(makeID("DRAG"), "theartifex/audio/Drag.ogg");
        BaseMod.addAudio(makeID("SHANK"), "theartifex/audio/Shank.ogg");
        BaseMod.addAudio(makeID("DISASSEMBLE"), "theartifex/audio/Disassemble.ogg");
        BaseMod.addAudio(makeID("LEARN_SCHEMATIC"), "theartifex/audio/LearnSchematic.ogg");
        BaseMod.addAudio(makeID("TINKER_BUILD"), "theartifex/audio/TinkerBuild.ogg");
        BaseMod.addAudio(makeID("TINKER_MOD"), "theartifex/audio/TinkerMod.ogg");
        BaseMod.addAudio(makeID("INJECTOR"), "theartifex/audio/Injector.ogg");
        BaseMod.addAudio(makeID("GRENADE_THROW"), "theartifex/audio/GrenadeThrow.ogg");
        BaseMod.addAudio(makeID("EXPLOSIVE"), "theartifex/audio/Explosive.ogg");
        BaseMod.addAudio(makeID("EMP"), "theartifex/audio/Emp.ogg");
        BaseMod.addAudio(makeID("GAS_EXPLOSIVE"), "theartifex/audio/GasExplosive.ogg");
        BaseMod.addAudio(makeID("HAND_E_NUKE"), "theartifex/audio/HandENuke.ogg");
        BaseMod.addAudio(makeID("MEDITATE"), "theartifex/audio/Meditate.ogg");
        BaseMod.addAudio(makeID("WITCHWOOD"), "theartifex/audio/Witchwood.ogg");
        BaseMod.addAudio(makeID("DISMEMBER"), "theartifex/audio/Dismember.ogg");
        BaseMod.addAudio(makeID("BECOMING_NOOK"), "theartifex/audio/BecomingNook.ogg");
        BaseMod.addAudio(makeID("UNIMPLANT"), "theartifex/audio/Unimplant.ogg");
        BaseMod.addAudio(makeID("IMPLANT"), "theartifex/audio/Implant.ogg");
        BaseMod.addAudio(makeID("CLEAVE"), "theartifex/audio/Cleave.ogg");
    }

    //This will be called by ModTheSpire because of the @SpireInitializer annotation at the top of the class.
    public static void initialize() {
        new TheArtifexMod();

        TheArtifexCharacter.Meta.registerColor();
    }

    public TheArtifexMod() {
        BaseMod.subscribe(this); //This will make BaseMod trigger all the subscribers at their appropriate times.
        logger.info(modID + " subscribed to BaseMod.");
    }

    @Override
    public void receivePostInitialize() {
        //This loads the image used as an icon in the in-game mods menu.
        Texture badgeTexture = TextureLoader.getTexture(imagePath("badge.png"));
        //Set up the mod information displayed in the in-game mods menu.
        //The information used is taken from your pom.xml file.

        //If you want to set up a config panel, that will be done here.
        //You can find information about this on the BaseMod wiki page "Mod Config and Panel".
        BaseMod.registerModBadge(badgeTexture, info.Name, GeneralUtils.arrToString(info.Authors), info.Description, null);

        initializeCardModTextures();
    }

    private void initializeCardModTextures() {
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Jacked.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Jacked2.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Reinforced.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Reinforced2.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Nulling.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Nulling2.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Beamsplitter.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Beamsplitter2.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Flexiweaved.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Flexiweaved2.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Sharp.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Sharp2.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Springloaded.png")));
        cardModTextures.add(TextureLoader.getTexture(imagePath("modicons/Springloaded2.png")));
    }

    /*----------Localization----------*/

    //This is used to load the appropriate localization files based on language.
    private static String getLangString()
    {
        return Settings.language.name().toLowerCase();
    }
    private static final String defaultLanguage = "eng";

    public static final Map<String, KeywordInfo> keywords = new HashMap<>();

    @Override
    public void receiveEditStrings() {
        /*
            First, load the default localization.
            Then, if the current language is different, attempt to load localization for that language.
            This results in the default localization being used for anything that might be missing.
            The same process is used to load keywords slightly below.
        */
        loadLocalization(defaultLanguage); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage.equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLocalization(String lang) {
        //While this does load every type of localization, most of these files are just outlines so that you can see how they're formatted.
        //Feel free to comment out/delete any that you don't end up using.
        BaseMod.loadCustomStringsFile(CardStrings.class,
                localizationPath(lang, "CardStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                localizationPath(lang, "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class,
                localizationPath(lang, "EventStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                localizationPath(lang, "OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                localizationPath(lang, "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                localizationPath(lang, "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                localizationPath(lang, "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class,
                localizationPath(lang, "UIStrings.json"));
    }

    @Override
    public void receiveEditKeywords()
    {
        Gson gson = new Gson();
        String json = Gdx.files.internal(localizationPath(defaultLanguage, "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            keyword.prep();
            registerKeyword(keyword);
        }

        if (!defaultLanguage.equals(getLangString())) {
            try
            {
                json = Gdx.files.internal(localizationPath(getLangString(), "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    keyword.prep();
                    registerKeyword(keyword);
                }
            }
            catch (Exception e)
            {
                logger.warn(modID + " does not support " + getLangString() + " keywords.");
            }
        }
    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(modID.toLowerCase(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
        if (!info.ID.isEmpty())
        {
            keywords.put(info.ID, info);
        }
    }

    //These methods are used to generate the correct filepaths to various parts of the resources folder.
    public static String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public static String imagePath(String file) {
        return resourcesFolder + "/images/" + file;
    }
    public static String characterPath(String file) {
        return resourcesFolder + "/images/" + file;
    }
    public static String powerPath(String file) {
        return resourcesFolder + "/images/powers/" + file;
    }
    public static String relicPath(String file) {
        return resourcesFolder + "/images/relics/" + file;
    }

    /**
     * Checks the expected resources path based on the package name.
     */
    private static String checkResourcesPath() {
        String name = TheArtifexMod.class.getName(); //getPackage can be iffy with patching, so class name is used instead.
        int separator = name.indexOf('.');
        if (separator > 0)
            name = name.substring(0, separator);

        FileHandle resources = new LwjglFileHandle(name, Files.FileType.Internal);

        if (!resources.exists()) {
            throw new RuntimeException("\n\tFailed to find resources folder; expected it to be at  \"resources/" + name + "\"." +
                    " Either make sure the folder under resources has the same name as your mod's package, or change the line\n" +
                    "\t\"private static final String resourcesFolder = checkResourcesPath();\"\n" +
                    "\tat the top of the " + TheArtifexMod.class.getSimpleName() + " java file.");
        }
        if (!resources.child("images").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'images' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "images folder is in the correct location.");
        }
        if (!resources.child("localization").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'localization' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "localization folder is in the correct location.");
        }

        return name;
    }

    /**
     * This determines the mod's ID based on information stored by ModTheSpire.
     */
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo)->{
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(TheArtifexMod.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        }
        else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    @Override
    public void receiveEditCharacters() {
        TheArtifexCharacter.Meta.registerCharacter();
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID) //Loads files from this mod
                .packageFilter(BaseCard.class) //In the same package as this class
                .setDefaultSeen(true) //And marks them as seen in the compendium
                .cards(); //Adds the cards
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID) //Loads files from this mod
                .packageFilter(BaseRelic.class) //In the same package as this class
                .any(BaseRelic.class, (info, relic) -> { //Run this code for any classes that extend this class
                    if (relic.pool != null)
                        BaseMod.addRelicToCustomPool(relic, relic.pool); //Register a custom character specific relic
                    else
                        BaseMod.addRelic(relic, relic.relicType); //Register a shared or base game character specific relic

                    //If the class is annotated with @AutoAdd.Seen, it will be marked as seen, making it visible in the relic library.
                    //If you want all your relics to be visible by default, just remove this if statement.
                    if (info.seen)
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                });

    }

    public static int getMaxCredits() {
        int maxCredits = 0;
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null) {
            for (AbstractRelic r : p.relics) {
                if (r instanceof AbstractCreditRelic) {
                    AbstractCreditRelic cr = (AbstractCreditRelic) r;
                    maxCredits += cr.getAmount();
                }
            }
        }
        return maxCredits;
    }

    public static int getAvailableCredits() {
        int availableCredits = getMaxCredits();
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null) {
            for (AbstractRelic r : p.relics) {
                if (r instanceof AbstractCyberneticRelic) {
                    AbstractCyberneticRelic cy = (AbstractCyberneticRelic) r;
                    availableCredits -= cy.getCost();
                }
            }
        }
        return availableCredits;
    }

    public static boolean hasCyberneticRelic() {
        boolean hasRelic = false;
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null) {
            for (AbstractRelic r : p.relics) {
                if (r instanceof AbstractCyberneticRelic) {
                    hasRelic = true;
                    break;
                }
            }
        }
        return hasRelic;
    }

    public static boolean hasCyberneticCard() {
        boolean hasCard = false;
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null) {
            for (AbstractCard c : p.masterDeck.group) {
                if (c.hasTag(CustomCardTags.THEARTIFEXCYBERNETIC)) {
                    hasCard = true;
                    break;
                }
            }
        }
        return hasCard;
    }

    public static CardGroup getCyberneticCards() {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (c instanceof AbstractCyberneticCard) {
                retVal.group.add(c);
            }
        }

        return retVal;
    }

    public static CardGroup getCyberneticRelicsAsCards() {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = AbstractDungeon.player.relics.iterator();

        while (var2.hasNext()) {
            AbstractRelic r = (AbstractRelic) var2.next();
            if (r instanceof AbstractCyberneticRelic) {
                AbstractCyberneticRelic cy = (AbstractCyberneticRelic) r;
                retVal.group.add(CardLibrary.getCard(cy.getCard()));
            }
        }

        return retVal;
    }
}
