package edu.kit.informatik.runas_strive.ui.gamestate;

import java.util.List;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.deck.Deck;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * This class represents 2.2 in A.1 in the assignment. It needs 2 seeds, one for
 * Runa's skill-cards, the other for the monster-cards. Look into
 * {@link Deck#Deck(int, int, int, edu.kit.informatik.runas_strive.model.character.runa.Runa)}
 * 
 * @author uogok
 * @version 29
 */
public class Shuffle extends GameState {

    private static final int MAX_SEED = 2147483647;
    private static final int NUMBER_OF_ARGUMENTS_NEEDED = 2;
    private final RunasStrive game;

    /**
     * Constructor for this class. It doesn't need anything special.
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link #game} represents {@link RunasStrive}
     */
    Shuffle(Session session, RunasStrive game) {
        super(session);
        this.game = game;
    }

    @Override
    public void execute() {
        entryMessage();
        if (!processInput()) {
            return;
        }
        goToNextState();
    }

    private void entryMessage() {
        System.out.println(OutputMessage.ENTRY_MESSAGE_SHUFFLE.toString());
    }

    private String askQuestion() {
        return OutputMessage.ENTER_EXACTLY_TWO_SEEDS.toString();
    }

    private boolean processInput() {

        List<Integer> listOfArguments = processInputAndExpectMultipleNumbersCanContainDuplicates(askQuestion(),
                NUMBER_OF_ARGUMENTS_NEEDED, MAX_SEED);
        if (listOfArguments == null) {
            return false;
        }
        this.game.setDeck(
                new Deck(listOfArguments.get(0), listOfArguments.get(1), this.game.getLevel(), this.game.getRuna()));
        return true;
    }

    @Override
    protected void goToNextState() {
        GameState next = new StartFight(getSession(), this.game);
        next.execute();
    }

}
