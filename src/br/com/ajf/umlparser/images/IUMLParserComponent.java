package br.com.ajf.umlparser.images;

import javax.swing.*;

/**
 * The Interface IUMLParserComponent.
 */
public interface IUMLParserComponent
{
    
    /**
     * Gets the component.
     *
     * @return the component
     */
    JComponent getComponent();
    
    /**
     * Gets the package name.
     *
     * @return the package name
     */
    String getPackageName();
    
    /**
     * Gets the tool tip label.
     *
     * @return the tool tip label
     */
    JLabel getToolTipLabel();
    
}