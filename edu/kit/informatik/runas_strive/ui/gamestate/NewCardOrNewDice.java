package edu.kit.informatik.runas_strive.ui.gamestate;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * This class represents 2.4.1 in A.1 in the assignment. It either chooses a new
 * Dice or new Cards for Runa, if the max dice is already achieved then don't
 * show the dice option anymore.
 * 
 * @author uogok
 * @version 29
 */
public class NewCardOrNewDice extends GameState {
    private static final int INDEX_OF_DICE_SELECT = 2;

    private final List<String> listOfOptions = List.of(OutputMessage.NEW_ABILITY_CARDS.toString(),
            OutputMessage.NEXT_PLAYER_DICE.toString());

    private int optionChosenForReward;
    private int numberOfLootYouCanCollect;
    private List<Card> listOfSkillsLootRunaCanAcquire;
    private final RunasStrive game;

    /**
     * Constructor for this class.
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link #game} represents {@link RunasStrive}
     */
    public NewCardOrNewDice(Session session, RunasStrive game) {
        super(session);
        this.game = game;
    }

    @Override
    public void execute() {
        if (!this.game.hasMaxDice()) {
            printQuestionRunaReward();
            printOptions();
            boolean processSuccesful = processInputForRewardSelection();
            if (!processSuccesful) {
                return;
            }
            if (optionChosenForReward == INDEX_OF_DICE_SELECT) {
                this.game.upgradeDice();
                printDiceUpgrade();
                goToNextState();
                return;
            }
        }

        this.listOfSkillsLootRunaCanAcquire = this.game.getRunasCardReward();
        this.numberOfLootYouCanCollect = this.game.getHowManyCardsRunaCanGet(listOfSkillsLootRunaCanAcquire.size());

        printLootQuestion();
        printLoot();
        boolean processSuccesful = processInputForLootSelection();
        if (!processSuccesful) {
            return;
        }
        goToNextState();
        return;
    }

    private boolean processInputForLootSelection() {
        List<Integer> listOfArguments = new ArrayList<>();
        if (numberOfLootYouCanCollect == 1) {
            int input = processInputAndExpectOneInput(askQuestionForLoot(), listOfSkillsLootRunaCanAcquire.size());
            if (input == ERROR_NUMBER) {
                return false;
            }
            listOfArguments.add(input);
        } else {
            listOfArguments = processInputAndExpectMultipleNumbers(askQuestionForLoot(), numberOfLootYouCanCollect,
                    listOfSkillsLootRunaCanAcquire.size());
            if (listOfArguments == null) {
                return false;
            }
        }
        List<Card> skillsToAddToRuna = new ArrayList<>();
        for (Integer index : listOfArguments) {
            skillsToAddToRuna.add(listOfSkillsLootRunaCanAcquire.get(index - 1));
        }
        this.game.addSkillsToRuna(skillsToAddToRuna);
        printRunaGetsSkills(skillsToAddToRuna);
        return true;
    }

    private String askQuestionForLoot() {
        OutputMessage message = OutputMessage.SELECT_MORE_THAN_ONE_OPTION;

        if (numberOfLootYouCanCollect == 1) {
            message = OutputMessage.SELECT_ONE_OPTION;
        }
        return message.format(listOfSkillsLootRunaCanAcquire.size());
    }

    private void printLoot() {
        printListOfCards(this.listOfSkillsLootRunaCanAcquire);
    }

    private void printLootQuestion() {
        System.out.println(OutputMessage.PICK_LOOT.format(numberOfLootYouCanCollect));
    }

    private void printDiceUpgrade() {
        System.out.println(OutputMessage.DICE_UPGRADE.format(this.game.getSidesOfDie()));
    }

    private boolean processInputForRewardSelection() {
        int input = processInputAndExpectOneInput(askQuestionForReward(), listOfOptions.size());
        if (input == ERROR_NUMBER) {
            return false;
        }
        this.optionChosenForReward = input;
        return true;
    }

    private String askQuestionForReward() {
        return OutputMessage.SELECT_ONE_OPTION.format(listOfOptions.size());
    }

    private void printOptions() {
        printListOfOptionsInString(listOfOptions);
    }

    private void printQuestionRunaReward() {
        System.out.println(OutputMessage.RUNA_REWARD.toString());
    }

    @Override
    protected void goToNextState() {
        GameState next = new Healing(getSession(), this.game);
        next.execute();
    }

}
