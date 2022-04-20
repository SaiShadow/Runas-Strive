package edu.kit.informatik.runas_strive.ui.gamestate;

import java.util.List;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.ui.Session;

/**
 * This class represents the state 2.4.2 in A.1 in the assignment. It prints out
 * all the cards Runa gained through upgrading her cards after defeating the
 * Boss of level 1 which is Spider King.
 * 
 * @author uogok
 * @version 29
 */
public class UpgradeRunasSkills extends GameState {
    private final RunasStrive game;

    /**
     * Constructor for this class. Is constructed in/from
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.Fight}.
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link #game} represents {@link RunasStrive}.
     */
    public UpgradeRunasSkills(Session session, RunasStrive game) {
        super(session);
        this.game = game;
    }

    @Override
    public void execute() {
        List<Card> gainedCards = this.game.upgradeRunasSkills();
        printRunaGetsSkills(gainedCards);
        goToNextState();
        return;
    }

    @Override
    protected void goToNextState() {
        GameState next = new Healing(getSession(), this.game);
        next.execute();
    }

}
