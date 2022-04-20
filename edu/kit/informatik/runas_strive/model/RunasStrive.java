package edu.kit.informatik.runas_strive.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.kit.informatik.runas_strive.model.character.Elemental;
import edu.kit.informatik.runas_strive.model.character.Player;
import edu.kit.informatik.runas_strive.model.character.monster.Monster;
import edu.kit.informatik.runas_strive.model.character.runa.Runa;
import edu.kit.informatik.runas_strive.model.character.runa.RunasClass;
import edu.kit.informatik.runas_strive.model.deck.Deck;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.model.skill_card.monster.MonsterSkillCard;
import edu.kit.informatik.runas_strive.model.skill_card.runa.RunaSkillCard;

/**
 * Represents the main Runa's Strive game-manager. It holds all the necessary
 * information to run all the
 * {@link edu.kit.informatik.runas_strive.ui.gamestate.GameState#execute()}
 * commands.
 * 
 * @author uogok
 * @version 34
 */
public class RunasStrive {

    private static final String RUNAS_NAME = "Runa";
    private static final int MAXIMUM_HEALTH_RUNA = 50;
    private static final int MINIMUM_NUMBER_OF_SIDES_ON_DICE = 4;
    private static final int MAXIMUM_NUMBER_OF_SIDES_ON_DICE = 12;
    private static final int NUMBER_OF_SIDES_TO_ADD_TO_DICE = 2;
    private static final int MAXIMUM_NUMBER_OF_ROOMS = 4;
    private static final int MAXIMUM_NUMBER_OF_LEVELS = 2;
    private static final int HEALTH_MULTIPLIER = 10;
    private static final Map<Integer, Integer> MAP_NUMBER_OF_MONSTERS_IN_ROOM = Map.of(1, 1, 2, 2, 3, 2, 4, 1);

    private final Runa runa;
    private Deck deck;
    private List<Monster> monsters;
    private int sidesOfDie;
    private int level;
    private int room;

    /**
     * Constructs a new RunasStrive game manager. @param runasClass is the only
     * parameter required as the other needed arguments have been declared at in the
     * assignment {@link RunasStrive#MAXIMUM_HEALTH_RUNA}, {@link #RUNAS_NAME}.
     * 
     * @param runasClass is Runa's class, {@link RunasClass}. This is the only
     *                   parameter needed to start a new game.
     */
    public RunasStrive(RunasClass runasClass) {
        this.runa = new Runa(RUNAS_NAME, Elemental.NONE, MAXIMUM_HEALTH_RUNA, runasClass,
                runasClass.getAbilityCards(1));
        this.sidesOfDie = MINIMUM_NUMBER_OF_SIDES_ON_DICE;
        this.level = 1;
        this.room = 1;
    }

    /**
     * Gets the number of cards Runa can pick in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.NewCardOrNewDice}
     * game-state. This depends on the @param numberOfSkillsOfferedToRuna.
     * 
     * @param numberOfSkillsOfferedToRuna is the number of skills that Runa gets to
     *                                    pick from, depending on this the number of
     *                                    cards that Runa can collect varies.
     * @return the number of cards that Runa can collect varies
     */
    public int getHowManyCardsRunaCanGet(int numberOfSkillsOfferedToRuna) {
        int numberOfLootYouCanCollect = numberOfSkillsOfferedToRuna / 2;
        numberOfLootYouCanCollect += numberOfSkillsOfferedToRuna % 2 == 0 ? 0 : 1;
        return numberOfLootYouCanCollect;
    }

    /**
     * Upgrades the dice of {@link #sidesOfDied}, i.e. increases the number of sides
     * by {@link #NUMBER_OF_SIDES_TO_ADD_TO_DICE}. The maximum number of sides the
     * die can have is {@link #MAXIMUM_NUMBER_OF_SIDES_ON_DICE}. Also sets Runas
     * max. FP to the number of sides after upgrading {@link Runa#setMaxFP(int)}
     */
    public void upgradeDice() {
        this.sidesOfDie += (hasMaxDice()) ? 0 : NUMBER_OF_SIDES_TO_ADD_TO_DICE;
        runa.setMaxFP(sidesOfDie);
    }

    /**
     * Checks if the dice already has max. number of sides.
     * 
     * @return true, if sides of dice equals =
     *         {@link #MAXIMUM_NUMBER_OF_SIDES_ON_DICE}, otherwise false.
     */
    public boolean hasMaxDice() {
        return sidesOfDie == MAXIMUM_NUMBER_OF_SIDES_ON_DICE;
    }

    /**
     * Checks if Runa can heal, look into {@link Runa#canHeal()}. Is used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.Healing#execute()}.
     * 
     * @return value of {@link Runa#canHeal()}, that is true, if Runa can heal.
     */
    public boolean canRunaHeal() {
        return this.runa.canHeal();
    }

    /**
     * Get the number of monsters Runa has to fight in the current room
     * {@link #room}, this depends on the mappings of
     * {@link #MAP_NUMBER_OF_MONSTERS_IN_ROOM}.
     * 
     * @return the number of monsters Runa has to fight in the current room
     *         {@link #room}.
     */
    public int getNumberOfMonstersInRoom() {
        return MAP_NUMBER_OF_MONSTERS_IN_ROOM.get(room);
    }

    /**
     * Adds focus points to @param player, by adding the amount of
     * {@link Player#getFpToGain()} to {@link Player#getCurrentFP()}. Returns the
     * number of focus points that could be added to @param player.
     * 
     * @param player this player's focus points are to be increased, if possible,
     *               i.e. if the player hasn't reached their max. FP.
     * @return the number of focus points that could be added to @param player.
     */
    public int addFocusPoints(Player player) {
        int fpBeforeAdding = player.getCurrentFP();
        player.increaseFP(player.getFpToGain());
        int fpAfterAdding = player.getCurrentFP();
        return fpAfterAdding - fpBeforeAdding;
    }

    /**
     * Resets the a few attributes of the monsters, check out {@link Player#reset()}
     * to get more details.
     */
    public void resetMonsters() {
        for (Monster monsterIterator : monsters) {
            resetPlayer(monsterIterator);
        }
    }

    /**
     * Gets a List of cards, that Runa can gain as loot in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.NewCardOrNewDice}, she
     * can choose a number {@link #getHowManyCardsRunaCanGet(int)} of the cards in
     * this list. Look into {@link Deck#getNumberOfRunasSkillCards(int)} for more
     * details.
     * 
     * @return a List of cards, that Runa can gain as loot.
     */
    public List<Card> getRunasCardReward() {
        return this.deck.getNumberOfRunasSkillCards(2 * getNumberOfMonstersInRoom());
    }

    /**
     * Resets Runa, look into {@link Player#reset()} to get more details.
     */
    public void resetRuna() {
        resetPlayer(this.runa);
    }

    /**
     * Get all the cards that Runa has right now.
     * 
     * @return List of all cards that Runa can use, at this point of the game.
     */
    public List<Card> getRunasCards() {
        return this.runa.getCardsToUse();
    }

    private void resetPlayer(Player player) {
        player.reset();
    }

    private void removeMonstersIfDead() {
        List<Monster> listOfMonstersToRemove = new ArrayList<>();
        for (Monster monster : monsters) {
            if (monster.isDead()) {
                listOfMonstersToRemove.add(monster);
            }
        }
        this.monsters.removeAll(listOfMonstersToRemove);
    }

    /**
     * Evaluates the @param card used by Runa, against @param defender. Evaluate
     * means calculating the damage done to the defender and if necessary increasing
     * Runas stats. An optional @param diceThrow is entered for a few cards used by
     * Runa, this is to be set to the value to Runa {@link Runa#setDiceThrow(int)}.
     * 
     * @param card      is used against @param defender.
     * @param defender  Runa attacks them using @param card.
     * @param diceThrow An optional @param diceThrow is entered for a few cards used
     *                  by Runa, this is to be set to the value to Runa
     *                  {@link Runa#setDiceThrow(int)}.
     * @return the amount of damage dealt to @param defender, or the amount of
     *         defence gained by Runa.
     */
    public int evaluateRunasCards(Card card, Player defender, int diceThrow) {
        this.runa.setDiceThrow(diceThrow);
        RunaSkillCard skillCard = getRunaSkillCard(card);
        int dmgDealt = skillCard.evaluate(card, runa, defender);
        removeMonstersIfDead();
        return dmgDealt;
    }

    /**
     * Evaluates the @param card used by @param attacker, against Runa. Evaluate
     * means calculating the damage done to the defender/Runa and if necessary
     * increasing @param attacker's stats.
     * 
     * @param card     is used against Runa by @param attacker.
     * @param attacker attacks Runa using @param card.
     * @return the amount of damage dealt to Runa, or the amount of defence gained
     *         by @param attacker .
     */
    public int evaluateMonstersCards(Card card, Monster attacker) {
        MonsterSkillCard skillCard = getMonsterSkillCard(card);
        int dmgDealt = skillCard.evaluate(card, attacker, this.runa);
        removeMonstersIfDead();
        return dmgDealt;
    }

    private RunaSkillCard getRunaSkillCard(Card card) {
        String nameOfCardToSearchForInCaps = card.getName().toUpperCase();
        for (RunaSkillCard runaSkill : RunaSkillCard.values()) {
            if (nameOfCardToSearchForInCaps.equals(runaSkill.toString())) {
                return runaSkill;
            }
        }
        return RunaSkillCard.NONE;
    }

    /**
     * Get the monsters Runa is fighting in this current room and level.
     * 
     * @return Get the monsters Runa is fighting in this current room and level.
     */
    public List<Monster> getMonsters() {
        return new ArrayList<>(monsters);
    }

    /**
     * Sets the value of @param monsters to {@link #monsters}. Which means that
     * the @param monsters are the new monsters that Runa has to fight in the
     * current/next room.
     * 
     * @param monsters is to be set to {@link #monsters}.
     */
    public void setMonsters(List<Monster> monsters) {
        this.monsters = new ArrayList<>(monsters);
    }

    /**
     * Gets the sides of the dice {@link #sidesOfDie}.
     * 
     * @return sides of the dice, i.e. value of {@link #sidesOfDie}.
     */
    public int getSidesOfDie() {
        return sidesOfDie;
    }

    /**
     * 
     * Sets the sides of the dice {@link #sidesOfDie} to the value of @param
     * sidesOfDie.
     * 
     * @param sidesOfDie this value is to be set to {@link #sidesOfDie}.
     */
    public void setSidesOfDie(int sidesOfDie) {
        this.sidesOfDie = sidesOfDie;
    }

    /**
     * Get the level of the game.
     * 
     * @return the level of the game.
     */
    public int getLevel() {
        return level;
    }

    /**
     * it is on private because this mustn't be accessed from other classes, to
     * increase the value of {@link #level} then you have to execute the method
     * {@link #nextRoom()}.
     */
    private void nextLevel() {
        this.level++;
    }

    /**
     * Adds {@link HEALTH_MULTIPLIER} * @param multiplier amount of health to Runa
     * and returns the amount of health Runa gained. Used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.Healing#execute()}.
     * 
     * @param multiplier * {@link HEALTH_MULTIPLIER} is the amount of health Runa
     *                   can heal/gain.
     * @return the amount of health Runa gained.
     */
    public int addHealthToRuna(int multiplier) {
        int runasHPBeforeAdding = this.runa.getCurrentHP();
        this.runa.increaseHP(HEALTH_MULTIPLIER * multiplier);
        return this.runa.getCurrentHP() - runasHPBeforeAdding;
    }

    /**
     * Removes the skills in @param skillsToRemove from
     * {@link Runa#getCardsToUse()}. Used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.Healing#execute()}.
     * 
     * @param skillsToRemove the skill-cards contained in this list are to be
     *                       removed from {@link Runa#getCardsToUse()}.
     */
    public void removeSkillsFromRuna(List<Card> skillsToRemove) {
        this.runa.removeSkills(skillsToRemove);
    }

    /**
     * Adds the class-skill-cards with but now with level 2 to Runa, used in
     * {@link edu.kit.informatik.runas_strive.ui.gamestate.UpgradeRunasSkills#execute()}.
     * 
     * @return The list of cards, that Runa gained.
     */
    public List<Card> upgradeRunasSkills() {
        return new ArrayList<>(this.deck.addNewClassCardsToRunaEnterLevel2(this.runa));
    }

    /**
     * Gets the room that the Runa is currently in.
     * 
     * @return the room that the Runa is currently in.
     */
    public int getRoom() {
        return room;
    }

    /**
     * Increases the count of room by 1, if Runa is already at
     * {@link #MAXIMUM_NUMBER_OF_ROOMS}, then set the room to 1, and
     * {@link #nextLevel()}. It also automatically sets {@link #monsters} to the new
     * monsters that appear while entering this new room, if Runa is not at
     * {@link #MAXIMUM_NUMBER_OF_ROOMS}.
     */
    public void nextRoom() {
        this.room++;
        if (room > MAXIMUM_NUMBER_OF_ROOMS) {
            this.room = 1;
            nextLevel();
        } else {
            this.monsters = this.deck.getNumberOfMonsters(MAP_NUMBER_OF_MONSTERS_IN_ROOM.get(room));
        }
    }

    /**
     * Gets a reference to the {@link #runa} object.
     * 
     * @return a reference to the {@link #runa} object.
     */
    public Runa getRuna() {
        return runa;
    }

    /**
     * Sets @param deck to {@link #deck} and also automatically gets the monsters
     * that Runa has to face in this room and sets it to {@link #monsters}.
     * 
     * @param deck
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
        this.monsters = this.deck.getNumberOfMonsters(1);
    }

    /**
     * Return if the game ended i.e. if Runa died or won.
     * 
     * @return true, if game ended.
     */
    public boolean didGameEnd() {
        return runa.isDead()
                || (room == MAXIMUM_NUMBER_OF_ROOMS && level == MAXIMUM_NUMBER_OF_LEVELS && monsters.isEmpty());
    }

    /**
     * Returns if Runa won the game.
     * 
     * @return true, if Runa won the game, otherwise false.
     */
    public boolean didRunaWin() {
        return !runa.isDead() && didGameEnd();
    }

    /**
     * Returns if Runa defeated every monster in this room.
     * 
     * @return true, if Runa defeated every monster in this room, otherwise false.
     */
    private boolean clearedRoom() {

        return this.monsters.isEmpty();
    }

    /**
     * Returns true, if Runa died or Runa defeated everyone in this room. Indicates
     * if Runa can stop fighting.
     * 
     * @return true, if Runa died or Runa defeated everyone in this room.
     */
    public boolean allowedToExitFightingPhase() {
        return clearedRoom() || runa.isDead();
    }

    /**
     * Get the first card that @param monster can use against Runa.
     * 
     * @param monster search a card that this monster can use, right now against
     *                Runa.
     * @return a card that this monster can use, right now against Runa.
     */
    public Card getCardThatMonsterCanUse(Monster monster) {
        Card cardToUse = MonsterSkillCard.NONE.getCard(1);
        do {
            cardToUse = monster.getCurrentCardAndIncreasePointer();
        } while (!getMonsterSkillCard(cardToUse).canEvaluateCard(cardToUse, monster));
        return cardToUse;
    }

    private MonsterSkillCard getMonsterSkillCard(Card card) {
        String nameOfCardToSearchForInCaps = card.getName().toUpperCase();
        for (MonsterSkillCard monsterSkill : MonsterSkillCard.values()) {
            if (nameOfCardToSearchForInCaps.equals(monsterSkill.toString())) {
                return monsterSkill;
            }
        }
        return MonsterSkillCard.NONE;
    }

    /**
     * Add all the cards in this list to Runa.
     * 
     * @param skillsToAdd the cards in this list is to be added to the cards that
     *                    Runa can use {@link Runa#getCardsToUse()}.
     */
    public void addSkillsToRuna(List<Card> skillsToAdd) {
        this.runa.addSkills(skillsToAdd);
    }

}
