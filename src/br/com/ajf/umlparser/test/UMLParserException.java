package br.com.ajf.umlparser.test;

import br.com.ajf.umlparser.utils.UMLParserToolTip;

/**
 * The Class UMLParserException.
 */
@UMLParserToolTip(Author = "AJF",
                  Date = "Today: 21/32/43",
                  Version = "1.0",
                  Since = "1.0",
                  Package = "br.com.ajf.umlparser.test",
                  Description = "UMLParserException class.\nCreated for test.")
public class UMLParserException extends RuntimeException
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new UML parser exception.
	 *
	 * @param message the message
	 */
	public UMLParserException(String message)
    {
        super(message);
    }
}