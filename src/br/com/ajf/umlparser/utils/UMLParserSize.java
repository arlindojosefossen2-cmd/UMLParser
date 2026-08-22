package br.com.ajf.umlparser.utils;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Window;
import java.util.List;

/**
 * The Class UMLParserSize.
 */
public final class UMLParserSize
{
    
    /**
     * Instantiates a new UML parser size.
     */
    private UMLParserSize()
    {
    
    }
    
    /**
     * Calculate.
     *
     * @param list the list
     * @param font the font
     * @param window the window
     * @return the UML size
     */
    public static UMLSize calculate(List<String> list, Font font, Window window)
    {
        final FontMetrics fm = window.getFontMetrics(font);
        
        int width = 0;
        
        for(String a : list)
        {
            width = Math.max(width,fm.stringWidth(a));
        }
        
        width += (font.getSize()+16);
        
        final int height = ((fm.getAscent()+fm.getDescent()+fm.getLeading())*list.size()+font.getSize()+16);
        
        return new UMLSize(width,height);
    }
    
    /**
     * Calculate.
     *
     * @param list the list
     * @param font the font
     * @param window the window
     * @return the UML size
     */
    public static UMLSize calculate(String[] list, Font font, Window window)
    {
        final FontMetrics fm = window.getFontMetrics(font);
        
        int width = 0;
        
        for(String a : list)
        {
            width = Math.max(width,fm.stringWidth(a));
        }
        
        width += (font.getSize()+16);
        
        final int height = ((fm.getAscent()+fm.getDescent()+fm.getLeading())*list.length+font.getSize()+16);
        
        return new UMLSize(width,height);
    }
}