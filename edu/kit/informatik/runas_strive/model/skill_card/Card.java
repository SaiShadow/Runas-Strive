package edu.kit.informatik.runas_strive.model.skill_card;

import edu.kit.informatik.runas_strive.model.character.Player;

/**
 * Represents a skill card.
 * 
 * @author uogok
 * @version 35
 */
public abstract class Card {

    private static final String OPENING_BRACKET = "(";
    private static final String CLOSING_BRACKET = ")";

    private final int level;
    private final String name;

    // Refers to if the card has a magical ability or a physical ability.
    private final CardAbility ability;

    // Refers to if the card is offensive or defensive.
    private final CardType type;

    /**
     * Constructs a new Skill-card.
     * 
     * @param name    name of card.
     * @param level   level of card.
     * @param ability {@link CardAbility} of card.
     * @param type    {@link CardType} of card.
     */
    public Card(String name, int level, CardAbility ability, CardType type) {
        this.level = level;
        this.name = name;
        this.ability = ability;
        this.type = type;
    }

    /**
     * Gets the value of {@link #level}, which is the cose of magical skill cards
     * for monsters, this is to be overrided in {@link RunasSkillCard}.
     * 
     * @return value of {@link #level}.
     */
    public int getCost() {
        return level;
    }

    /**
     * Refers to if the card has a magical ability or a physical ability.
     * 
     * @return value of {@link #ability}.
     */
    public CardAbility getAbility() {
        return ability;
    }

    // TODO
    /**
     * Refers to if the card offensive or defensive.
     * 
     * @return value of {@link #type}.
     */
    public CardType getType() {
        return type;
    }

    /**
     * Gets the value of {@link #level}.
     * 
     * @return value of {@link #level}.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the value of {@link #name}, which is the name of the card.
     * 
     * @return value of {@link #name}.
     */
    public String getName() {
        return name;
    }

    /**
     * Breaks the focus of the @param defender, {@link PhysicalCard#breaksFocus}.
     * 
     * @param defender breaks focus of this player.
     */
    public void breakFocus(Player defender) {
        return;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        Card otherCard = (Card) other;
        return this.getName().equals(otherCard.getName()) && this.getLevel() == otherCard.getLevel();
    }

    @Override
    public String toString() {
        return getName() + OPENING_BRACKET + getLevel() + CLOSING_BRACKET;
    }

}