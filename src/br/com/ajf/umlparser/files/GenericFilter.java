package br.com.ajf.umlparser.files;

import javax.swing.filechooser.*;
import java.io.File;

/**
 * The Class GenericFilter.
 */
public final class GenericFilter extends FileFilter
{
    
    /** The description. */
    private final String description;
    
    /** The filters. */
    private final String[] filters;
    
    /**
     * Instantiates a new generic filter.
     *
     * @param description the description
     * @param filters the filters
     */
    public GenericFilter(String description, String[] filters)
    {
        this.description = description;
        this.filters = filters;
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
        if (f.isDirectory())
        {
            return true;
        }
        
        final String extension = getExtension(f);
        
        if(extension != null)
        {
            for (String filter : filters)
            {
                if(extension.equalsIgnoreCase(filter))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Gets the extension.
     *
     * @param f the f
     * @return the extension
     */
    private String getExtension(File f)
    {
        String extension = null;
        
        final String name = f.getName();
        final int i = name.lastIndexOf(".");
        
        if(i > 0 && i < name.length()-1)
        {
            extension = name.substring(i+1).toLowerCase();
        }
        
        return extension;
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    @Override
    public String getDescription()
    {
        return this.description;
    }
}
