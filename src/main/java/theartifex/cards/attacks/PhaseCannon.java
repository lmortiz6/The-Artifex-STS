package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import theartifex.TheArtifexMod;
import theartifex.abstracts.AbstractGun;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class PhaseCannon extends AbstractGun {

    public static final String ID = makeID(PhaseCannon.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            9
    );
    private static final int DAMAGE = 32;
    private static final int UPG_DAMAGE = 8;

    public PhaseCannon() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CustomCardTags.THEARTIFEXGUN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        if (isMultiDamage)
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        else
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void atTurnStart() {
        resetAttributes();
        applyPowers();
        setCostForTurn(this.cost - TheArtifexMod.cardsDrawnAtTurnStart);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        setCostForTurn(this.cost - TheArtifexMod.cardsDrawnAtTurnStart);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        setCostForTurn(this.cost - TheArtifexMod.cardsDrawnAtTurnStart);
    }

    @Override
    public AbstractCard makeCopy() {
        AbstractCard tmp = new PhaseCannon();
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null &&
                (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
            setCostForTurn(this.cost - TheArtifexMod.cardsDrawnAtTurnStart);
        return tmp;
    }
}
