package edu.kit.informatik.runas_strive.model.evaluator;

import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.character.runa.Runa;
import edu.kit.informatik.runas_strive.model.skill_card.Card;

/**
 * Evaluates all the physical skill cards, from both monsters and Runa.
 * 
 * @author uogok
 * @version 35
 */
public interface PhysicalEvaluator extends Evaluator {

    /**
     * Is the requirement of dice throw to get bonus damage.
     */
    int REQUIREMENT_FOR_BONUS = 6;

    /**
     * Evaluates physical attacks used by all monster skill cards and some Runa's
     * skill cards.
     * 
     * @param attacker   uses @param card against @param defender.
     * @param defender   @param attacker uses @param card against them.
     * @param multiplier used in damage calculations.
     * @param card       @param attacker uses this against @param defender.
     * @return damage @param attacker dealt with @param card against @param
     *         defender.
     */
    default int evaluateAttackWithMultiplier(Player attacker, Player defender, int multiplier, Card card) {

        int initialDamage = multiplier * card.getLevel();
        return dmgAfterBlocking(initialDamage, defender);

    }

    /**
     * Evaluates physical defence cards.
     * 
     * @param attacker   uses @param card.
     * @param multiplier used for intensity calculations.
     * @param card       physical defence card.
     * @return 0, as no damage is dealt to the enemy.
     */
    default int evaluateDefenseWithMultiplier(Player attacker, int multiplier, Card card) {
        attacker.setPhysicalDefense(multiplier * card.getLevel());
        return 0;
    }

    /**
     * Evaluates physical attacks used only by some Runa's skill cards.
     * 
     * @param card
     * @param attacker   Runa.
     * @param defender   Monster.
     * @param multiplier used in damage calculations.
     * @param bonus      used in damage calculations, {@link Runa#getDiceThrow()} is
     *                   greater than or equal to {@link #REQUIREMENT_FOR_BONUS}.
     * @return damage @param attacker dealt with @param card against @param
     *         defender.
     */
    default int evaluateAttackWithMultiplierAndDice(Card card, Runa attacker, Player defender, int multiplier,
            int bonus) {
        int dice = attacker.getDiceThrow();
        int initialDamage = multiplier * card.getLevel() + dice;
        initialDamage += (dice >= REQUIREMENT_FOR_BONUS) ? bonus * card.getLevel() : 0;
        return dmgAfterBlocking(initialDamage, defender);
    }

    private int dmgAfterBlocking(int initialDamage, Player defender) {

        int dmgAfterBlocking = initialDamage - defender.getPhysicalDefense();
        if (dmgAfterBlocking <= 0) {
            return 0;
        }
        defender.reduceHP(dmgAfterBlocking);
        return dmgAfterBlocking;

    }
}
