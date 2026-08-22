package br.com.ajf.umlparser.test;

import br.com.ajf.umlparser.utils.UMLParserToolTip;

/**
 * The Interface InterFaceTest.
 */
@UMLParserToolTip(Author = "AJF",
                  Date = "Today: 21/32/43",
                  Version = "1.0",
                  Since = "1.0",
                  Package = "br.com.ajf.umlparser.test",
                  Description = "Simple Interface class")
public interface InterFaceTest
{
    
    /** The zero. */
    int zero = 0;
    
    /** The value. */
    String value = "";
    
    /**
     * Gets the.
     *
     * @return the string
     */
    String get();
    
    /**
     * Make.
     */
    void make();
    
    /**
     * Make.
     *
     * @param done the done
     */
    default void make(String done)
    {
    
    }
    
    /**
     * Other.
     */
    private static void other()
    {
    
    }
    
    /**
     * Other 1.
     */
    static void other1()
    {
        other();
    }
}