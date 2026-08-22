package br.com.ajf.umlparser.images;

import br.com.ajf.umlparser.parsers.IUMLParser;
import br.com.ajf.umlparser.utils.MouseCanvasInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class UMLParserImageLabelPackage.
 */
public class UMLParserImageLabelPackage
{
    
    /** The canvas. */
    private final JLabel canvas;
    
    /**
     * Instantiates a new UML parser image label package.
     *
     * @param packageName the package name
     * @param classNames the class names
     * @param parser the parser
     * @param window the window
     */
    public UMLParserImageLabelPackage(String packageName, List<String> classNames, IUMLParser parser, Window window)
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
        
        final List<UMLParserLabel> labels = new LinkedList<>(classNames.stream().map(cn -> new UMLParserLabel(parser, cn, window)).collect(Collectors.toList()));

        int x = 8;
        int y = 32;
        int w = 0;
        int h = 0;
        int maxHeight = 0;
        
        for(UMLParserLabel img : labels)
        {
            img.getComponent().setLocation(x, y);
            
            w = Math.max(w, img.getComponent().getX()+img.getComponent().getWidth());
            h = Math.max(img.getComponent().getY()+img.getComponent().getHeight(), h);
            maxHeight = Math.max(maxHeight, img.getComponent().getHeight());
            x += img.getComponent().getWidth() + 8;
            
            if (x + img.getComponent().getWidth() >=  620)
            {
                x = 8;
                y += maxHeight + 8;
            }
        }
        
        canvas.setSize(w, h);
        
        labels.forEach(l -> 
        {
        	canvas.add(l.getToolTipLabel(),0);  
            canvas.add(l.getComponent(),1);
        });
        
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