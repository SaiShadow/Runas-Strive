package edu.kit.informatik.runas_strive.ui.resources;

/**
 * All messages that are shown to the user with an error message
 * {@link #ERROR_ENTERED_ARGUMENTS}.
 * 
 * @author uogok
 * @version 19
 */
public enum OutputMessage {
    /**
     * Is displayed at the start of the game.
     */
    ENTRY_MESSAGE_GAME("Welcome to Runa's Strive"),

    /**
     * Is used to ask the user to only input a single integer, in the specified
     * range.
     */
    SELECT_ONE_OPTION("Enter number [1--%d]:"),

    /**
     * Is used to ask the user to input integers, in the specified range.
     */
    SELECT_MORE_THAN_ONE_OPTION("Enter numbers [1--%d] separated by comma:"),

    /**
     * Is used to ask the user to input a dice roll i.e. an integer in the specified
     * range. Range differs from 4-12.
     */
    ENTER_ROLL_OF_DIE_WITH_d_SIDES("Enter dice roll [1--%d]:"),

    /**
     * Is used to ask the user to enter two seeds for
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.Shuffle#execute()}. Range
     * differs from 1-2147483647.
     */
    ENTER_EXACTLY_TWO_SEEDS("Enter seeds [1--2147483647] separated by comma:"),

    /**
     * Entry message for the shuffle state,
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.Shuffle#execute()}.
     */
    ENTRY_MESSAGE_SHUFFLE("To shuffle ability cards and monsters, enter two seeds"),

    /**
     * Entry message when Runa enters a fight,
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.StartFight#execute()}.
     */
    ENTER_FIGHT("Runa enters Stage %d of Level %d"),

    /**
     * Displayed/used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.RunasTurn#execute()}.
     */
    VS("vs."),

    /**
     * Displayed/used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.RunasTurn#execute()}.
     * Is to display the monsters statistics, like <HP>, <FP> and what card they
     * attempt next.
     */
    MONSTER_STATS("%s (%d HP, %d FP): attempts %s next"),

    /**
     * Displayed/used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.RunasTurn#execute()}.
     * Is to display the Runa's statistics, like maximum and current <HP> and <FP>.
     */
    RUNAS_STATS("Runa (%d/%d HP, %d/%d FP)"),

    /**
     * Displayed/used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.Initialisation#execute()}.
     * Is used to ask user to select Runa's class for the game.
     */
    SELECT_RUNAS_CLASS("Select Runa's character class"),

    /**
     * Displayed/used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.RunasTurn#execute()}.
     * Is used to ask user to select Runa's card to play for this turn of the game.
     */
    SELECT_CARD("Select card to play"),

    /**
     * Displayed/used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.RunasTurn#execute()}.
     * Is used to ask user to select Runa's monster to fight for this turn of the
     * game.
     */
    SELECT_TARGET("Select Runa's target."),

    /**
     * Used to list a list of elements.
     */
    DISPLAY_ELEMENTS("%d) %s"),

    /**
     * Used to display that a player <s> has taken <d> amount of mag. damage.
     */
    CHARACTER_TAKES_MAG_DMG("%s takes %d mag. damage"),

    /**
     * Used to display that a player <s> has taken <d> amount of phy. damage.
     */
    CHARACTER_TAKES_PHY_DMG("%s takes %d phy. damage"),

    /**
     * Used to display that a player used the mentioned card.
     */
    CHARACTER_USES_CARD("%s uses %s"),

    /**
     * Used to display that a player gained focus.
     */
    GAINS_FOCUS("%s gains %d focus"),

    /**
     * Used to display that a player died.
     */
    CHARACTER_DIED("%s dies"),

    /**
     * Used to display that Runa gained a card.
     */
    RUNA_GETS_CARD("Runa gets %s"),

    /**
     * Used to display that Runa won.
     */
    GAME_WON("Runa won!"),

    /**
     * Used to display that Runa lost i.e. she died.
     */
    GAME_LOST("Runa dies"),

    /**
     * Used to display Runa's reward.
     */
    RUNA_REWARD("Choose Runa's reward"),

    /**
     * Used to display the option 'new ability cards'.
     */
    NEW_ABILITY_CARDS("new ability cards"),

    /**
     * Used to display the option 'next player dice'.
     */
    NEXT_PLAYER_DICE("next player dice"),

    /**
     * Used to display that Runa upgraded her die.
     */
    DICE_UPGRADE("Runa upgrades her die to a d%d"),

    /**
     * Used to display the question to the user how many cards he has to choose as
     * loot.
     */
    PICK_LOOT("Pick %d card(s) as loot"),

    /**
     * Used to display the question to the user if he wants Runa to heal.
     */
    HEALING_QUESTION("%s (%d/%d HP) can discard ability cards for healing (or none)"),

    /**
     * Used to display the question to the user how much health Runa gained.
     */
    HEALING_GAINED_HP("Runa gains %d health"),

    /**
     * To avoid null.
     */
    NONE(""),

    /**
     * Is to be displayed in
     * {@link edu.kit.informatik.runas_strive.Application#main(String[])} if
     * arguments are sent in while starting the program.
     */
    ERROR_ENTERED_ARGUMENTS("arguments were entered while starting the program, this program doesn't need arguments.") {

        private static final String PREFIX = "Error, ";

        @Override
        public String toString() {
            return PREFIX + this.getMessage();
        }
    };

    private final String message;

    private OutputMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the {@link #message}. Used only in
     * {@link #ERROR_ENTERED_ARGUMENTS#toString()}.
     * 
     * @return Gets the value of {@link #message}.
     */
    protected String getMessage() {
        return message;
    }

    /**
     * Formats this message with the specified arguments.
     *
     * Calls {@link String#format(String, Object...)} internally.
     *
     * @param args arguments referenced by the format specifiers in the format
     *             string
     * @return the formatted string
     */
    public String format(Object... args) {
        return String.format(this.message, args);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
