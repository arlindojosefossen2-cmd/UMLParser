package br.com.ajf.umlparser.images;

import br.com.ajf.umlparser.parsers.IUMLParser;

import br.com.ajf.umlparser.utils.MouseInput;
import br.com.ajf.umlparser.utils.UMLParserInfo;

import javax.swing.*;
import java.awt.*;

/**
 * The Class UMLParserButton.
 */
public final class UMLParserButton implements IUMLParserComponent
{
    
    /** The button. */
    private final JButton button;
    
    /** The package name. */
    private final String packageName;
    
    /** The tool tip label. */
    private final JLabel toolTipLabel;
    
    /**
     * Instantiates a new UML parser button.
     *
     * @param window the window
     * @param parser the parser
     * @param className the class name
     */
    public UMLParserButton(Window window, IUMLParser parser, String className)
    {
        final UMLParserImage umlParserImage = new UMLParserImage(parser, className, window);
        packageName = umlParserImage.getPackageName();
        final String toolTipText = UMLParserInfo.tooTip(className);
        button = create(window,new ImageIcon(umlParserImage.getImg()));
        
        button.setBounds(0,0, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());
        
        button.setToolTipText(toolTipText);
        
        final MouseInput mouse = new MouseInput(this);
        button.addMouseListener(mouse);
        button.addMouseMotionListener(mouse);
        
        toolTipLabel = new JLabel(create(toolTipText.split("\n"), window));
        toolTipLabel.setSize(toolTipLabel.getIcon().getIconWidth(),toolTipLabel.getIcon().getIconHeight());
        toolTipLabel.setBounds(0,0, toolTipLabel.getWidth(), toolTipLabel.getHeight());
        toolTipLabel.setLocation(0,0);
  
        toolTipLabel.setVisible(false);
    }
    
    /**
     * Creates the.
     *
     * @param window the window
     * @param imageIcon the image icon
     * @return the j button
     */
    private JButton create(Window window,ImageIcon imageIcon)
    {
        final int w = imageIcon.getIconWidth();
        final int h = imageIcon.getIconHeight();
        
        final Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        
        Image image = window.getGraphicsConfiguration().createCompatibleImage(w,h,Transparency.TRANSLUCENT);
        Graphics2D g = (Graphics2D) image.getGraphics();
        
        final Composite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
        g.setComposite(alpha);
        
        g.drawRoundRect(0,0,w,h,4,4);
        g.drawImage(imageIcon.getImage(),0,0,null);
        g.dispose();
        
        final ImageIcon iconDefault = new ImageIcon(image);
        
        image = window.getGraphicsConfiguration().createCompatibleImage(w,h,Transparency.TRANSLUCENT);
        g = (Graphics2D) image.getGraphics();
        
        g.drawRoundRect(0,0,w,h,4,4);
        g.drawImage(imageIcon.getImage(),2,2,null);
        g.dispose();
        
        final ImageIcon iconPressed = new ImageIcon(image);
        
        final JButton button = new JButton();
        
        button.setFocusable(false);
        button.setBorder(null);
        button.setContentAreaFilled(false);
        button.setCursor(cursor);
        button.setIcon(iconDefault);
        button.setRolloverIcon(imageIcon);
        button.setPressedIcon(iconPressed);
        
        return button;
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
        return button;
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