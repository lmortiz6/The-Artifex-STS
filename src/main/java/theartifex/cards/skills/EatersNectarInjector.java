package theartifex.cards.skills;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractInjector;
import theartifex.cards.optionCards.*;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

public class EatersNectarInjector extends AbstractInjector {

    public static final String ID = makeID(EatersNectarInjector.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.NONE,
            1
    );

    public EatersNectarInjector() {
        super(ID, info); //Pass the required information to the BaseCard constructor.

        this.setExhaust(true);
        tags.add(CardTags.HEALING);
        tags.add(CustomCardTags.THEARTIFEXINJECTOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        ArrayList<AbstractCard> stanceChoices = getList();
        if (this.upgraded)
            for (AbstractCard c : stanceChoices)
                c.upgrade();
        addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
    }

    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new StrengthUp());
        stanceChoices.add(new AgilityUp());
        stanceChoices.add(new ToughnessUp());
        stanceChoices.add(new WillpowerUp());
        stanceChoices.add(new IntelligenceUp());
        stanceChoices.add(new EgoUp());
        if (upgraded) {
            for (AbstractCard c : stanceChoices) {
                c.upgrade();
            }
        }
        return stanceChoices;
    }

    @Override
    public void adverseReaction() {
        if (EnergyPanel.totalCount > this.costForTurn)
            addToBot(new GainEnergyAction(-1));
    }

    @Override
    public void upgrade() {
        super.upgrade();
        dupeListForPrev.clear();
    }

    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

    @Override
    public void update() {
        super.update();
        if (dupeListForPrev.isEmpty()) {
            dupeListForPrev.addAll(getList());
        }
        if (hb.hovered) {
            if (rotationTimer <= 0F) {
                rotationTimer = 2F;
                if (dupeListForPrev.isEmpty()) {
                    cardsToPreview = CardLibrary.cards.get("Madness");
                } else {
                    cardsToPreview = dupeListForPrev.get(previewIndex);
                }
                if (previewIndex == dupeListForPrev.size() - 1) {
                    previewIndex = 0;
                } else {
                    previewIndex++;
                }
            } else {
                rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EatersNectarInjector();
    }
}
