package br.com.ajf.umlparser.files;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.com.ajf.umlparser.images.UMLParserImage;

/**
 * The Class UMLParserSaveJButton.
 *
 * @author Arlindo Jose Fossem . Date: Aug 21, 2026.
 * @version 1.0v.
 * @since 1.5v.
 */
public final class UMLParserSaveJButton
{
	
	/**
	 * Instantiates a new UML parser save J button.
	 */
	public UMLParserSaveJButton()
	{
		
	}
	
	/**
	 * Save J button.
	 *
	 * @param file the file
	 * @param canvas the canvas
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveJButton(File file,JLabel canvas) throws IOException
	{
		final JButton jc = (JButton) canvas.getComponent(1);
		
		final BufferedImage img = new BufferedImage(jc.getIcon().getIconWidth(),
													jc.getIcon().getIconHeight(), 
													BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g = img.createGraphics();
		
		g.drawImage(((ImageIcon)jc.getPressedIcon()).getImage(), 0, 0, null);
		g.dispose();
		
		ImageIO.write(img,"png",file);
	}
	
	/**
	 * Save J button.
	 *
	 * @param file the file
	 * @param pkList the pk list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveJButton(File file,List<JLabel> pkList) throws IOException
	{
		final JLabel jc = pkList.getFirst();
		
		final BufferedImage img = new BufferedImage(jc.getWidth(),
													jc.getHeight(),
													BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g = img.createGraphics();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,img.getWidth(),img.getHeight());
		
		for(Component c : jc.getComponents())
		{
			if(c instanceof JButton jl)
			{
				if(jl.getPressedIcon() != null && jl.isVisible())
				{
					g.drawImage(((ImageIcon)jl.getPressedIcon()).getImage(),
								jl.getX(), jl.getY(),jl.getWidth(),jl.getHeight(), null);
				}
			}
			else if(c instanceof JLabel jl)
			{
				if(jl.getText() != null)
				{
					g.setColor(Color.RED);
					g.drawRoundRect(jl.getX()+2, jl.getY()+2, jl.getWidth()+2, jl.getHeight()+2,6,6);
					g.setFont(UMLParserImage.FONT_ARIAL_14.deriveFont(16));
					g.drawString(jl.getText(),8,20);
				}
			}
		}
		
		g.dispose();
		
		ImageIO.write(img,"png",file);
	}
}