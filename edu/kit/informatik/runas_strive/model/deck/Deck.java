package edu.kit.informatik.runas_strive.model.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import edu.kit.informatik.runas_strive.model.character.monster.Monster;
import edu.kit.informatik.runas_strive.model.character.monster.MonsterCard;
import edu.kit.informatik.runas_strive.model.character.runa.Runa;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.model.skill_card.runa.RunaSkillCard;

/**
 * Represents a deck which manages all Runa's skill cards and Monster-cards of
 * Runa's Strive.
 * 
 * @author uogok
 * @version 39
 */
public class Deck {

    private static final int PLACEMENT_OF_BOSS = 5;
    private final Monster finalBossLevel1 = MonsterCard.SPIDER_KING.getMonster();
    private final Monster finalBossLevel2 = MonsterCard.MEGA_SAURUS.getMonster();

    private List<Card> runasSkills;
    private List<Monster> monsters;

    /**
     * Constructs a new Deck.
     * 
     * @param seedForRunasCards   the seed to shuffle Runa's skill cards.
     * @param seedForMonsterCards the seed to shuffle monster cards.
     * @param floor               the current floor that Runa is in
     * @param runa                Runa
     */
    public Deck(int seedForRunasCards, int seedForMonsterCards, int floor, Runa runa) {
        this.monsters = setListOfMonsters(seedForMonsterCards, floor);
        this.runasSkills = setListOfRunasSkills(seedForRunasCards, floor, runa);
    }

    /**
     * Adds the list of Runa's class's unique cards at level 2.
     * 
     * @param runa The List of Cards are to be added to this player.
     * @return list of Runa's class's unique cards at level 2.
     */
    public List<Card> addNewClassCardsToRunaEnterLevel2(Runa runa) {
        List<Card> listToAdd = new LinkedList<>(runa.getRunasClass().getAbilityCards(2));
        runa.addSkills(listToAdd);
        return new ArrayList<>(listToAdd);
    }

    private List<Monster> setListOfMonsters(int seedForMonsterCards, int floor) {

        List<Monster> listOfMonster = new LinkedList<>();
        for (MonsterCard monsterCard : MonsterCard.values()) {
            if (monsterCard.getLevelOfGameMonsterCanEnter() == floor) {
                listOfMonster.add(monsterCard.getMonster());
            }
        }
        listOfMonster.remove(this.finalBossLevel1);
        listOfMonster.remove(this.finalBossLevel2);

        Collections.shuffle(listOfMonster, new Random(seedForMonsterCards));
        if (floor == 1) {
            listOfMonster.add(PLACEMENT_OF_BOSS, finalBossLevel1);
        } else if (floor == 2) {
            listOfMonster.add(PLACEMENT_OF_BOSS, finalBossLevel2);
        }
        return listOfMonster;
    }

    /**
     * Get a @param ammount of monsters from the top of the deck of
     * {@link #monsters}, remove them and return them.
     * 
     * @param amount this amount of monsters are to be returned in a list, and
     *               removed from {@link #monsters}.
     * @return list of monsters from {@link #monsters}.
     */
    public List<Monster> getNumberOfMonsters(int amount) {
        List<Monster> listOfMonstersToReturn = new ArrayList<>(monsters.subList(0, amount));
        monsters.removeAll(listOfMonstersToReturn);
        return listOfMonstersToReturn;
    }

    /**
     * Get a @param ammount of Runa's skill cards from the top of the deck of
     * {@link #runasSkills}, remove them and return them.
     * 
     * @param amount his amount of Runa's skill cards are to be returned in a list,
     *               and removed from {@link #runasSkills}.
     * @return list of Runa's skill cards from {@link #runasSkills}.
     */
    public List<Card> getNumberOfRunasSkillCards(int amount) {
        List<Card> listOfCardsToReturn = (runasSkills.size() >= amount)
                ? new ArrayList<Card>(runasSkills.subList(0, amount))
                : new ArrayList<Card>(runasSkills);
        runasSkills.removeAll(listOfCardsToReturn);
        return listOfCardsToReturn;
    }

    private List<Card> setListOfRunasSkills(int seedForRunasCards, int floor, Runa runa) {

        List<Card> listOfRunasCards = new ArrayList<>();
        for (RunaSkillCard skill : RunaSkillCard.values()) {
            if (skill != RunaSkillCard.NONE) {
                listOfRunasCards.add(skill.getCard(floor));
            }
        }
        listOfRunasCards.removeAll(runa.getRunasClass().getAbilityCards(floor));
        Collections.shuffle(listOfRunasCards, new Random(seedForRunasCards));
        return listOfRunasCards;
    }

}
