package theartifex.abstracts;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theartifex.cards.BaseCard;
import theartifex.powers.JuicerPower;
import theartifex.util.CardStats;
import theartifex.util.CustomCardTags;

public class AbstractInjector extends BaseCard {

    protected AbstractCard reaction;

    public AbstractInjector(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.playV(makeID("INJECTOR"), 1.5f);
        int isBroken = 0;
        if (this.hasTag(CustomCardTags.THEARTIFEXBROKEN))
            isBroken = 1;
        if (AbstractDungeon.cardRandomRng.random(0, 2) < (1 + isBroken)) {
            this.adverseReaction();
            addToBot(new TextAboveCreatureAction(AbstractDungeon.player, "Adverse Reaction!"));
            for (AbstractPower power : p.powers)
                if (power instanceof JuicerPower){
                        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player , new StrengthPower(p, power.amount)));
                }
        }
    }

    public void adverseReaction() {}

}
