package edu.kit.informatik.runas_strive.model.character.monster;

import java.util.ArrayList;
import java.util.List;

import edu.kit.informatik.runas_strive.model.character.Elemental;
import edu.kit.informatik.runas_strive.model.skill_card.Card;
import edu.kit.informatik.runas_strive.model.skill_card.monster.MonsterSkillCard;

/**
 * Represents every monster in this game.
 * 
 * @author uogok
 * @version 3
 */
public enum MonsterCard {

    /**
     * Represents the boss of level 1, with name 'Spider King'.
     */
    SPIDER_KING(1) {
        private static final int MAX_HEALTH = 50;

        @Override
        public Monster getMonster() {

            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.BITE.getCard(1), MonsterSkillCard.BLOCK.getCard(1),
                            MonsterSkillCard.FOCUS.getCard(1), MonsterSkillCard.LIGHTNING.getCard(1)));
            return new Monster(1, "Spider King", Elemental.LIGHTNING, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 1, with name 'Frog'.
     */
    FROG(1) {
        private static final int MAX_HEALTH = 16;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.FOCUS.getCard(1), MonsterSkillCard.WATER.getCard(1)));
            return new Monster(1, "Frog", Elemental.WATER, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 1, with name 'Ghost'.
     */
    GHOST(1) {
        private static final int MAX_HEALTH = 15;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.FOCUS.getCard(1), MonsterSkillCard.ICE.getCard(1)));
            return new Monster(1, "Ghost", Elemental.ICE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 1, with name 'Gorgon'.
     */
    GORGON(1) {
        private static final int MAX_HEALTH = 13;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.FOCUS.getCard(1), MonsterSkillCard.FIRE.getCard(1)));
            return new Monster(1, "Gorgon", Elemental.FIRE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 1, with name 'Skeleton'.
     */
    SKELETON(1) {
        private static final int MAX_HEALTH = 14;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.FOCUS.getCard(1), MonsterSkillCard.LIGHTNING.getCard(1)));
            return new Monster(1, "Skeleton", Elemental.LIGHTNING, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 1, with name 'Spider'.
     */
    SPIDER(1) {
        private static final int MAX_HEALTH = 15;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.BITE.getCard(1), MonsterSkillCard.BLOCK.getCard(1)));
            return new Monster(1, "Spider", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 1, with name 'Goblin'.
     */
    GOBLIN(1) {
        private static final int MAX_HEALTH = 12;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.SMASH.getCard(1), MonsterSkillCard.DEFLECT.getCard(1)));
            return new Monster(1, "Goblin", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 1, with name 'Rat'.
     */
    RAT(1) {
        private static final int MAX_HEALTH = 14;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.BLOCK.getCard(1), MonsterSkillCard.CLAW.getCard(1)));
            return new Monster(1, "Rat", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 1, with name 'Mushroomlin'.
     */
    MUSHROOMLIN(1) {
        private static final int MAX_HEALTH = 20;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.DEFLECT.getCard(1), MonsterSkillCard.SCRATCH.getCard(1)));
            return new Monster(1, "Mushroomlin", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents the boss of level 2, with name 'Mega Saurus'.
     */
    MEGA_SAURUS(2) {
        private static final int MAX_HEALTH = 100;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(List.of(MonsterSkillCard.BITE.getCard(2),
                    MonsterSkillCard.BLOCK.getCard(2), MonsterSkillCard.FOCUS.getCard(2),
                    MonsterSkillCard.FIRE.getCard(1), MonsterSkillCard.LIGHTNING.getCard(1)));
            return new Monster(2, "Mega Saurus", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 2, with name 'Snake'.
     */
    SNAKE(2) {
        private static final int MAX_HEALTH = 31;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(List.of(MonsterSkillCard.BITE.getCard(2),
                    MonsterSkillCard.FOCUS.getCard(2), MonsterSkillCard.ICE.getCard(2)));
            return new Monster(2, "Snake", Elemental.ICE, MAX_HEALTH, listOfCards);
        }
    },
    /**
     * Represents a monster from level 2, with name 'Dark Elf'.
     */
    DARK_ELF(2) {
        private static final int MAX_HEALTH = 34;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(List.of(MonsterSkillCard.FOCUS.getCard(2),
                    MonsterSkillCard.WATER.getCard(1), MonsterSkillCard.LIGHTNING.getCard(1)));
            return new Monster(2, "Dark Elf", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 2, with name 'Shadow Blade'.
     */
    SHADOW_BLADE(2) {
        private static final int MAX_HEALTH = 27;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(List.of(MonsterSkillCard.SCRATCH.getCard(2),
                    MonsterSkillCard.FOCUS.getCard(2), MonsterSkillCard.LIGHTNING.getCard(2)));
            return new Monster(2, "Shadow Blade", Elemental.LIGHTNING, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 2, with name 'Hornet'.
     */
    HORNET(2) {
        private static final int MAX_HEALTH = 32;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(
                    List.of(MonsterSkillCard.SCRATCH.getCard(2), MonsterSkillCard.FOCUS.getCard(2),
                            MonsterSkillCard.FIRE.getCard(1), MonsterSkillCard.FIRE.getCard(2)));
            return new Monster(2, "Hornet", Elemental.FIRE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 2, with name 'Tarantula'.
     */
    TARANTULA(2) {
        private static final int MAX_HEALTH = 33;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(List.of(MonsterSkillCard.BITE.getCard(2),
                    MonsterSkillCard.BLOCK.getCard(2), MonsterSkillCard.SCRATCH.getCard(2)));
            return new Monster(2, "Tarantula", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 2, with name 'Bear'.
     */
    BEAR(2) {
        private static final int MAX_HEALTH = 40;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(List.of(MonsterSkillCard.CLAW.getCard(2),
                    MonsterSkillCard.DEFLECT.getCard(2), MonsterSkillCard.BLOCK.getCard(2)));
            return new Monster(2, "Bear", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },
    /**
     * Represents a monster from level 2, with name 'Mushroomlon'.
     */
    MUSHROOMLON(2) {
        private static final int MAX_HEALTH = 50;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(List.of(MonsterSkillCard.DEFLECT.getCard(2),
                    MonsterSkillCard.SCRATCH.getCard(2), MonsterSkillCard.BLOCK.getCard(2)));
            return new Monster(2, "Mushroomlon", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    },

    /**
     * Represents a monster from level 2, with name 'Wild Boar'.
     */
    WILD_BOAR(2) {
        private static final int MAX_HEALTH = 27;

        @Override
        public Monster getMonster() {
            List<Card> listOfCards = new ArrayList<>(List.of(MonsterSkillCard.SCRATCH.getCard(2),
                    MonsterSkillCard.DEFLECT.getCard(2), MonsterSkillCard.SCRATCH.getCard(2)));
            return new Monster(2, "Wild Boar", Elemental.NONE, MAX_HEALTH, listOfCards);
        }
    };

    private final int level;

    private MonsterCard(int level) {
        this.level = level;
    }

    /**
     * Gets the level that this monster can be found in {@link #level}.
     * 
     * @return the level that this monster can be found in {@link #level}.
     */
    public int getLevelOfGameMonsterCanEnter() {
        return level;
    }

    /**
     * Gets the monster, with certain attributes.
     * 
     * @return the monster, with certain attributes.
     */
    public Monster getMonster() {
        return null;
    }
}
