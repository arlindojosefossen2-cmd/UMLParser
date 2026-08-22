package br.com.ajf.umlparser.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The Class MouseCanvasInput.
 */
public class MouseCanvasInput extends MouseAdapter
{
    
    /** The point. */
    public final Point point = new Point(0,0);
    
    /** The component. */
    public final JComponent component;
    
    /**
     * Instantiates a new mouse canvas input.
     *
     * @param component the component
     */
    public MouseCanvasInput(JComponent component)
    {
        this.component = component;
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
    }
    
    /**
     * Mouse dragged.
     *
     * @param e the e
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        final int nx = component.getX() + e.getX() - point.x;
        final int ny = component.getY() + e.getY() - point.y;
        component.setLocation(nx,ny);
    }
}