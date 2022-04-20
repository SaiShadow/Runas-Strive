package edu.kit.informatik.runas_strive.model.character;

/**
 * This class represents the Element every {@link Player} can have/has.
 * 
 * @author uogok
 * @version 4
 */
public enum Elemental {

    /**
     * Is one of the elements that the
     * {@link edu.kit.informatik.runas_strive.model.character.monster.Monster} can
     * inhibit.
     */
    WATER,

    /**
     * Is one of the elements that the
     * {@link edu.kit.informatik.runas_strive.model.character.monster.Monster} can
     * inhibit.
     */
    FIRE,

    /**
     * Is one of the elements that the
     * {@link edu.kit.informatik.runas_strive.model.character.monster.Monster} can
     * inhibit.
     */
    ICE,

    /**
     * Is one of the elements that the
     * {@link edu.kit.informatik.runas_strive.model.character.monster.Monster} can
     * inhibit.
     */
    LIGHTNING,

    /**
     * Is one of the elements that the
     * {@link edu.kit.informatik.runas_strive.model.character.monster.Monster} and
     * {@link edu.kit.informatik.runas_strive.model.character.runa.Runa} can
     * inhibit. This is also the default {@link Elemental} {@link Player} gets if
     * they can't obtains the other {@link Elemental}.
     */
    NONE;

}
