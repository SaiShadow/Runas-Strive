package edu.kit.informatik.runas_strive.ui.gamestate;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.gamestate.fight.RunasTurn;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * This class represents the state between 2.2 {@link Shuffle} and 2.3
 * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.Fight} i.e.
 * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.RunasTurn} in A.1
 * in the assignment. It prints out the room and level of the fight Runa enters
 * {@link OutputMessage#ENTER_FIGHT}.
 * 
 * @author uogok
 * @version 29
 */
public class StartFight extends GameState {

    private final RunasStrive game;

    /**
     * Constructor for this class.
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link #game} represents {@link RunasStrive}
     */
    StartFight(Session session, RunasStrive game) {
        super(session);
        this.game = game;
    }

    @Override
    public void execute() {
        System.out.println(OutputMessage.ENTER_FIGHT.format(this.game.getRoom(), this.game.getLevel()));
        goToNextState();
    }

    @Override
    protected void goToNextState() {
        GameState next = new RunasTurn(getSession(), this.game);
        next.execute();
    }
}
