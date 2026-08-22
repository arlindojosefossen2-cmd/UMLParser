package br.com.ajf.umlparser.utils;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;

/**
 * The Class DrawString.
 */
public class DrawString
{
    /**
     * Can't instantiate a new draw string.
     */
    private DrawString()
    {
    
    }
    
    /**
     * Draw centered string.
     *
     * @param g the g
     * @param w the w
     * @param y the y
     * @param text the text
     * @return the int
     */
    public static int drawCenteredString(Graphics2D g,int w,int y,String text)
    {
        return drawCenteredString(g, w, y, new String[] {text});
    }
    
    /**
     * Draw centered string.
     *
     * @param g the g
     * @param w the w
     * @param y the y
     * @param texts the texts
     * @return the int
     */
    public static int drawCenteredString(Graphics2D g, int w, int y, String... texts)
    {
        final FontMetrics fm = g.getFontMetrics();
        final int height = fm.getAscent() + fm.getDescent() + fm.getLeading();
        
        for (String text : texts)
        {
            final Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);
            final int x = (int)(w - bounds.getWidth())/2;
            g.drawString(text, x, y+fm.getAscent());
            y += height;
        }
        
        return y;
    }
    
    /**
     * Draw string.
     *
     * @param g the g
     * @param x the x
     * @param y the y
     * @param texts the texts
     * @return the int
     */
    public static int drawString(Graphics2D g, int x, int y, List<String> texts)
    {
        return drawString(g, x, y, texts.toArray(new String[0]));
    }
    
    /**
     * Draw string.
     *
     * @param g the g
     * @param x the x
     * @param y the y
     * @param texts the texts
     * @return the int
     */
    public static int drawString(Graphics2D g, int x, int y, String... texts)
    {
        final FontMetrics fm = g.getFontMetrics();
        final int height = fm.getAscent() + fm.getDescent() + fm.getLeading();
        
        for (String text : texts)
        {
            g.drawString(text, x, y+fm.getAscent());
            y += height;
        }
        
        return y;
    }
}