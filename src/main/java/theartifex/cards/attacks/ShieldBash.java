package theartifex.cards.attacks;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theartifex.actions.ShieldBashAction;
import theartifex.cards.BaseCard;
import theartifex.cards.skills.FulleriteAegis;
import theartifex.character.TheArtifexCharacter;
import theartifex.util.CardStats;

public class ShieldBash extends BaseCard implements OnObtainCard {
    public static final String ID = makeID(ShieldBash.class.getSimpleName());

    private static final CardStats info = new CardStats(
            TheArtifexCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 6;

    public ShieldBash() {
        super(ID, info);
        this.setDamage(DAMAGE, UPG_DAMAGE);
        this.setExhaust(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //CardCrawlGame.sound.playV(makeID("CARBIDE_AXE"), 1.3f); // Sound Effect
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ShieldBashAction(p, 1,  this));
    }

    public AbstractCard makeCopy() {
        return new ShieldBash();
    }

    @Override
    public void onObtainCard() {
        for(AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if ((c.cardID.equals(this.cardID) && c.uuid != this.uuid) || c.cardID.equals(FulleriteAegis.ID)) {
                return;
            }
        }
        CardCrawlGame.sound.playV(makeID("LEARN_SCHEMATIC"), 1.6f); // Sound Effect
    }
}
