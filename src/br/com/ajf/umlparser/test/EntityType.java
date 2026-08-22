package br.com.ajf.umlparser.test;

import br.com.ajf.umlparser.utils.UMLParserToolTip;

/**
 * The Enum EntityType.
 */
@UMLParserToolTip(Author = "AJF",
                  Date = "Today: 21/32/43",
                  Version = "1.0",
                  Since = "1.0",
                  Package = "br.com.ajf.umlparser.test",
                  Description = "Simple Enum class.")
public enum EntityType
{
    
    /** The player. */
    PLAYER,
    
    /** The entity none. */
    ENTITY_NONE,
    
    /** The old man. */
    OLD_MAN,
    
    /** The old woman. */
    OLD_WOMAN,
    
    /** The boy. */
    BOY,
    
    /** The girl. */
    GIRL,
    
    /** The enemy. */
    ENEMY;
    
    /** The checked. */
    private String checked;
    
    /**
     * Gets the checker.
     *
     * @param checked the checked
     * @return the checker
     */
    public EntityType getChecker(String checked)
    {
        return switch (checked)
        {
            case "P" -> PLAYER;
            case "EN" -> ENTITY_NONE;
            case "M" -> OLD_MAN;
            case "W" -> OLD_WOMAN;
            case "B" -> BOY;
            case "G" -> GIRL;
            case "E" -> ENEMY;
            default -> null;
        };
    }
    
    /**
     * Instantiates a new entity type.
     */
    EntityType()
    {
        this("EN");
    }
    
    /**
     * Instantiates a new entity type.
     *
     * @param checked the checked
     */
    EntityType(String checked)
    {
        this.checked = checked;
    }
    
    /**
     * Gets the checked.
     *
     * @return the checked
     */
    public String getChecked()
    {
        return checked;
    }
    
    /**
     * Sets the checked.
     *
     * @param checked the new checked
     */
    public void setChecked(String checked)
    {
        this.checked = checked;
    }
}