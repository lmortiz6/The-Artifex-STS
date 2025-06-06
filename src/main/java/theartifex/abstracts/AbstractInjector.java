package theartifex.abstracts;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theartifex.cards.BaseCard;
import theartifex.powers.MedassistModulePower;
import theartifex.relics.MedassistModuleRelic;
import theartifex.util.CardStats;

public class AbstractInjector extends BaseCard {

    protected AbstractCard reaction;

    public AbstractInjector(String ID, CardStats info) {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.cardRandomRng.random(0, 2) == 0) {
            addToBot(new MakeTempCardInDrawPileAction(reaction, 1, true, true));
        }
    }

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
