package br.com.ajf.umlparser.files;

import javax.swing.*;
import java.io.File;

/**
 * The Class ShowOpenDialog.
 */
public class ShowOpenDialog
{
    /**
     * Instantiates a new show open dialog.
     */
    private ShowOpenDialog()
    {
    
    }
    
    /**
     * Select.
     *
     * @return the file
     */
    public static File select()
    {
        final JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setFileFilter(new DirectoryFilter());
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        final int selected = chooser.showOpenDialog(null);
        
        if(selected != JFileChooser.APPROVE_OPTION)
        {
            return null;
        }
        
        return chooser.getSelectedFile();
    }
}