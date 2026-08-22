package br.com.ajf.umlparser.utils;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The Interface UMLParserToolTip.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface UMLParserToolTip
{
    
    /**
     * Author.
     *
     * @return the string
     */
    String Author();
    
    /**
     * Date.
     *
     * @return the string
     */
    String Date();
    
    /**
     * Version.
     *
     * @return the string
     */
    String Version();
    
    /**
     * Since.
     *
     * @return the string
     */
    String Since();
    
    /**
     * Package.
     *
     * @return the string
     */
    String Package();
    
    /**
     * Description.
     *
     * @return the string
     */
    String Description();
}