package br.com.ajf.umlparser.files;

import javax.swing.filechooser.*;
import java.io.File;

/**
 * The Class DirectoryFilter.
 */
public final class DirectoryFilter extends FileFilter
{
    /**
     * Instantiates a new directory filter.
     */
    public DirectoryFilter()
    {
    	
    }
    /**
     * Accept.
     *
     * @param f the f
     * @return true, if successful
     */
    @Override
    public boolean accept(File f)
    {
        return f.isDirectory();
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription()
    {
        return "Select A Package";
    }
}
