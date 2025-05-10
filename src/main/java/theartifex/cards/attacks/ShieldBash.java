package theartifex.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.cards.BaseCard;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class ShieldBash extends BaseCard {
    public static final String ID = makeID(ShieldBash.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 5;

    public ShieldBash() {
        super(ID, info);
        this.setBlock(BLOCK, UPG_BLOCK);
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null) {
            this.setDamage(p.currentBlock + this.block);
        }else {
            this.setDamage(this.block);
        }
        this.setExhaust(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new WaitAction(0.2F));
        this.baseDamage = p.currentBlock + this.block;
        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        //this.rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
        //initializeDescription();
    }

    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null) {
            this.baseDamage = p.currentBlock + this.block;
        }
        super.applyPowers();
        //this.rawDescription = cardStrings.DESCRIPTION;
        //this.rawDescription += cardStrings.UPGRADE_DESCRIPTION;
        //initializeDescription();
    }

    public void onMoveToDiscard() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null)
            this.baseDamage = p.currentBlock + this.block;
        //this.rawDescription = cardStrings.DESCRIPTION;
        //initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int currentBlock = AbstractDungeon.player.currentBlock;
        this.baseDamage = currentBlock + this.block;
        super.calculateCardDamage(mo);
        //this.rawDescription = cardStrings.DESCRIPTION;
        //this.rawDescription += cardStrings.UPGRADE_DESCRIPTION;
        //initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new ShieldBash();
    }
}
