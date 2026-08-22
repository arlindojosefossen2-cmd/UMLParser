package br.com.ajf.umlparser.images;

import br.com.ajf.umlparser.parsers.IUMLParser;
import br.com.ajf.umlparser.utils.MouseInput;
import br.com.ajf.umlparser.utils.UMLParserInfo;

import javax.swing.*;
import java.awt.*;

/**
 * The Class UMLParserLabel.
 */
public final class UMLParserLabel implements IUMLParserComponent
{
    
    /** The label. */
    private final JLabel label;
    
    /** The package name. */
    private final String packageName;
    
    /** The tool tip label. */
    private final JLabel toolTipLabel;
    
    /**
     * Instantiates a new UML parser label.
     *
     * @param parser the parser
     * @param className the class name
     * @param window the window
     */
    public UMLParserLabel(IUMLParser parser, String className, Window window)
    {
        final UMLParserImage umlParserImage = new UMLParserImage(parser, className, window);
        packageName = umlParserImage.getPackageName();
        final String toolTipText = UMLParserInfo.tooTip(className);
        label = new JLabel(new ImageIcon(umlParserImage.getImg()));
        label.setBounds(0,0,label.getIcon().getIconWidth(),label.getIcon().getIconHeight());
        
        label.setToolTipText(toolTipText);
        
        final MouseInput mouse = new MouseInput(this);
        label.addMouseListener(mouse);
        label.addMouseMotionListener(mouse);
        
        toolTipLabel = new JLabel(create(toolTipText.split("\n"), window));
        toolTipLabel.setSize(toolTipLabel.getIcon().getIconWidth(),toolTipLabel.getIcon().getIconHeight());
        toolTipLabel.setBounds(0,0, toolTipLabel.getWidth(), toolTipLabel.getHeight());
        toolTipLabel.setLocation(0,0);
       
        toolTipLabel.setVisible(false);
    }
    
    /**
     * Creates the.
     *
     * @param array the array
     * @param window the window
     * @return the image icon
     */
    public ImageIcon create(String[] array,Window window)
    {
        return new ImageIcon(UMLParserInfo.createToolTipImage(array,UMLParserImage.FONT_ARIAL_14,window));
    }
    
    /**
     * Gets the component.
     *
     * @return the component
     */
    @Override
	public JComponent getComponent()
    {
        return label;
    }
    
    /**
     * Gets the package name.
     *
     * @return the package name
     */
    @Override
	public String getPackageName()
    {
        return packageName;
    }
    
    /**
     * Gets the tool tip label.
     *
     * @return the tool tip label
     */
    @Override
	public JLabel getToolTipLabel()
    {
        return toolTipLabel;
    }
}