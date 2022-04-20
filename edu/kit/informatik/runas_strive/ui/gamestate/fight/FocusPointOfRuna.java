package edu.kit.informatik.runas_strive.ui.gamestate.fight;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.gamestate.GameState;

/**
 * This class represents the state 2.3.3 in A.1 in the assignment. It calculates
 * prints how many focus points the Runa gains.
 * 
 * @author uogok
 * @version 29
 */
public class FocusPointOfRuna extends Fight {

    /**
     * Constructor. Is constructed from {@link MonstersTurn#goToNextState()}.
     * 
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link Fight#Fight(Session, RunasStrive)}
     */
    FocusPointOfRuna(Session session, RunasStrive game) {
        super(session, game);
    }

    @Override
    public void execute() {
        printFocusPoints(getGame().getRuna());
        getGame().resetRuna();
        goToNextState();
    }

    @Override
    protected void goToNextState() {
        GameState next = new RunasTurn(getSession(), getGame());
        next.execute();
    }

}
