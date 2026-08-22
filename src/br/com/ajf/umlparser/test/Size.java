package br.com.ajf.umlparser.test;

import br.com.ajf.umlparser.utils.UMLParserToolTip;

/**
 * The Record Size.
 *
 * @param width the width
 * @param height the height
 */
@UMLParserToolTip(Author = "AJF",
                  Date = "Today: 21/32/43",
                  Version = "1.0",
                  Since = "1.0",
                  Package = "br.com.ajf.umlparser.test",
                  Description = "Record Size class")
public record Size(int width, int height)
{

}