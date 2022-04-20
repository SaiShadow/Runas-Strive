package edu.kit.informatik.runas_strive.ui.gamestate.fight;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.character.monster.Monster;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.gamestate.GameState;

/**
 * This class represents the state 2.3.3 in A.1 in the assignment. It calculates
 * prints how many focus points the monsters gain.
 * 
 * @author uogok
 * @version 29
 */
public class FocusPointsOfMonsters extends Fight {

    /**
     * Constructor. Is constructed from {@link RunasTurn#goToNextState()}.
     * 
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link Fight#Fight(Session, RunasStrive)}
     */
    FocusPointsOfMonsters(Session session, RunasStrive game) {
        super(session, game);
    }

    @Override
    public void execute() {
        printFocusForAllMonsters();
        getGame().resetMonsters();
        goToNextState();
    }

    private void printFocusForAllMonsters() {
        for (Monster monster : getGame().getMonsters()) {
            printFocusPoints(monster);
        }
    }

    @Override
    protected void goToNextState() {
        GameState next = new MonstersTurn(getSession(), getGame());
        next.execute();
    }

}
