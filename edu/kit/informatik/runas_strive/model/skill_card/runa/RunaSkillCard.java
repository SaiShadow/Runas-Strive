package edu.kit.informatik.runas_strive.model.skill_card.runa;

import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.character.runa.Runa;
import edu.kit.informatik.runas_strive.model.evaluator.MagicalEvaluator;
import edu.kit.informatik.runas_strive.model.evaluator.PhysicalEvaluator;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.model.skill_card.CardAbility;
import edu.kit.informatik.runas_strive.model.skill_card.CardType;
import edu.kit.informatik.runas_strive.model.skill_card.MagicalCard;
import edu.kit.informatik.runas_strive.model.skill_card.PhysicalCard;
import edu.kit.informatik.runas_strive.model.skill_card.SkillCardFactory;

/**
 * This enum represents all the skill cards that Runa can use that exist in
 * Runa's Strive.
 * 
 * @author uogok
 * @version 34
 */
public enum RunaSkillCard implements PhysicalEvaluator, MagicalEvaluator, SkillCardFactory {

    /**
     * Represents Runa's skill card with name 'Slash'.
     */
    SLASH {
        private static final int MULTIPLIER = 4;
        private static final int BONUS = 0;

        @Override
        public Card getCard(int level) {
            return getPhysicalOffensive("Slash", level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            card.breakFocus(defender);
            return evaluateAttackWithMultiplierAndDice(card, (Runa) attacker, defender, MULTIPLIER, BONUS);
        }

    },

    /**
     * Represents Runa's skill card with name 'Swing'.
     */
    SWING {
        private static final int MULTIPLIER = 5;
        private static final int BONUS = 0;

        @Override
        public Card getCard(int level) {
            return getPhysicalOffensive("Swing", level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            card.breakFocus(defender);
            return evaluateAttackWithMultiplierAndDice(card, (Runa) attacker, defender, MULTIPLIER, BONUS);
        }

    },

    /**
     * Represents Runa's skill card with name 'Thrust'.
     */
    THRUST {
        private static final int MULTIPLIER = 6;
        private static final int BONUS = 4;

        @Override
        public Card getCard(int level) {
            return getPhysicalOffensive("Thrust", level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateAttackWithMultiplierAndDice(card, (Runa) attacker, defender, MULTIPLIER, BONUS);
        }

    },

    /**
     * Represents Runa's skill card with name 'Pierce'.
     */
    PIERCE {
        private static final int MULTIPLIER = 7;
        private static final int BONUS = 5;

        @Override
        public Card getCard(int level) {
            return getPhysicalOffensive("Pierce", level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateAttackWithMultiplierAndDice(card, (Runa) attacker, defender, MULTIPLIER, BONUS);
        }

    },

    /**
     * Represents Runa's skill card with name 'Parry'.
     */
    PARRY {
        private static final int MULTIPLIER = 7;

        @Override
        public Card getCard(int level) {
            return new PhysicalCard("Parry", level, CardAbility.PHYSICAL, CardType.DEFENSIVE);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateDefenseWithMultiplier(attacker, MULTIPLIER, card);
        }

    },

    // Magical

    /**
     * Represents Runa's skill card with name 'Focus'.
     */
    FOCUS {
        @Override
        public Card getCard(int level) {
            return getFocus(level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateFocus(attacker, card);
        }

    },

    /**
     * Represents Runa's skill card with name 'Reflect'.
     */
    REFLECT {

        private static final int MULTIPLIER = 10;
        private static final int ADDER = 0;

        @Override
        public Card getCard(int level) {
            return new MagicalCard("Reflect", level, CardAbility.MAGICAL, CardType.DEFENSIVE);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateReflect(attacker, card, MULTIPLIER, ADDER);
        }

    },

    /**
     * Represents Runa's skill card with name 'Water'.
     */
    WATER {
        private static final int ADDER_IN_BRACKET = 4;
        private static final int OPTIONAL_ADDER = 0;

        @Override
        public Card getCard(int level) {
            return getWater(level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateElementalsRuna((Runa) attacker, defender, card, ADDER_IN_BRACKET, OPTIONAL_ADDER);
        }
    },

    /**
     * Represents Runa's skill card with name 'Ice'.
     */
    ICE {
        private static final int ADDER_IN_BRACKET = 4;
        private static final int OPTIONAL_ADDER = 2;

        @Override
        public Card getCard(int level) {
            return getIce(level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateElementalsRuna((Runa) attacker, defender, card, ADDER_IN_BRACKET, OPTIONAL_ADDER);
        }
    },

    /**
     * Represents Runa's skill card with name 'Fire'.
     */
    FIRE {
        private static final int ADDER_IN_BRACKET = 5;
        private static final int OPTIONAL_ADDER = 0;

        @Override
        public Card getCard(int level) {
            return getFire(level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateElementalsRuna((Runa) attacker, defender, card, ADDER_IN_BRACKET, OPTIONAL_ADDER);
        }
    },

    /**
     * Represents Runa's skill card with name 'Lightning'.
     */
    LIGHTNING {
        private static final int ADDER_IN_BRACKET = 5;
        private static final int OPTIONAL_ADDER = 2;

        @Override
        public Card getCard(int level) {
            return getLightning(level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateElementalsRuna((Runa) attacker, defender, card, ADDER_IN_BRACKET, OPTIONAL_ADDER);
        }
    },

    /**
     * Represents default Runa's skill card to avoid null.
     */
    NONE;

    @Override
    public int evaluate(Card card, Player attacker, Player defender) {
        return 0;
    }

    /**
     * Gets the card with @param level and with properties of the chosen enum
     * element.
     * 
     * @param level
     * @return the card with @param level and with properties of the chosen enum
     *         element.
     */
    public Card getCard(int level) {
        return getDefaultCard(level);
    }

}
