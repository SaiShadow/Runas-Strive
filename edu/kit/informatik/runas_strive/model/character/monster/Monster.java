package edu.kit.informatik.runas_strive.model.character.monster;

import java.util.List;

import edu.kit.informatik.runas_strive.model.character.Elemental;
import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.skill_card.Card;

/**
 * This class represents a Monster. It also implements a method to get the next
 * card the monster should attempt.
 * 
 * @author uogok
 * @version 45
 */
public class Monster extends Player {

    private final int levelOfGameToEnter;
    /**
     * Points at the next card to attempt in {@link #getCardsToUse()}.
     */
    private int indexOfNextCard;

    /**
     * Constructs a monster
     * 
     * @param levelOfGameTheyEnter the level of Runa's Stride this monster can be
     *                             found in.
     * @param name                 the name of this monster.
     * @param element              the {@link Elemental} of the monster.
     * @param startingHP           the starting HP
     * @param cardsToUse           the list of cards that this monster can use.
     */
    protected Monster(int levelOfGameTheyEnter, String name, Elemental element, int startingHP, List<Card> cardsToUse) {
        super(name, element, startingHP, cardsToUse);
        this.levelOfGameToEnter = levelOfGameTheyEnter;
        this.indexOfNextCard = 0;
    }

    /**
     * Gets the card the Monster should attempt to use and also increase the pointer
     * to the next card of the list to indicate that the next card to attempt is the
     * next card.
     * 
     * @return {@link #getCardToAttempt()} the card the Monster should attempt.
     */
    public Card getCurrentCardAndIncreasePointer() {
        Card nextCardInList = getCardToAttempt();
        indexOfNextCard++;
        return nextCardInList;
    }

    /**
     * Gets the card that the monster attempts to use.
     * 
     * @return the card that the monster attempts to use.
     */
    public Card getCardToAttempt() {
        return getCardsToUse().get(getPositionOfCardToAttempt());
    }

    private int getPositionOfCardToAttempt() {
        return indexOfNextCard % getCardsToUse().size();
    }

    /**
     * Gets the level of Runa's Stride this monster can be found in.
     * 
     * @return value of {@link #levelOfGameToEnter}.
     */
    public int getLevelOfGameToEnter() {
        return levelOfGameToEnter;
    }

    @Override
    public void addSkills(List<Card> newSkills) {
        return;
    }

    @Override
    public void removeSkills(List<Card> skillsToRemove) {
        return;
    }
}
