package theartifex.ui;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import theartifex.TheArtifexMod;
import theartifex.util.TextureLoader;
import theartifex.vfx.BecomingNookEffect;

import static theartifex.TheArtifexMod.imagePath;
import static theartifex.TheArtifexMod.makeID;

public class CyberneticCampfireOption extends AbstractCampfireOption {

    public CyberneticCampfireOption(boolean active) {
        this.label = CardCrawlGame.languagePack.getUIString(makeID("UI")).TEXT[2];

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            c.update();
        }

        this.usable = active;
        if (active) {
            this.description = CardCrawlGame.languagePack.getUIString(makeID("UI")).TEXT[3];
            this.img = TextureLoader.getTexture(imagePath("ui/cyberCampfire.png"));
        } else {
            this.description = CardCrawlGame.languagePack.getUIString(makeID("UI")).TEXT[4];
            this.img = TextureLoader.getTexture(imagePath("ui/cyberCampfireDisabled.png"));
        }

    }

    @Override
    public void useOption() {
        if (this.usable) {
            TheArtifexMod.currBecomingNookEffect = new BecomingNookEffect();
            AbstractDungeon.effectList.add(TheArtifexMod.currBecomingNookEffect);
        }
    }

    public void reCheck() {
        usable = TheArtifexMod.hasCyberneticRelic() || TheArtifexMod.hasCyberneticCard();
        if (this.usable) {
            this.description = CardCrawlGame.languagePack.getUIString(makeID("UI")).TEXT[3];
            this.img = TextureLoader.getTexture("images/ui/cyberCampfire.png");
        } else {
            this.description = CardCrawlGame.languagePack.getUIString(makeID("UI")).TEXT[4];
            this.img = TextureLoader.getTexture("images/ui/cyberCampfireDisabled.png");
        }
    }

    @Override
    public void update() {
        float hackScale = ReflectionHacks.getPrivate(this, AbstractCampfireOption.class, "scale");

        if (this.hb.hovered) {

            if (!this.hb.clickStarted) {
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, Settings.scale));
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, Settings.scale));

            } else {
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale));

            }
        } else {
            ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale));
        }
        super.update();
    }
}
