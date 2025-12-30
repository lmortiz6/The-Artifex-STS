package theartifex.cards.skills;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.NostrumAction;
import theartifex.cards.BaseCard;
import theartifex.cards.attacks.LoveInjector;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

import java.util.ArrayList;

public class Nostrum extends BaseCard {

    public static final String ID = makeID(Nostrum.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0
    );

    public Nostrum() {
        super(ID, info);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new NostrumAction(!this.upgraded));
    }

    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new RubbergumInjector());
        stanceChoices.add(new SalveInjector());
        stanceChoices.add(new HulkHoneyInjector());
        stanceChoices.add(new SkulkInjector());
        stanceChoices.add(new SphynxSaltInjector());
        stanceChoices.add(new EatersNectarInjector());
        stanceChoices.add(new LoveInjector());
        stanceChoices.add(new ShadeOilInjector());
        return stanceChoices;
    }

    private float rotationTimer;
    private int previewIndex;
    private final ArrayList<AbstractCard> dupeListForPrev = new ArrayList<>();

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
    public AbstractCard makeCopy() {
        return new Nostrum();
    }
}
