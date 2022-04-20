package edu.kit.informatik.runas_strive.ui.gamestate;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.runas_strive.model.character.monster.Monster;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * Represents a GameState. Which is every state Runa's Strive can be found in.
 * This is an abstract class to represent every game-state illustrated in the
 * assignment in A.1. Every child class (i.e. the specific game-states) of this
 * class must implement {@link #execute()} and {@link #goToNextState()}.
 * 
 * @author uogok
 * @version 29
 */
public abstract class GameState {

    /**
     * This int indicates that an error occurred, mostly displayed when the scanner
     * closes because 'quit' was entered as an input.
     */
    protected static final int ERROR_NUMBER = -1;

    /**
     * The maximum number of rooms on each level are 4.
     */
    protected static final int MAXIMUM_NUMBER_OF_ROOMS = 4;

    private final Session session;

    /**
     * Constructs a GameState which only requires a {@link Session}.
     * 
     * @param session is to be set as {@link #session}
     */
    protected GameState(Session session) {
        this.session = session;
    }

    /**
     * Gets the current Session's reference.
     * 
     * @return current Session.
     */
    protected Session getSession() {
        return session;
    }

    /**
     * Every game state must implement this method, and it is run right after
     * instantiating a new GameState object. This method processes the input and
     * outputs the specific messages found in
     * {@link edu.kit.informatik.runas_strive.ui.resources.OutputMessage}, and send
     * the necessary parameters and arguments to
     * {@link edu.kit.informatik.runas_strive.model.RunasStrive} for further
     * evaluation.
     */
    public abstract void execute();

    /**
     * Checks if the scanner in {@link Session} has been closed i.e. returns the
     * value of {@link Session#isQuit()}.
     * 
     * @return value of {@link Session#isQuit()}.
     */
    protected boolean checkIfScannerClosed() {
        return this.session.isQuit();
    }

    private boolean checkRangeOfAllElementsInListWithExactNumberOfElements(int numberOfElementsNeeded,
            List<Integer> listToCheck, int lowerLimit, int upperLimit) {

        if (listToCheck.size() != numberOfElementsNeeded) {
            return false;
        }

        return checkRangeOfElements(listToCheck, lowerLimit, upperLimit);
    }

    private boolean checkRangeOfElements(List<Integer> listToCheck, int lowerLimit, int upperLimit) {
        for (int valueOfInteger : listToCheck) {

            if (!isInRange(valueOfInteger, lowerLimit, upperLimit)) {
                return false;
            }
        }

        return true;
    }

    private boolean isInRange(int toCheck, int lowerLimit, int upperLimit) {
        return ((toCheck >= lowerLimit) && (toCheck <= upperLimit));
    }

    /**
     * This method formats {@link OutputMessage#RUNA_GETS_CARD} and prints it on
     * console. It prints which skill-cards Runa obtained, theses card are saved
     * in @param skillsAddedToRuna.
     * 
     * @param skillsAddedToRuna The cards in this list are to be printed onto the
     *                          console after formatting it with
     *                          {@link OutputMessage#RUNA_GETS_CARD}.
     */
    protected void printRunaGetsSkills(List<Card> skillsAddedToRuna) {
        for (Card card : skillsAddedToRuna) {
            System.out.println(OutputMessage.RUNA_GETS_CARD.format(card.toString()));
        }
    }

    /**
     * This method processes the input and needs multiple integer values(i.e. at
     * least 2 integer values). And the @return List<Integer> must not contains any
     * duplicates, this is checked using {@link Session#hasDuplicates(List)}. The
     * List of integer values is first got from
     * {@link #processInputAndExpectMultipleNumbersCanContainDuplicates(String, int, int)}
     * which might contain duplicates, and then is checked using , this is checked
     * using {@link Session#hasDuplicates(List)}.
     * 
     * @param message                 this is the message that is to be printed on
     *                                the console to ask to enter a specific amount
     *                                of numbers, it is usually
     *                                {@link OutputMessage#SELECT_MORE_THAN_ONE_OPTION}.
     * @param numberOfArgumentsNeeded the List<Integer> which is to be returned must
     *                                have exactly @param numberOfArgumentsNeeded
     *                                number of elements.
     * @param upperLimit              The elements in the @return List must not have
     *                                values that exceed @param upperLimit.
     * @return ArrayList<Integer> of integer values with size @param
     *         numberOfArgumentsNeeded, elements that don't exceed the value
     *         of @param upperLimit and doesn't contain duplicates. null is returned
     *         if 'quit' was entered as the input.
     */
    protected List<Integer> processInputAndExpectMultipleNumbers(String message, int numberOfArgumentsNeeded,
            int upperLimit) {

        List<Integer> listOfArguments = new ArrayList<>();
        do {
            listOfArguments = processInputAndExpectMultipleNumbersCanContainDuplicates(message, numberOfArgumentsNeeded,
                    upperLimit);

            if (listOfArguments == null) {
                return null;
            }
        } while (this.session.hasDuplicates(listOfArguments));
        return new ArrayList<>(listOfArguments);
    }

    /**
     * This method processes the input and needs multiple integer values(i.e. at
     * least 2 integer values). The @return List might contain duplicate entries.
     * Check out{@link #printMessageAndGetInput(String)} and
     * {@link #checkRangeOfAllElementsInListWithExactNumberOfElements(int, List, int, int)}.
     * 
     * @param message                 this is the message that is to be printed on
     *                                the console to ask to enter a specific amount
     *                                of numbers, it is usually
     *                                {@link OutputMessage#SELECT_MORE_THAN_ONE_OPTION}
     *                                or
     *                                {@link OutputMessage#ENTER_EXACTLY_TWO_SEEDS}.
     * @param numberOfArgumentsNeeded the @return List<Integer> which is to be
     *                                returned must have exactly @param
     *                                numberOfArgumentsNeeded number of elements.
     * @param upperLimit              the elements in the list to @return must not
     *                                exceed this value @param upperLimit
     * @return ArrayList<Integer> of integer values with size @param
     *         numberOfArgumentsNeeded and elements that don't exceed the value
     *         of @param upperLimit. null is returned if 'quit' was entered as the
     *         input.
     */
    protected List<Integer> processInputAndExpectMultipleNumbersCanContainDuplicates(String message,
            int numberOfArgumentsNeeded, int upperLimit) {
        List<Integer> listOfArguments = new ArrayList<>();
        do {
            listOfArguments = printMessageAndGetInput(message);
            if (listOfArguments == null) {
                return null;
            }
        } while (!this.checkRangeOfAllElementsInListWithExactNumberOfElements(numberOfArgumentsNeeded, listOfArguments,
                1, upperLimit));
        return new ArrayList<>(listOfArguments);

    }

    /**
     * Prints @param message onto the console, which is a question to enter an
     * amount of integers in the correct syntax. After that it gets a List<Integer>
     * from {@link Session#expectMultipleNumbers()}. If the scanner closed
     * {@link #checkIfScannerClosed()} then returns null.
     * 
     * 
     * @param message Is to be printed on the console, to ask the specific question.
     * @return null, if the scanner closed, due to 'quit' as input. Otherwise return
     *         the List from {@link Session#expectMultipleNumbers()}.
     */
    private List<Integer> printMessageAndGetInput(String message) {
        System.out.println(message);
        List<Integer> listOfArguments = session.expectMultipleNumbers();
        if (this.checkIfScannerClosed()) {
            return null;
        }
        return new ArrayList<>(listOfArguments);
    }

    /**
     * This method processes the input and needs at most @param maxNumberOfArguments
     * many elements. This method however doesn't return List<Integer> with
     * duplicate entries. This method is usually used in {@link Healing#execute()}.
     * If the scanner closed {@link #checkIfScannerClosed()} then returns null.
     * Check out {@link GameState#printMessageAndGetInput(String)}
     * 
     * @param message              Is to be printed on the console, to ask the
     *                             specific question.
     * @param maxNumberOfArguments the number of elements(i.e. size of the list) in
     *                             the list to @return must not exceed this
     *                             value @param maxNumberOfArguments
     * @param upperLimit           the elements in the list to @return must not
     *                             exceed this value @param upperLimit
     * @return ArrayList<Integer> of integer values maximum size of @param
     *         numberOfArgumentsNeeded and elements that don't exceed the value
     *         of @param upperLimit. null is returned if 'quit' was entered as the
     *         input (i.e. if scanner closed).
     */
    protected List<Integer> processInputAndExpectMaxArguments(String message, int maxNumberOfArguments,
            int upperLimit) {
        List<Integer> listOfArguments = new ArrayList<>();
        do {
            listOfArguments = printMessageAndGetInput(message);
            if (listOfArguments == null) {
                return null;
            }
        } while (!this.checkRangeOfAllElementsInListWithMaxArguments(maxNumberOfArguments, listOfArguments, 1,
                upperLimit) || this.session.hasDuplicates(listOfArguments));
        return new ArrayList<>(listOfArguments);

    }

    private boolean checkRangeOfAllElementsInListWithMaxArguments(int maxNumberOfArguments, List<Integer> listToCheck,
            int lowerBoundary, int upperBoundary) {
        if (listToCheck.size() > maxNumberOfArguments) {
            return false;
        }

        return checkRangeOfElements(listToCheck, lowerBoundary, upperBoundary);
    }

    /**
     * This method processes the input and needs exactly one integer as input. Check
     * out {@link #processInputGetSingleOrNoInteger(String, int, int)} and
     * {@link Session#expectOneNumber()}.
     * 
     * @param messageToAsk this String is to be printed on the console, to ask the
     *                     specific question.
     * @param upperLimit   the entered integer mustn't exceed this value.
     * @return value of the entered input as integer. Returns {@link #ERROR_NUMBER}
     *         if {@link #checkIfScannerClosed()} is true.
     */
    protected int processInputAndExpectOneInput(String messageToAsk, int upperLimit) {
        return processInputGetSingleOrNoInteger(messageToAsk, 1, upperLimit);
    }

    private int processInputGetSingleOrNoInteger(String messageToAsk, int lowerLimit, int upperLimit) {
        int input = ERROR_NUMBER;
        do {
            System.out.println(messageToAsk);

            input = session.expectOneNumber();

            if (this.checkIfScannerClosed()) {
                return ERROR_NUMBER;
            }

        } while (!(this.isInRange(input, lowerLimit, upperLimit)));
        return input;
    }

    /**
     * Prints all Cards in @param listOfCards. Check out
     * {@link #printOptions(int, String)}.
     * 
     * @param listOfCards The cards in this list are to be printed onto the console
     *                    in this format {@link OutputMessage#DISPLAY_ELEMENTS}.
     */
    protected void printListOfCards(List<Card> listOfCards) {
        int i = 1;
        for (Card card : listOfCards) {
            printOptions(i, card.toString());
            i++;
        }
    }

    /**
     * Prints all String values in @param optionsList. Check out
     * {@link #printOptions(int, String)}.
     * 
     * @param optionsList The Strings in this list are to be printed onto the
     *                    console in this format
     *                    {@link OutputMessage#DISPLAY_ELEMENTS}.
     */
    protected void printListOfOptionsInString(List<String> optionsList) {
        int i = 1;
        for (String message : optionsList) {
            printOptions(i, message);
            i++;
        }
    }

    private void printOptions(int index, String mesage) {
        System.out.println(OutputMessage.DISPLAY_ELEMENTS.format(index, mesage));
    }

    /**
     * Prints all Monsters {@link Monster} in @param listOfCards. Check out
     * {@link #printOptions(int, String)}.
     * 
     * @param listOfMonsters The Monsters in this list are to be printed onto the
     *                       console in this format
     *                       {@link OutputMessage#DISPLAY_ELEMENTS}.
     */
    protected void printListOfMonsters(List<Monster> listOfMonsters) {
        int i = 1;
        for (Monster monster : listOfMonsters) {
            printOptions(i, monster.getName());
            i++;
        }
    }

    /**
     * Every class that extends {@link GameState} must implement this method.
     * Executes (/goes to) the next game-state as drawn in illustration A.1 in the
     * assignment. Also increases the room count in {@link Healing#goToNextState()}.
     */
    protected abstract void goToNextState();
}
