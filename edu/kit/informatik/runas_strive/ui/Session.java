package edu.kit.informatik.runas_strive.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import edu.kit.informatik.runas_strive.ui.gamestate.GameState;
import edu.kit.informatik.runas_strive.ui.gamestate.Initialisation;

/**
 * This class describes a session for interactive command execution. Also gets
 * the user input and send it to {@link GameState#execute()}.
 * 
 * @author uogok
 * @version 19
 */
public class Session {
    private static final String QUIT = "quit";
    private static final String SPLIT_COMMA = ",";
    private static final char SPLIT_COMMA_AS_CHAR = ',';
    private static final int DEFAULT_INT_FOR_ERRORS = -1;

    private Scanner scanner;
    private String lookahead;
    private boolean quit;

    /**
     * Constructs a new Session by starting a scanner, it doesn't need any
     * arguments.
     */
    public Session() {
        this.scanner = new Scanner(System.in);
        this.quit = false;
    }

    /**
     * Starts the game by entering the GameState {@link Initialisation} and
     * executing it, which is the starting game-state of Runa's Strive.
     */
    public void start() {
        GameState gameState = new Initialisation(this);
        gameState.execute();
    }

    /**
     * This method gets nextLine(){@link #scanner} from the scanner and saves it to
     * {@link #lookahead}, if the input equals 'quit' then {@link #setQuit(true)}
     * which indicates that the program must terminate.
     */
    private void goToNextLine() {
        lookahead = scanner.nextLine();
        if (lookahead.equals(QUIT)) {
            setQuit(true);
        }
    }

    /**
     * This method is called if the {@link GameState#execute()} needs a single
     * int-input. This method then returns the input if its syntactically correct.
     * 
     * @return the single integer input if the input is syntactically and
     *         semantically correct, otherwise return
     *         {@link #DEFAULT_INT_FOR_ERRORS}.
     */
    public int expectOneNumber() {
        goToNextLine();
        if (!checkSyntax()) {
            return DEFAULT_INT_FOR_ERRORS;
        }
        return getIntFromString(lookahead);
    }

    /**
     * Checks the syntax of the input, it checks for empty inputs,
     * {@link #SPLIT_COMMA} at the end of the input and if there are unnecessary
     * spaces.
     * 
     * @return true, if the input is syntactically correct, otherwise false.
     */
    private boolean checkSyntax() {
        String trimmedLookAhead = lookahead.trim();
        return !(lookahead.isEmpty() || (lookahead.charAt(lookahead.length() - 1) == SPLIT_COMMA_AS_CHAR)
                || (!(trimmedLookAhead.equals(lookahead))));
    }

    /**
     * This method is called if the {@link GameState#execute()} needs multiple
     * integers to execute. This method then returns the input converted to integers
     * and saved in a List<Integer> if its syntactically correct.
     * 
     * @return List<Integer> of all the integer values of the current input if input
     *         is syntactically correct, otherwise return an empty list.
     */
    public List<Integer> expectMultipleNumbers() {
        goToNextLine();

        List<Integer> listWithArguments = new ArrayList<>();
        if (!checkSyntax()) {

            if (!lookahead.isEmpty()) {
                listWithArguments.add(DEFAULT_INT_FOR_ERRORS);
            }
            return listWithArguments;
        }

        String[] splitArray = lookahead.split(SPLIT_COMMA);
        if (splitArray.length == 0) {
            return listWithArguments;
        }
        for (String str : splitArray) {
            int intValue = getIntFromString(str);
            listWithArguments.add(intValue);
        }

        return listWithArguments;
    }

    /**
     * Checks if @param list contains duplicate integer entries.
     * 
     * @param list this list to be checked for duplicates.
     * @return true, if @param list contains duplicate integer entries/values,
     *         otherwise return false.
     */
    public boolean hasDuplicates(List<Integer> list) {
        TreeSet<Integer> listInTreeSet = new TreeSet<>(list);
        return listInTreeSet.size() != list.size();
    }

    /**
     * Gets the int value of a String.
     * 
     * @param str is to be converted to an int.
     * @return int value of @param str, if conversion failed then return
     *         {@link #DEFAULT_INT_FOR_ERRORS}.
     */
    private int getIntFromString(String str) {
        int value = 0;
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException message) {
            return DEFAULT_INT_FOR_ERRORS;
        }
        return value;
    }

    /**
     * Indicates if 'quit' was entered as an input(it also returns true if the game
     * entered the game-state
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.End}, but this method is
     * irrelevant if the program ever reaches
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.End#execute()}) and if it
     * returns true, then the program must terminate as soon as possible.
     * 
     * @return true, if 'quit' was entered as an input (also indicates that the
     *         {@link #scanner} closed), otherwise return false.
     */
    public boolean isQuit() {
        return quit;
    }

    /**
     * Sets {@link #quit} to the value in the parameter. If @param quit is true,
     * then it closes {@link #scanner} and indicates through {@link #isQuit()} that
     * the program must terminate.
     * 
     * @param quit
     */
    public void setQuit(boolean quit) {
        this.quit = quit;
        if (quit) {
            scanner.close();
        }
    }
}
