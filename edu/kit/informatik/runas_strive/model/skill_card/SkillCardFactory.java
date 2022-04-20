package edu.kit.informatik.runas_strive.model.skill_card;

/**
 * This interface helps construct new cards, which both monsters and Runa share.
 * 
 * @author uogok
 * @version 23
 */
public interface SkillCardFactory {

    /**
     * Is an empty string.
     */
    String DEFAULT_STRING = "";

    /**
     * Get a Water card with level @param level.
     * 
     * @param level Get a Water card with this level.
     * @return Get a Water card with @param level.
     */
    default Card getWater(int level) {
        return getMagicalOffensive("Water", level);
    }

    /**
     * Get a Ice card with level @param level.
     * 
     * @param level Get a Ice card with this level.
     * @return Get a Ice card with @param level.
     */
    default Card getIce(int level) {
        return getMagicalOffensive("Ice", level);
    }

    /**
     * Get a Fire card with level @param level.
     * 
     * @param level Get a Fire card with this level.
     * @return Get a Fire card with @param level.
     */
    default Card getFire(int level) {
        return getMagicalOffensive("Fire", level);
    }

    /**
     * Get a Lightning card with level @param level.
     * 
     * @param level Get a Lightning card with this level.
     * @return Get a Lightning card with @param level.
     */
    default Card getLightning(int level) {
        return getMagicalOffensive("Lightning", level);
    }

    /**
     * Get a Focus card with level @param level.
     * 
     * @param level Get a Focus card with this level.
     * @return Get a Focus card with @param level.
     */
    default Card getFocus(int level) {
        return new MagicalCard("Focus", level, CardAbility.MAGICAL, CardType.NONE);
    }

    private Card getMagicalOffensive(String name, int level) {
        return new MagicalCard(name, level, CardAbility.MAGICAL, CardType.OFFENSIVE);
    }

    /**
     * Get a default card with level @param level, to avoid null.
     * 
     * @param level Get a Focus card with this level.
     * @return Get a Focus card with @param level
     */
    default Card getDefaultCard(int level) {
        return new PhysicalCard(DEFAULT_STRING, level, CardAbility.PHYSICAL, CardType.NONE);
    }

    /**
     * Get a physical offensive card.
     * 
     * @param name  name of the card.
     * @param level level of the card.
     * @return a physical offensive card with the specific attributes listed
     *         in @param.
     */
    default Card getPhysicalOffensive(String name, int level) {
        return new PhysicalCard(name, level, CardAbility.PHYSICAL, CardType.OFFENSIVE);
    }
}
