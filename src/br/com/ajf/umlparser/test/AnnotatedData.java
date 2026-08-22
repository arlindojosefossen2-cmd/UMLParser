package br.com.ajf.umlparser.test;

import br.com.ajf.umlparser.utils.UMLParserToolTip;

/**
 * The Interface AnnotatedData.
 */
@UMLParserToolTip(Author = "AJF",
                  Date = "Today: 21/32/43",
                  Version = "1.0",
                  Since = "1.0",
                  Package = "br.com.ajf.umlparser.test",
                  Description = "Simple Annotation class.")
public @interface AnnotatedData
{
    
    /** The default user name. */
    String DEFAULT_USER_NAME = "NONE";
    
    /** The default annotated name. */
    String DEFAULT_ANNOTATED_NAME = "ANNOTATED NONE";
   
    /** The used. */
    boolean USED = true;
    
    /** The not used. */
    boolean NOT_USED = false;
    
    /**
     * Name.
     *
     * @return the string
     */
    String name();
    
    /**
     * User.
     *
     * @return the string
     */
    String user();
    
    /**
     * Used.
     *
     * @return true, if successful
     */
    boolean used();
}
