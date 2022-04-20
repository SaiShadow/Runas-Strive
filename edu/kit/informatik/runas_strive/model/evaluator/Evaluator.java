package edu.kit.informatik.runas_strive.model.evaluator;

import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.skill_card.Card;

/**
 * Represents an evaluator, whose purpose is to evaluate cards which is used by
 * an attacker against a defender.
 * 
 * @author uogok
 * @version 2
 */
public interface Evaluator {

    /**
     * Evaluates the action that @param attacker used @param card against @param
     * defender. And return the amount of damage dealt to @param defender.
     * 
     * @param card     is used by @param attacker against @param defender.
     * @param attacker uses @param card against @param defender.
     * @param defender is attacked by @param attacker using @param card.
     * @return the amount of damage dealt to @param defender.
     */
    int evaluate(Card card, Player attacker, Player defender);
}
