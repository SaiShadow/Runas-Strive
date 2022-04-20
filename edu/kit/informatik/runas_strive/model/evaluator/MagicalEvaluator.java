package edu.kit.informatik.runas_strive.model.evaluator;

import java.util.Map;

import edu.kit.informatik.runas_strive.model.character.Elemental;
import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.character.runa.Runa;
import edu.kit.informatik.runas_strive.model.skill_card.Card;

/**
 * Evaluates all the magical skill cards, from both monsters and Runa.
 * 
 * @author uogok
 * @version 35
 */
public interface MagicalEvaluator extends Evaluator {
    /**
     * is the adder used in
     * {@link #evaluateElementalsMonster(Player, Player, Card, int)}
     */
    int ADDER = 2;

    /**
     * Multiplier used in
     * {@link #evaluateElementalsRuna(Runa, Player, Card, int, int)}
     */
    int MULTIPLIER_RUNAS_MAGICAL_SKILL = 2;

    /**
     * Is the Elemental advantage Map used in
     * {@link #checkForCriticalAttack(Card, Player)}.
     */
    Map<Elemental, Elemental> ELEMENT_ADVANTAGE_MAP = Map.of(Elemental.WATER, Elemental.LIGHTNING, Elemental.ICE,
            Elemental.WATER, Elemental.FIRE, Elemental.ICE, Elemental.LIGHTNING, Elemental.FIRE);

    /**
     * Evaluates focus.
     * 
     * @param attacker uses focus
     * @param card     is focus card of some level
     * @return 0, as no damage has been dealt to enemy.
     */
    default int evaluateFocus(Player attacker, Card card) {
        int level = card.getLevel();
        attacker.setFpToGain(level);
        return 0;
    }

    /**
     * Checks if @param attacker has enough FP to use @param card.
     * 
     * @param card     check if @param attacker can use this.
     * @param attacker check if @param card can be used by this player.
     * @return true, if @param attacker can use @param card, else false.
     */
    default boolean canEvaluateMagicalOffensiveCards(Card card, Player attacker) {
        return attacker.getCurrentFP() >= card.getLevel();
    }

    /**
     * Evaluates reflect and deflect.
     * 
     * @param attacker   @param attacker uses @param card.
     * @param card       @param attacker uses this.
     * @param multiplier is used in defence calculation.
     * @param adder      additional defence in damage calculation.
     * @return 0, no damage has been done.
     */
    default int evaluateReflect(Player attacker, Card card, int multiplier, int adder) {
        int intensity = multiplier * card.getLevel() + adder;
        attacker.setMagicalDefense(intensity);
        return 0;
    }

    /**
     * Evaluates elemental attacks used by Runa against monsters.
     * 
     * @param attacker       Runa
     * @param defender       monster
     * @param card           Runa uses this against @param defender
     * @param adderInBracket used in damage calculation.
     * @param optionalAdder  used in damage calculation.
     * @return amount of damage the @param attacker dealt on @param defender.
     */
    default int evaluateElementalsRuna(Runa attacker, Player defender, Card card, int adderInBracket,
            int optionalAdder) {

        int initialDamage = (MULTIPLIER_RUNAS_MAGICAL_SKILL * card.getLevel() + adderInBracket)
                * attacker.getCurrentFP() + optionalAdder;
        attacker.reduceFP((attacker.getCurrentFP() == 1) ? 0 : 1);

        // critical hits
        if (checkForCriticalAttack(card, defender)) {
            initialDamage += MULTIPLIER_RUNAS_MAGICAL_SKILL * card.getLevel();
        }
        return damageAfterDeflecting(initialDamage, defender);
    }

    private boolean checkForCriticalAttack(Card card, Player defender) {
        String cardNameInCaps = card.getName().toUpperCase();
        for (Elemental element : ELEMENT_ADVANTAGE_MAP.keySet()) {
            if (cardNameInCaps.equals(element.toString())) {
                return (ELEMENT_ADVANTAGE_MAP.get(element)).equals(defender.getElement());
            }
        }
        return false;
    }

    /**
     * Evaluates elemental attacks used by monsters against runa.
     * 
     * @param attacker   monster
     * @param defender   Runa
     * @param card       @param attacker uses this against @param defender
     * @param multiplier used in damage calculation.
     * @return amount of damage the @param attacker dealt on @param defender.
     */
    default int evaluateElementalsMonster(Player attacker, Player defender, Card card, int multiplier) {
        int cost = card.getLevel();
        if (!canEvaluateMagicalOffensiveCards(card, attacker)) {
            return 0;
        }
        attacker.setCurrentFP(attacker.getCurrentFP() - cost);

        int magicalDamage = multiplier * cost + ADDER;

        return damageAfterReflecting(magicalDamage, attacker, defender);
    }

    private int damageAfterDeflecting(int magicalDamage, Player defender) {

        int dmgAfterDeflecting = magicalDamage - defender.getMagicalDefense();
        if (dmgAfterDeflecting <= 0) {
            return 0;
        }
        defender.reduceHP(dmgAfterDeflecting);
        return dmgAfterDeflecting;
    }

    private int damageAfterReflecting(int magicalDamage, Player attacker, Player defender) {

        int defense = defender.getMagicalDefense();

        if (magicalDamage <= defense) {
            attacker.reduceHP(magicalDamage);
            return 0;
        } else {
            int magicalDmgDealtToDefender = magicalDamage - defense;
            attacker.reduceHP(defense);
            defender.reduceHP(magicalDmgDealtToDefender);
            return magicalDmgDealtToDefender;
        }

    }
}
