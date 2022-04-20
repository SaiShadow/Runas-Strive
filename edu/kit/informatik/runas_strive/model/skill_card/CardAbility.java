package edu.kit.informatik.runas_strive.model.skill_card;

/**
 * Represents a skill cards ability. Every card is either physical or magical.
 * 
 * @author uogok
 * @version 2
 */
public enum CardAbility {

    /**
     * Is one of the 2 card abilities, these cards don't have any cost and might
     * break enemies focus.
     */
    PHYSICAL,

    /**
     * Is the other card ability, some these sill cards have costs.
     */
    MAGICAL
}
