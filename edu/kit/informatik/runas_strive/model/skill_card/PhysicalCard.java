package edu.kit.informatik.runas_strive.model.skill_card;

import edu.kit.informatik.runas_strive.model.character.Player;

/**
 * Represents a physical card.
 * 
 * @author uogok
 * @version 34
 */
public class PhysicalCard extends Card {

    /**
     * Constructs a physical card with the following properties.
     * 
     * @param name    name of card.
     * @param level   level of card
     * @param ability {@link CardAblity} of card
     * @param type    {CardType CardAblity} of card
     */
    public PhysicalCard(String name, int level, CardAbility ability, CardType type) {
        super(name, level, ability, type);
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public void breakFocus(Player defender) {
        defender.setFpToGain(0);
    }

}
