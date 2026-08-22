package br.com.ajf.umlparser.images;

import br.com.ajf.umlparser.parsers.IUMLParser;
import br.com.ajf.umlparser.utils.DrawString;
import br.com.ajf.umlparser.utils.UMLParserSize;
import br.com.ajf.umlparser.utils.UMLSize;

import java.awt.*;
import java.awt.image.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class UMLParserImage.
 */
public class UMLParserImage
{
    /** The Constant FONT_ARIAL_14. */
    public static final Font FONT_ARIAL_14 = new Font("Arial",Font.PLAIN,16);
    
    /** The img. */
    private final BufferedImage img;
    
    /** The package name. */
    private final String packageName;
    
    /**
     * Instantiates a new UML parser image.
     *
     * @param parser the parser
     * @param className the class name
     * @param window the window
     */
    public UMLParserImage(IUMLParser parser, String className, Window window)
    {	
        parser.parse(className,true,
                true,true,
                true,true);
        
        packageName = parser.getPackage();
        
        final List<String> fields = parser.fields();
        final List<String> constructors = parser.constructors();
        final List<String> methods = parser.methods();
        
        final String name = parser.getName();
        
        final UMLSize size = getUmlSize(window, name, fields, constructors, methods);
        final int w = size.width();
        final int h = size.height();
        
        img = new BufferedImage(w,h, BufferedImage.TYPE_INT_ARGB);
        
        final Graphics2D g = img.createGraphics();
        g.setFont(FONT_ARIAL_14);
        
        g.setColor(Color.white);
        g.fillRoundRect(0, 0, w, h,2,2);
        
        g.setColor(Color.black);
        g.drawRoundRect(1, 1, w-4, h-4,2,2);
        
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        final int x = 8;
        int y = DrawString.drawCenteredString(g, w , 2 ,name);
        g.drawLine(1, y+4, w-4, y+4);
        y = DrawString.drawString(g, x, y+6, fields);
        g.drawLine(1, y+4, w-4, y+4);
        y = DrawString.drawString(g, x, y+6, constructors);
        g.drawLine(1, y+4, w-4, y+4);
        DrawString.drawString(g, x, y+6, methods);
        
        g.dispose();
    }
    
    /**
     * Gets the uml size.
     *
     * @param window the window
     * @param name the name
     * @param fields the fields
     * @param constructors the constructors
     * @param methods the methods
     * @return the uml size
     */
    private static UMLSize getUmlSize(Window window, String name, List<String> fields,
                                      List<String> constructors, List<String> methods)
    {
        final List<String> pc = new LinkedList<>();
        pc.add(name);
        pc.addAll(fields);
        pc.addAll(constructors);
        pc.addAll(methods);
        
        return UMLParserSize.calculate(pc,FONT_ARIAL_14, window);
    }
    
    /**
     * Gets the img.
     *
     * @return the img
     */
    public BufferedImage getImg()
    {
        return img;
    }
    
    /**
     * Gets the package name.
     *
     * @return the package name
     */
    public String getPackageName()
    {
        return packageName;
    }
}