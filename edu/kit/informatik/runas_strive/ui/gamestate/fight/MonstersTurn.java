package edu.kit.informatik.runas_strive.ui.gamestate.fight;

import java.util.List;

import edu.kit.informatik.runas_strive.model.RunasStrive;
import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.character.monster.Monster;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.ui.Session;
import edu.kit.informatik.runas_strive.ui.gamestate.GameState;
import edu.kit.informatik.runas_strive.ui.resources.OutputMessage;

/**
 * This class represents the state 2.3.3 in A.1 in the assignment. During the
 * monsters' turn, they apply the ability that is next possible for them, one at
 * a time. Each action of a monster is first fully evaluated. For example, if
 * Runa dies from the attack of a first monster, the subsequent turn of a second
 * monster is no longer evaluated. Actions are logged analogously to Runa's
 * actions. I.e. it is first stated that a monster plays an ability. Then the
 * damage is calculated and displayed how much damage Runa or the monster
 * suffers. If a character dies, this is output as <name> dies, analogous to
 * Runa's turn.
 * 
 * @author uogok
 * @version 29
 */
public class MonstersTurn extends Fight {

    private final List<Monster> listOfMonsters;

    /**
     * Constructor for this class. Is constructed from
     * {@link FocusPointsOfMonsters#goToNextState()}.
     * 
     * 
     * @param session is to be set as Session in
     *                {@link GameState#GameState(Session)}.
     * @param game    Is to be set as {@link Fight#Fight(Session, RunasStrive)}
     */
    MonstersTurn(Session session, RunasStrive game) {
        super(session, game);
        this.listOfMonsters = getGame().getMonsters();
    }

    @Override
    public void execute() {
        for (Monster monster : listOfMonsters) {
            Card cardMonsterCanUse = getGame().getCardThatMonsterCanUse(monster);
            printPlayerUsesCard(monster, cardMonsterCanUse);
            int hpMonsterBeforeEvaluation = monster.getCurrentHP();
            int damage = getGame().evaluateMonstersCards(cardMonsterCanUse, monster);
            printDamageTakenByDefender(getGame().getRuna(), cardMonsterCanUse, damage);
            if (getGame().getRuna().isDead()) {
                exitFightState();
                return;
            }
            printReflectDamage(hpMonsterBeforeEvaluation, monster);
            printPlayerDiedIfTheyDied(monster);
            if (hasFightStopped()) {
                exitFightState();
                return;
            }
        }
        goToNextState();
    }

    private void printReflectDamage(int hpBeforeEvaluation, Player attacker) {
        if (hpBeforeEvaluation == attacker.getCurrentHP()) {
            return;
        }
        System.out.println(OutputMessage.CHARACTER_TAKES_MAG_DMG.format(attacker.getName(),
                hpBeforeEvaluation - attacker.getCurrentHP()));
    }

    @Override
    protected void goToNextState() {
        GameState next = new FocusPointOfRuna(getSession(), getGame());
        next.execute();
    }
}
