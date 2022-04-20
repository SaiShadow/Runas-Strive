package edu.kit.informatik.runas_strive.model.skill_card;

/**
 * Represents a Magical skill card.
 * 
 * @author uogok
 * @version 12
 */
public class MagicalCard extends Card {

    /**
     * Constructs a magical card with the following properties.
     * 
     * @param name    name of card.
     * @param level   level of card
     * @param ability {@link CardAblity} of card
     * @param type    {CardType CardAblity} of card
     */
    public MagicalCard(String name, int level, CardAbility ability, CardType type) {
        super(name, level, ability, type);
    }

}
