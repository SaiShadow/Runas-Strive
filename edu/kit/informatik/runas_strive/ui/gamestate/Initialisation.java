package edu.kit.informatik.runas_strive.ui.gamestate;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.character.runa.RunasClass;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * This represents the Initialisation state, which is the starting state of
 * Runa's Strive. It prints the entry message and instantiates
 * {@link RunasStrive#RunasStrive(Runa)} by getting {@link RunasClass} by
 * {@link #processInput()} and setting it into
 * {@link Runa#Runa(String, Elemental, int, RunasClass, List)}.
 * 
 * @author uogok
 * @version 3
 */
public class Initialisation extends GameState {

    private static final String CLOSING_BRACKET = ")";
    private static final String SPACE = " ";

    private List<RunasClass> runasClassInList;
    private RunasStrive runasStrive;

    /**
     * Constructs this instance.
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     */
    public Initialisation(Session session) {
        super(session);
    }

    @Override
    public void execute() {
        entryMessage();
        outputRunasClass();
        boolean processSuccesful = processInput();
        if (!processSuccesful) {
            return;
        }
        goToNextState();
    }

    @Override
    protected void goToNextState() {
        GameState next = new Shuffle(getSession(), runasStrive);
        next.execute();
    }

    private String outputSelectionOfRunasClass() {
        return OutputMessage.SELECT_ONE_OPTION.format(this.runasClassInList.size());
    }

    private void entryMessage() {
        System.out.println(OutputMessage.ENTRY_MESSAGE_GAME.toString());
    }

    private void outputRunasClass() {
        System.out.println(OutputMessage.SELECT_RUNAS_CLASS.toString());
        runasClassInList = new ArrayList<>();
        int i = 1;
        for (RunasClass race : RunasClass.values()) {
            System.out.println(i + CLOSING_BRACKET + SPACE + race.toString());
            i++;
            runasClassInList.add(race);
        }
    }

    private boolean processInput() {

        int input = processInputAndExpectOneInput(outputSelectionOfRunasClass(), this.runasClassInList.size());
        if (input == ERROR_NUMBER) {
            return false;
        }
        RunasClass classOfRuna = this.runasClassInList.get(input - 1);
        this.runasStrive = new RunasStrive(classOfRuna);
        return true;
    }
}
