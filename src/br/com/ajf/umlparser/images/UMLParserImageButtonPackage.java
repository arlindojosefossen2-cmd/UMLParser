package br.com.ajf.umlparser.images;

import br.com.ajf.umlparser.parsers.IUMLParser;
import br.com.ajf.umlparser.utils.MouseCanvasInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class UMLParserImageButtonPackage.
 */
public class UMLParserImageButtonPackage
{
    
    /** The canvas. */
    private final JLabel canvas;
    
    /**
     * Instantiates a new UML parser image button package.
     *
     * @param packageName the package name
     * @param classNames the class names
     * @param parser the parser
     * @param window the window
     */
    public UMLParserImageButtonPackage(String packageName, List<String> classNames, IUMLParser parser, Window window)
    {
        this.canvas = new JLabel();
        this.canvas.setLayout(null);
        this.canvas.setBackground(Color.LIGHT_GRAY);
        this.canvas.setForeground(Color.GRAY);
        this.canvas.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4)));
        
        final MouseAdapter adapter = new MouseCanvasInput(canvas);
        this.canvas.addMouseMotionListener(adapter);
        this.canvas.addMouseListener(adapter);
        
        final JLabel jLabel = new JLabel(packageName);
        jLabel.setForeground(Color.WHITE);
        jLabel.setBackground(Color.DARK_GRAY);
        jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        jLabel.setBounds(0, 0, jLabel.getText().length()*8, 20);
        canvas.add(jLabel);
        jLabel.setLocation(0, 0);
        
        final List<UMLParserButton> labels = new LinkedList<>();
        int maxHeight = 0;
        int maxWidth = 0;
        
        for (String cn : classNames)
        {
            final UMLParserButton img = new UMLParserButton(window,parser,cn);
            maxHeight = Math.max(maxHeight, img.getComponent().getHeight());
            maxWidth += img.getComponent().getWidth();
            
            labels.add(img);
        }
        
        this.canvas.setSize(maxWidth+96, maxHeight+96);
       
        int x = 8;
        int y = 32;
        
        for(UMLParserButton img : labels)
        {
            canvas.add(img.getComponent());
            canvas.add(img.getToolTipLabel(),0);
            img.getComponent().setLocation(x, y);
            
            x += img.getComponent().getWidth() + 8;
            
            if (x >= canvas.getWidth())
            {
                x = 8;
                y += maxHeight + 16;
            }
        }
        
        this.canvas.repaint();
    }
    
    /**
     * Gets the canvas.
     *
     * @return the canvas
     */
    public JLabel getCanvas()
    {
        return canvas;
    }
}