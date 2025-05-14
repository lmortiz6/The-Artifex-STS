package theartifex.abstracts;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theartifex.actions.AddToHandAction;
import theartifex.cards.BaseCard;
import theartifex.powers.MedassistModulePower;
import theartifex.util.CardStats;

import java.util.ArrayList;

public class AbstractInjector extends BaseCard {

    protected AbstractCard reaction;

    public AbstractInjector(String ID, CardStats info) {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.add(reaction.makeCopy());
        if (AbstractDungeon.cardRandomRng.random(0, 3) == 0) {
            addToBot(new AddToHandAction(cards));
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
        return false;
    }
}
