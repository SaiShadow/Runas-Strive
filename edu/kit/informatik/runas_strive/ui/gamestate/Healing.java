package edu.kit.informatik.runas_strive.ui.gamestate;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.character.runa.Runa;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * This class represents the healing state of RunasStrive(2.4.3). This only has
 * significance if {@link Runa#canHeal()}. Otherwise it just
 * {@link #goToNextState()}, which is{@link StartFight} or {@link Shuffle}
 * depending on the room level {@link RunasStrive#getRoom()}.
 * {@link #goToNextState()} also runs {@link RunasStrive#nextRoom()} which
 * increases the current number of rooms. by 1
 * 
 * @author uogok
 * @version 29
 */
public class Healing extends GameState {

    private final RunasStrive game;
    private final List<Card> runasCards;

    /**
     * Constructs this class. is constructed from {@link NewCardOrNewDice} or
     * {@link UpgradeRunasSkills}.
     * 
     * @param session is to be set as Session represents {@link Session}, useful for
     *                methods in {@link GameState}.
     * @param game    Is to be set as {@link #game} represents {@link RunasStrive}
     */
    Healing(Session session, RunasStrive game) {
        super(session);
        this.game = game;
        this.runasCards = this.game.getRunasCards();
    }

    @Override
    public void execute() {
        if (this.game.canRunaHeal()) {
            printInformation();
            boolean processSuccesful = processInput();
            if (!processSuccesful) {
                return;
            }
        }
        goToNextState();
        return;
    }

    private void printInformation() {
        Runa runa = this.game.getRuna();
        System.out.println(OutputMessage.HEALING_QUESTION.format(runa.getName(), runa.getCurrentHP(), runa.getMaxHP()));
        printListOfCards(runasCards);
    }

    private String getQuestion() {
        OutputMessage message = OutputMessage.SELECT_MORE_THAN_ONE_OPTION;

        if (runasCards.size() == 2) {
            message = OutputMessage.SELECT_ONE_OPTION;
        }
        return message.format(runasCards.size());
    }

    private boolean processInput() {
        List<Integer> listOfArguments = new ArrayList<>();
        listOfArguments = processInputAndExpectMaxArguments(getQuestion(), runasCards.size() - 1, runasCards.size());
        if (listOfArguments == null) {
            return false;
        }

        List<Card> skillsToRemoveFromRuna = new ArrayList<>();
        for (Integer index : listOfArguments) {
            skillsToRemoveFromRuna.add(runasCards.get(index - 1));
        }
        this.game.removeSkillsFromRuna(skillsToRemoveFromRuna);
        int amount = skillsToRemoveFromRuna.size();
        if (amount != 0) {
            int hpGained = this.game.addHealthToRuna(amount);
            System.out.println(OutputMessage.HEALING_GAINED_HP.format(hpGained));
        }
        return true;
    }

    @Override
    protected void goToNextState() {
        GameState next = null;
        if (this.game.getRoom() < MAXIMUM_NUMBER_OF_ROOMS) {
            next = new StartFight(getSession(), this.game);
        } else {
            next = new Shuffle(getSession(), this.game);
        }
        this.game.nextRoom();
        next.execute();
    }

}
