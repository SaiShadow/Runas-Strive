package edu.kit.informatik.runas_strive.model.character;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.runas_strive.model.skill_card.Card;

/**
 * This class represents a Player, which is extended by
 * {@link edu.kit.informatik.runas_strive.model.character.monster.Monster} and
 * {@link edu.kit.informatik.runas_strive.model.character.runa.Runa}.
 * 
 * @author uogok
 * @version 25
 */
public abstract class Player {

    private int currentHP;
    private int currentFP;
    private final List<Card> cardsToUse;
    private final String name;
    private final Elemental element;
    private int physicalDefense;
    private int magicalDefense;
    private int fpToGain;

    /**
     * Constructs a new player. All the parameters needed are set to final
     * attributes, except @param startingHP.
     * 
     * @param name       is identity of every player.
     * @param element    an element from {@link Elemental}
     * @param startingHP is the HP that a Player starts with.
     * @param cardsToUse are the list of cards that a player can use.
     */
    protected Player(String name, Elemental element, int startingHP, List<Card> cardsToUse) {
        this.name = name;
        this.element = element;
        this.currentHP = startingHP;
        this.cardsToUse = new ArrayList<>(cardsToUse);
    }

    /**
     * Sets these values to 0. As they need to be updated every turn of the fight.
     */
    public void reset() {
        this.setMagicalDefense(0);
        this.setPhysicalDefense(0);
        this.setFpToGain(0);
    }

    /**
     * Gets the value of {@link Player#element}.
     * 
     * @return the value of {@link Player#element}.
     */
    public Elemental getElement() {
        return element;
    }

    /**
     * Gets the value of {@link #name}.
     * 
     * @return the value of {@link #name}.
     */
    public String getName() {
        return name;
    }

    /**
     * Reduces {@link #currentHP} by the amount specified in @param ammountToReduce.
     * 
     * @param ammountToReduce this value is to be reduced from {@link #currentHP}.
     */
    public void reduceHP(int ammountToReduce) {
        this.currentHP -= ammountToReduce;
    }

    /**
     * Reduces {@link #currentFP} by the amount specified in @param ammountToReduce.
     * 
     * @param ammountToReduce this value is to be reduced from {@link #currentFP}.
     */
    public void reduceFP(int ammountToReduce) {

        this.currentFP -= ammountToReduce;
    }

    /**
     * Increases {@link #currentHP} by the amount specified in @param
     * ammountToIncrease.
     * 
     * @param ammountToIncrease this value is to be reduced from {@link #currentHP}.
     */
    public void increaseHP(int ammountToIncrease) {
        this.currentHP += ammountToIncrease;
    }

    /**
     * Increases {@link #currentFP} by the amount specified in @param
     * ammountToIncrease.
     * 
     * @param ammountToIncrease this value is to be reduced from {@link #currentFP}.
     */
    public void increaseFP(int ammountToIncrease) {

        this.currentFP += ammountToIncrease;
    }

    /**
     * Gets the value of {@link #currentHP}.
     * 
     * @return the value of {@link #currentHP}.
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Checks if the player is dead, i.e. if {@link #currentHP} is lower than or
     * equal to 0.
     * 
     * @return true, if the player is dead, otherwise false.
     */
    public boolean isDead() {
        return (this.currentHP <= 0);
    }

    /**
     * Sets the value of {@link #currentHP} to the value of @param currentHP.
     * 
     * @param currentHP this value is to be set to {@link #currentHP}.
     */
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    /**
     * Gets the value of {@link #currentFP}.
     * 
     * @return the value of {@link #currentFP}.
     */
    public int getCurrentFP() {
        return currentFP;
    }

    /**
     * Sets the value of {@link #currentFP} to the value of @param currentFP.
     * 
     * @param currentFP this value is to be set to {@link #currentFP}.
     */
    public void setCurrentFP(int currentFP) {
        this.currentFP = currentFP;
    }

    /**
     * Gets the value of {@link #physicalDefense}.
     * 
     * @return the value of {@link #physicalDefense}.
     */
    public int getPhysicalDefense() {
        return physicalDefense;
    }

    /**
     * Add the Cards in @param newSkills into {@link #cardsToUse}.
     * 
     * @param newSkills the cards in this List are to be added to
     *                  {@link #cardsToUse}.
     */
    public void addSkills(List<Card> newSkills) {
        this.cardsToUse.addAll(new ArrayList<>(newSkills));
    }

    /**
     * Remove the Cards in @param newSkills from {@link #cardsToUse}.
     * 
     * @param skillsToRemove the cards in this List are to be removed from
     *                       {@link #cardsToUse}.
     */
    public void removeSkills(List<Card> skillsToRemove) {
        this.cardsToUse.removeAll(new ArrayList<>(skillsToRemove));
    }

    /**
     * Sets the value of {@link #physicalDefense} to the value of @param
     * physicalDefense.
     * 
     * @param physicalDefense this value is to be set to {@link #physicalDefense}.
     */
    public void setPhysicalDefense(int physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    /**
     * Gets the value of {@link #magicalDefense}.
     * 
     * @return the value of {@link #magicalDefense}.
     */
    public int getMagicalDefense() {
        return magicalDefense;
    }

    /**
     * Sets the value of {@link #magicalDefense} to the value of @param
     * magicalDefense.
     * 
     * @param magicalDefense this value is to be set to {@link #magicalDefense}.
     */
    public void setMagicalDefense(int magicalDefense) {
        this.magicalDefense = magicalDefense;
    }

    /**
     * Gets the value of {@link #fpToGain}.
     * 
     * @return the value of {@link #fpToGain}.
     */
    public int getFpToGain() {
        return fpToGain;
    }

    /**
     * Sets the value of {@link #fpToGain} to the value of @param fpToGain.
     * 
     * @param fpToGain this value is to be set to {@link #fpToGain}.
     */
    public void setFpToGain(int fpToGain) {
        this.fpToGain = fpToGain;
    }

    /**
     * Gets the value/list of all cards in {@link #cardsToUse}.
     * 
     * @return the value/list of all cards in {@link #cardsToUse}.
     */
    public List<Card> getCardsToUse() {
        return new ArrayList<>(cardsToUse);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        return this.getName().equals(((Player) other).getName());
    }

}
