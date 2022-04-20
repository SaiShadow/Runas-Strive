package edu.kit.informatik.runas_strive.ui.gamestate.fight;

import java.util.List;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.character.monster.Monster;
import edu.kit.informatik.runas_strive.model.character.runa.Runa;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.model.skill_card.CardAbility;
import edu.kit.informatik.runas_strive.model.skill_card.CardType;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.gamestate.GameState;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * This class represents the state 2.3.1 in A.1 in the assignment. When it is
 * Runa's turn, she can play exactly one of her ability cards. All offensive
 * abilities require Runa to target one of the monsters. While offensive
 * abilities trigger immediately, defensive abilities (For Runa: Parry or
 * Reflect) reduce damage from monster attacks in the monster's subsequent turn.
 * Runa's turn begins with an output from the game that describes the current
 * situation in the room: for better readability, the game separates the output
 * using 40 minus signs in the lines of the overview. First, Runa's condition
 * and who she is fighting are displayed. Their condition includes their hit
 * points (<HP>) and their current focus points (<FP>). Both are specified as
 * <Current Value>/<Maximum Value>.
 * 
 * @author uogok
 * @version 29
 */
public class RunasTurn extends Fight {

    private static final String MINUS = "-";
    private static final int NUMBER_OF_MINUS = 40;
    private final String minusLineWith40;

    private Runa runa;
    private List<Card> runasCards;
    private Card cardToUse;
    private Monster monster;
    private int diceThrow;

    /**
     * Constructor for this class, it also generates {@link #minusLineWith40} which
     * is String which contains 40 {@link RunasTurn#MINUS} which form a line. Is
     * constructed from {@link FocusPointsOfRuna#goToNextState()}.
     * 
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link Fight#Fight(Session, RunasStrive)}
     */
    public RunasTurn(Session session, RunasStrive game) {
        super(session, game);
        minusLineWith40 = get40MinusSymbols();
        this.runa = getGame().getRuna();
        this.runasCards = this.runa.getCardsToUse();
    }

    @Override
    protected void goToNextState() {
        GameState next = new FocusPointsOfMonsters(getSession(), getGame());
        next.execute();

    }

    @Override
    public void execute() {
        printStatsBlock();
        // selects the card Runa has to use.
        selectCardToPlay();
        boolean processSuccesful = processSkillsInput();
        if (!processSuccesful) {
            return;
        }

        this.monster = getGame().getMonsters().get(0);
        if (getGame().getMonsters().size() > 1 && cardToUse.getType() == CardType.OFFENSIVE) {
            // selects the monster that Runa has to fight against.
            selectTarget();
            processSuccesful = processTargetsInput();
            if (!processSuccesful) {
                return;
            }
        }
        printPlayerUsesCard(this.runa, cardToUse);
        if (checkDiceRoll()) {
            // Dice roll
            processSuccesful = processDiceInput();
            if (!processSuccesful) {
                return;
            }
        }
        /**
         * evaluate card
         * {@link edu.kit.informatik.runas_strive.model.RunasStrive#evaluateRunasCards()}.
         */
        int damageTakenByDefender = getGame().evaluateRunasCards(cardToUse, monster, diceThrow);
        printDamageTakenByDefender(monster, cardToUse, damageTakenByDefender);
        printPlayerDiedIfTheyDied(monster);

        /**
         * Check if fight ended.
         */
        if (hasFightStopped()) {
            exitFightState();
            return;
        }

        goToNextState();
    }

    private boolean processDiceInput() {
        int input = processInputAndExpectOneInput(askQuestionDice(), getGame().getSidesOfDie());
        if (input == ERROR_NUMBER) {
            return false;
        }
        this.diceThrow = input;
        return true;
    }

    private String askQuestionDice() {
        return OutputMessage.ENTER_ROLL_OF_DIE_WITH_d_SIDES.format(getGame().getSidesOfDie());
    }

    private boolean checkDiceRoll() {
        return CardAbility.PHYSICAL.equals(cardToUse.getAbility()) && CardType.OFFENSIVE.equals(cardToUse.getType());
    }

    private String askQuestionTarget() {
        return OutputMessage.SELECT_ONE_OPTION.format(getGame().getMonsters().size());
    }

    private boolean processTargetsInput() {
        int input = processInputAndExpectOneInput(askQuestionTarget(), getGame().getMonsters().size());
        if (input == ERROR_NUMBER) {
            return false;
        }
        this.monster = getGame().getMonsters().get(input - 1);
        return true;
    }

    private void selectTarget() {
        System.out.println(OutputMessage.SELECT_TARGET);
        printListOfMonsters(getGame().getMonsters());
    }

    private void selectCardToPlay() {
        System.out.println(OutputMessage.SELECT_CARD.toString());
        printListOfCards(runasCards);
    }

    private boolean processSkillsInput() {
        int input = processInputAndExpectOneInput(askQuestionSkills(), runasCards.size());
        if (input == ERROR_NUMBER) {
            return false;
        }
        cardToUse = runasCards.get(input - 1);
        return true;
    }

    private String askQuestionSkills() {
        return OutputMessage.SELECT_ONE_OPTION.format(runasCards.size());
    }

    private void printStatsBlock() {
        System.out.println(minusLineWith40);
        printRunasStats();
        System.out.println(OutputMessage.VS);
        printMonsterStats();
        System.out.println(minusLineWith40);
    }

    private void printRunasStats() {
        System.out.println(OutputMessage.RUNAS_STATS.format(runa.getCurrentHP(), runa.getMaxHP(), runa.getCurrentFP(),
                runa.getMaxFP()));
    }

    private void printMonsterStats() {
        for (Monster monster : getGame().getMonsters()) {
            System.out.println(OutputMessage.MONSTER_STATS.format(monster.getName(), monster.getCurrentHP(),
                    monster.getCurrentFP(), monster.getCardToAttempt()));
        }
    }

    private String get40MinusSymbols() {
        String minusLine = MINUS;
        for (int i = 1; i < NUMBER_OF_MINUS; i++) {
            minusLine += MINUS;
        }
        return minusLine;
    }

}
