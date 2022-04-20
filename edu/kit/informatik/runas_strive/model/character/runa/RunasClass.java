package edu.kit.informatik.runas_strive.model.character.runa;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.model.skill_card.runa.RunaSkillCard;

/**
 * Represents the class Runa can be, each class has different starter cards.
 * 
 * @author uogok
 * @version 23
 */
public enum RunasClass {

    /**
     * Represents the Warrior class, which contains the starter cards Thrust(n) and
     * Parry(n)
     */
    Warrior {

        @Override
        public List<Card> getAbilityCards(int level) {

            return new ArrayList<>(List.of(RunaSkillCard.THRUST.getCard(level), RunaSkillCard.PARRY.getCard(level)));
        }
    },

    /**
     * Represents the Mage class, which contains the starter cards Focus(n) and
     * Water(n)
     */
    Mage {

        @Override
        public List<Card> getAbilityCards(int level) {
            return new ArrayList<>(List.of(RunaSkillCard.FOCUS.getCard(level), RunaSkillCard.WATER.getCard(level)));
        }
    },

    /**
     * Represents the Paladin class, which contains the starter cards Slash(n) and
     * Reflect(n)
     */
    Paladin {
        @Override
        public List<Card> getAbilityCards(int level) {
            return new ArrayList<>(List.of(RunaSkillCard.SLASH.getCard(level), RunaSkillCard.REFLECT.getCard(level)));
        }
    };

    /**
     * Gets the classes unique starter skill cards, at this level @param level.
     * 
     * @param level the cards to return must be of this level.
     * @return the classes unique starter skill cards at this level @param level.
     */
    public List<Card> getAbilityCards(int level) {
        return new ArrayList<>();
    }

}
