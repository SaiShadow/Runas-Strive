package edu.kit.informatik.runas_strive.ui.gamestate;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * Represents the end of Runa's Strive, if the program enters this instance then
 * it terminates after printing out {@link OutputMessage#GAME_LOST} or
 * {@link OutputMessage#GAME_WON} and terminates the program as
 * {@link #goToNextState()} is empty.
 * 
 * @author uogok
 * @version 34
 */
public class End extends GameState {

    private final RunasStrive game;

    /**
     * Constructs this instance. Is constructed from
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.Fight#goToNextState()}.
     * 
     * @param session is to be set as Session represents {@link Session}.
     * @param game    Is to be set as {@link #game} represents {@link RunasStrive}
     */
    public End(Session session, RunasStrive game) {
        super(session);
        this.game = game;
    }

    @Override
    public void execute() {
        getSession().setQuit(true);
        String messageToPrint = (!this.game.didRunaWin()) ? OutputMessage.GAME_LOST.toString()
                : OutputMessage.GAME_WON.toString();
        System.out.println(messageToPrint);
        goToNextState();
    }

    @Override
    protected void goToNextState() {
        return;
    }
}
