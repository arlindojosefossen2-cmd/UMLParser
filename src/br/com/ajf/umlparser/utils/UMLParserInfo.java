package br.com.ajf.umlparser.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/**
 * The Class UMLParserInfo.
 */
public final class UMLParserInfo
{
    /** The Constant ICON. */
    public static final ImageIcon ICON = new ImageIcon(UMLParserInfo.class.getResource("/icon.png"));
    
    /**
     * Instantiates a new UML parser info.
     */
    private UMLParserInfo()
    {
    
    }
    
    /**
     * Show.
     *
     * @param title the title
     * @param message the message
     */
    public static void show(String title,String message)
    {
        JOptionPane.showMessageDialog(null,message,title,
                JOptionPane.INFORMATION_MESSAGE,ICON);
    }
    
    /**
     * Too tip.
     *
     * @param className the class name
     * @return the string
     */
    public static String tooTip(String className)
    {
        final Class<?> cls;
        
        try
        {
            cls = Class.forName(className);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        
        final StringBuilder sb = new StringBuilder();
        
        if(cls.isAnnotationPresent(UMLParserToolTip.class))
        {
            final UMLParserToolTip toolTip = cls.getAnnotation(UMLParserToolTip.class);
            
            sb.append("Author: ").append(toolTip.Author()).append(" \n")
                    .append("Date: ").append(toolTip.Date()).append(" \n")
                    .append("Version: ").append(toolTip.Version())
                    .append(" ; Since: ").append(toolTip.Since()).append(" \n")
                    .append("Package: ").append(toolTip.Package()).append(" \n")
                    .append("Description: ").append(toolTip.Description()).append(" \n");
        }
        else
        {
            sb.append("THIS UML NOT HAVE A TOOLTIP.");
        }
        
        return sb.toString();
    }
    
    /**
     * Creates the tool tip image.
     *
     * @param toolTipSplit the tool tip split
     * @param font the font
     * @param window the window
     * @return the buffered image
     */
    public static BufferedImage createToolTipImage(String[] toolTipSplit, Font font,Window window)
    {
    	font = font.deriveFont(12);
        final UMLSize size = UMLParserSize.calculate(toolTipSplit,font,window);
        final int w = size.width();
        final int h = size.height();
        
        final BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = img.createGraphics();
        g.setFont(font);
        
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(0,0,w,h,5,5);
   
        g.setColor(Color.WHITE);
        g.drawRoundRect(4,2,w-10,h-4,5,5);
        
        g.setColor(Color.RED);
        DrawString.drawString(g,10,10,toolTipSplit);
        
        g.dispose();
        
        return img;
    }
}