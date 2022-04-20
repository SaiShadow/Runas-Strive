package edu.kit.informatik.runas_strive.ui.gamestate.fight;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.model.skill_card.CardAbility;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.gamestate.End;
import edu.kit.informatik.runas_strive.ui.gamestate.GameState;
import edu.kit.informatik.runas_strive.ui.gamestate.NewCardOrNewDice;
import edu.kit.informatik.runas_strive.ui.gamestate.UpgradeRunasSkills;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * This class represents the state 2.3 in A.1 in the assignment. It holds common
 * methods that the subclasses need.
 * 
 * @author uogok
 * @version 29
 */
public abstract class Fight extends GameState {

    private final RunasStrive game;

    /**
     * Constructor for this class. Is constructed in/from
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.fight.Fight}.
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link #game} represents {@link RunasStrive}.
     */
    public Fight(Session session, RunasStrive game) {
        super(session);
        this.game = game;
    }

    /**
     * Gets the RunasStrive object reference in {@link #game}.
     * 
     * @return {@link #game}.
     */
    protected RunasStrive getGame() {
        return game;
    }

    /**
     * The value of {@link RunasStrive#allowedToEnterNextRoom()} which indicates if
     * the fight has ended and if you can enter the next stage.
     * 
     * @return value of {@link RunasStrive#allowedToEnterNextRoom()}.
     */
    protected boolean hasFightStopped() {
        return this.game.allowedToExitFightingPhase();
    }

    /**
     * Print that @param player used @param cardToUse using the format
     * {@link OutputMessage#CHARACTER_USES_CARD}.
     * 
     * @param player    is the player who uses @param cardToUse.
     * @param cardToUse is used by @param player.
     */
    protected void printPlayerUsesCard(Player player, Card cardToUse) {
        System.out.println(OutputMessage.CHARACTER_USES_CARD.format(player.getName(), cardToUse.toString()));
    }

    /**
     * Prints the damage taken by a defending player i.e. @param defender in the
     * format of {@link OutputMessage#CHARACTER_TAKES_MAG_DMG} if the @param
     * cardToUse deals mag. damage or {@link OutputMessage#CHARACTER_TAKES_PHY_DMG}
     * if the @param cardToUse deals phy. damage.
     * 
     * @param defender              defends against @param cardToUse.
     * @param cardToUse             is used against @param defender.
     * @param damageTakenByDefender damage taken from @param cardToUse on @param
     *                              defender .
     */
    protected void printDamageTakenByDefender(Player defender, Card cardToUse, int damageTakenByDefender) {
        if (damageTakenByDefender == 0) {
            return;
        }
        OutputMessage output = (CardAbility.MAGICAL.equals(cardToUse.getAbility()))
                ? OutputMessage.CHARACTER_TAKES_MAG_DMG
                : OutputMessage.CHARACTER_TAKES_PHY_DMG;
        System.out.println(output.format(defender.getName(), damageTakenByDefender));
    }

    /**
     * Prints the certain @param player if they died {@link Player#isDead()}, using
     * this format
     * 
     * @param player is to be printed onto the console if {@link Player#isDead()} is
     *               {@link OutputMessage#CHARACTER_DIED} true.
     */
    protected void printPlayerDiedIfTheyDied(Player player) {
        if (player.isDead()) {
            System.out.println(OutputMessage.CHARACTER_DIED.format(player.getName()));
        }
    }

    /**
     * If the certain fight ended i.e. by winning or losing then this method
     * transitions into the next {@link GameState}. It also resets Runa
     * {@link RunasStrive#resetRuna()} if the game transitions into
     * {@link UpgradeRunasSkills} or {@link NewCardOrNewDice}.
     */
    protected void exitFightState() {

        if (this.game.didGameEnd()) {
            GameState next = new End(getSession(), this.game);
            next.execute();
            return;
        } else if (hasFightStopped()) {

            this.game.resetRuna();
            if (this.game.getRoom() == MAXIMUM_NUMBER_OF_ROOMS) {

                GameState next = new UpgradeRunasSkills(getSession(), this.game);
                next.execute();
                return;
            } else {
                GameState next = new NewCardOrNewDice(getSession(), this.game);
                next.execute();
                return;
            }
        }
    }

    /**
     * Prints the number of focus points gained by @param player in the format
     * {@link OutputMessage#GAINS_FOCUS}.
     * 
     * @param player This player's gained focus points is to be printed.
     */
    protected void printFocusPoints(Player player) {
        int added = this.game.addFocusPoints(player);
        if (added > 0) {
            System.out.println(OutputMessage.GAINS_FOCUS.format(player.getName(), added));
        }
    }

}
