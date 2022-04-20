package edu.kit.informatik.runas_strive.model.character.runa;

import java.util.List;

import edu.kit.informatik.runas_strive.model.character.Elemental;
import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.skill_card.Card;

/**
 * Represents the main character Runa.
 * 
 * @author uogok
 * @version 51
 */
public class Runa extends Player {

    private static final String RUNAS_NAME = "Runa";
    private static final int MAXIMUM_HEALTH_RUNA = 50;
    private static final int DEFAULT_MAX_FP = 4;
    private final RunasClass runasClass;
    private int maxFP;
    private final int maxHP;
    private int diceThrow;

    /**
     * Constructs a new Runa object.
     * 
     * @param name       Runa's name {@link #RUNAS_NAME}.
     * @param element    the {@link Elemental} of the Runa.
     * @param startingHP the amount of HP Runa starts with
     *                   {@link #MAXIMUM_HEALTH_RUNA}.
     * @param runasClass an element from {@link RunasClass}
     * @param cardsToUse the cards that Runa can use against monsters.
     */
    public Runa(String name, Elemental element, int startingHP, RunasClass runasClass, List<Card> cardsToUse) {
        super(RUNAS_NAME, element, MAXIMUM_HEALTH_RUNA, cardsToUse);
        this.setCurrentFP(1);
        this.maxHP = MAXIMUM_HEALTH_RUNA;
        this.setMaxFP(DEFAULT_MAX_FP);
        this.runasClass = runasClass;
    }

    /**
     * Get Runa's {@link RunasClass}, i.e. {@link #runasClass}.
     * 
     * @return get {@link #runasClass}.
     */
    public RunasClass getRunasClass() {
        return runasClass;
    }

    /**
     * Checks if Runa can heal. Depends on how many cards and HP she has left.
     * 
     * @return true, if Runa can heal, otherwise return false.
     */
    public boolean canHeal() {
        return this.getCurrentHP() < this.maxHP && getCardsToUse().size() > 1;
    }

    /**
     * Gets value of {@link #maxFP}.
     * 
     * @return value of {@link #maxFP}.
     */
    public int getMaxFP() {
        return maxFP;
    }

    /**
     * Sets value of {@link #maxFP}, to the value of @param maxFP.
     * 
     * @param maxFP this value is to be set to {@link #maxFP}.
     */
    public void setMaxFP(int maxFP) {
        this.maxFP = maxFP;
    }

    /**
     * Gets value of {@link #maxHP}.
     * 
     * @return value of {@link #maxHP}.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Gets value of {@link #diceThrow}.
     * 
     * @return value of {@link #diceThrow}.
     */
    public int getDiceThrow() {
        return diceThrow;
    }

    @Override
    public void increaseHP(int ammountToIncrease) {
        int currentHP = getCurrentHP();
        currentHP += ammountToIncrease;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
        this.setCurrentHP(currentHP);
    }

    @Override
    public void reset() {
        this.setMagicalDefense(0);
        this.setPhysicalDefense(0);
        this.setFpToGain(0);
        this.setDiceThrow(0);
    }

    @Override
    public void increaseFP(int ammountToIncrease) {

        int currentFP = getCurrentFP();
        currentFP += ammountToIncrease;
        if (currentFP > maxFP) {
            currentFP = maxFP;
        }
        this.setCurrentFP(currentFP);
    }

    /**
     * Sets value of {@link #diceThrow}, to the value of @param diceThrow.
     * 
     * @param diceThrow this value is to be set to {@link #diceThrow}.
     */
    public void setDiceThrow(int diceThrow) {
        this.diceThrow = diceThrow;
    }

}
