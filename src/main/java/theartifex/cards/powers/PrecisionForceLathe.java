package theartifex.cards.powers;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.abstracts.AbstractCyberneticCard;
import theartifex.cards.attacks.ForceKnife;
import theartifex.character.TheArtifexCharacter;
import theartifex.powers.PrecisionForceLathePower;
import theartifex.relics.PrecisionForceLatheRelic;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

import java.util.ArrayList;

public class PrecisionForceLathe extends AbstractCyberneticCard {

    public static final String ID = makeID(PrecisionForceLathe.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );
    public static final int creditCost = info.baseCost;
    private static final int BUFF = 1;
    private static final String cyberneticRelic = makeID(PrecisionForceLatheRelic.class.getSimpleName());

    public PrecisionForceLathe() {
        super(ID, info, cyberneticRelic);

        AbstractCard card = new ForceKnife();
        card.upgrade();
        this.tags.add(CustomCardTags.THEARTIFEXCYBERNETIC);

    }

    @Override
    public boolean canUpgrade() { return false; }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PrecisionForceLathePower(p, p, BUFF)));
    }

    private ArrayList<AbstractCard> getList() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        AbstractCard c1 = new ForceKnife();
        c1.upgrade();
        stanceChoices.add(c1);
        stanceChoices.add(new ForceKnife());
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
        return new PrecisionForceLathe();
    }
}
