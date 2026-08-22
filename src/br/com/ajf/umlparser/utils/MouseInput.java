package br.com.ajf.umlparser.utils;

import br.com.ajf.umlparser.images.IUMLParserComponent;

import java.awt.*;
import java.awt.event.*;

/**
 * The Class MouseInput.
 */
public class MouseInput extends MouseAdapter
{
    
    /** The point. */
    public final Point point = new Point(0,0);
    
    /** The component. */
    public final IUMLParserComponent component;
    
    /**
     * Instantiates a new mouse input.
     *
     * @param component the component
     */
    public MouseInput(IUMLParserComponent component)
    {
        this.component = component;
    }
    
    /**
     * Mouse released.
     *
     * @param e the e
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        component.getToolTipLabel().setVisible(false);
    }
    
    /**
     * Mouse pressed.
     *
     * @param e the e
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        point.x = e.getX();
        point.y = e.getY();
       
        if(!component.getToolTipLabel().isVisible())
        {
            component.getToolTipLabel().setLocation(component.getComponent().getX(), component.getComponent().getY());
            component.getToolTipLabel().setBounds(component.getComponent().getX(),
                    component.getComponent().getY(),
                    component.getToolTipLabel().getWidth(),
                    component.getToolTipLabel().getHeight());
        }
        
        component.getToolTipLabel().setVisible(true);
    }
    
    /**
     * Mouse dragged.
     *
     * @param e the e
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        final int nx = component.getComponent().getX() + e.getX() - point.x;
        final int ny = component.getComponent().getY() + e.getY() - point.y;
        component.getComponent().setLocation(nx,ny);
        
        if(!component.getToolTipLabel().isVisible())
        {
            return;
        }
        
        component.getToolTipLabel().setLocation(component.getComponent().getX(),
                component.getComponent().getY());
    }
}