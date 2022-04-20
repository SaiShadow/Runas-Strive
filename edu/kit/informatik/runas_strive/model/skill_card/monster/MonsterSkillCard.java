package edu.kit.informatik.runas_strive.model.skill_card.monster;

import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.evaluator.MagicalEvaluator;
import edu.kit.informatik.runas_strive.model.evaluator.PhysicalEvaluator;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.model.skill_card.CardAbility;
import edu.kit.informatik.runas_strive.model.skill_card.CardType;
import edu.kit.informatik.runas_strive.model.skill_card.MagicalCard;
import edu.kit.informatik.runas_strive.model.skill_card.PhysicalCard;
import edu.kit.informatik.runas_strive.model.skill_card.SkillCardFactory;

/**
 * This enum represents all the monster skill cards that exist in Runa's Strive.
 * 
 * @author uogok
 * @version 45
 */
public enum MonsterSkillCard implements PhysicalEvaluator, MagicalEvaluator, SkillCardFactory {

    /**
     * Represents the monster card with name 'Scratch'.
     */
    SCRATCH {
        private static final int MULTIPLIER = 5;

        @Override
        public Card getCard(int level) {
            return getPhysicalOffensive("Scratch", level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            card.breakFocus(defender);
            return evaluateAttackWithMultiplier(attacker, defender, MULTIPLIER, card);
        }
    },

    /**
     * Represents the monster card with name 'Claw'.
     */
    CLAW {

        private static final int MULTIPLIER = 6;

        @Override
        public Card getCard(int level) {
            return getPhysicalOffensive("Claw", level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            card.breakFocus(defender);
            return evaluateAttackWithMultiplier(attacker, defender, MULTIPLIER, card);
        }
    },

    /**
     * Represents the monster card with name 'Smash'.
     */
    SMASH {

        private static final int MULTIPLIER = 8;

        @Override
        public Card getCard(int level) {
            return getPhysicalOffensive("Smash", level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateAttackWithMultiplier(attacker, defender, MULTIPLIER, card);
        }
    },

    /**
     * Represents the monster card with name 'Bite'.
     */
    BITE {

        private static final int MULTIPLIER = 10;

        @Override
        public Card getCard(int level) {
            return getPhysicalOffensive("Bite", level);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateAttackWithMultiplier(attacker, defender, MULTIPLIER, card);
        }
    },

    /**
     * Represents the monster card with name 'Block'.
     */
    BLOCK {

        private static final int MULTIPLIER = 7;

        @Override
        public Card getCard(int level) {
            return new PhysicalCard("Block", level, CardAbility.PHYSICAL, CardType.DEFENSIVE);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateDefenseWithMultiplier(attacker, MULTIPLIER, card);
        }
    },
    // Magical
    /**
     * Represents the monster card with name 'Focus'.
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
     * Represents the monster card with name 'Deflect'.
     */
    DEFLECT {

        private static final int MULTIPLIER = 11;
        private static final int ADDER = 2;

        @Override
        public Card getCard(int level) {
            return new MagicalCard("Deflect", level, CardAbility.MAGICAL, CardType.DEFENSIVE);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateReflect(attacker, card, MULTIPLIER, ADDER);
        }
    },

    /**
     * Represents the monster card with name 'Water'.
     */
    WATER {
        private static final int MULTIPLIER = 8;

        @Override
        public Card getCard(int level) {
            return getWater(level);
        }

        @Override
        public boolean canEvaluateCard(Card card, Player attacker) {
            return canEvaluateMagicalOffensiveCards(card, attacker);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateElementalsMonster(attacker, defender, card, MULTIPLIER);

        }
    },

    /**
     * Represents the monster card with name 'Ice'.
     */
    ICE {
        private static final int MULTIPLIER = 10;

        @Override
        public Card getCard(int level) {
            return getIce(level);
        }

        @Override
        public boolean canEvaluateCard(Card card, Player attacker) {
            return canEvaluateMagicalOffensiveCards(card, attacker);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateElementalsMonster(attacker, defender, card, MULTIPLIER);

        }
    },

    /**
     * Represents the monster card with name 'Fire'.
     */
    FIRE {
        private static final int MULTIPLIER = 12;

        @Override
        public Card getCard(int level) {
            return getFire(level);
        }

        @Override
        public boolean canEvaluateCard(Card card, Player attacker) {
            return canEvaluateMagicalOffensiveCards(card, attacker);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateElementalsMonster(attacker, defender, card, MULTIPLIER);

        }
    },

    /**
     * Represents the monster card with name 'Lightning'.
     */
    LIGHTNING {
        private static final int MULTIPLIER = 14;

        @Override
        public Card getCard(int level) {
            return getLightning(level);
        }

        @Override
        public boolean canEvaluateCard(Card card, Player attacker) {
            return canEvaluateMagicalOffensiveCards(card, attacker);
        }

        @Override
        public int evaluate(Card card, Player attacker, Player defender) {
            return evaluateElementalsMonster(attacker, defender, card, MULTIPLIER);
        }
    },
    /**
     * Represents the default monster card to avoid null.
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

    /**
     * Checks if @param attacker can evaluate @param card.
     * 
     * @param card     checks if this can be used by @param attacker.
     * @param attacker checks if they can use @param card.
     * @return true, if @param attacker can evaluate @param card.
     */
    public boolean canEvaluateCard(Card card, Player attacker) {
        return true;
    }
}
