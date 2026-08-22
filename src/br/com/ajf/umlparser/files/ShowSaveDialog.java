package br.com.ajf.umlparser.files;

import java.awt.Window;
import java.io.File;

import javax.swing.JFileChooser;

import br.com.ajf.umlparser.utils.UMLParserInfo;

/**
 * The Class ShowSaveDialog.
 *
 * @author Arlindo Jose Fossem . Date: Aug 21, 2026.
 * @version 1.0v.
 * @since 1.5v.
 */
public final class ShowSaveDialog
{
	
	/**
	 * Instantiates a new show save dialog.
	 */
	private ShowSaveDialog()
	{
		
	}
	
	/**
	 * Show save dialog.
	 *
	 * @param window the window
	 * @return the file
	 */
	public static File showSaveDialog(Window window)
	{
		final JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
		jfc.setFileFilter(new GenericFilter("Save as PNG", new String[] {"PNG","png"}));
		final int choice = jfc.showSaveDialog(window);
		
		if(choice == JFileChooser.APPROVE_OPTION)
		{
			return jfc.getSelectedFile();
		}
		else
		{
			UMLParserInfo.show("Info", "Couldn't load File.");
			return null;
		}
	}
}