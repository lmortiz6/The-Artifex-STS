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
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theartifex.cards.BaseCard;
import theartifex.powers.JuicerPower;
import theartifex.powers.MedassistModulePower;
import theartifex.relics.MedassistModuleRelic;
import theartifex.util.CardStats;

import static theartifex.TheArtifexMod.makeID;

public class AbstractInjector extends BaseCard {

    protected AbstractCard reaction;

    public AbstractInjector(String ID, CardStats info) {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        //this.keywords.add(this.keywords.size()-1, "theartifex:adversereaction");
        //this.keywords.add("theartifex:adversereaction");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.playV(makeID("INJECTOR"), 1.5f); // Sound Effect
        if (AbstractDungeon.cardRandomRng.random(0, 2) == 0) {
            //addToBot(new MakeTempCardInDrawPileAction(reaction, 1, true, true));
            this.adverseReaction();
            addToBot(new TextAboveCreatureAction(AbstractDungeon.player, "Adverse Reaction!"));
            for (AbstractPower power : p.powers)
                if (power instanceof JuicerPower){
                        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player , new StrengthPower(p, power.amount)));
                }
        }
    }

    public void adverseReaction() {}

    @Override
    public boolean freeToPlay() {
        if (this.freeToPlayOnce)
            return true;
        String medassistID = makeID(MedassistModulePower.class.getSimpleName());
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
                (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                AbstractDungeon.player.hasPower(medassistID) &&
                AbstractDungeon.player.getPower(medassistID).amount > 0) {
            return true;
        }
        String medassistRelicID = makeID(MedassistModuleRelic.class.getSimpleName());
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
                (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                AbstractDungeon.player.hasRelic(medassistRelicID) &&
                ((MedassistModuleRelic)AbstractDungeon.player.getRelic(medassistRelicID)).amount > 0) {
            return true;
        }
        return false;
    }
}
