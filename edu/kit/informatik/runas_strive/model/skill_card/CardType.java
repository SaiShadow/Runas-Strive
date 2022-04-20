package edu.kit.informatik.runas_strive.model.skill_card;

/**
 * Represents the cards type.
 * 
 * @author uogok
 * @version 2
 */
public enum CardType {

    /**
     * These cards deal damage to enemies.
     */
    OFFENSIVE,

    /**
     * These cards protect you from enemy attacks in the consecutive turn.
     */
    DEFENSIVE,

    /**
     * If it neither of the above types.
     */
    NONE;
}
